package com.example.finallogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Browser extends AppCompatActivity {
    private EditText editText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bro);
        Button btn=findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Browser.this,MainActivity3.class));
                Toast.makeText(getApplicationContext(),"Thanks For using Our Web browser",Toast.LENGTH_SHORT).show();
            }
        });
        webView = findViewById(R.id.webView);
        editText = findViewById(R.id.editText);
        webView.setWebViewClient(new WebViewClient());
    }

    public void buttonGO_Browse(View view){
        String stringURL = editText.getText().toString();
        stringURL = "https://www."+stringURL;

        webView.loadUrl(stringURL);
    }
}