package com.demo.crudmixto.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String cargo;
    private BigDecimal salario;

    @Column(unique = true)
    private String email;

    public Empleado() { }

    public Empleado(String nombre, String cargo, BigDecimal salario, String email) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
