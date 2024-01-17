package com.generation.lojaGames.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogos")
public class Jogo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O título é obrigatório.")
	@Size(min = 5, max = 100, message = "O título deve conter entre 5 e 100 caracteres.")
	private String nome;
	
	@NotBlank(message = "A descrição é obrigatória.")
	@Size(min = 10, max = 1000, message = "A descrição deve conter entre 10 e 1000 caracteres.")
	private String descricao;
	
	@NotBlank(message = "O desenvolvedor é obrigatório.")
	private String desenvolvedor;
	
	@UpdateTimestamp
	private LocalDateTime dataLancamento;
	
	@ManyToOne
	@JsonIgnoreProperties("jogo")
	private Categoria categoria;
	
	// ----------------------------------------------------------------------
	// GETTERS e SETTERS
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

	public String getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public LocalDateTime getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDateTime dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
