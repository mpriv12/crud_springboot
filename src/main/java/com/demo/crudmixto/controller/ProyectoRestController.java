package com.demo.crudmixto.controller;

import com.demo.crudmixto.entity.Proyecto;
import com.demo.crudmixto.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/proyectos")
public class ProyectoRestController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @GetMapping
    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> obtenerPorId(@PathVariable String id) {
        return proyectoRepository.findById(id);
    }

    @PostMapping
    public Proyecto crear(@RequestBody Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable String id, @RequestBody Proyecto proyectoActualizado) {
        return proyectoRepository.findById(id).map(p -> {
            p.setNombre(proyectoActualizado.getNombre());
            p.setDescripcion(proyectoActualizado.getDescripcion());
            return proyectoRepository.save(p);
        }).orElseGet(() -> {
            proyectoActualizado.setId(id);
            return proyectoRepository.save(proyectoActualizado);
        });
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        proyectoRepository.deleteById(id);
    }
}
