package lib;


import javax.persistence.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keytable")
public class KeyEssence {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "key")
    private String key;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "keyEssence", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ValueEssence> valueEssenceList;

    public KeyEssence(){

    }

    public KeyEssence(String key,String type){
        this.key = key;
        this.type = type;
        valueEssenceList = new ArrayList<>();
    }

    public void addValue(ValueEssence value){
        value.setKey(this);
        valueEssenceList.add(value);
    }

    public void removeValue(ValueEssence value){
        valueEssenceList.remove(value);
    }

    public List<ValueEssence> getValueEssenceList() {
        return valueEssenceList;
    }

    public void setValueEssenceList(List<ValueEssence> valueEssenceList) {
        this.valueEssenceList = valueEssenceList;
    }


    public Integer getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "models.KeyEssence{" +
                "id=" + id +
                ", name='" + key + '\'' +
                ", age=" + type +
                '}';
    }

}
