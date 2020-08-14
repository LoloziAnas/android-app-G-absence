package com.lzi.gestionabsence.DAO;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lzi.gestionabsence.ClasseActivity;
import com.lzi.gestionabsence.api.Constants;
import com.lzi.gestionabsence.api.MySingleton;
import com.lzi.gestionabsence.entities.Classe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClasseDAO {

    private Activity activity;

    public ClasseDAO(Activity activity) {
        this.activity = activity;
    }

    public List<Classe> getAllClassesFromApi(){
        final List<Classe> classeList = new ArrayList<>();
        final Classe classe = new Classe();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.CLASSE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        classe.setId(jsonObject.getLong("id"));
                        classe.setIntitule(jsonObject.getString("name"));

                        classeList.add(classe);
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
        MySingleton.getInstance(activity).addToRequestQueue(stringRequest);
        return classeList;
    }
}
