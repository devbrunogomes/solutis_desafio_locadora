
package com.squad1.locadora.repositories;

import com.squad1.locadora.entities.carro.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarroRepository extends JpaRepository<Carro, Long> {

}
