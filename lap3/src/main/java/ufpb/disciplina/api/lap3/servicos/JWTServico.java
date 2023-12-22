package ufpb.disciplina.api.lap3.servicos;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufpb.disciplina.api.lap3.daos.*;
import ufpb.disciplina.api.lap3.dtos.*;
import ufpb.disciplina.api.lap3.entidades.*;
import ufpb.disciplina.api.lap3.filtros.*;
import ufpb.disciplina.api.lap3.jwt.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
@SuppressWarnings("deprecation")
@Service
public class JWTServico {
	@Autowired
    private UsuarioDAO repositorioDeUsuarios;

    public LoginResponse autentica(UsuarioLoginDTO usuario) {
        String msgErro = "Email e/ou senha invalido(s). Login nao realizado";
        Optional<Usuario> optUsuario = repositorioDeUsuarios.findByEmail(usuario.getEmail());
        if (optUsuario.isPresent() && usuario.getSenha().equals(optUsuario.get().getSenha()))
            return new LoginResponse(geraToken(usuario));

        return new LoginResponse(msgErro);

    }

    private String geraToken(UsuarioLoginDTO usuario) {
        String token = Jwts.builder().setSubject(usuario.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
        return token;
    }

    public static final Key TOKEN_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public Optional<String> recuperaUsuario(String cabecalhoAutorizacao) {
        if (cabecalhoAutorizacao == null ||
                !cabecalhoAutorizacao.startsWith("bearer ")) {
            throw new SecurityException();
        }
        String token = cabecalhoAutorizacao.substring(TokenFiltro.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return Optional.of(subject);
    }

    public String getSujeitoDoToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = authorizationHeader.substring(TokenFiltro.TOKEN_INDEX);

        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
        } catch (SignatureException e) {
            throw new SecurityException("Token invalido ou expirado!");
        }
        return subject;
    }

}
