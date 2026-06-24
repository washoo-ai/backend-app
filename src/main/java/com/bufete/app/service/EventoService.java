package com.bufete.app.service;

import com.bufete.app.model.Evento;
import com.bufete.app.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    // LISTAR
    public List<Evento> listar() {
        return repository.findAll();
    }

    // GUARDAR
    public Evento guardar(Evento evento) {
        return repository.save(evento);
    }

    // OBTENER
    public Evento obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    // POR FECHA
    public List<Evento> obtenerPorFecha(LocalDate fecha) {
        return repository.findByFecha(fecha);
    }

}