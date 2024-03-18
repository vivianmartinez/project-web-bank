package validator;

import java.lang.reflect.Field;
import model.entity.Customer;
import validator.customexceptions.CustomerValidator;

public class EntityValidator {

    public EntityValidator() {

    }

    public void validateCustomer(Customer customer)
            throws IllegalArgumentException, IllegalAccessException, CustomerValidator {
        // recorro customer obteniendo cada propiedad (campo) que tieme declarada
        for (Field field : customer.getClass().getDeclaredFields()) {
            // hago la propiedad accesible si es privada
            field.setAccessible(true);
            Object property_value = field.get(customer);
            // System.out.println(field.getType()); // para obtener el tipo
            if (property_value == null | property_value == "") {
                throw new CustomerValidator("Property cannot be null: ".concat(field.getName()));
            }
        }
    }

}
