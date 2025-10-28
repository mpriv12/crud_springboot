package com.demo.crudmixto.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "proyectos")
public class Proyecto {

    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private Long empleadoId;

    // Lista de tareas como Map<String,String> { "titulo": "...", "estado": "..." }
    private List<Map<String, String>> tareas;

    public Proyecto() {}

    public Proyecto(String nombre, String descripcion, Long empleadoId, List<Map<String, String>> tareas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.empleadoId = empleadoId;
        this.tareas = tareas;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Long empleadoId) { this.empleadoId = empleadoId; }
    public List<Map<String, String>> getTareas() { return tareas; }
    public void setTareas(List<Map<String, String>> tareas) { this.tareas = tareas; }
}
