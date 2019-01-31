package com.entity;


import javax.persistence.*;


@Entity
@Table(name = "valuetable")
public class ValueEssence {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyid")
    private KeyEssence keyEssence;

    public ValueEssence(){

    }

    public ValueEssence(String value){
        this.value = value;
    }


    public Integer getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public KeyEssence getKey() {
        return this.keyEssence;
    }

    public void setKey(KeyEssence keyEssence) {
        this.keyEssence = keyEssence;
    }

    @Override
    public String toString() {
        return value + " " + id;
    }



}
