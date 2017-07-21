package com.example.john.volleysimple;

import android.graphics.Bitmap;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    ImageView img;
    Button btn;
    Button btn_img;
    String url;
    String url_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.reqText);
        img = (ImageView)findViewById(R.id.img);
        btn = (Button)findViewById(R.id.btn_get_text);
        btn_img = (Button) findViewById(R.id.btn_get_img);
        url = "http://www.thecrazyprogrammer.com/wp-content/uploads/demo.txt";
        url_img = "http://autonom.iiar.pwr.wroc.pl/mobile/Android.png";
        final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s){
                        txt.setText(s);
                        txt.setGravity(1);
                    }}, new Response.ErrorListener() {
                        @Override
                                public void onErrorResponse(VolleyError volleyError){
                                String error = "Error: "+ volleyError;
                                txt.setText(error);
                        }
                    });

                requestQueue.add(stringRequest);
                }
            });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequest imageRequest = new ImageRequest(url_img, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap b){
                        img.setImageBitmap(b);
                    }},0,0, ImageView.ScaleType.CENTER,Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError){
                        String error = "Error: "+ volleyError;
                        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
                        Log.d("com", error);
                    }
                });

                requestQueue.add(imageRequest);
            }

        });
        }
    }
