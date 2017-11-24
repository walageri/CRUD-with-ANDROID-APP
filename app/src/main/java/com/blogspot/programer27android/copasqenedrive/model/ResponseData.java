package com.blogspot.programer27android.copasqenedrive.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gery on 11/20/17.
 */

public class ResponseData {

    List<Data> result;
    @SerializedName("kode")
    private String mKode;
    @SerializedName("pesan")
    private String mPesan;
    //list data
    public List<Data> getResult() {
        return result;
    }

    public void setResult(List<Data> result) {
        this.result = result;
    }

    public String getKode() {
        return mKode;
    }

    public void setKode(String kode) {
        mKode = kode;
    }

    public String getPesan() {
        return mPesan;
    }

    public void setPesan(String pesan) {
        mPesan = pesan;
    }

}
