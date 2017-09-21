package com.example.admed.crudrealm.vo;

import io.realm.RealmObject;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class ProfessorVO extends RealmObject {
    private long id;
    private String nome;

    public ProfessorVO() {
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
