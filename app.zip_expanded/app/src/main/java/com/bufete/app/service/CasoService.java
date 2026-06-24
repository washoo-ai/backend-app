package com.bufete.app.service;



import com.bufete.app.model.Caso;
import com.bufete.app.repository.CasoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CasoService {

    private final CasoRepository repository;

    public CasoService(CasoRepository repository) {
        this.repository = repository;
    }

    public List<Caso> listar() {
        return repository.findAll();
    }

    public Caso guardar(Caso caso) {
        return repository.save(caso);
    }

    public Caso buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    
    public Map<String, Long> contarPorEstado() {

        Map<String, Long> datos = new HashMap<>();

        datos.put("activos", repository.countByEstado("ACTIVO"));
        datos.put("proceso", repository.countByEstado("EN_PROCESO"));
        datos.put("finalizados", repository.countByEstado("FINALIZADO"));

        return datos;
    }
    
    public List<Caso> obtenerPorEstado(String estado) {
        return repository.findByEstado(estado);
    }
    
    public List<Caso> obtenerFechas() {
        return repository.findByFechaLimiteNotNull();
    }
}
    
