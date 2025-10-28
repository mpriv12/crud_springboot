package com.demo.crudmixto.service;

import org.springframework.stereotype.Service;

import com.demo.crudmixto.entity.Empleado;
import com.demo.crudmixto.repository.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> buscarPorNombreOCargo(String nombre, String cargo) {
        return empleadoRepository.findByNombreContainingOrCargoContaining(nombre, cargo);
    }

    public boolean emailExiste(String email) {
        return empleadoRepository.findByEmail(email).isPresent();
    }
}

