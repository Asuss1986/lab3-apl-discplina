package ufpb.disciplina.api.lap3.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufpb.disciplina.api.lap3.entidades.*;

@Repository
public interface ComentarioDAO extends JpaRepository<Comentario, Long> {

}
