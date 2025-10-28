package com.demo.crudmixto.controller;

import com.demo.crudmixto.entity.Empleado;
import com.demo.crudmixto.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoRestController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todos los empleados
    @GetMapping
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public Optional<Empleado> obtenerPorId(@PathVariable Long id) {
        return empleadoRepository.findById(id);
    }

    // Crear un nuevo empleado
    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Actualizar un empleado existente
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleadoActualizado) {
        return empleadoRepository.findById(id).map(empleado -> {
            empleado.setNombre(empleadoActualizado.getNombre());
            empleado.setEmail(empleadoActualizado.getEmail());
            empleado.setCargo(empleadoActualizado.getCargo());
            return empleadoRepository.save(empleado);
        }).orElseGet(() -> {
            empleadoActualizado.setId(id);
            return empleadoRepository.save(empleadoActualizado);
        });
    }

    // Eliminar un empleado por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoRepository.deleteById(id);
    }
}