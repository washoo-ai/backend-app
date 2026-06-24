package com.bufete.app.repository;



import com.bufete.app.model.Caso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Long> {

	  long countByEstado(String estado);
	  List<Caso> findByEstado(String estado);
	  List<Caso> findByFechaLimiteNotNull();
	
	
}
