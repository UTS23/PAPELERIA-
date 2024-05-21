package com.papeleria.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.papeleria.app.entidades.Producto;
import com.papeleria.app.service.ProductoService;


@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
	
	
	@Autowired
    private ProductoService productoService;
	
	
	
	@GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable String id) {
        return productoService.obtenerProductoPorId(id);
    }
}
