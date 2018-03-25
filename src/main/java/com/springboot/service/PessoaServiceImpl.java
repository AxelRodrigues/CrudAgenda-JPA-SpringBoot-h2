package com.springboot.service;

import java.util.List;
import com.springboot.model.Pessoa;
import com.springboot.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("pessoaService")
@Transactional
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa findById(Long id) {
		return pessoaRepository.findOne(id);
	}

	public Pessoa findByName(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public void savePessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	public void updatePessoa(Pessoa pessoa){
		savePessoa(pessoa);
	}

	public void deletePessoaById(Long id){
		pessoaRepository.delete(id);
	}

	public void deleteAllPessoa(){
		pessoaRepository.deleteAll();
	}

	public List<Pessoa> findAllPessoa(){
		return pessoaRepository.findAll();
	}

	public boolean isUserExist(Pessoa pessoa) {
		return findByNome(pessoa.getNome()) != null;
	}

	@Override
	public Pessoa findByNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPessoaExist(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return false;
	}

}
