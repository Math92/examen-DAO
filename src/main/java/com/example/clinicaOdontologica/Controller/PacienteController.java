package com.example.clinicaOdontologica.Controller;

import com.example.clinicaOdontologica.Model.Paciente;
import com.example.clinicaOdontologica.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Endpoint para listar todos los pacientes
    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.listarTodosLosPacientes();
        model.addAttribute("pacientes", pacientes);
        return "pacientes"; // Nombre de la vista Thymeleaf para listar pacientes
    }

    // Endpoint para buscar un paciente por email
    @GetMapping("/paciente/email")
    public String buscarPacientePorEmail(@RequestParam("email") String email, Model model) {
        Paciente paciente = pacienteService.buscarPorEmail(email);
        if (paciente != null) {
            model.addAttribute("paciente", paciente);
        } else {
            model.addAttribute("mensaje", "Paciente no encontrado");
        }
        return "paciente"; // Nombre de la vista Thymeleaf para mostrar el paciente
    }
}
