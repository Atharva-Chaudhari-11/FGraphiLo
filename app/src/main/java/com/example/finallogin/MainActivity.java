package com.example.finallogin;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btn,btn4,btn2,lockbtn,pass,ip,btn6;
    static final String KEY="pass" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        pass =SharedPrefUtil.getInstance(this).getString(KEY);//this line of code retrieve pass from shared preference otherwise return empty string here;
        final Context con=this;
        btn= findViewById(+R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       AlertDialog.Builder builder = new AlertDialog.Builder(con);
                                       builder.setTitle("Alert You can't Proceed !!");
                                       builder.setMessage("Have You Accept All Permissions? If Not Then Access First! ");
                                       builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               Intent intent = new Intent(MainActivity.this, ShowAllInstallApps.class);
                                               startActivity(intent);
                                           }
                                       });
                                       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                               Toast.makeText(getApplicationContext(), "select Image again", Toast.LENGTH_SHORT).show();
                                               startActivity(intent);
                                           }
                                       });
                                       builder.show();
                                   }
                               });

                btn4 = findViewById(+R.id.btn4);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                        startActivity(intent);
                    }
                });

                lockbtn = findViewById(+R.id.lockabtn);
                lockbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, LockApps.class));
                    }
                });


                btn2 = findViewById(+R.id.pass);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                });

                ip = findViewById(R.id.check);
                ip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, Passwordth.class));
                    }
                });
                btn6=findViewById(R.id.button6);
                btn6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                });

    }

            //dailog box
            private void setPassword(final Context con) {


            }


            private void UpdatePassword(final Context con) {

            }


            private boolean isAccessGranted() {
                try {
                    PackageManager packageManager = getPackageManager();
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
                    AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
                    int mode = 0;
                    if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                        mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                                applicationInfo.uid, applicationInfo.packageName);
                    }
                    return (mode == AppOpsManager.MODE_ALLOWED);

                } catch (PackageManager.NameNotFoundException e) {
                    return false;
                }
            }



}