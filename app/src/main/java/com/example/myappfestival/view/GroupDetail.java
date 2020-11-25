package com.example.myappfestival.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myappfestival.R;
import com.example.myappfestival.business.implementation.GroupImpl;
import com.example.myappfestival.business.interfaces.GroupInterface;
import com.example.myappfestival.model.Group;
import com.example.myappfestival.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class GroupDetail extends AppCompatActivity {

    TextInputEditText grpName;
    TextInputEditText description;
    TextInputEditText scene;
    TextInputEditText dateShow;
    Button btnLike;
    Button btnDislike;
    private Group group;
    private User user;
    private GroupInterface groupI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grouppage);
        init();
    }

    public void init(){
        groupI = new GroupImpl();
        btnLike = (Button)findViewById(R.id.like);
        btnDislike= (Button)findViewById(R.id.dislike);
        grpName = (TextInputEditText)findViewById(R.id.grpName);
        description = (TextInputEditText)findViewById(R.id.description);
        scene = (TextInputEditText)findViewById(R.id.scene);
        dateShow = (TextInputEditText)findViewById(R.id.dateShow);

        Bundle extras = getIntent().getExtras();
        group =(Group) extras.getSerializable("group");
        user =(User) extras.getSerializable("user");

        String desc = group.getDescription();
        String grp = group.getGroupeName();
        String sceneName = group.getTypeScene()+"";
        Date showDate= group.getDayShow();

        grpName.setText(grp.toString());
        description.setText(desc.toString());
        scene.setText(sceneName.toString());
        dateShow.setText(showDate.toString());

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        groupI.likeOrDislikeAGroup(user,group,true);
                    }
                }).start();
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        groupI.likeOrDislikeAGroup(user,group,false);
                    }
                }).start();
            }
        });
    }
}