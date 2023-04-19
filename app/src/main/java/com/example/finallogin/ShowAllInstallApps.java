package com.example.finallogin;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class ShowAllInstallApps extends AppCompatActivity {
    RecyclerView recyclerView;
    //we have to set adapter for recyclerview
    List<AppModel>appModelList=new ArrayList<>();
    //i have many application so we have to create list
    AppAdapter adapter;
    //add adapter to recyclerview
    ProgressDialog progressDialog;
    Context con;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_install_apps);
        con=this;
        recyclerView=findViewById(+R.id.recyclerview);
        adapter=new AppAdapter(appModelList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        btn=findViewById(+R.id.g);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowAllInstallApps.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //it will display the data from adapter

        progressDialog=new ProgressDialog(this);

        progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                getInstalledApp();
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        progressDialog.setMessage("Fetching Apps");

        progressDialog.setMessage("Laoding");
        progressDialog.show();
    }

    @Override
    protected void onStop() {

        super.onStop();
        progressDialog.setOnShowListener(null);
    }

    //this method help us to connect all application from android device and add to list and recycler view automatically show that data
    public void getInstalledApp(){

        //use lock app list
        List<String> list=SharedPrefUtil.getInstance(con).getListString();
        List<PackageInfo> packageInfos=getPackageManager().getInstalledPackages(0);
        //collect all info of all applications
        //add to list of dataset-
        for (int i = 0; i < packageInfos.size(); i++) {
            String name=packageInfos.get(i).applicationInfo.loadLabel(getPackageManager()).toString();//get application name
            Drawable icon=packageInfos.get(i).applicationInfo.loadIcon(getPackageManager());//get application icon
            //unlock so status 0
            String packname=packageInfos.get(i).packageName;

            // read real time from locked app list
            if(!list.isEmpty()){

                if(list.contains(packname)){
                    appModelList.add(new AppModel(name,icon,1,packname));
                }else {
                    appModelList.add(new AppModel(name,icon,0,packname));
                }

            }else {
                appModelList.add(new AppModel(name,icon,0,packname));

            }

        }
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();

    }
}