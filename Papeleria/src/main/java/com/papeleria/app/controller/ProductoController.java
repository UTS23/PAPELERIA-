package com.papeleria.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.papeleria.app.entidades.Producto;
import com.papeleria.app.service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
    private ProductoService productoService;		

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);
        return "productos";
    }
}
