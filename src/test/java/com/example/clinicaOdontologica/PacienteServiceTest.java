package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.Dao.BD;
import com.example.clinicaOdontologica.Model.Paciente;
import com.example.clinicaOdontologica.Service.PacienteService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacienteServiceTest {
    private static final Logger logger = Logger.getLogger(PacienteServiceTest.class);

    @Test
    public void pruebaBusquedaPaciente() {
        logger.info("Iniciando pruebaBusquedaPaciente...");
        BD.crearTablas();
        PacienteService pacienteService = new PacienteService();
        Integer idABuscar = 2;
        Paciente paciente = pacienteService.buscarPorID(idABuscar);

        // Verifica que el paciente no sea nulo
        Assertions.assertNotNull(paciente, "El paciente no debe ser nulo");
        logger.info("Paciente encontrado: " + paciente);

        // Verifica que el apellido del paciente sea el esperado
        Assertions.assertTrue(paciente.getApellido().equals("Vasquez"), "El apellido del paciente debería ser 'Vasquez'");
        logger.info("El apellido del paciente es el esperado: Vasquez");

        // Muestra la información del paciente en la salida
        System.out.println("Paciente encontrado: " + paciente);
    }
}
