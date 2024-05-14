package com.papeleria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.papeleria.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Cliente findByEmail(String email);
}
