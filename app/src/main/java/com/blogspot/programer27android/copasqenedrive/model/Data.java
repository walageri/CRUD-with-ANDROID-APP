package com.blogspot.programer27android.copasqenedrive.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gery on 11/20/17.
 */

public class Data {
    
    @SerializedName("id")
    private String mId;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("email")
    private String memail;
    @SerializedName("password")
    private String mpassword;
    
    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNama() {
        return mNama;
    }

    public void setNama(String nama) {
        mNama = nama;
    }

    public String getemail() {
        return memail;
    }

    public void setemail(String email) {
        memail = email;
    }

    public String getpassword() {
        return mpassword;
    }

    public void setpassword(String password) {
        mpassword = password;
    }

}
