package com.lzi.gestionabsence.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.lzi.gestionabsence.AbsenceActivity;
import com.lzi.gestionabsence.R;
import com.lzi.gestionabsence.SeanceActivity;
import com.lzi.gestionabsence.entities.Classe;
import com.lzi.gestionabsence.entities.Seance;

import java.util.ArrayList;
import java.util.List;

public class SeanceAdapter extends BaseAdapter  {

    private Activity activity;
    private ArrayList<Seance> seanceList;
    private LayoutInflater layoutInflater;

    public SeanceAdapter(Activity activity, ArrayList<Seance> seanceList) {
        this.activity = activity;
        this.seanceList = seanceList;
    }

    @Override
    public int getCount() {
        return seanceList.size();
    }

    @Override
    public Seance getItem(int position) {
        return seanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater == null)
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.activity_seance_items,null);

        TextView tv_dateSeance = convertView.findViewById(R.id.tv_seance_date);


        final Seance seance = seanceList.get(position);

        tv_dateSeance.setText(seance.getDate_Seance().toString());

        tv_dateSeance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,AbsenceActivity.class );
                intent.putExtra("id",seance.getId());
                intent.putExtra("date",seance.getDate_Seance());
                activity.startActivity(intent);
                activity.finish();
            }
        });

        return convertView;
    }
}
