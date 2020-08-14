package com.lzi.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzi.gestionabsence.DAO.ClasseDAO;
import com.lzi.gestionabsence.adapters.ClasseAdapter;
import com.lzi.gestionabsence.api.Constants;
import com.lzi.gestionabsence.entities.Classe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
