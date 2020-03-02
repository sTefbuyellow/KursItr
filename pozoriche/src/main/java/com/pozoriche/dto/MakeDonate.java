package com.pozoriche.dto;

public class MakeDonate {
    private String page;
    private Long amount;

    public MakeDonate() { ;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
