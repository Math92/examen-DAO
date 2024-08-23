package com.example.clinicaOdontologica.Service;
//import com.example.clinicaOdontologica.Dao.OdontologoDAOInMemory;
import com.example.clinicaOdontologica.Dao.OdontologoDAOH2;
import com.example.clinicaOdontologica.Dao.iDao;
import com.example.clinicaOdontologica.Model.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService {
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
       // odontologoiDao = new OdontologoDAOInMemory(); // Usa DAO en memoria
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        logger.info("Intentando guardar odontólogo: " + odontologo);
        Odontologo result = odontologoiDao.guardar(odontologo);
        logger.info("Odontólogo guardado con éxito: " + result);
        return result;
    }

    public void actualizarOdontologo(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }

    public Odontologo buscarPorID(Integer id){
        logger.info("Buscando odontologo con ID: " + id);
        Odontologo result = odontologoiDao.buscarporId(id);
        if (result != null) {
            logger.info("odontologo encontrado: " + result);
        } else {
            logger.warn("odontologo con ID " + id + " no encontrado");
        }
        return result;
    }

    public List<Odontologo> listarOdontologos() {
        logger.info("Listando todos los odontólogos");
        List<Odontologo> result = odontologoiDao.listarTodos();
        logger.info("Número de odontólogos encontrados: " + result.size());
        return result;
    }
}
