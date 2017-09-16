package com.example.admed.crudrealm.vo;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Luis Eduardo on 16/09/2017.
 */

public class MensagemVO {
    @PrimaryKey
    private long id;
    @Required
    private String texto;
    @Required
    private long idAluno;
    @Required
    private long idProfessor;

    public MensagemVO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(long idProfessor) {
        this.idProfessor = idProfessor;
    }
}
