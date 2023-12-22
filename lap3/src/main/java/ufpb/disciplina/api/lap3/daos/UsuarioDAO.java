package ufpb.disciplina.api.lap3.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ufpb.disciplina.api.lap3.entidades.*;
@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, String>{
	Optional<Usuario> findByEmail(String email);

}
