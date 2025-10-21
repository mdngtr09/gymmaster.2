package com.academia.gymmaster.controller;

import com.academia.gymmaster.model.DivisaoTreino;
import com.academia.gymmaster.model.Aluno;
import com.academia.gymmaster.model.Exercicio;
import com.academia.gymmaster.repository.DivisaoTreinoRepository;
import com.academia.gymmaster.repository.AlunoRepository;
import com.academia.gymmaster.repository.ExercicioRepository;
import com.academia.gymmaster.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/treinos")
@CrossOrigin(origins = "*")
public class DivisaoTreinoController {

    @Autowired
    private DivisaoTreinoRepository divisaoTreinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping
    public List<DivisaoTreino> listarTodos() {
        return divisaoTreinoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisaoTreino> buscarPorId(@PathVariable Long id) {
        return divisaoTreinoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DivisaoTreino> salvar(@RequestBody DivisaoTreinoRequest request) {
        DivisaoTreino divisao = new DivisaoTreino();
        divisao.setNome(request.nome);

        Aluno aluno = alunoRepository.findById(request.alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + request.alunoId));
        divisao.setAluno(aluno);

        List<Exercicio> exercicios = request.exerciciosIds.stream()
                .map(id -> exercicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exercicio não encontrado: " + id)))
                .collect(Collectors.toList());
        divisao.setExercicios(exercicios);

        return ResponseEntity.ok(divisaoTreinoRepository.save(divisao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DivisaoTreino> atualizar(@PathVariable Long id, @RequestBody DivisaoTreinoRequest request) {
        return divisaoTreinoRepository.findById(id)
                .map(div -> {
                    div.setNome(request.nome);

                    Aluno aluno = alunoRepository.findById(request.alunoId)
                            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + request.alunoId));
                    div.setAluno(aluno);

                    List<Exercicio> exercicios = request.exerciciosIds.stream()
                            .map(eid -> exercicioRepository.findById(eid)
                                    .orElseThrow(() -> new ResourceNotFoundException("Exercicio não encontrado: " + eid)))
                            .collect(Collectors.toList());
                    div.setExercicios(exercicios);

                    return ResponseEntity.ok(divisaoTreinoRepository.save(div));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        divisaoTreinoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Classe interna para receber JSON do frontend
    public static class DivisaoTreinoRequest {
        public String nome;
        public Long alunoId;
        public List<Long> exerciciosIds;
    }
}
