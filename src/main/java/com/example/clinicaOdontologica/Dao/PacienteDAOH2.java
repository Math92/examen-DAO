package com.example.clinicaOdontologica.Dao;

import com.example.clinicaOdontologica.Model.Domicilio;
import com.example.clinicaOdontologica.Model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PacienteDAOH2 implements iDao<Paciente> {

    private static final Logger logger = Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_INSERT = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, EMAIL, DOMICILIO_ID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_ONE = "SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT = "SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_DELETE = "DELETE FROM PACIENTES WHERE ID=?";

    @Override
    public List<Paciente> listarTodos() {
        logger.info("Iniciando las operaciones de: listar todos los pacientes");
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            while (rs.next()) {
                Domicilio domicilio = daoAux.buscarporId(rs.getInt("DOMICILIO_ID"));
                Paciente paciente = new Paciente(
                        rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("CEDULA"),
                        rs.getDate("FECHA_INGRESO").toLocalDate(),
                        rs.getString("EMAIL"),
                        domicilio
                );
                pacientes.add(paciente);
            }
        } catch (Exception e) {
            logger.error("Problemas con la BD: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Problemas al cerrar la conexión: " + e.getMessage());
            }
        }
        return pacientes;
    }

    public Paciente buscarPorEmail(String email) {
//        logger.info("Iniciando la operación de búsqueda de paciente por email: " + email);
//        Connection connection = null;
//        Paciente paciente = null;
//        try {
//            connection = BD.getConnection();
//            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
//            psSelect.setString(1, email);
//            ResultSet rs = psSelect.executeQuery();
//            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
//            if (rs.next()) {
//                Domicilio domicilio = daoAux.buscarporId(rs.getInt("DOMICILIO_ID"));
//                paciente = new Paciente(
//                        rs.getInt("ID"),
//                        rs.getString("NOMBRE"),
//                        rs.getString("APELLIDO"),
//                        rs.getString("CEDULA"),
//                        rs.getDate("FECHA_INGRESO").toLocalDate(),
//                        rs.getString("EMAIL"),
//                        domicilio
//                );
//            }
//        } catch (Exception e) {
//            logger.error("Problemas con la BD: " + e.getMessage());
//        } finally {
//            try {
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                logger.error("Problemas al cerrar la conexión: " + e.getMessage());
//            }
//        }
//        return paciente;
        logger.info("iniciando las operaciones de busqueda de : "+email);
        Paciente paciente=null;
        Domicilio domicilio= null;
        DomicilioDAOH2 daoAux= new DomicilioDAOH2();
        Connection connection=null;
        try{
            connection=BD.getConnection();
            PreparedStatement psSelectEmail= connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectEmail.setString(1,email);
            ResultSet rs= psSelectEmail.executeQuery();
            while(rs.next()){
                domicilio= daoAux.buscarporId(rs.getInt(6)); //--< FK
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getDate(5).toLocalDate(),rs.getString(6),domicilio);
            }

        }catch (Exception e){
            logger.error("Error en la conexion: "+e.getMessage());
        }
        if(paciente!=null){
            logger.info("busqueda exitosa:  Nombre y Apellido: "+paciente.getNombre()+" "+paciente.getApellido());
        }else{
            logger.warn("busqueda sin resultados coincidentes: "+paciente);

        }
        return paciente;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("Iniciando las operaciones de: guardado de: " + paciente.getNombre());
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, paciente.getDomicilio().getId());
            psInsert.setString(6, paciente.getEmail());

            psInsert.execute(); // Ejecutar la inserción
            ResultSet rs = psInsert.getGeneratedKeys(); // Obtener el ID generado
            if (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            logger.error("Problemas con la BD: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Problemas al cerrar la conexión: " + e.getMessage());
            }
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Iniciando las operaciones de: actualización de: " + paciente.getNombre());
        // Implementar la lógica para actualizar un paciente
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando las operaciones de: eliminación de paciente con ID: " + id);
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
            logger.info("Paciente con ID: " + id + " eliminado exitosamente.");
        } catch (Exception e) {
            logger.error("Problemas con la BD: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Problemas al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    @Override
    public Paciente buscarporId(Integer id) {
        logger.info("Iniciando las operaciones de: búsqueda de paciente con ID: " + id);
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_ONE);
            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();
            DomicilioDAOH2 daoAux = new DomicilioDAOH2();
            if (rs.next()) {
                Domicilio domicilio = daoAux.buscarporId(rs.getInt("DOMICILIO_ID"));
                paciente = new Paciente(
                        rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("CEDULA"),
                        rs.getDate("FECHA_INGRESO").toLocalDate(),
                        rs.getString("EMAIL"),
                        domicilio
                );
            }
        } catch (Exception e) {
            logger.error("Problemas con la BD: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Problemas al cerrar la conexión: " + e.getMessage());
            }
        }
        return paciente;
    }
}
