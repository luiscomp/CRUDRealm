package com.example.admed.crudrealm.vo;

import io.realm.RealmObject;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class ConversaVO extends RealmObject {
    private CursoVO curso;
    private AlunoVO aluno;

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
}
