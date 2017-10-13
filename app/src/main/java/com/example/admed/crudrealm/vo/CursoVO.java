package com.example.admed.crudrealm.vo;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class CursoVO extends RealmObject {
    @PrimaryKey
    private long id;
    private ProfessorVO professorVO;
    private String nome;

    public CursoVO() {
    }

    public CursoVO(long id, ProfessorVO professorVO, String nome){
        this.id = id;
        this.professorVO = professorVO;
        this.nome = nome;
    }

    public static Long autoIncrementId(){
        Long key = 1L;
        Realm realm = Realm.getDefaultInstance();
        try {
            key = realm.where(CursoVO.class).max("id").longValue() + 1;
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

    public long getId_professor() {
        return professorVO.getId();
    }

    public void setId_professor(long id_professor) {
        professorVO.setId(id_professor);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
