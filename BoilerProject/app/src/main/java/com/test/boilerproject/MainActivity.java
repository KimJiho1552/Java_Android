package com.test.boilerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_json;
    private TextView tv_json2;
    private Button p_btn;
    private RecyclerView recyclerView;

    static List<Boiler> data;

    Handler handler = new Handler();
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_json = findViewById(R.id.tv_json);
        tv_json2 = findViewById(R.id.tv_json2);
        p_btn = findViewById(R.id.p_btn);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String page = "http://10.10.141.30:8080/boiler/boilerList";


                try {
                    URL url = new URL(page);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    StringBuilder stringBuilder = new StringBuilder();

                    if(conn != null){

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

                    Type type = new TypeToken<List<Boiler>>() {}.getType();
                    data = gson.fromJson(String.valueOf(stringBuilder),type);

                    state = data.get(0).getState();
                    int tem = data.get(0).getTem();

                    tv_println(state);
                    tv_println2(tem);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } //End of run()
        }); //End of Thread
        thread.start();

        // 보일러 상태창 열기
        p_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    public void tv_println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(state.equals("ON")){
                    tv_json.setTextColor(Color.RED);
                }else {
                    tv_json.setTextColor(Color.BLACK);
                }
               tv_json.append(data + "");
            }
        });
    }

    public void tv_println2(final int data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_json2.append(data + "ºc");
            }
        });
    }
}