package com.bufete.app.controller;

import com.bufete.app.model.Evento;
import com.bufete.app.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    // LISTAR
    @GetMapping
    public List<Evento> listar() {
        return service.listar();
    }

    // GUARDAR
    @PostMapping
    public Evento guardar(@RequestBody Evento evento) {
        return service.guardar(evento);
    }

    // OBTENER
    @GetMapping("/{id}")
    public Evento obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable("id") Long id) {

        service.eliminar(id);

    }

    // EVENTOS POR FECHA
    @GetMapping("/fecha/{fecha}")
    public List<Evento> porFecha(
            @PathVariable LocalDate fecha
    ) {
        return service.obtenerPorFecha(fecha);
    }

}