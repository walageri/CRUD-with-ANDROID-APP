package com.blogspot.programer27android.copasqenedrive;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.programer27android.copasqenedrive.api.RestApi;
import com.blogspot.programer27android.copasqenedrive.api.RetroServer;
import com.blogspot.programer27android.copasqenedrive.model.ResponseData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    @BindView(R.id.edtnamaR)
    TextInputEditText edtnama;
    @BindView(R.id.edtemailR)
    TextInputEditText edtmail;
    @BindView(R.id.edtpasswordR)
    TextInputEditText edtpassword;
    @BindView(R.id.btnregisterR)
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        edtnama=(TextInputEditText)findViewById(R.id.edtnamaR);
        edtmail=(TextInputEditText)findViewById(R.id.edtemailR);
        edtpassword=(TextInputEditText)findViewById(R.id.edtpasswordR);
        btnregister=(Button)findViewById(R.id.btnregisterR);

        Intent data = getIntent();
        final String idData = data.getStringExtra("id");
        if(idData != null) {
            edtnama.setText(data.getStringExtra("nama"));
            edtmail.setText(data.getStringExtra("email"));
            edtpassword.setText(data.getStringExtra("password"));
            }
        //regiter
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String snama = edtnama.getText().toString();
                String semail = edtmail.getText().toString();
                String spassword = edtpassword.getText().toString();
                if (snama.isEmpty()) {
                    edtnama.setError("nama tidak boleh kosong");
                } else if (semail.isEmpty()) {
                    edtmail.setError("email tidak boleh kosong");
                } else if (!cekemail(semail)) {
                    edtmail.setError("email harus diisi dengan benar");
                } else if (spassword.isEmpty()) {
                    edtpassword.setError("password tidak boleh kosong");
                }else if (spassword.length()<6) {
                    edtpassword.setError("password harus lebih dari 6 caracter");
                }else if (spassword.equals(snama)) {
                    edtpassword.setError("password tidak boleh sama dengan nama");
                }else {
                    //getclient hubungan dengan url
                    RestApi api= RetroServer.getClient().create(RestApi.class);

                    Call<ResponseData> kirim_data_user =api.senddatauser(snama,semail,spassword);
                    kirim_data_user.enqueue(new Callback<ResponseData>(){

                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                            Log.d("RETRO", "response : " + response.body().toString());
                            String kode = response.body().getKode();
                            //baca kode kirim data di php
                            if(kode.equals("1"))
                            {
                                Toast.makeText(Register.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, TampilanDataUser.class));
                                edtnama.getText().clear();
                                edtmail.getText().clear();
                                edtpassword.getText().clear();
                            }else
                            {
                                Toast.makeText(Register.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                            Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                            Toast.makeText(Register.this, "Periksa Koneksi internet anda", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private boolean cekemail(String emailInput) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailInput);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setTitle("warnig...!!!");
        a.setMessage("do you wan to exit");
        a.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Register.this.finish();
            }
        });
        a.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        a.show();
    }
}