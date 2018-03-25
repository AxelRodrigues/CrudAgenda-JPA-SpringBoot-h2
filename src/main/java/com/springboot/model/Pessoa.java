package com.springboot.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_PESSOA")
public class Pessoa implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NOME", nullable=false)
	private String nome;

	@Column(name="EMAIL", nullable=false)
	private String email;

	@Column(name="TELEFONE", nullable=false)
	private String telefone;

	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pessoa pessoa = (Pessoa) o;

		if (id != null ? !id.equals(pessoa.id) : pessoa.id != null) return false;
		if (nome != null ? !nome.equals(pessoa.nome) : pessoa.nome != null) return false;
		return email != null ? email.equals(pessoa.email) : pessoa.email == null;
	}



}
