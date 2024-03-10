package servlets;

import java.io.IOException;
import java.util.ArrayList;

import controller.AccountController;
import controller.CustomerController;
import utilities.Utilities;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Customer;
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
                        ArrayList<Entity> accounts = accountController.list();
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
}
