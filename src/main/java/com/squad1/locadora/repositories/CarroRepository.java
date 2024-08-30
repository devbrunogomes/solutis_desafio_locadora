package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.carro.Carro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    
    //Abaixo está comentado pois está quebrando o pathing "carros"
//    @EntityGraph(attributePaths = {"modeloCarro", "modeloCarro.nomeFabricante", "acessorios", "aluguel"})
    @Override
    List<Carro> findAll();
    
    @EntityGraph(attributePaths = {"acessorios", "modeloCarro", "aluguel"})
    @Override
    Optional<Carro> findById(Long id);
    
    @Query("SELECT c FROM Carro c LEFT JOIN FETCH c.acessorios LEFT JOIN FETCH c.modeloCarro WHERE c.id = :id")
    Optional<Carro> findByIdWithAcessorios(@Param("id") Long id);

}
