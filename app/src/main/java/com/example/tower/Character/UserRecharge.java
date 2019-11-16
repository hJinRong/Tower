package com.example.tower.Character;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class UserRecharge {
    String account;
    BigDecimal figure;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setFigure(BigDecimal figure) {
        this.figure = figure;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getFigure() {
        return figure;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" + "\"account\":" + "\"" + account + "\"" + "," + "\"figure\":" + figure + "}";
    }
}
