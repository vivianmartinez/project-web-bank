package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.HashMap;
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
        // defino una fecha default si no se ingresa fecha - pero haré validación para
        // que la fecha de nacimiento sea obligatoria
        LocalDate dateB = convertStringToLocalDate("1900-01-01");
        return new Customer(
                hash.get("dni").toString(), hash.get("name").toString(), hash.get("last_name").toString(),
                hash.get("city").toString(),
                hash.get("date_birth") == null ? dateB : convertStringToLocalDate(hash.get("date_birth").toString()),
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
        return new User(hash.get("name").toString(), hash.get("email").toString(), hash.get("password").toString());
    }

    public static long generateAccountNumber(ArrayList<HashMap> data) {
        String generate_account_number = data.get(0).get("account_number").toString();
        long account_number = Long.parseLong(generate_account_number) + 1;
        // long account_number = 12345495995L;
        return account_number;
    }
}
