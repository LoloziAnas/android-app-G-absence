package com.lzi.gestionabsence.entities;

import androidx.annotation.NonNull;

public class Classe {
    private Long id;
    private String intitule;

    public Classe() {
        this.id = null;
        this.intitule = "";
    }

    public Classe(Long id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String toJson(){
        return "{ 'id': '"+id+"', 'intitule': '"+intitule+"'}";
    }


}
