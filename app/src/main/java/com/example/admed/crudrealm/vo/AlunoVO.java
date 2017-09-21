package com.example.admed.crudrealm.vo;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class AlunoVO extends RealmObject {
    @PrimaryKey
    private long id;
    private String nome;

    public AlunoVO() {
    }

    public static Long autoIncrementId(){
        Long key = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            key = realm.where(AlunoVO.class).max("id").longValue() + 1;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
