package com.demo.crudmixto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.demo.crudmixto.entity.Empleado;
import com.demo.crudmixto.entity.Proyecto;
import com.demo.crudmixto.service.EmpleadoService;
import com.demo.crudmixto.service.ProyectoService;

import java.util.List;

@Controller
@RequestMapping("/proyectos")
public class ProyectoViewController {

    private final ProyectoService proyectoService;
    private final EmpleadoService empleadoService;

    public ProyectoViewController(ProyectoService proyectoService, EmpleadoService empleadoService) {
        this.proyectoService = proyectoService;
        this.empleadoService = empleadoService;
    }

    // ✅ Listar proyectos
    @GetMapping
    public String listar(Model model) {
        List<Proyecto> proyectos = proyectoService.listarTodos();
        model.addAttribute("proyectos", proyectos);
        return "proyectos/list";
    }

    // ✅ Mostrar formulario nuevo proyecto
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("empleados", empleadoService.listarTodos()); // Para elegir el empleado asignado
        return "proyectos/nuevo";
    }

    // ✅ Guardar proyecto nuevo
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto) {
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos";
    }

    // ✅ Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable String id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id).orElseThrow();
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("empleados", empleadoService.listarTodos());
        return "proyectos/editar";
    }

    // ✅ Actualizar proyecto existente
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable String id, @ModelAttribute Proyecto proyecto) {
        proyecto.setId(id);
        proyectoService.guardar(proyecto);
        return "redirect:/proyectos";
    }

    // ✅ Eliminar proyecto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        proyectoService.eliminar(id);
        return "redirect:/proyectos";
    }

    // ✅ Detalle del proyecto
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id).orElseThrow();

        Long empleadoId = proyecto.getEmpleadoId();
        Empleado empleado = null;
        if (empleadoId != null) {
            empleado = empleadoService.buscarPorId(empleadoId).orElse(null);
        }

        model.addAttribute("proyecto", proyecto);
        model.addAttribute("empleado", empleado);
        return "proyectos/detalle";
    }
}
