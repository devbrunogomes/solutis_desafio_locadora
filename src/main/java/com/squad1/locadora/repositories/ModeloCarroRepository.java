package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.carro.ModeloCarro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {

}
