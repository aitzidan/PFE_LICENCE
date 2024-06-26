package com.lus.dawm.model;

public class StatusMessage {
    private String codeStatut;
    private String message;

    public StatusMessage(String codeStatut, String message) {
        this.codeStatut = codeStatut;
        this.message = message;
    }

    public String getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(String codeStatut) {
        this.codeStatut = codeStatut;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
