package com.example.Aula.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Aula.entidades.Local;
import com.example.Aula.repositories.LocalRepository;

@RestController
@RequestMapping("/local") /*http://localhost:8080*/
public class LocalController {
	List<Local> local = new ArrayList<>();
	
	@Autowired
	LocalRepository repo;
	
	@PostMapping
	public ResponseEntity<Local> salvar(@RequestBody Local local) {
		repo.save(local);
		return ResponseEntity.status(HttpStatus.CREATED).body(local);
	}
	
	@PutMapping("/{idlocal}")
	public ResponseEntity<Object> alterar(@PathVariable("idlocal") Long idlocal,
			@RequestBody Local local) {
		
		Optional<Local> opt = repo.findById(idlocal);
		try {
		    Local ct = opt.get();
		    ct.setNome(local.getNome());
		    ct.setRua(local.getRua());
			ct.setNumero(local.getNumero());
			ct.setBairro(local.getBairro());
			ct.setCidade(local.getCidade());
			ct.setEstado(local.getEstado());
			ct.setCep(local.getCep());
		    repo.save(ct);
		    return ResponseEntity.status(HttpStatus.OK).body(ct);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado");
		}	
	}
	
	@DeleteMapping("/{idlocal}")
	public ResponseEntity<Object> excluir(@PathVariable("idlocal") Long idlocal) {
		Optional<Local> opt = repo.findById(idlocal);
		try {
		    Local ct = opt.get();
		    repo.delete(ct);
		    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado");
		}	
	}
	
	
	@GetMapping
	public ResponseEntity<List<Local>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@GetMapping("/{idlocal}")
	public ResponseEntity<Object> consultar(@PathVariable("idlocal") Long idlocal) {
		
		Optional<Local> opt = repo.findById(idlocal);
		try {
		    Local ct = opt.get();
		    return ResponseEntity.status(HttpStatus.OK).body(ct);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Local não encontrado");
		}				
	}
}