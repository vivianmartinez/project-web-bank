package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Entity {
    private int id;
    private String dni;
    private String name;
    private String last_name;
    private String city;
    private LocalDate date_birth;
    private String email;
    private String password;
    private LocalDate created_at;
    private LocalDate updated_at;
    private ArrayList<Account> accounts;

    public Customer() {
    }

    public Customer(int id, String dni, String name, String last_name, String city, LocalDate date_birth, String email,
            String password, LocalDate created_at, LocalDate updated_at, ArrayList<Account> accounts) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.last_name = last_name;
        this.city = city;
        this.date_birth = date_birth;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.accounts = accounts;
    }

    public Customer(String dni, String name, String last_name, String city, LocalDate date_birth, String email,
            String password) {
        this.dni = dni;
        this.name = name;
        this.last_name = last_name;
        this.city = city;
        this.date_birth = date_birth;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate_birth() {
        return this.date_birth;
    }

    public void setDate_birth(LocalDate date_birth) {
        this.date_birth = date_birth;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", dni='" + getDni() + "'" +
                ", name='" + getName() + "'" +
                ", last_name='" + getLast_name() + "'" +
                ", city='" + getCity() + "'" +
                ", date_birth='" + getDate_birth() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", created_at='" + getCreated_at() + "'" +
                ", updated_at='" + getUpdated_at() + "'" +
                ", accounts='" + getAccounts() + "'" +
                "}";
    }

}
