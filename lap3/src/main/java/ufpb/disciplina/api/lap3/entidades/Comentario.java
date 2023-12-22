package ufpb.disciplina.api.lap3.entidades;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Comentario implements Serializable{
	 private static final long serialVersionUID = 1265022599564907145L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    
		@NotNull
	    private String texto;

	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name="disciplina_id")
	    private Disciplina disciplina;

	    public Comentario(Disciplina disciplina, String texto){
	        super();
	    }

	    public Comentario(String texto) {
	        this.texto = texto;
	    }

	    public Comentario() {
	        super();
	    }

	    public static long getSerialVersionUID() {
	        return serialVersionUID;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTexto() {
	        return texto;
	    }

	    public void setTexto(String texto) {
	        this.texto = texto;
	    }

	    public Disciplina getDisciplina() {
	        return disciplina;
	    }

	    public void setDisciplina(Disciplina disciplina) {
	        this.disciplina = disciplina;
	    }


}
