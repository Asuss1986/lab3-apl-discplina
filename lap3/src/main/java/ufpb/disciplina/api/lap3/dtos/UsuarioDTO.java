package ufpb.disciplina.api.lap3.dtos;

import ufpb.disciplina.api.lap3.entidades.*;
import lombok.Data;
@Data
public class UsuarioDTO {
	private String email;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

}
