package ufpb.disciplina.api.lap3.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.disciplina.api.lap3.dtos.*;
import ufpb.disciplina.api.lap3.jwt.*;
import ufpb.disciplina.api.lap3.servicos.*;

@RestController
@RequestMapping("/v1/api/auth")
public class LoginControlador {
	@Autowired
    private JWTServico jwtServico;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autentica(@RequestBody UsuarioLoginDTO usuario) {
        return new ResponseEntity<LoginResponse>(jwtServico.autentica(usuario), HttpStatus.OK);
    }

}
