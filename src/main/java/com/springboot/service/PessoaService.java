package com.springboot.service;

import com.springboot.model.Pessoa;
import java.util.List;

public interface PessoaService {
	
	Pessoa findById(Long id);

	Pessoa findByNome(String nome);

	void savePessoa(Pessoa pessoa);

	void updatePessoa(Pessoa pessoa);

	void deletePessoaById(Long id);

	void deleteAllPessoa();

	List<Pessoa> findAllPessoa();

	boolean isPessoaExist(Pessoa pessoa);
}