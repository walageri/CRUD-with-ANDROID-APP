package com.blogspot.programer27android.copasqenedrive;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;

public class Login extends AppCompatActivity {
    @BindView(R.id.edtemail)
    TextInputEditText edtmail;
    @BindView(R.id.edtpassword)
    TextInputEditText edtpassword;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    @BindView(R.id.btnbelumpunyaakun)
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edtmail=(TextInputEditText)findViewById(R.id.edtemail);
        edtpassword=(TextInputEditText)findViewById(R.id.edtpassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnregister=(Button)findViewById(R.id.btnbelumpunyaakun);
    }
}
