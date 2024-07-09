package DAO;

import Interfaz.CRUDPuestoInforme;
import Modelo.PuestoInforme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MySqlConectar;

public class PuestoInformeDAO implements CRUDPuestoInforme {

    @Override
    public List<PuestoInforme> findAllPuestoInforme() {
        List<PuestoInforme> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = new MySqlConectar().getConnection();
            String sql = "SELECT " +
                         "    pu.idPuesto AS 'ID del Puesto', " +
                         "    c.nombreCategoria AS 'Nombre de la Categoría', " +
                         "    pr.Descripcion AS 'Descripción del Producto', " +
                         "    CONCAT(cl.nombre, ' ', cl.apellido) AS 'Nombre Cliente', " +
                         "    pu.dueño AS 'Dueño del Puesto', " +
                         "    CASE " +
                         "        WHEN pu.estado = 1 THEN 'Abierto' " +
                         "        ELSE 'Cerrado' " +
                         "    END AS 'Estado' " +
                         "FROM " +
                         "    puestos pu " +
                         "JOIN " +
                         "    categoria c ON pu.idCategoria = c.idCategoria " +
                         "JOIN " +
                         "    productos pr ON pu.idProducto = pr.IdProducto " +
                         "JOIN " +
                         "    cliente cl ON pu.idCliente = cl.idCliente";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                PuestoInforme puestoInforme = new PuestoInforme();
                puestoInforme.setIdPuestoInforme(rs.getInt("ID del Puesto"));
                puestoInforme.setCategoriaPuestoInforme(rs.getString("Nombre de la Categoría"));
                puestoInforme.setProductoPuestoInforme(rs.getString("Descripción del Producto"));
                puestoInforme.setClientePuestoInforme(rs.getString("Nombre Cliente"));
                puestoInforme.setDuenoPuestoInforme(rs.getString("Dueño del Puesto"));
                puestoInforme.setEstadoPuestoInforme(rs.getString("Estado"));
                lista.add(puestoInforme);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
            }
        }
        return lista;
    }

 @Override
    public PuestoInforme findPuestoById(int idPuesto) {
        PuestoInforme puestoInforme = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = new MySqlConectar().getConnection();
            String sql = "SELECT " +
                         "    pu.idPuesto AS 'ID del Puesto', " +
                         "    c.nombreCategoria AS 'Nombre de la Categoría', " +
                         "    pr.Descripcion AS 'Descripción del Producto', " +
                         "    CONCAT(cl.nombre, ' ', cl.apellido) AS 'Nombre Cliente', " +
                         "    pu.dueño AS 'Dueño del Puesto', " +
                         "    CASE " +
                         "        WHEN pu.estado = 1 THEN 'Abierto' " +
                         "        ELSE 'Cerrado' " +
                         "    END AS 'Estado' " +
                         "FROM " +
                         "    puestos pu " +
                         "JOIN " +
                         "    categoria c ON pu.idCategoria = c.idCategoria " +
                         "JOIN " +
                         "    productos pr ON pu.idProducto = pr.IdProducto " +
                         "JOIN " +
                         "    cliente cl ON pu.idCliente = cl.idCliente " +
                         "WHERE pu.idPuesto = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idPuesto);
            rs = pstm.executeQuery();
            if (rs.next()) {
                puestoInforme = new PuestoInforme();
                puestoInforme.setIdPuestoInforme(rs.getInt("ID del Puesto"));
                puestoInforme.setCategoriaPuestoInforme(rs.getString("Nombre de la Categoría"));
                puestoInforme.setProductoPuestoInforme(rs.getString("Descripción del Producto"));
                puestoInforme.setClientePuestoInforme(rs.getString("Nombre Cliente"));
                puestoInforme.setDuenoPuestoInforme(rs.getString("Dueño del Puesto"));
                puestoInforme.setEstadoPuestoInforme(rs.getString("Estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return puestoInforme;
    }

    @Override
    public int savePuesto(PuestoInforme puesto) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int resultado = 0;
        try {
            cn = new MySqlConectar().getConnection();
            String sql = "INSERT INTO puestos (idPuesto, idCategoria, idProducto, idCliente, dueño, estado) VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, puesto.getIdPuestoInforme());
            pstm.setString(2, puesto.getCategoriaPuestoInforme());
            pstm.setString(3, puesto.getProductoPuestoInforme());
            pstm.setString(4, puesto.getClientePuestoInforme());
            pstm.setString(5, puesto.getDuenoPuestoInforme());
            pstm.setString(6, puesto.getEstadoPuestoInforme());
            resultado = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
            }
        }
        return resultado;
    }

    @Override
    public int updatePuesto(PuestoInforme puesto) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int resultado = 0;
        try {
            cn = new MySqlConectar().getConnection();
            String sql = "UPDATE puestos SET idCategoria = ?, idProducto = ?, idCliente = ?, dueño = ?, estado = ? WHERE idPuesto = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, puesto.getCategoriaPuestoInforme());
            pstm.setString(2, puesto.getProductoPuestoInforme());
            pstm.setString(3, puesto.getClientePuestoInforme());
            pstm.setString(4, puesto.getDuenoPuestoInforme());
            pstm.setString(5, puesto.getEstadoPuestoInforme());
            pstm.setInt(6, puesto.getIdPuestoInforme());
            resultado = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
            }
        }
        return resultado;
    }

    @Override
    public int deletePuestoById(int idPuesto) {
        Connection cn = null;
        PreparedStatement pstm = null;
        int resultado = 0;
        try {
            cn = new MySqlConectar().getConnection();
            String sql = "DELETE FROM puestos WHERE idPuesto = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idPuesto);
            resultado = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar adecuadamente las excepciones en tu aplicación
            }
        }
        return resultado;
    }

}
