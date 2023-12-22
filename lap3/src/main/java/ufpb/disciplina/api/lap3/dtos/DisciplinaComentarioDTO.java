package ufpb.disciplina.api.lap3.dtos;

import java.util.List;


import ufpb.disciplina.api.lap3.entidades.*;
import lombok.Data;

@Data
public class DisciplinaComentarioDTO {
	private Long id;
    private String nome;
    private List<Comentario> comentarios;

    public DisciplinaComentarioDTO(Disciplina disciplina) {
        this.id = disciplina.getId();
        this.nome = disciplina.getNome();
        this.comentarios = disciplina.getComentarios();
    }

}
