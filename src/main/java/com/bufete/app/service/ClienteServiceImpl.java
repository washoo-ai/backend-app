package com.bufete.app.service;

import com.bufete.app.dto.ClienteRequest;
import com.bufete.app.model.Cliente;
import com.bufete.app.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    public ClienteServiceImpl(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Cliente crearCliente(ClienteRequest request) {

        if (repo.existsByCedula(request.getCedula())) {
            throw new RuntimeException("La cédula ya existe");
        }

        Cliente c = new Cliente();
        c.setCedula(request.getCedula());
        c.setNombres(request.getNombres());
        c.setApellidos(request.getApellidos());
        c.setEdad(request.getEdad());
        c.setEstadoCivil(request.getEstadoCivil());
        c.setObservaciones(request.getObservaciones());
        c.setTelefono(request.getTelefono());
        

        return repo.save(c);
    }

    @Override
    public List<Cliente> listarClientes() {
        return repo.findAll();
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente actualizarCliente(Long id, ClienteRequest request) {

        Cliente c = obtenerCliente(id);
        System.out.println("TELEFONO QUE LLEGA: " + request.getTelefono());

        c.setCedula(request.getCedula());
        c.setNombres(request.getNombres());
        c.setApellidos(request.getApellidos());
        c.setEdad(request.getEdad());
        c.setEstadoCivil(request.getEstadoCivil());
        c.setObservaciones(request.getObservaciones());
        if (request.getTelefono() != null) {
            c.setTelefono(request.getTelefono());
        }

        return repo.save(c);
    }

    @Override
    public void eliminarCliente(Long id) {
        repo.deleteById(id);
    }
}
