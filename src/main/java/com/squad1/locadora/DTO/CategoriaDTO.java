package com.squad1.locadora.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squad1.locadora.entities.carro.Categoria;
import com.squad1.locadora.entities.carro.Fabricante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoriaDTO {
    @JsonProperty("Descrição")
    private String descricao;

    @JsonProperty("Nome do Fabricante")
    private Fabricante nomeFabricante;

    @JsonProperty("Categoria")
    private Categoria categoria;

    @JsonProperty("Id do Fabricante")
    private Long id;


    public CategoriaDTO(Long id, String descricao, Fabricante nomeFabricante, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.nomeFabricante = nomeFabricante;
        this.categoria = categoria;
    }
}
