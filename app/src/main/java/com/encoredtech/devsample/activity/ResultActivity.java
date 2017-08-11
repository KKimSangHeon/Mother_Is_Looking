package com.encoredtech.devsample.activity;

import android.media.Image;
import android.os.Bundle;

import com.encoredtech.devsample.R;

/**
 * Created by 상헌 on 2016-03-25.
 */
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.encoredtech.devsample.service.Define;
import com.encoredtech.devsample.service.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultActivity extends AppCompatActivity {


    int counter=0;
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    boolean checker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);







    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //  ListItemVo item = (ListItemVo) getIntent().getSerializableExtra("item");

        try {
            HttpRequest request = new HttpRequest();

            //     String requestUrl = Define.HOME_DOMAIN + item.getId().replace(":deviceId", Define.UUID);
            Log.i("URL", "들어옴");
            //         if(item.getQs() != null) {
            //            requestUrl += item.getQs();
            //       }
            // request.execute("GET", requestUrl, "Bearer", Define.ACCESS_TOKEN, null);
            //  request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/6F0344FD-FB4D-4119-AE9C-1E51C119BF99/forecastUsage", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
            // request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/6F0344FD-FB4D-4119-AE9C-1E51C119BF99/relay", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공

            //request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/list", "Bearer", Define.ACCESS_TOKEN, null);  //  GET /devices/list 성공


            //(확인)테스트오케 request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/relay", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
            //  request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/relay", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
            request.execute("GET", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/realtimeUsage", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
            //String result = request.get().toString();

            //    TextView urlText = (TextView) findViewById(R.id.url);
            //     urlText.setText(item.getId());

            //   TextView paramsText = (TextView) findViewById(R.id.param);
            //  paramsText.setText(item.getQs());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(request.get());


            final ImageView resultMoniter=(ImageView)findViewById(R.id.resultMoniter);
            final ImageView nowUse=(ImageView)findViewById(R.id.nowUse);
            final ImageView tb=(ImageView)findViewById(R.id.toggleButton);


            if(!json.contains("current\": 0"))
            {
                //끄고 켜기.. 사용중인지 알려주기

                resultMoniter.setImageResource(R.drawable.onmonitor);
                Log.i("URL", "컴퓨터 켜져있음");
                nowUse.setImageResource(R.drawable.nowuse);
                //test ok     request2.execute("PUT", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/relay?mode=OFF", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
                checker=true;
            }
            else
            {

                nowUse.setImageResource(R.drawable.nownotuse);
                resultMoniter.setImageResource(R.drawable.moniter);
                Log.i("URL", "컴퓨터 꺼져있음");
                checker=false;
            }

            tb.setOnClickListener(new View.OnClickListener() {



                public void onClick(View v) {
                    if(checker==false)//꺼진상태
                    {
                        HttpRequest request3 = new HttpRequest();
                        request3.execute("PUT", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/relay?mode=ON", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
                        //켬
                        checker=true;
                        resultMoniter.setImageResource(R.drawable.onmonitor);
                        Log.i("URL", "컴퓨터 켜져있음");
                        nowUse.setImageResource(R.drawable.nowuse);
                        request3=null;
                    }
                    else
                    {
                        HttpRequest request3 = new HttpRequest();
                        request3.execute("PUT", "https://api.encoredtech.com:8082/1.2/devices/D8BB356C-B51D-4B13-9CF9-FFE926C2E04A/relay?mode=OFF", "Bearer", Define.ACCESS_TOKEN, null);  //GET /devices/:deviceId/forecastUsage 성공
                        //끔
                        nowUse.setImageResource(R.drawable.nownotuse);
                        resultMoniter.setImageResource(R.drawable.moniter);
                        Log.i("URL", "컴퓨터 꺼져있음");
                        checker=false;
                        request3=null;
                    }

                }
            });

            // resultText.setText(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}