package com.lzi.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import com.lzi.gestionabsence.DAO.ClasseDAO;
import com.lzi.gestionabsence.adapters.ClasseAdapter;
import com.lzi.gestionabsence.entities.Classe;
import java.util.ArrayList;
import java.util.List;

public class ClasseActivity extends AppCompatActivity {

    private List<Classe> classeList = new ArrayList<>();
    private ListView listViewClasse;
    private ClasseAdapter classeAdapter;

    private ClasseDAO classeDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe);

        listViewClasse = findViewById(R.id.lv_classe);

        classeDAO = new ClasseDAO(this);
        classeList = classeDAO.getAllClassesFromApi();

        classeAdapter = new ClasseAdapter(this,classeList);
        listViewClasse.setAdapter(classeAdapter);
    }



}
