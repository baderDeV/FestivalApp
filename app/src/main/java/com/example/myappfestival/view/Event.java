package com.example.myappfestival.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.myappfestival.R;
import com.example.myappfestival.business.implementation.GroupImpl;
import com.example.myappfestival.business.interfaces.GroupInterface;
import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.TypeScene;
import com.example.myappfestival.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Event extends AppCompatActivity {
    Button btnGo;
    Button btnRefresh;
    Spinner spinnerTypeList;
    Spinner spinnerDay;
    Spinner spinnerScene;
    ListView listGroup;
    ArrayGroupsAdapter adapter;
    List<Group> groups = new ArrayList<>();
    User user;
    GroupInterface groupI;
    Logger log = Logger.getLogger("LogEvent");
    List<Group> rs =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventpage);
        init();
    }
    public void init(){
        btnGo = (Button)findViewById(R.id.GO);
        btnRefresh = (Button)findViewById(R.id.Refresh);
        spinnerTypeList = (Spinner)findViewById(R.id.optionsEvent);
        spinnerDay = (Spinner)findViewById(R.id.filterDay);
        spinnerScene = (Spinner)findViewById(R.id.filterScene);
        listGroup = (ListView)findViewById(R.id.listGroup);
        groupI = new GroupImpl();

        final List<String> typeListGroup = new ArrayList<String>();
        typeListGroup.add("NONE");
        typeListGroup.add("All_Groups");
        typeListGroup.add("Favorite_Group");

        final List<String> listDay = new ArrayList<String>();
        listDay.add("NONE");
        listDay.add("vendredi");
        listDay.add("samedi");

        final List<String> listScene = new ArrayList<String>();
        listScene.add("NONE");
        listScene.add("scene_acoustique");
        listScene.add("scene_amplifie");

        ArrayAdapter<String> adpListGroup = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, typeListGroup);
        spinnerTypeList.setAdapter(adpListGroup);

        ArrayAdapter<String> adpListDay = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listDay);
        spinnerDay.setAdapter(adpListDay);

        ArrayAdapter<String> adpListScene = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listScene);
        spinnerScene.setAdapter(adpListScene);

        adapter = new ArrayGroupsAdapter(this,groups);
        listGroup.setAdapter(adapter);

        /*for(int i = 0; i<20 ; i++)
            groups.add(new Group(1L,"A"+i,"sd",new Date(), TypeScene.scene_acoustique));
        */
        /*Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(new Runnable() {
            public void run()
            {
                adapter.clear();
                adapter.addAll(rs);
                adapter.notifyDataSetChanged();
            }
        });*/
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        log.info("refresh : "+rs.toString());
                        adapter.clear();
                        groups.clear();
                        groups.addAll(rs);
                        //adapter.addAll(groups);
                    }
                });
            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String typelistValue = spinnerTypeList.getSelectedItem().toString();
                        String daySelected = spinnerDay.getSelectedItem().toString();
                        String sceneSelected = spinnerScene.getSelectedItem().toString();
                        TypeScene ts;
                        if(sceneSelected.equals("scene_acoustique")){
                            ts = TypeScene.scene_acoustique;
                        } else if(sceneSelected.equals("scene_amplifie")){
                            ts = TypeScene.scene_amplifie;
                        } else{
                            ts = null;
                        }
                        rs = new ArrayList<>();
                        rs.addAll(groupI.getGroupesBySettingCriteria(typelistValue, daySelected, ts, user));
                        log.info("rs = "+ rs.toString());
                        //groups.addAll(rs);
                    }
                }).start();
            }
        });
        this.listGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                log.info("Position : "+position);
                Group item = (Group)
                        listGroup.getAdapter().getItem(position);
                log.info("g== "+item.toString());
                Intent intent=new Intent(Event.this,GroupDetail.class);
                intent.putExtra("group",item);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });
        user = getUser();
    }

    private User getUser(){
        Bundle extras = getIntent().getExtras();
        User user = (User)extras.getSerializable("user");

        return user;
    }
}