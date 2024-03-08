package model.entity;

import java.time.LocalDate;

public class Account extends Entity {
    private int id;
    private int customer_id; // foreign key
    private int type_account_id; // foreign key
    private long account_number;
    private Double balance;
    private Boolean active;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Account() {
    }

    public Account(int id, int customer_id, int type_account_id, long account_number, Double balance, Boolean active,
            LocalDate created_at, LocalDate updated_at) {
        this.id = id;
        this.customer_id = customer_id;
        this.type_account_id = type_account_id;
        this.account_number = account_number;
        this.balance = balance;
        this.active = active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Account(int customer_id, int type_account_id, long account_number, Double balance, Boolean active) {
        this.customer_id = customer_id;
        this.type_account_id = type_account_id;
        this.account_number = account_number;
        this.balance = balance;
        this.active = active;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getType_account_id() {
        return this.type_account_id;
    }

    public void setType_account_id(int type_account_id) {
        this.type_account_id = type_account_id;
    }

    public long getAccount_number() {
        return this.account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean isActive() {
        return this.active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return this.updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", customer_id='" + getCustomer_id() + "'" +
                ", type_account_id='" + getType_account_id() + "'" +
                ", account_number='" + getAccount_number() + "'" +
                ", balance='" + getBalance() + "'" +
                ", active='" + isActive() + "'" +
                ", created_at='" + getCreated_at() + "'" +
                ", updated_at='" + getUpdated_at() + "'" +
                "}";
    }
}
