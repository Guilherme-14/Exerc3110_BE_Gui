package com.projetojpa.controller;

import java.util.List;

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

import com.projetojpa.entities.Func;
import com.projetojpa.service.FuncService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Funcionarios" , description = "API REST DE GERENCIAMENTO DE FUNCIONÁRIOS")
@RestController
@RequestMapping("/funcionarios")
public class FuncController {
	
	private final FuncService funcService;

	@Autowired
	public FuncController (FuncService funcService) {
		this.funcService = funcService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza o funcionário por ID")
	public ResponseEntity<Func> buscaFuncControlId(@PathVariable Long id){
		Func func = funcService.BuscaFuncId(id);
		if (func != null) {
			return ResponseEntity.ok(func);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os Funcionarios")
	public ResponseEntity<List<Func>> buscaTodosFuncCOntrol(){
		List<Func> Func = funcService.buscaTodosFunc();
		return ResponseEntity.ok(Func);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um funcionario")
	public ResponseEntity<Func> salvaFuncControl (@RequestBody @Valid Func func){
		Func salvaFunc = funcService.salvaFunc(func);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaFunc);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Altera os funcionarios")
	public ResponseEntity<Func> alterarFuncControl(@PathVariable Long id, @RequestBody @Valid Func func){
		Func alterarFunc = funcService.alterarFunc(id, func);
		if(alterarFunc != null) {
			return ResponseEntity.ok(func);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui os funcionarios")
	public ResponseEntity<Func> apagaFuncControl (@PathVariable Long id){
		boolean apagar = funcService.apagarFunc(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
