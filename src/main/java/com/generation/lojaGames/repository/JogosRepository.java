package com.generation.lojaGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.lojaGames.model.Jogo;

public interface JogosRepository extends JpaRepository<Jogo, Long>{
	public List<Jogo> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
