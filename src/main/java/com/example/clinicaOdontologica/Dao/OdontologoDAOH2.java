package com.example.clinicaOdontologica.Dao;
import com.example.clinicaOdontologica.Model.Odontologo;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger=Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS VALUES(?,?,?)";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de : guardado de: "+odontologo.getNombre());
        Connection connection=null;
        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();

            ResultSet rs= psInsert.getGeneratedKeys();
            while(rs.next()){
                odontologo.setId(rs.getInt(1));
            }
        }catch(Exception e){
            logger.error("problemas con la BD"+e.getMessage());
        }
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("iniciando las operaciones de listado");
        Connection connection=null;
        List<Odontologo> odontologos = new ArrayList<>();
        try{
            connection=BD.getConnection();
            Statement statement = connection.createStatement();
//            PreparedStatement psListarTodos = connection.prepareStatement(SQL_SELECT);
//            psListarTodos.setString();
            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String numeroMatricula = rs.getString("NUMERO_MATRICULA");
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                Odontologo odontologo = new Odontologo(id, numeroMatricula, nombre, apellido);
                odontologos.add(odontologo);
            }


        }catch(Exception e){
            logger.error("problemas al listar en la BD "+e.getMessage());
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarporId(Integer id) {
        return null;
    }
}
