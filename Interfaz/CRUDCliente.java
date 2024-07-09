
package Interfaz;

import Modelo.Cliente;
import java.util.List;

import Modelo.DTOusuario;

public interface CRUDCliente{
    List<Cliente> findAllClientes();
    Cliente findClienteById(int idCliente);
    int saveCliente(Cliente cliente);
    int updateCliente(Cliente cliente);
    int deleteClienteById(int idCliente);
    public Cliente InicioSesion(String nombreUsuario, String contrasena);
}
