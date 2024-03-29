package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;

import model.entity.Account;
import model.entity.Customer;
import model.entity.User;

public class Utilities {
    /**
     * convertir String a LocalDate
     * 
     * @Autor Vivian Martínez
     * @param date
     * @return LocalDate
     */
    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .toFormatter();
        LocalDate dateConverted = LocalDate.parse(date, formatter);
        return dateConverted;
    }

    /**
     * convertir String a LocalDate
     * 
     * @Autor Vivian Martínez
     * @param date
     * @return LocalDate
     */
    public static LocalDate convertStringToLocalDateFormatSlash(String date) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .toFormatter();
        LocalDate dateConverted = LocalDate.parse(date, formatter);
        return dateConverted;
    }
    /**
     * convertir LocalDate to String
     * 
     * @Autor Vivian Martínez
     * @param date
     * @return String
     */
    public static String convertLocalDateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateConverted = formatter.format(date);
        return dateConverted;
    }

    /**
     * Convertir hashMap a Customer
     * 
     * @Autor Vivian Martínez
     * @param hash
     * @return Customer
     */
    public static Customer customerFromHash(@SuppressWarnings("rawtypes") HashMap hash) {
        LocalDate dateB = convertStringToLocalDate(hash.get("date_birth").toString());
        return new Customer(
                hash.get("dni").toString(), hash.get("name").toString(), hash.get("last_name").toString(),
                hash.get("city").toString(),
                dateB,
                hash.get("email").toString(), hash.get("password").toString());
    }

    /**
     * Convertir hashMap a Usuario
     * 
     * @Autor Vivian Martínez
     * @param hash
     * @return Usuario
     */
    public static User userFromHash(@SuppressWarnings("rawtypes") HashMap hash) {
        return new User(hash.get("name").toString(), hash.get("email").toString(),hash.get("role").toString(), hash.get("password").toString());
    }

    /**
     * Convertir hashMap a Account
     * 
     * @Autor Vivian Martínez
     * @param hash
     * @return Account
     */
    public static Account accountFromHash(@SuppressWarnings("rawtypes") HashMap hash) {
        Long number_account = Long.parseLong(hash.get("account_number").toString());
        return new Account((int) hash.get("customer_id"), (int) hash.get("type_account_id"),number_account,Double.parseDouble(hash.get("balance").toString()), Boolean.parseBoolean(hash.get("active").toString()));
    }

    public static long generateAccountNumber(ArrayList<HashMap> data) {
        String generate_account_number = data.get(0).get("account_number").toString();
        long account_number = Long.parseLong(generate_account_number) + 1;
        // long account_number = 12345495995L;
        return account_number;
    }
}
