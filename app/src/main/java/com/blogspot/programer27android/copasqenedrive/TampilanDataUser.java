package com.blogspot.programer27android.copasqenedrive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.blogspot.programer27android.copasqenedrive.api.RestApi;
import com.blogspot.programer27android.copasqenedrive.api.RetroServer;
import com.blogspot.programer27android.copasqenedrive.model.Data;
import com.blogspot.programer27android.copasqenedrive.model.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilanDataUser extends AppCompatActivity {
    private RecyclerView tRecycler;
    private RecyclerView.Adapter tAdapter;
    private RecyclerView.LayoutManager tManager;
    private List<Data> tItems = new ArrayList<>();
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampilan_datauser);
        pb = (ProgressBar) findViewById(R.id.pb);
        pb.setIndeterminate(true);
        pb.setVisibility(View.VISIBLE);
        tRecycler = (RecyclerView) findViewById(R.id.recycleview);
        tManager = new LinearLayoutManager(this);
        tRecycler.setLayoutManager(tManager);

        RestApi api = RetroServer.getClient().create(RestApi.class);
        Call<ResponseData> getdata = api.getdatauser();
        getdata.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pb.setVisibility(View.GONE);
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                tItems = response.body().getResult();
                tAdapter = new RecyclerAdapter(TampilanDataUser.this, tItems);
                tRecycler.setAdapter(tAdapter);
                //mAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pb.setVisibility(View.GONE);
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });
    }
}
