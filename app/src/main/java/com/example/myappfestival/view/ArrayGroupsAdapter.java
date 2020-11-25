package com.example.myappfestival.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myappfestival.R;
import com.example.myappfestival.model.Group;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArrayGroupsAdapter extends ArrayAdapter<Group> {
    List<Group> groups;
    public ArrayGroupsAdapter(@NonNull Context context, @NonNull List<Group> objects) {
        super(context, R.layout.eventpage, objects);
        this.groups=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Group user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listgroupitem, parent, false);
        }
        // Lookup view for data population
        TextView namGroup = (TextView) convertView.findViewById(R.id.label);
        // Populate the data into the template view using the data object
        namGroup.setText(user.getGroupeName());
        // Return the completed view to render on screen
        return convertView;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listgroupitem, null);

            holder.label = (TextView)convertView.findViewById(R.id.grpName);
            //sauvgarde la ref du holder en memoire pour la réutilisation par la suite
            convertView.setTag(holder);

        } else {
            //réutilisation du holder déja existant
            holder = (ViewHolder) convertView.getTag();
        }

        //stocker les données dans une vue via settag
        holder.label.setText(groups.get(position).getGroupeName());
        //holder.img.setImageResource(R.drawable.ensias);
        return convertView;
    }

    static class ViewHolder
    {
        public TextView label ;
    }*/
}
