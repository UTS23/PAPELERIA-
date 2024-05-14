package com.papeleria.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.papeleria.entity.Admin;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByEmail(String email);
}
