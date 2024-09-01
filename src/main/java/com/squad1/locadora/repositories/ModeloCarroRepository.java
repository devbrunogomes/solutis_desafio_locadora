package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.carro.Categoria;
import com.squad1.locadora.entities.carro.ModeloCarro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeloCarroRepository extends JpaRepository<ModeloCarro, Long> {


    List<ModeloCarro> findByCategoria(Categoria categoria);
}
