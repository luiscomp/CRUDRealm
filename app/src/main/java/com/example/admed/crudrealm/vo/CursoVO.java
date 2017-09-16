package com.example.admed.crudrealm.vo;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class CursoVO extends RealmObject {
    @PrimaryKey
    private long id;
    @Required
    private String nome;
    @LinkingObjects("cursos")
    private RealmResults<ProfessorVO> professor;

    public CursoVO() {
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

    public RealmResults<ProfessorVO> getProfessor() {
        return professor;
    }
}
