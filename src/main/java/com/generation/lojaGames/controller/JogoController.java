package com.generation.lojaGames.controller;

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

import com.generation.lojaGames.model.Jogo;
import com.generation.lojaGames.repository.CategoriaRepository;
import com.generation.lojaGames.repository.JogosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogoController {
	
	@Autowired
	private JogosRepository jogosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Jogo>> getAll(){
		return ResponseEntity.ok(jogosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogo> getById(@PathVariable Long id){
		return jogosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Jogo>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(jogosRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Jogo> post(@Valid @RequestBody Jogo jogo){
		if(categoriaRepository.existsById(jogo.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(jogosRepository.save(jogo));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe.", null);
	}
	
	@PutMapping
	public ResponseEntity<Jogo> put(@Valid @RequestBody Jogo jogo){
		if(jogosRepository.existsById(jogo.getId())) {
			if(categoriaRepository.existsById(jogo.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(jogosRepository.save(jogo));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe.", null); 
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Jogo> jogo = jogosRepository.findById(id);
		
		if(jogo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		jogosRepository.deleteById(id);
	}

}
