package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet de Login para redirigir a los usuarios según el prefijo del nombre de usuario.
 */
public class ServletLogin extends HttpServlet {

    // Usuario y contraseñas predeterminados
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("ADM001", "ADM001"); // Usuario administrador
        usuarios.put("ADM002", "ADM002"); // Usuario administrador
        usuarios.put("CLI001", "CLI001"); // Usuario empleado
        usuarios.put("CLI002", "CLI002"); // Usuario empleado
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar usuario y contraseña
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("mensaje", "Usuario o contraseña no pueden estar vacíos.");
            request.getRequestDispatcher("Ingresar.jsp").forward(request, response);
            return;
        }

        // Verificar credenciales
        String storedPassword = usuarios.get(username);
        if (storedPassword == null || !storedPassword.equals(password)) {
            request.setAttribute("mensaje", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("Ingresar.jsp").forward(request, response);
            return;
        }

        // Crear sesión y establecer atributo de usuario
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        // Redireccionar según el prefijo del nombre de usuario
        if (username.startsWith("ADM")) {
            response.sendRedirect("Das.jsp");
        } else if (username.startsWith("CLI")) {
            response.sendRedirect("index.html");
        } else {
            response.sendRedirect("Ingresar.jsp?error=invalid");
        }
    }
  
    @Override
    public String getServletInfo() {
        return "Servlet para manejar el login y redirigir a diferentes vistas según el usuario.";
    }
}
