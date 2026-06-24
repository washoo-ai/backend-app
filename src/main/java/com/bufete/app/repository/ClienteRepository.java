package com.bufete.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bufete.app.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCedula(String cedula);
}
