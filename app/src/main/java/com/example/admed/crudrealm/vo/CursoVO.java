package com.example.admed.crudrealm.vo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
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
    private RealmList<AlunoVO> alunos;

    public CursoVO() {
    }

    public CursoVO(long id, ProfessorVO professorVO, String nome, RealmList<AlunoVO> alunos){
        this.id = id;
        this.professorVO = professorVO;
        this.nome = nome;
        this.alunos = alunos;
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

    public ProfessorVO getProfessorVO() {
        return professorVO;
    }

    public void setProfessorVO(ProfessorVO professorVO) {
        this.professorVO = professorVO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RealmList<AlunoVO> getAlunos() {
        return alunos;
    }

    public void setAlunos(RealmList<AlunoVO> alunos) {
        this.alunos = alunos;
    }
}
