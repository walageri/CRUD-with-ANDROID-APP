package com.blogspot.programer27android.copasqenedrive;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.programer27android.copasqenedrive.api.RestApi;
import com.blogspot.programer27android.copasqenedrive.api.RetroServer;
import com.blogspot.programer27android.copasqenedrive.model.ResponseData;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditData extends AppCompatActivity {
    @BindView(R.id.edtnamaE)
    TextInputEditText edtnama;
    @BindView(R.id.edtemailE)
    TextInputEditText edtmail;
    @BindView(R.id.edtpasswordE)
    TextInputEditText edtpassword;
    @BindView(R.id.btnupdateE)
    Button update;
    @BindView(R.id.btndeleteE)
    Button delete;
    @BindView(R.id.pbE)
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);
        edtnama=(TextInputEditText)findViewById(R.id.edtnamaE);
        edtmail=(TextInputEditText)findViewById(R.id.edtemailE);
        edtpassword=(TextInputEditText)findViewById(R.id.edtpasswordE);
        update=(Button)findViewById(R.id.btnupdateE);
        delete=(Button)findViewById(R.id.btndeleteE);
        
        pb=(ProgressBar)findViewById(R.id.pbE);
        pb.setIndeterminate(true);
        pb.setVisibility(View.GONE); 
        
        Intent data = getIntent();
        final String idData = data.getStringExtra("id");
        if(idData != null) {
            edtnama.setText(data.getStringExtra("nama"));
            edtmail.setText(data.getStringExtra("email"));
            edtpassword.setText(data.getStringExtra("password"));
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                RestApi api = RetroServer.getClient().create(RestApi.class);
                Call<ResponseData> update = api.updateData
                        (idData,
                                edtnama.getText().toString(),
                                edtmail.getText().toString(),
                                edtpassword.getText().toString());
                update.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(EditData.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditData.this, TampilanDataUser.class));
                        pb.setVisibility(View.GONE);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        pb.setVisibility(View.GONE);
                        Log.d("Retro", "OnFailure");

                    }
                });
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                RestApi api = RetroServer.getClient().create(RestApi.class);
                Call<ResponseData> d  = api.deleteData(idData);
                d.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        pb.setVisibility(View.GONE);
                        Log.d("Retro", "onResponse");
                        Toast.makeText(EditData.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent tampilkan = new Intent(EditData.this,TampilanDataUser.class);
                        startActivity(tampilkan);
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        pb.setVisibility(View.GONE);
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });
    }
}


