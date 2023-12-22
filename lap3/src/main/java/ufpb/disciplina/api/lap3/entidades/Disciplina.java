package ufpb.disciplina.api.lap3.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina  implements Serializable{
	
	private static final long serialVersionUID = -474122221565659685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int curtidas;
    private double nota;

    @OneToMany(mappedBy = "disciplina")
    private List<Comentario> comentarios = new ArrayList<>();

    public Disciplina(Long id, String nome, int curtidas, double nota, List<Comentario> comentarios) {
        this.id = id;
        this.nome = nome;
        this.curtidas = curtidas;
        this.nota = nota;
        this.comentarios = comentarios;
    }

    public Disciplina() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public int setCurtidas(int curtidas) {
        this.curtidas = curtidas;
        return curtidas;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

}
