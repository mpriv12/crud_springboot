package com.demo.crudmixto.controller;

import org.springframework.web.bind.annotation.*;

import com.demo.crudmixto.entity.Proyecto;
import com.demo.crudmixto.service.ProyectoService;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public List<Proyecto> listar() {
        return proyectoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Proyecto buscarPorId(@PathVariable String id) {
        return proyectoService.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public Proyecto guardar(@RequestBody Proyecto proyecto) {
        return proyectoService.guardar(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable String id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        return proyectoService.guardar(proyecto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        proyectoService.eliminar(id);
    }

    @GetMapping("/empleado/{empleadoId}")
    public List<Proyecto> proyectosPorEmpleado(@PathVariable Long empleadoId) {
        return proyectoService.buscarPorEmpleadoId(empleadoId);
    }
}


