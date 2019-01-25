package lib;


import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Transactional
public class DBCrud implements ICrud {
    private Map<String, List<String>> alpha = new HashMap<>();
    private List<String> ListFile = new ArrayList<>();
    private KeyEssence keyEssence;
    private List<String> valueStringList;
    @Autowired
    HibernateTemplate hibernateTemplate;

    public DBCrud (){}

    @Override
    public String read() {
        List<KeyEssence> essence = (List<KeyEssence>) hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from KeyEssence").list();
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
        return "Данные успешно загруженны";
    }

    @Override
    public String create(String key, String value, int i) {

        keyEssence = searchKeyObj(key);

        if (keyEssence ==null) {
            keyEssence = new KeyEssence(key, ListFile.get(i));

            ValueEssence valueEssence = new ValueEssence(value);
            valueEssence.setKey(keyEssence);
            keyEssence.addValue(valueEssence);
            hibernateTemplate.save(keyEssence);
            alpha.put(key, new ArrayList<String>(Arrays.asList(value)));
            return "Успешно добавленно";
        }
        else
            return "Данный ключ уже существует";
    }

    public String update(String key, String value, int i){
        keyEssence = new KeyEssence();
        keyEssence  = searchKeyObj(key);
        if (keyEssence !=null) {
            for (ValueEssence list:keyEssence.getValueEssenceList()) {
                if (list.getValue()==value) {
                    return "Такое значение уже существует";
                }
            }
            ValueEssence valueEssence = new ValueEssence(value);
            valueEssence.setKey(keyEssence);
            keyEssence.addValue(valueEssence);


            hibernateTemplate.update(keyEssence);
            valueStringList = alpha.get(key);
            valueStringList.add(value);
            alpha.put(key, valueStringList);


            return "Значение успешно добавлено";
        }
        else{
            return  "Данного ключа не существует";
        }
    }

    @Override
    public String delete(String key, int i) {
        keyEssence = searchKeyObj(key);
        if(keyEssence !=null) {
            hibernateTemplate.delete(keyEssence);
            alpha.remove(key);
            return "Успешно удаленно";
        }
        else
            return "Ключ не найден";
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


    private KeyEssence searchKeyObj(String key){

        Query query= hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("select from  KeyEssence where key=:key");
        query.setParameter("key", key);
        keyEssence = new KeyEssence();
        keyEssence =(KeyEssence) query.uniqueResult();
        return keyEssence;
    }
}
