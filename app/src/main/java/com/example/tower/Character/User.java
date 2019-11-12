package com.example.tower.Character;

public class User {
    private String name, account, password;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "{" + "\"name\":" + "\"" + name + "\"" + "," + "\"account\":" + "\"" + account + "\"" + "," + "\"password\":" + "\"" + password + "\"" + "}";
    }
}