package com.example.admed.crudrealm.vo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class AlunoVO extends RealmObject {
    @PrimaryKey
    private long id;
    @Required
    private String nome;

    public AlunoVO() {
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
