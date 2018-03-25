package com.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.model.Pessoa;
import com.springboot.service.PessoaService;
import com.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	PessoaService pessoaService;
	
	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/pessoa/", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listAllPessoa() {
		
		List<Pessoa> pessoa = pessoaService.findAllPessoa();
		if (pessoa.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<List<Pessoa>>(pessoa, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPessoa(@PathVariable("id") long id) {
		logger.info("Fetching Pessoa with id {}", id);
		Pessoa pessoa = pessoaService.findById(id);
		if (pessoa == null) {
			logger.error("Pessoa with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Pessoa with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/pessoa/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Pessoa : {}", pessoa);

		if (pessoaService.isPessoaExist(pessoa)) {
			logger.error("Unable to create. A Pessoa with name {} already exist", pessoa.getNome());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Pessoa with nome " + 
					pessoa.getNome() + " already exist."),HttpStatus.CONFLICT);
		}
		
		pessoaService.savePessoa(pessoa);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePessoa(@PathVariable("id") long id, @RequestBody Pessoa pessoa) {
		logger.info("Updating Pessoa with id {}", id);

		Pessoa currentPessoa = pessoaService.findById(id);

		if (currentPessoa == null) {
			logger.error("Unable to update. Pessoa with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Pessoa with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentPessoa.setNome(pessoa.getNome());
		currentPessoa.setEmail(pessoa.getEmail());
		currentPessoa.setTelefone(pessoa.getTelefone());

		pessoaService.updatePessoa(currentPessoa);
		return new ResponseEntity<Pessoa>(currentPessoa, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePessoa(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Pessoa with id {}", id);

		Pessoa pessoa = pessoaService.findById(id);
		if (pessoa == null) {
			logger.error("Unable to delete. Pessoa with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Pessoa with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		pessoaService.deletePessoaById(id);
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/pessoa/", method = RequestMethod.DELETE)
	public ResponseEntity<Pessoa> deleteAllPessoa() {
		logger.info("Deleting All Pessoa");

		pessoaService.deleteAllPessoa();
		return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
	}

}