package com.example.dynamicappexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CustomAdapter customAdapter;

    RecyclerView recyclerView;

    ArrayList<MovieModel> modelArrayList = new ArrayList<>();

    String url = "https://api.themoviedb.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        getJsonData();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView = findViewById(R.id.recycler_View);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                customAdapter = new CustomAdapter(MainActivity.this, modelArrayList);
                recyclerView.setAdapter(customAdapter);
            }
        }, 2000);


    }

    private void getJsonData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroService_Interface service_interface = retrofit.create(RetroService_Interface.class);

        Call<JsonModel> call = service_interface.getData();

        call.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
                JsonModel model = response.body();
                modelArrayList = model.getResults();
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}