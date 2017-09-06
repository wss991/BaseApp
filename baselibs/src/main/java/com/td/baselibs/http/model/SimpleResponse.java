package com.td.baselibs.http.model;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public ApiResponse toLzyResponse() {
        ApiResponse lzyResponse = new ApiResponse();
        lzyResponse.errcode = code;
        lzyResponse.errdesc = msg;
        return lzyResponse;
    }
}