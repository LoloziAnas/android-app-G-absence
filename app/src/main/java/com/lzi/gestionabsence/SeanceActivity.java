package com.lzi.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzi.gestionabsence.adapters.SeanceAdapter;
import com.lzi.gestionabsence.api.Constants;
import com.lzi.gestionabsence.api.MySingleton;
import com.lzi.gestionabsence.entities.Classe;
import com.lzi.gestionabsence.entities.Seance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

public class SeanceActivity extends AppCompatActivity {

    private ListView listViewSeances;
    private ArrayList<Seance> seanceList = new ArrayList<>();
    private static Classe classe = new Classe();
    private SeanceAdapter seanceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);

        listViewSeances = findViewById(R.id.lv_seances);

        Intent intent = getIntent();
        String sClasse = intent.getStringExtra("classe");
        try {
            JSONObject jsonObject = new JSONObject(sClasse);
            classe.setId(jsonObject.getLong("id"));
            classe.setIntitule(jsonObject.getString("intitule"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getSeancesByClasse(classe.getId());
        seanceAdapter = new SeanceAdapter(this,seanceList);


    }

    private void getSeancesByClasse(Long id) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.SEANCE_URL + "classe=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Seance seance = new Seance();
                        String date = jsonObject.getString("date");
                        seance.setDate_Seance(Date.valueOf(date));
                        seanceList.add(seance);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}