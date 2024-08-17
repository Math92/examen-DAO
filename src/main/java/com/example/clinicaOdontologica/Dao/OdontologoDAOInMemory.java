package com.example.clinicaOdontologica.Dao;
import com.example.clinicaOdontologica.Model.Odontologo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OdontologoDAOInMemory implements iDao<Odontologo> {
    private final List<Odontologo> odontologos = new ArrayList<>();
    private Integer idCounter = 1; // Para asignar IDs únicos a los odontólogos

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        // Asignar un ID único al odontólogo
        odontologo.setId(idCounter++);
        odontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        // Buscar el odontólogo en la lista y actualizarlo
        for (int i = 0; i < odontologos.size(); i++) {
            if (odontologos.get(i).getId().equals(odontologo.getId())) {
                odontologos.set(i, odontologo);
                return;
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        // Buscar y eliminar el odontólogo por ID
        odontologos.removeIf(o -> o.getId().equals(id));
    }

    @Override
    public List<Odontologo> listarTodos() {
        return new ArrayList<>(odontologos);
    }

    @Override
    public Odontologo buscarporId(Integer id) {
        Optional<Odontologo> odontologo = odontologos.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
        return odontologo.orElse(null);
    }
}
