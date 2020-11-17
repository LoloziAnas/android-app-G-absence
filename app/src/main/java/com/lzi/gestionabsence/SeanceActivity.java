package com.lzi.gestionabsence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

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
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);

        progressDialog = new ProgressDialog(this);
        listViewSeances = findViewById(R.id.lv_seances);

        Intent intent = getIntent();

        String sClasse = intent.getStringExtra("classe");
        try {
            JSONObject jsonObject = new JSONObject(sClasse);
            Long idClasse = jsonObject.getLong("id");
            Log.i(SeanceActivity.class.getName(),"idClass"+ idClasse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        seanceList.add(new Seance(1L, new Date(12122000), new Classe(1L,"SIM")));
        getSeancesByClasse(classe.getId());
        seanceAdapter = new SeanceAdapter(this,seanceList);

        listViewSeances.setAdapter(seanceAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_seance,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_ajouter:
                Toast.makeText(this,"Ajouter une Seance",Toast.LENGTH_LONG).show();
                return true;

            case R.id.item_rechercher:
                Toast.makeText(this,"Rechercher sur une Seance",Toast.LENGTH_LONG).show();
                return true;
        }

        return  false;
    }

    private void getSeancesByClasse(Long id) {

        seanceList.clear();
        seanceAdapter.notifyDataSetChanged();

        showPDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.SEANCE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    hideProgressDialog();
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
    private void showPDialog(){
        progressDialog.setMessage("Chargement ...");
        progressDialog.show();
    }
    private void hideProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null; 
        }
    }
}