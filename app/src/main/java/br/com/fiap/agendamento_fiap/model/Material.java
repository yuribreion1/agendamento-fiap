package br.com.fiap.agendamento_fiap.model;

import java.io.Serializable;

public class Material implements Serializable {
    private int id;
    private String professor;
    private String material;
    private String sala;
    private String data;
    private String periodo;

    public Material() {
    }

    public Material(int id, String professor, String material, String sala, String data, String periodo) {
        setId(id);
        setProfessor(professor);
        setMaterial(material);
        setSala(sala);
        setData(data);
        setPeriodo(periodo);
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
}
