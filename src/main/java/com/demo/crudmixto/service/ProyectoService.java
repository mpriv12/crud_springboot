package com.demo.crudmixto.service;

import org.springframework.stereotype.Service;

import com.demo.crudmixto.entity.Proyecto;
import com.demo.crudmixto.repository.ProyectoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> listarTodos() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> buscarPorId(String id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public void eliminar(String id) {
        proyectoRepository.deleteById(id);
    }

    public List<Proyecto> buscarPorEmpleadoId(Long empleadoId) {
        return proyectoRepository.findByEmpleadoId(empleadoId);
    }
}

