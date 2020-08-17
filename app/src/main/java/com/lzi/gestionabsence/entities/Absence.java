package com.lzi.gestionabsence.entities;

import java.util.ArrayList;

public class Absence {
    private Long id;
    private Seance seance;
    private ArrayList<Etudiant> absences;

    public Absence(Long id, Seance seance, ArrayList<Etudiant> absences) {
        this.id = id;
        this.seance = seance;
        this.absences = absences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public ArrayList<Etudiant> getAbsences() {
        return absences;
    }

    public void setAbsences(ArrayList<Etudiant> absences) {
        this.absences = absences;
    }
}
