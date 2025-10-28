package com.demo.crudmixto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.demo.crudmixto.entity.Empleado;
import com.demo.crudmixto.entity.Proyecto;
import com.demo.crudmixto.service.EmpleadoService;
import com.demo.crudmixto.service.ProyectoService;
import com.demo.crudmixto.export.EmpleadoPDFExporter; // 👈 importante: importar la clase exporter
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final ProyectoService proyectoService;

    public EmpleadoController(EmpleadoService empleadoService, ProyectoService proyectoService) {
        this.empleadoService = empleadoService;
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.listarTodos());
        return "empleados/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Empleado empleado) {
        return "empleados/nuevo";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated Empleado empleado, BindingResult result) {
        if (result.hasErrors()) {
            return "empleados/nuevo";
        }
        if (empleadoService.emailExiste(empleado.getEmail())) {
            result.rejectValue("email", "error.empleado", "El email ya existe");
            return "empleados/nuevo";
        }
        empleadoService.guardar(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id).orElseThrow();
        model.addAttribute("empleado", empleado);
        return "empleados/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @Validated Empleado empleado, BindingResult result) {
        if (result.hasErrors()) {
            return "empleados/editar";
        }
        empleado.setId(id);
        empleadoService.guardar(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
        return "redirect:/empleados";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoService.buscarPorId(id).orElseThrow();
        List<Proyecto> proyectos = proyectoService.buscarPorEmpleadoId(id);
        model.addAttribute("empleado", empleado);
        model.addAttribute("proyectos", proyectos);
        return "empleados/detalle";
    }

    // ✅ MÉTODO CORREGIDO PARA EXPORTAR PDF
    @GetMapping("/exportarPDF")
    public void exportarListaEmpleadosPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=empleados.pdf";
        response.setHeader(headerKey, headerValue);

        // ✅ Obtener la lista de empleados
        List<Empleado> listaEmpleados = empleadoService.listarTodos();

        // ✅ Crear el exporter con la lista y exportar
        EmpleadoPDFExporter exporter = new EmpleadoPDFExporter(listaEmpleados);
        exporter.exportar(response);
    }
}
