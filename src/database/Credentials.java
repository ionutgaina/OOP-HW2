package database;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    @JsonSerialize(using = ToStringSerializer.class)
    private int balance;

    public Credentials() { }

    public Credentials(String name, String password, String accountType,
                       String country, int balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Credentials{" +
               "name='" + name + '\'' +
               ", password='" + password + '\'' +
               ", accountType='" + accountType + '\'' +
               ", country='" + country + '\'' +
               ", balance=" + balance +
               '}';
    }
}
