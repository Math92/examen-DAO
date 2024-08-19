package com.example.clinicaOdontologica.Service;
import com.example.clinicaOdontologica.Dao.PacienteDAOH2;
import com.example.clinicaOdontologica.Dao.iDao;
import com.example.clinicaOdontologica.Model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PacienteService {
    private static final Logger logger = Logger.getLogger(PacienteService.class);
    private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao = new PacienteDAOH2();
    }

    public Paciente guardarPaciente(Paciente paciente){
        logger.info("Intentando guardar paciente: " + paciente);
        Paciente result = pacienteiDao.guardar(paciente);
        logger.info("Paciente guardado con éxito: " + result);
        return result;
    }

    public Paciente buscarPorID(Integer id){
        logger.info("Buscando paciente con ID: " + id);
        Paciente result = pacienteiDao.buscarporId(id);
        if (result != null) {
            logger.info("Paciente encontrado: " + result);
        } else {
            logger.warn("Paciente con ID " + id + " no encontrado");
        }
        return result;
    }

    public List<Paciente> listarTodosLosPacientes() {
        logger.info("Listando todos los pacientes");
        return pacienteiDao.listarTodos();
    }

    public Paciente buscarPorEmail(String email) {
        logger.info("Buscando paciente con email: " + email);
        return ((PacienteDAOH2) pacienteiDao).buscarPorEmail(email);
    }
}
