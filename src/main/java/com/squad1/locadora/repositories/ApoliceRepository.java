package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.aluguel.ApoliceSeguro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApoliceRepository extends JpaRepository<ApoliceSeguro, Long> {

}