package com.blogspot.programer27android.copasqenedrive;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.programer27android.copasqenedrive.model.Data;

import java.util.List;

/**
 * Created by gery on 11/23/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {
    List<Data> listdata ;
    Context c;

    public RecyclerAdapter(Context c, List<Data> listdata) {
        this.listdata = listdata;
        this.c= c;
    }
    @Override
    public RecyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MyHolder h, final int position) {
        h.lNama.setText(listdata.get(position).getNama());
        h.lEmail.setText(listdata.get(position).getemail());
        h.lPassword.setText(listdata.get(position).getpassword());


        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userdata = new Intent(c,EditData.class);
                try {
                    userdata.putExtra("id", listdata.get(position).getId());
                    userdata.putExtra("nama", listdata.get(position).getNama());
                    userdata.putExtra("email", listdata.get(position).getemail());
                    userdata.putExtra("password", listdata.get(position).getpassword());

                    c.startActivity(userdata);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(c, "Data Error" +e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        EditText lNama, lEmail, lPassword;
        Data lData;
        public MyHolder(View v) {
            super(v);
            lNama  = (EditText) v.findViewById(R.id.edtnamaL);
            lEmail = (EditText) v.findViewById(R.id.edtemailL);
            lPassword = (EditText) v.findViewById(R.id.edtpasswordL);
        }
    }
}
