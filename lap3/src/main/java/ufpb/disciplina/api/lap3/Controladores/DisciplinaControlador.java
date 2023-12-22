package ufpb.disciplina.api.lap3.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufpb.disciplina.api.lap3.dtos.*;
import ufpb.disciplina.api.lap3.entidades.*;
import ufpb.disciplina.api.lap3.servicos.*;

@RestController
@RequestMapping("/v1/api/disciplinas")
public class DisciplinaControlador {

	private DisciplinaServico disciplinaServico;

    @Autowired
    private DisciplinaControlador(DisciplinaServico disciplinaService) {
        this.disciplinaServico = disciplinaService;
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> getDisciplinas() {
        return new ResponseEntity<>(disciplinaServico.getDisciplinas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(disciplinaServico.getDisciplinaById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/likes/{id}")
    public ResponseEntity<DisciplinaLikesDTO> adicionarCurtida(@PathVariable Long id) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaServico.adicionarCurtida(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/nota/{id}")
    public ResponseEntity<DisciplinaNotasDTO> adicionarNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO) throws Exception {
        try {
            return new ResponseEntity<>(disciplinaServico.adicionarNota(id, notaDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/comentarios/{id}")
    public ResponseEntity<DisciplinaComentarioDTO> adicionarComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) throws Exception {
        try {

            return new ResponseEntity<>(disciplinaServico.adicionarComentario(id, comentarioDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ranking/likes")
    public ResponseEntity<List<Disciplina>> ranquearLikeDisciplinas() {
        return new ResponseEntity<>(disciplinaServico.ranquearLikesDisciplina(), HttpStatus.OK);
    }

    @GetMapping("/ranking/notas")
    public ResponseEntity<List<Disciplina>> ranquearNotaDisciplinas() {
        return new ResponseEntity<>(disciplinaServico.ranquearNotasDisciplina(), HttpStatus.OK);
    }
}
