package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import controller.AccountController;
import controller.CustomerController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Entity;

@WebServlet(name = "index", urlPatterns = "/index")
public class ControllerServlet extends HttpServlet {

    private CustomerController customerController = new CustomerController();
    private AccountController accountController = new AccountController();

    @override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String routeController = request.getParameter("page");
        String routeMethod = request.getParameter("action");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        request.setAttribute("routeController", routeController);
        if (routeController.equals("customer") || routeController.equals("account")) {
            switch (routeMethod) {
                case "home":
                    // validar usuario si existe en base de datos
                    dispatcher = request.getRequestDispatcher("/home.jsp");
                    break;
                case "create":
                    dispatcher = request.getRequestDispatcher(routeController + "/create.jsp");
                    break;
                case "list":
                    if (routeController.equals("customer")) {
                        ArrayList<Entity> customers = customerController.list();
                        request.setAttribute("customers_list", customers);
                    } else if (routeController.equals("account")) {
                        ArrayList<HashMap> accounts = accountController.listJoin();
                        request.setAttribute("account_list", accounts);
                    }
                    dispatcher = request.getRequestDispatcher(routeController + "/list.jsp");
                    break;
                case "admin":
                    System.out.println("admin something " + routeController);
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("/index");
                    break;
            }
        }
        dispatcher.forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
    }
}
