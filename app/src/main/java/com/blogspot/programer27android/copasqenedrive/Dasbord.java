package com.blogspot.programer27android.copasqenedrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dasbord extends AppCompatActivity {

    @BindView(R.id.btsign)
    Button btsign;
    @BindView(R.id.btlogin)
    Button btlogin;
    @BindView(R.id.btdatauser)
    Button btdatauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dasbord);
        ButterKnife.bind(this);
        btsign=(Button) findViewById(R.id.btsign);
        btlogin=(Button) findViewById(R.id.btlogin);
        btdatauser=(Button) findViewById(R.id.btdatauser);
        btsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dasbord.this, Register.class));
                //finish();
            }
        });
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dasbord.this, Login.class));
                //finish();
            }
        });
        btdatauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dasbord.this, TampilanDataUser.class));
                //finish();
            }
        });
    }

    public void signup(View view) {
    }
    public void login(View view) {
    }
}
