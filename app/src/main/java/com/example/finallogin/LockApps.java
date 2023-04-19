package com.example.finallogin;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LockApps extends AppCompatActivity {
    RecyclerView recyclerView;
    List<AppModel> appModelList=new ArrayList<>();
    AppAdapter adapter;
    ProgressDialog progressDialog;
    Context con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_apps);
        con=this;
        recyclerView=findViewById(+R.id.applocklist);
        adapter=new AppAdapter(appModelList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //it will display the data from adapter

        progressDialog=new ProgressDialog(this);
        progressDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                getLockedApp();
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
    public void getLockedApp(){

        //use lock app list
        List<String> list=SharedPrefUtil.getInstance(con).getListString();
        List<PackageInfo> packageInfos=getPackageManager().getInstalledPackages(0);

        //Add to list of dataset
        for (int i = 0; i < packageInfos.size(); i++) {
            String name=packageInfos.get(i).applicationInfo.loadLabel(getPackageManager()).toString();//get application name
            Drawable icon=packageInfos.get(i).applicationInfo.loadIcon(getPackageManager());//get application icon
            //unlock so status 0
            String packname=packageInfos.get(i).packageName;

            if(list.contains(packname)){
                appModelList.add(new AppModel(name,icon,1,packname));
            }
            else {
                continue;
            }
        }
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();

    }
}