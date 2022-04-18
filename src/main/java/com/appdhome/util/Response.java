package com.appdhome.util;

import java.io.Serializable;

public class Response implements Serializable {
    private String msj;

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
}
