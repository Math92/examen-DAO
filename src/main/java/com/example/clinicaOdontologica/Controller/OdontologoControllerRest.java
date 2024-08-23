package com.example.clinicaOdontologica.Controller;

import com.example.clinicaOdontologica.Model.Odontologo;
import com.example.clinicaOdontologica.Service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class OdontologoControllerRest {
    private OdontologoService odontologoService;

    public void OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @PutMapping
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoService.actualizarOdontologo(odontologo);
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }
    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){
        return odontologoService.buscarPorID(id);
    }
}
