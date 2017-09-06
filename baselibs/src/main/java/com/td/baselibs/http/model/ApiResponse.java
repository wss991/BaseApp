package com.td.baselibs.http.model;

public class ApiResponse<T> {
    public int errcode;
    public String errdesc;
    public T data;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int  errcode) {
        this.errcode = errcode;
    }

    public String getErrdesc() {
        return errdesc;
    }

    public void setErrdesc(String errdesc) {
        this.errdesc = errdesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "errcode='" + errcode + '\'' +
                ", errdesc='" + errdesc + '\'' +
                ", data=" + data +
                '}';
    }
}