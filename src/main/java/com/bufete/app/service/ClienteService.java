package com.bufete.app.service;

import com.bufete.app.dto.ClienteRequest;
import com.bufete.app.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente crearCliente(ClienteRequest request);
    List<Cliente> listarClientes();
    Cliente obtenerCliente(Long id);
    Cliente actualizarCliente(Long id, ClienteRequest request);
    void eliminarCliente(Long id);
}
