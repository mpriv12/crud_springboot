package com.demo.crudmixto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.crudmixto.entity.Proyecto;

import java.util.List;

@Repository
public interface ProyectoRepository extends MongoRepository<Proyecto, String> {
    List<Proyecto> findByEmpleadoId(Long empleadoId);
}
