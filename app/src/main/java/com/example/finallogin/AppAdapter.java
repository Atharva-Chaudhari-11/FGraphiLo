package com.example.finallogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.adapter_design_backend> {

    List<AppModel> appModels=new ArrayList<>();
    Context con;
    List<String> lockedapps=new ArrayList<>();

    public AppAdapter(List<AppModel> appModels, Context con) {
        this.appModels = appModels;
        this.con = con;

    }

    @NonNull
    @Override
    //tell which design i should render on screen
    public adapter_design_backend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(con).inflate(R.layout.adapterdesign,parent,false);
        //frontend to backend import
        //display
        adapter_design_backend design=new adapter_design_backend( view);//convert->simple view to this desgin adapter one
        return design;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_design_backend holder, int position) {
        //last thing design required data like app name,icon status onbind use;
        AppModel app=appModels.get(position);


        holder.appname.setText(app.getAppname());
        holder.appicon.setImageDrawable(app.getAppicon());

        if (app.getStatus()==0){
            holder.appstatus.setImageResource(R.drawable.ic_baseline_lock_open_24);
        }else {
            holder.appstatus.setImageResource(R.drawable.ic_baseline_lock_24);
            lockedapps.add(app.getPackagename());
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(app.getStatus()==0){
                    app.setStatus(1);
                    holder.appstatus.setImageResource(R.drawable.ic_baseline_lock_24);
                    Toast.makeText(con,app.getAppname()+ " is Locked",Toast.LENGTH_LONG).show();
                    lockedapps.add(app.getPackagename());
                    //this line of code updates data
                    SharedPrefUtil.getInstance(con).putListString(lockedapps);
                }else {
                    app.setStatus(0);
                    holder.appstatus.setImageResource(R.drawable.ic_baseline_lock_open_24);
                    Toast.makeText(con,app.getAppname()+ " is UnLocked",Toast.LENGTH_LONG).show();
                    lockedapps.remove(app.getPackagename());
                    SharedPrefUtil.getInstance(con).putListString(lockedapps);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return appModels.size();//if 4 then show first 4 items
    }

    public class adapter_design_backend extends RecyclerView.ViewHolder{

        TextView appname;
        ImageView appicon,appstatus;
        public adapter_design_backend(@NonNull View itemView){
            super(itemView);
            appname=itemView.findViewById(R.id.appname);//frontend is connected to backend
            appicon=itemView.findViewById(R.id.appicon);
            appstatus=itemView.findViewById((R.id.appstatus));

        }

    }
}

