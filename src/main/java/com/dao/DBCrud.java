package com.dao;

import com.entity.KeyEssence;
import com.entity.ValueEssence;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Transactional
public class DBCrud implements ICrud {
    private Map<String, List<String>> alpha = new HashMap<>();
    private List<String> ListFile = new ArrayList<>();
    private KeyEssence keyEssence;
    @Autowired
    public SessionFactory sessionFactory;
   // public HibernateTemplate hibernateTemplate;
    public Session session;
    public List<KeyEssence> essence;
    public DBCrud (){}

    @Override
    public String read() {
        session =sessionFactory.getCurrentSession();
        essence = (List<KeyEssence>) session.createQuery("from KeyEssence").list();
        String key = null;
        List<String> valueStringList;
        for (KeyEssence keyobj : essence) {
            key = keyobj.getKey();
            List<ValueEssence> valueEssences = keyobj.getValueEssenceList();
            valueStringList = new ArrayList<>();

            if (!ListFile.contains(keyobj.getType())) {
                ListFile.add(keyobj.getType());
            }
            for (ValueEssence list : valueEssences) {
                valueStringList.add(list.getValue());
            }
            if (key != null)
                alpha.put(key, valueStringList);

        }
        return "Data successfully loaded";
    }

    @Override
    public String create(String key, String value, String idGroup) {

        try {
            if (!alpha.containsKey(key)) {
                keyEssence = searchKeyObj(key);
                if (keyEssence == null) {
                    keyEssence = new KeyEssence(key, idGroup);

                    ValueEssence valueEssence = new ValueEssence(value);
                    valueEssence.setKey(keyEssence);
                    keyEssence.addValue(valueEssence);
                    session = sessionFactory.getCurrentSession();
                    session.save(keyEssence);
                    alpha.put(key, new ArrayList<String>(Arrays.asList(value)));
                    return ("Data successfully added");
                }
            }
            else return ("This key already exists");
        } catch (Exception e) {
            return ("Error adding key");
        }

        return "no data added, please try again later";
    }



    public String update(String key, String value,String idGroup){
        keyEssence = new KeyEssence();
        keyEssence  = searchKeyObj(key);
        if (keyEssence !=null) {
            for (ValueEssence list:keyEssence.getValueEssenceList()) {
                if (list.getValue().equals(value)) {
                    return "This value already exists.";
                }
            }
            ValueEssence valueEssence = new ValueEssence(value);
            valueEssence.setKey(keyEssence);
            keyEssence.addValue(valueEssence);
            session =sessionFactory.getCurrentSession();
            session.update(keyEssence);
            List<String> valueStringList = alpha.get(key);
            valueStringList.add(value);
            alpha.put(key, valueStringList);


            return "Value added successfully";
        }
        else{
            return  "This key does not exist.";
        }
    }

    @Override
    public String delete(String key,String idGroup) {

        try {
            keyEssence = searchKeyObj(key);
            if(keyEssence !=null) {
                session =sessionFactory.getCurrentSession();
                session.delete(keyEssence);
                alpha.remove(key);
                return "successfully deleted";
            }else {
                return "Key not detected";
            }
        } catch (Exception e) {
            return ("error deleting key");
        }
    }

    @Override
    public Map<String, List<String>> getAlphabet() {
        return new HashMap<>(alpha);
    }

    @Override
    public List<String> getValue(String key) {
        if (alpha.get(key) != null) {
            return alpha.get(key);
        }
        else
            return null;
    }

    @Override
    public List<String> getAlphabetNames() {
        return ListFile;
    }


    public KeyEssence searchKeyObj(String key){
        session =sessionFactory.getCurrentSession();
        Query query= session.createQuery("from  KeyEssence where key=:key");
        query.setParameter("key", key);
        keyEssence = new KeyEssence();
        keyEssence =(KeyEssence) query.uniqueResult();
        return keyEssence;
    }
    public List<KeyEssence> getEssence(){
        return essence;
    }
}