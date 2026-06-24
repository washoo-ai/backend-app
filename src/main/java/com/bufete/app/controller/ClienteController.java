package com.bufete.app.controller;

import com.bufete.app.dto.ClienteRequest;
import com.bufete.app.model.Cliente;
import com.bufete.app.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")

@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public Cliente crear(@RequestBody ClienteRequest request) {
        return service.crearCliente(request);
    }

    @GetMapping
    public List<Cliente> listar() {
        return service.listarClientes();
    }
    
  
  

    @GetMapping("/{id}")
    public Cliente obtener(@PathVariable("id") Long id) {
        return service.obtenerCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable("id") Long id,
                              @RequestBody ClienteRequest request) {
    	 
    	    System.out.println("TELEFONO: " + request.getTelefono());
    	    
    	
        return service.actualizarCliente(id, request);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminarCliente(id);
    }
}
