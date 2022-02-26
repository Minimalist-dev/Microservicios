package com.example.Sistema.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.example.Sistema.modelo.GetAndSet;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface SistemaRepositorio extends CrudRepository<GetAndSet, Integer> {

}