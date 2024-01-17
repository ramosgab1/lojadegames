package com.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojadegames.model.Produto;

// Aqui em baixo estamos fazendo herança com a interface JPA Repository que recebe
// nossa classe Postagem que está no package model e Long que representa nossa chave 
// primária (recebeu a notação @Id na classe Produto). 

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findAll(); 
	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

// CRIAÇÃO DE MÉTODOS --> existem métodos padrão mas podemos criar métodos personalizados. 
	


}
