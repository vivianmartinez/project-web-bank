package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import controller.CustomerController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Account;
import model.entity.Customer;
import utilities.Utilities;
import validator.EntityValidator;
import validator.customexceptions.CustomerValidator;

@WebServlet("/customercontroller")
public class CustomerControllerServlet extends HttpServlet {

    private CustomerController customerController = new CustomerController();
    private EntityValidator entityValidator = new EntityValidator();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        // recogemos los parámetros
        String name = request.getParameter("customerName") != null ? request.getParameter("customerName") : null;
        String lastName = request.getParameter("customerLastName") != null ? request.getParameter("customerLastName")
                : null;
        String dni = request.getParameter("customerDni") != null ? request.getParameter("customerDni") : null;
        String city = request.getParameter("customerCity") != null ? request.getParameter("customerCity") : null;
        LocalDate dateBirth = request.getParameter("dateBirth") != null && request.getParameter("dateBirth") != ""
                ? Utilities.convertStringToLocalDate(request.getParameter("dateBirth"))
                : null;
        String email = request.getParameter("customerEmail") != null ? request.getParameter("customerEmail") : null;
        String password = request.getParameter("customerPassword") != null ? request.getParameter("customerPassword")
                : null;
        int typeAccount = request.getParameter("selectTypeAccount") != "0"
                ? Integer.parseInt(request.getParameter("selectTypeAccount"))
                : null;

        // creamos un cliente
        Customer customer = new Customer(dni, name, lastName, city, dateBirth, email, password);
        // validar cliente
        try {
            this.entityValidator.validateCustomer(customer);
            // creamos una cuenta con tipo de cuenta, status active y saldo 0
            Account account = new Account();
            account.setActive(true);
            account.setBalance(0.0);
            account.setType_account_id(typeAccount);
            // llamamos al controlador de cliente
            this.customerController.create(customer, account);
            request.setAttribute("message_create_customer", "Cliente creado correctamente.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (CustomerValidator e) {
            request.setAttribute("message_create_customer", e.getMessage());
            //e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/create.jsp");
        dispatcher.forward(request, response);

    }

    @SuppressWarnings("unused")
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Unimplemented method 'processRequest'");
    }
}
