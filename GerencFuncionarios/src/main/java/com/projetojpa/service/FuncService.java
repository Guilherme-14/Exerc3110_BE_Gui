package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entities.Func;
import com.projetojpa.repository.FuncRepository;

@Service
public class FuncService {
	
private final FuncRepository funcRepository;
	
	@Autowired
	public FuncService(FuncRepository funcRepository) {
		this.funcRepository = funcRepository;
	}
	
	public List<Func> buscaTodosFunc(){
		return funcRepository.findAll();
	}
	
	public Func BuscaFuncId(Long id) {
		Optional <Func> Func = funcRepository.findById(id);
		return Func.orElse(null);
	}
	
	public Func salvaFunc (Func func) {
		return funcRepository.save(func);	
	}
	
	public Func alterarFunc (Long id, Func alterarF) {
		Optional <Func> existeFunc = funcRepository.findById(id);
		if (existeFunc.isPresent()) {
			alterarF.setId(id);
			return funcRepository.save(alterarF);
		}
		return null;
	}
	
	public boolean apagarFunc(Long id) {
		Optional <Func> existeFunc = funcRepository.findById(id);
		if (existeFunc.isPresent()) {
			funcRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
