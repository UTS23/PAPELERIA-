package com.papeleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.papeleria.entity.Admin;
import com.papeleria.entity.Cliente;
import com.papeleria.repository.AdminRepository;
import com.papeleria.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/crear")
    public ResponseEntity<String> crearAdmin(@RequestBody Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            return new ResponseEntity<>("El correo electrónico ya está en uso", HttpStatus.BAD_REQUEST);
        }
        adminRepository.save(admin);
        return new ResponseEntity<>("Administrador creado correctamente", HttpStatus.CREATED);
    }
    
  
    
    

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        Admin existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registrar-cliente")
    public ResponseEntity<String> registrarCliente(@RequestBody Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            return new ResponseEntity<>("El cliente ya está registrado", HttpStatus.BAD_REQUEST);
        }
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente registrado correctamente", HttpStatus.CREATED);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable("id") String id, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente actualizado correctamente", HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") String id) {
        if (!clienteRepository.existsById(id)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
        clienteRepository.deleteById(id);
        return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
    }
}

