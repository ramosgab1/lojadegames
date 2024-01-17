package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.CategoriaRepository;
import com.generation.lojadegames.repository.ProdutoRepository;

import jakarta.validation.Valid;

/** AQUI SOU EU REVISANDO O ASSUNTO JACQUE E LIZA, PODE PULAR!! 
* 
* @ RestController eﬁne que a Classe é do tipo RestController, que receberá 
* requisições que serão compostas por: URL: Endereço da requisição (endpoint)
* Verbo: Define qual Método HTTP será acionado na Classe controladora.
* Corpo da requisição (Request Body): Objeto que contém os dados que serão 
* persistidos no Banco de dadas. Nem toda a requisição enviará dados no Corpo da Requisição.
*
* @ RequestMapping é usada para mapear as solicitações para os Métodos da Classe 
* controladora PostagemController. Define a URL (endereço) padrão do Recurso (/postagens).
*
* @ CrossOrigin indica que a Classe controladora permitirá o recebimento de requisições 
* realizadas de fora do domínio (localhost e futuramente da nuvem quando o Deploy da 
* aplicação for efetivado) ao qual ela pertence.
*
**/

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class ProdutoController {
	
	@Autowired // INJEÇÃO DE DEPENDÊNCIA!! -- Estudar outra vez que fiquei confusa. 
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
// Método getAll(). 

@GetMapping // procurar todos 
public ResponseEntity<List<Produto>> getAll(){
	return ResponseEntity.ok(produtoRepository.findAll()); 
}

@GetMapping("/{id}") // procurar todos por id 
public ResponseEntity<Produto> getByID(@PathVariable Long id){
    return produtoRepository.findById(id)
            .map(resp -> ResponseEntity.ok(resp))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());    
}

@GetMapping("/nome/{nome}") // procurar todos por nome
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
@PostMapping // método post. 
public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
	if (categoriaRepository.existsById(produto.getCategoria().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(produtoRepository.save(produto));
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
}

@PutMapping // atualizar produto.
public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
	if (produtoRepository.existsById(produto.getId())) {
		if (categoriaRepository.existsById(produto.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.OK)
					.body(produtoRepository.save(produto)); 
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null); 
	}
return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}

@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}") // deletar produto
public void delete(@PathVariable Long id) {
	Optional<Produto> produto = produtoRepository.findById(id); 
	
	if(produto.isEmpty())
		throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
	
	produtoRepository.deleteById(id);
	}
}