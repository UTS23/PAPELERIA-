package com.papeleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.papeleria.entity.Cliente;
import com.papeleria.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Endpoint para crear un nuevo cliente
    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        // Verificar si el cliente ya está registrado
        if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
            return new ResponseEntity<>("El cliente ya está registrado", HttpStatus.BAD_REQUEST);
        }

        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente creado correctamente", HttpStatus.CREATED);
    }

    // Endpoint para obtener un cliente por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") String id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar la información de un cliente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable("id") String id, @RequestBody Cliente cliente) {
        // Verificar si el cliente existe
        if (!clienteRepository.existsById(id)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        // Actualizar la información del cliente
        cliente.setId(id); // Asegurarse de que el ID del cliente sea el mismo que el proporcionado en la URL
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente actualizado correctamente", HttpStatus.OK);
    }

    // Endpoint para eliminar un cliente por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") String id) {
        // Verificar si el cliente existe
        if (!clienteRepository.existsById(id)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }

        // Eliminar el cliente de la base de datos
        clienteRepository.deleteById(id);
        return new ResponseEntity<>("Cliente eliminado correctamente", HttpStatus.OK);
    }
}
