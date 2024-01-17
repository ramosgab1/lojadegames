package com.generation.lojadegames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // Define entidade usada para criar uma tabela no banco de dados. 
@Table(name = "tb_produto") // Indica o nome da tabela no banco de dados. 
public class Produto {
	
	// Aqui dentro criamos nossos atributos, no caso da tabela "PRODUTO": 
	// id BIGINT, nome VARCHAR(255), descricao TEXT(500), console VARCHAR(255)
	// quantidade INT, preco DECIMAL(8,2), foto VARCHAR(5000), categoria_id BIGINT
	// usuarios_id BIGINT 
	
	 
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id; 
	
	// @Id --> Indica Chave Primária (Primary Key) da tabela criada. 
	// @Generated Value --> indica que o banco de dados vai gerar a chave primária e 
	// a estratégia que a chave será gerada através do auto incremento.

	@NotBlank(message="O atributo 'nome' é obrigatório!")
	@Size(max = 100, message = "Deve conter no máximo 100 caracteres.")
	private String nome; 
	
	// @NotBlank --> Não permite que seja nulo ou contenha espaços em branco. 
	// @Size --> Define valores minimos e máximos. 
	
	@Size(max = 1000, message = "A descrição deve conter no máximo 1000 caracteres.")
	private String descricao; 
	
	@Size (max = 100, message = "O nome do console deve conter no máximo 100 caracteres.")
	private String console; 
	
	@Min(value = 0, message = "A quantidade deve ser um número positivo.")
	private int quantidade;
	
	@DecimalMin(value = "0.00", inclusive = false, message = "O preço deve ser maior que zero.")
	private BigDecimal preco;
	
	@Size (max = 5000, message = "O link da foto não pode exceder 5000 caracteres.")
	private String foto;
	
	@Size(max = 50, message = "A classificação indicativa deve conter no máximo 50 caracteres.")
	private String classificacao;
	
	// RELAÇÃO MANY TO ONE 
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria; 
	
	// GERANDO GETTERS E SETTERS.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	// Getters e setters da categoria many to one!!
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	} 
	
	
	
	
}
