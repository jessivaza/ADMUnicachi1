/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/ServletClienteJSON")
public class ServletClienteJSON extends HttpServlet {

    public ServletClienteJSON() {
        super();
    }

	
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       

        List<Cliente> lista = new ClienteDAO().findAllClientes();
        Gson gson = new Gson();
        String json = gson.toJson(lista);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
    }


    
}