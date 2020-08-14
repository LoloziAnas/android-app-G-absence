package com.lzi.gestionabsence.entities;

import java.util.Date;

public class Seance {
    private Long id;
    private Date date_Seance;
    private Classe classe;

    public Seance(Long id, Date date_Seance, Classe classe) {
        this.id = id;
        this.date_Seance = date_Seance;
        this.classe = classe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_Seance() {
        return date_Seance;
    }

    public void setDate_Seance(Date date_Seance) {
        this.date_Seance = date_Seance;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
