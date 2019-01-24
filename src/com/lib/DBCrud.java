package lib;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Array;
import java.util.*;

public class DBCrud implements ICrud {
    private Map<String, List<String>> alpha = new HashMap<>();
    private List<String> ListFile = new ArrayList<>();
    private KeyEssence keyEssence;
    private List<String> valueStringList;

    public DBCrud (){}

    @Override
    public String read() {
        List<KeyEssence> essence = HibernateUtil.getSessionFactory().openSession().createQuery("from KeyEssence").list();
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

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(keyEssence);
            tx1.commit();
            session.close();
            alpha.put(key, new ArrayList<String>(Arrays.asList(value)));
            return "Успешно добавленно";
        }
        else
            return "Данный ключ уже существует";
    }

    public String update(String key, String value, int i){
        keyEssence = new KeyEssence();
        keyEssence  = searchKeyObj(key);
        System.out.println(keyEssence.toString());
        if (keyEssence !=null) {
            ValueEssence valueEssence = new ValueEssence(value);
            valueEssence.setKey(keyEssence);
            keyEssence.addValue(valueEssence);
            System.out.println( valueEssence.toString());


            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.update(keyEssence);
            tx1.commit();
            session.close();
            valueStringList = alpha.get(key);
            if (valueStringList == null) {
                valueStringList = new ArrayList<>();
                alpha.put(key, valueStringList);
            }

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
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.delete(keyEssence);

            tx1.commit();
            session.close();
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("from KeyEssence where key=:key");
        query.setParameter("key", key);
        keyEssence = new KeyEssence();
        keyEssence =(KeyEssence) query.uniqueResult();
        session.close();

        return keyEssence;
    }
}
