package ufpb.disciplina.api.lap3.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.disciplina.api.lap3.dtos.*;
import ufpb.disciplina.api.lap3.entidades.*;
import ufpb.disciplina.api.lap3.excecoes.*;
import ufpb.disciplina.api.lap3.servicos.*;

@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioControlador {
	@Autowired
    private UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criaUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<UsuarioDTO>(usuarioServico.criaUsuario(usuario), HttpStatus.CREATED);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UsuarioDTO> deletaUsuario(@RequestHeader("Authorization") String token) {
        try {
            return new ResponseEntity<>(usuarioServico.deletaUsuario(token), HttpStatus.OK);
        } catch (UsuarioJaExisteException uee) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UsuarioInvalidoException uie) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


}
