package com.example.admed.crudrealm.vo;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class ConversaVO extends RealmObject {
    @PrimaryKey
    private CursoVO curso;
    @PrimaryKey
    private AlunoVO aluno;

    @LinkingObjects("conversas")
    private RealmResults<ProfessorVO> professor;

    public ConversaVO() {
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    public AlunoVO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoVO aluno) {
        this.aluno = aluno;
    }

    public RealmResults<ProfessorVO> getProfessor() {
        return professor;
    }

    public void setProfessor(RealmResults<ProfessorVO> professor) {
        this.professor = professor;
    }
}
