package com.test.boilerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private TextView sb_tx;
    private Button sb_btn1;
    private Button sb_btn2;
    private Button sb_btn3;

    static List<Boiler> data;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sb_tx=findViewById(R.id.sb_tx);
        sb_btn1=findViewById(R.id.sb_btn1);
        sb_btn2=findViewById(R.id.sb_btn2);
        sb_btn3=findViewById(R.id.sb_btn3);

        sb_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String page = "http://10.10.141.30:8080/boiler/on_off/ON";

                        try {
                            URL url = new URL(page);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            StringBuilder stringBuilder = new StringBuilder();

                            if(conn != null) {
                                conn.setConnectTimeout(10000);
                                conn.setRequestMethod("GET");
                                conn.setUseCaches(false);

                                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                                    while(true){
                                        String line = bufferedReader.readLine();
                                        if(line == null) break;
                                        stringBuilder.append(line + "\n");
                                    }
                                    bufferedReader.close();
                                }
                                conn.disconnect();
                            }
                            Gson gson = new Gson();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(),"보일러가 켜졌습니다.",Toast.LENGTH_LONG).show();
                thread.start();
            }
        });

        sb_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String page = "http://10.10.141.30:8080/boiler/on_off/OFF";

                        try {
                            URL url = new URL(page);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            StringBuilder stringBuilder = new StringBuilder();

                            if(conn != null) {
                                conn.setConnectTimeout(10000);
                                conn.setRequestMethod("GET");
                                conn.setUseCaches(false);

                                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                                    while(true){
                                        String line = bufferedReader.readLine();
                                        if(line == null) break;
                                        stringBuilder.append(line + "\n");
                                    }
                                    bufferedReader.close();
                                }
                                conn.disconnect();
                            }
                            Gson gson = new Gson();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Toast.makeText(getApplicationContext(),"보일러가 꺼졌습니다.",Toast.LENGTH_LONG).show();
                thread.start();
            }
        });

        sb_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}