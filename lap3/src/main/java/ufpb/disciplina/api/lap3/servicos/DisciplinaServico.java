package ufpb.disciplina.api.lap3.servicos;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import ufpb.disciplina.api.lap3.daos.ComentarioDAO;
import ufpb.disciplina.api.lap3.daos.DisciplinaDAO;
import ufpb.disciplina.api.lap3.dtos.ComentarioDTO;
import ufpb.disciplina.api.lap3.dtos.DisciplinaComentarioDTO;
import ufpb.disciplina.api.lap3.dtos.DisciplinaDTO;
import ufpb.disciplina.api.lap3.dtos.DisciplinaLikesDTO;
import ufpb.disciplina.api.lap3.dtos.DisciplinaNotasDTO;
import ufpb.disciplina.api.lap3.dtos.NotaDTO;
import ufpb.disciplina.api.lap3.entidades.Comentario;
import ufpb.disciplina.api.lap3.entidades.Disciplina;


@Service
public class DisciplinaServico {
	private DisciplinaDAO repositorioDeDisciplinas;
    private ComentarioDAO repositorioDeComentarios;

    @Autowired
    public DisciplinaServico(DisciplinaDAO repositorioDeDisciplinas, ComentarioDAO repositorioDeComentarios) {
        this.repositorioDeDisciplinas = repositorioDeDisciplinas;
        this.repositorioDeComentarios = repositorioDeComentarios;
    }

    public List<DisciplinaDTO> getDisciplinas(){
        List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
        List<DisciplinaDTO> listaDisciplinas = disciplinas.stream().map(DisciplinaDTO::new).collect(Collectors.toList());
        return listaDisciplinas;
    }

    public Disciplina getDisciplinaById(Long id) throws Exception{
        Optional<Disciplina> disciplinaOptional = repositorioDeDisciplinas.findById(id);
        Disciplina disciplinaRecuperada = disciplinaOptional.get();

        if (disciplinaOptional.isEmpty()){
            throw new Exception();
        }

        return disciplinaRecuperada;
    }

    public DisciplinaLikesDTO adicionarCurtida(Long id) throws Exception {
        Disciplina disciplinaRecuperada = getDisciplinaById(id);

        int curtidas = disciplinaRecuperada.getCurtidas();
        disciplinaRecuperada.setCurtidas(curtidas+1);
        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplinaRecuperada);
        repositorioDeDisciplinas.save(disciplinaRecuperada);

        return disciplinaLikesDTO;
    }

    public DisciplinaNotasDTO adicionarNota(Long id, NotaDTO notaDTO) throws Exception{

        Disciplina disciplina = getDisciplinaById(id);

        if (disciplina.getNota() == 0) disciplina.setNota(notaDTO.getNota());
        else disciplina.setNota((disciplina.getNota()+notaDTO.getNota())/2);
        repositorioDeDisciplinas.save(disciplina);

        return new DisciplinaNotasDTO(disciplina);
    }

    public DisciplinaComentarioDTO adicionarComentario(Long id, ComentarioDTO comentarioDTO) throws Exception{

        Disciplina disciplina = getDisciplinaById(id);

        Comentario comentario = new Comentario(comentarioDTO.getTexto());
        comentario.setDisciplina(disciplina);
        repositorioDeComentarios.save(comentario);
        disciplina.getComentarios().add(comentario);

        DisciplinaLikesDTO disciplinaLikesDTO = new DisciplinaLikesDTO(disciplina);
        repositorioDeDisciplinas.save(disciplina);

        return new DisciplinaComentarioDTO(disciplina);
    }

    public List<Disciplina> ranquearNotasDisciplina() {
        return this.repositorioDeDisciplinas.findByOrderByNota();
    }

    public List<Disciplina> ranquearLikesDisciplina() {
        return repositorioDeDisciplinas.findAll(Sort.by(Sort.Direction.DESC, "curtidas"));
    }

    @PostConstruct
    public void initDisciplinas(){
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
        InputStream inputStream = ObjectMapper.class.getResourceAsStream("/json/disciplinas.json");
        try{
            List<Disciplina>disciplinasAll = mapper.readValue(inputStream, typeReference);
            this.repositorioDeDisciplinas.saveAll(disciplinasAll);
            System.out.println("Salvo com sucesso!");
        }
        catch (IOException e){
            System.out.println("Não foi possível salvar as disciplinas!" + e.getMessage());
        }
    }

}
