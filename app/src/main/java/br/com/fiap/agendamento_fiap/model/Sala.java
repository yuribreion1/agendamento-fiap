package br.com.fiap.agendamento_fiap.model;

import java.io.Serializable;

public class Sala implements Serializable{
    private int id;
    private String professor;
    private String sala;
    private String data;
    private String periodo;
    private String tipo;
    private String quantidade;

    public Sala() {
    }

    public Sala(int id, String professor, String sala, String data, String periodo, String tipo, String quantidade) {
        setId(id);
        setProfessor(professor);
        setSala(sala);
        setData(data);
        setPeriodo(periodo);
        setTipo(tipo);
        setQuantidade(quantidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
