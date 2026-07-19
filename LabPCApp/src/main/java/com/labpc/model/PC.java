package com.labpc.model;

public class PC {
    private String kodePC;
    private String status;

    public PC() {}

    public PC(String kodePC, String status) {
        this.kodePC = kodePC;
        this.status = status;
    }

    public String getKodePC() { return kodePC; }
    public void setKodePC(String kodePC) { this.kodePC = kodePC; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
