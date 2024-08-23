package com.example.clinicaOdontologica.Controller;

import com.example.clinicaOdontologica.Model.Odontologo;
import com.example.clinicaOdontologica.Model.Paciente;
import com.example.clinicaOdontologica.Model.Turno;
import com.example.clinicaOdontologica.Service.OdontologoService;
import com.example.clinicaOdontologica.Service.PacienteService;
import com.example.clinicaOdontologica.Service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService= new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

//    // Constructor con inyección de dependencias
//    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
//        this.turnoService = turnoService;
//        this.pacienteService = pacienteService;
//        this.odontologoService = odontologoService;
//    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorID(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno)); //si el retorno es correcto , seria un 200
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos(){
        return ResponseEntity.ok(turnoService.listarTurnos());
    }
}
