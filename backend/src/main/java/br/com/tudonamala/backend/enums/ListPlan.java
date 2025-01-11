package br.com.tudonamala.backend.enums;

public enum ListPlan {
    FREE("free"),
    PREMIUM("premium");

    private String plan;

    ListPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }

}