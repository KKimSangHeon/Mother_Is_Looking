package com.encoredtech.devsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.encoredtech.devsample.R;
import com.encoredtech.devsample.adapter.ViewPagerAdapter;
import com.encoredtech.devsample.service.Define;
import com.encoredtech.devsample.service.HttpRequest;

import org.json.JSONObject;

/**
 * Created by koansang on 2016. 2. 16..
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton use=(ImageButton) findViewById(R.id.usageCheck);
        use.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });
        ImageButton real=(ImageButton) findViewById(R.id.realTimeUseCheck);
        real.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UseActivity.class);
                startActivity(intent);
            }
        });
        }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String code = getIntent().getExtras().getString("code");

        try {
            JSONObject json = new JSONObject();
            json.put("client_id", Define.CLIENT_ID);
            json.put("client_secret", Define.CLIENT_SECRET);
            json.put("grant_type", "authorization_code");
            json.put("code", code);

            HttpRequest request = new HttpRequest();
            request.execute("POST", Define.AUTH_DOMAIN + "/token", "", "", json);

            JSONObject result = (JSONObject) request.get();

            Define.ACCESS_TOKEN = result.getString("access_token");

            request = new HttpRequest();
            request.execute("GET", Define.AUTH_DOMAIN + "/uuid", "Bearer", Define.ACCESS_TOKEN, null);
            result = (JSONObject) request.get();

            Define.UUID = result.getString("uuid");
            Log.i("Device UUId", Define.UUID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public void onButtonClicked(View v) {
        Button btn = (Button) v;
        if (btn.getId() == R.id.usageCheck) {
            final Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
       else if(btn.getId()==R.id.realTimeUseCheck)
        {
            final Intent intent = new Intent(this, UseActivity.class);
            startActivity(intent);
        }
       else
        {



        }
    }
}
