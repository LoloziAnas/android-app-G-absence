package com.lzi.gestionabsence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzi.gestionabsence.DAO.ClasseDAO;
import com.lzi.gestionabsence.adapters.ClasseAdapter;
import com.lzi.gestionabsence.api.Constants;
import com.lzi.gestionabsence.api.MySingleton;
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
    private ProgressDialog progressDialog;
    private ClasseDAO classeDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe);

        listViewClasse = findViewById(R.id.lv_classe);
        progressDialog = new ProgressDialog(this);
        classeDAO = new ClasseDAO(this);
        //classeList = classeDAO.getAllClassesFromApi();

        getAllClasses();

        classeAdapter = new ClasseAdapter(this,classeList);
        listViewClasse.setAdapter(classeAdapter);
    }

    private void getAllClasses(){

        classeList.clear();
        classeAdapter.notifyDataSetChanged();

        showPDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.CLASSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideProgressDialog();

                Toast.makeText(getApplicationContext(),"Response"+response.toString(),Toast.LENGTH_LONG).show();

                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Classe classe = new Classe();

                        classe.setId(jsonObject.getLong("id"));
                        classe.setIntitule(jsonObject.getString("name"));

                        classeList.add(new Classe(jsonObject.getLong("id"),jsonObject.getString("name")));
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
