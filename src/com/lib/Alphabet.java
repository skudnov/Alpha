package lib;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table (name = "keytable")
public class Alphabet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "key")
    private String key;

    @Column(name = "key")
    private String type;



    public Alphabet() {
    }


    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "models.User{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", type=" + type +
                '}';
    }
}
