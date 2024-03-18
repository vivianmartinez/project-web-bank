package servlets;

import java.io.IOException;
import java.util.HashMap;
import controller.UserController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Entity;
import model.entity.User;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginControllerServlet extends HttpServlet {

    private UserController userController = new UserController();

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

        HttpSession loguedIn = request.getSession();
        HashMap params = new HashMap<>();
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        params.put("email", email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        if (!email.isEmpty() && !password.isEmpty()) {
            Entity findUser = userController.getOne(params);

            if (findUser != null) {
                User user = (User) findUser;

                if (user.getPassword().equals(password)) {
                    loguedIn.setAttribute("currentUser", user);
                    dispatcher = request.getRequestDispatcher("home.jsp");
                }
            }
        }

        dispatcher.forward(request, response);
    }

}
