package com.example.clinicaOdontologica.Controller;
import com.example.clinicaOdontologica.Model.Paciente;
import com.example.clinicaOdontologica.Service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteControllerRest {

    private final PacienteService pacienteService;

    public PacienteControllerRest(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Buscar paciente por id
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorID(id);
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar paciente por id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPacientePorId(@PathVariable Integer id) {
        Paciente paciente = pacienteService.buscarPorID(id);
        if (paciente != null) {
            pacienteService.eliminarPaciente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Listar todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodosLosPacientes() {
        List<Paciente> pacientes = pacienteService.listarTodosLosPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }
}

