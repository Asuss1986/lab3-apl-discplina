package ufpb.disciplina.api.lap3.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	 @Id
	    private String email;
	    private String nome;
	    private String senha;

	    public Usuario() {
	        super();
	    }

	    public Usuario(String email, String nome, String senha) {
	        this.email = email;
	        this.nome = nome;
	        this.senha = senha;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public boolean isValid() {
	        return !email.isBlank() && !nome.isBlank() && !senha.isBlank();
	    }
}
