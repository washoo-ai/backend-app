package com.bufete.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bufete.app.model.Caso;
import com.bufete.app.service.CasoService;
@RestController
@RequestMapping("/api/casos")
@CrossOrigin(origins = "http://localhost:4200")
public class CasoController {

    private final CasoService service;

    public CasoController(CasoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Caso> listar() {
        return service.listar();
    }

    @PostMapping
    public Caso guardar(@RequestBody Caso caso) {
        return service.guardar(caso);
    }

    @GetMapping("/resumen")
    public Map<String, Long> resumen() {
        return service.contarPorEstado();
    }
    
    @GetMapping("/estado/{estado}")
    public List<Caso> obtenerPorEstado(@PathVariable("estado") String estado) {
        return service.obtenerPorEstado(estado);
    }
   
    @GetMapping("/fechas")
    public List<Caso> obtenerFechas() {
        return service.obtenerFechas();
    }
   
    @GetMapping("/{id}")
    public Caso buscar(@PathVariable("id") Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public Caso actualizar(@PathVariable("id") Long id,
                           @RequestBody Caso caso) {

        Caso existente = service.buscar(id);

        if (existente == null) {
            return null; // o lanzar excepción
        }

        existente.setTitulo(caso.getTitulo());
        existente.setDescripcion(caso.getDescripcion());
        existente.setEstado(caso.getEstado());
        existente.setFechaInicio(caso.getFechaInicio());
        existente.setFechaLimite(caso.getFechaLimite());
        existente.setCliente(caso.getCliente());

        return service.guardar(existente);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }
}