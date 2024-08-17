package com.example.clinicaOdontologica;

import com.example.clinicaOdontologica.Dao.BD;
import com.example.clinicaOdontologica.Model.Odontologo;
import com.example.clinicaOdontologica.Service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OdontologoServiceTest {
    private static final Logger logger = Logger.getLogger(OdontologoServiceTest.class);

    @Test
    public void pruebaGuardarOdontologo() {
        logger.info("Iniciando pruebaGuardarOdontologo...");
        BD.crearTablas();

        // Prueba con DAO H2
        OdontologoService odontologoServiceH2 = new OdontologoService(false);
        List<Odontologo> odontologosH2 = odontologoServiceH2.listarOdontologos();

        // Verifica que la lista no sea nula
        assertNotNull(odontologosH2, "La lista de odontólogos no debe ser nula");
        logger.info("Lista de odontólogos obtenida con DAO H2: " + odontologosH2);

        // Verifica que la lista no esté vacía
        assertFalse(odontologosH2.isEmpty(), "La lista de odontólogos no debe estar vacía");
        logger.info("La lista de odontólogos con DAO H2 no está vacía");

        // Muestra la lista en la salida
        System.out.println("Lista de odontólogos con DAO H2: " + odontologosH2);
    }

//    @Test
//    public void pruebaGuardarOdontologoInMemory() {
//        logger.info("Iniciando pruebaGuardarOdontologoInMemory...");
//
//        // Prueba con DAO en memoria
//        OdontologoService odontologoServiceInMemory = new OdontologoService(true);
//        Odontologo odontologo = new Odontologo(1, "12345", "Juan", "Gonzalez");
//        odontologoServiceInMemory.guardarOdontologo(odontologo);
//        List<Odontologo> odontologosInMemory = odontologoServiceInMemory.listarOdontologos();
//
//        // Verifica que la lista no sea nula
//        assertNotNull(odontologosInMemory, "La lista de odontólogos no debe ser nula");
//        logger.info("Lista de odontólogos obtenida con DAO en memoria: " + odontologosInMemory);
//
//        // Verifica que la lista no esté vacía
//        assertFalse(odontologosInMemory.isEmpty(), "La lista de odontólogos no debe estar vacía");
//        logger.info("La lista de odontólogos con DAO en memoria no está vacía");
//
//        // Muestra la lista en la salida
//        System.out.println("Lista de odontólogos con DAO en memoria: " + odontologosInMemory);
//    }
}
