/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {

     public ServletUsuario() {
        super();
    }

	
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String tipo = request.getParameter("accion");

        if(tipo.equals("guardar"))
            guardarClientes(request, response);
        if(tipo.equals("editar"))
            editarClientes(request, response);
        if(tipo.equals("eliminar"))
            eliminarClientes(request, response);
        if(tipo.equals("login"))
            loginU(request, response);
  
    }

    protected void guardarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreClie = request.getParameter("nombreCliePag");
        String apellidoClie = request.getParameter("apellidoCliePag");
        String direcionClie = request.getParameter("direccionCliePag");
        String dniClie = request.getParameter("dniCliePag");
        String emailClie = request.getParameter("emailCliePag");
        String contrasenaClie = request.getParameter("contrasenaCliePag");
        String telefonoClie  = request.getParameter("telefonoCliePag");
        
        
        Cliente cliente = new Cliente();   
        
        cliente.setNombre(nombreClie);
        cliente.setApellido(apellidoClie);
        cliente.setDireccion(direcionClie);
        cliente.setDni(dniClie);
        cliente.setEmail(emailClie);
        cliente.setContrasena(contrasenaClie);
        cliente.setTelefono(telefonoClie);
        
 
        int estado = new ClienteDAO().saveCliente(cliente);
        
         
        if(estado == 1){
            System.out.println("Producto registrado");
            response.sendRedirect("ModalsAgregar/registroProducto.jsp"); 
        } else {
            System.out.println("Producto no registrado");
            response.sendRedirect("ModalsAgregar/registroProducto.jsp"); 
        }
    }
    protected void editarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idClientePag"));
        String nombreClie = request.getParameter("nombreCliePag");
        String apellidoClie = request.getParameter("apellidoCliePag");
        String direcionClie = request.getParameter("direccionCliePag");
        String dniClie = request.getParameter("dniCliePag");
        String emailClie = request.getParameter("emailCliePag");
        String contrasenaClie = request.getParameter("contrasenaCliePag");
        String telefonoClie  = request.getParameter("telefonoCliePag");

        Cliente cliente = new Cliente();

        cliente.setIdCliente(idCliente);
        cliente.setNombre(nombreClie);
        cliente.setApellido(apellidoClie);
        cliente.setDireccion(direcionClie);
        cliente.setDni(dniClie);
        cliente.setEmail(emailClie);
        cliente.setContrasena(contrasenaClie);
        cliente.setTelefono(telefonoClie);

        int estado = new ClienteDAO().updateCliente(cliente);

        if(estado == 1){
            System.out.println("Producto actualizado");
            response.sendRedirect("Das.jsp"); 
        } else {
            System.out.println("Producto no actualizado");
            response.sendRedirect("Das.jsp"); 
        }
    }
    protected void eliminarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idClientePag"));
        
        int estado = new ClienteDAO().deleteClienteById(idCliente);
        
        if(estado==1){
            System.out.println("Producto eliminado exitosamente");
            response.sendRedirect("Das.jsp"); 
        } else{
            System.out.println("No fue eliminado el producto");
            response.sendRedirect("Das.jsp"); 
        }
               
    }
    
    protected void loginU(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombrePag");
        String contra = request.getParameter("contrasenaPag");
        
        Cliente usu = new ClienteDAO().InicioSesion(nombre, contra);
        
        if (usu == null) {
                System.out.println("No se inicio sesion");
                response.sendRedirect("Registrarse.jsp");
        } else {
            
            //Obtener datos del usuario cliente si fue exitoso el login
            int idUsu = usu.getIdCliente();
            String nombreUsu = usu.getNombre();
            String apellidoUsu = usu.getApellido();
            String direccion = usu.getDireccion();
            String emailUsu = usu.getEmail();
            String contraUsu = usu.getContrasena();
            String telefonoUsu = usu.getTelefono();
            String dniUsu = usu.getDni();
            
            //se almacenan los datos del usuario o cliente ne la sesion http
            HttpSession session = request.getSession();
            session.setAttribute("idClie", idUsu);
            session.setAttribute("nombreClie", nombreUsu);
            session.setAttribute("apellidoClie", apellidoUsu);
            session.setAttribute("direccionClie", direccion);
            session.setAttribute("emailClie", emailUsu);
            session.setAttribute("contraClie", contraUsu);
            session.setAttribute("telefonoClie", telefonoUsu);
            session.setAttribute("dniClie", dniUsu);
            
            
            System.out.println("Sesion iniciada");
            response.sendRedirect("index.jsp");
        }
    }
      
}