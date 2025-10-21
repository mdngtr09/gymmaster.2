package com.academia.gymmaster.service;

import com.academia.gymmaster.model.DivisaoTreino;
import com.academia.gymmaster.model.Aluno;
import com.academia.gymmaster.model.Exercicio;
import com.academia.gymmaster.repository.DivisaoTreinoRepository;
import com.academia.gymmaster.repository.AlunoRepository;
import com.academia.gymmaster.repository.ExercicioRepository;
import com.academia.gymmaster.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DivisaoTreinoService {

    @Autowired
    private DivisaoTreinoRepository divisaoTreinoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<DivisaoTreino> listarTodos() {
        return divisaoTreinoRepository.findAll();
    }

    public Optional<DivisaoTreino> buscarPorId(Long id) {
        return divisaoTreinoRepository.findById(id);
    }

    public DivisaoTreino salvar(String nome, Long alunoId, List<Long> exerciciosIds) {
        DivisaoTreino divisao = new DivisaoTreino();
        divisao.setNome(nome);

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + alunoId));
        divisao.setAluno(aluno);

        List<Exercicio> exercicios = exerciciosIds.stream()
                .map(id -> exercicioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Exercicio não encontrado: " + id)))
                .collect(Collectors.toList());
        divisao.setExercicios(exercicios);

        return divisaoTreinoRepository.save(divisao);
    }

    public DivisaoTreino atualizar(Long id, String nome, Long alunoId, List<Long> exerciciosIds) {
        return divisaoTreinoRepository.findById(id)
                .map(div -> {
                    div.setNome(nome);

                    Aluno aluno = alunoRepository.findById(alunoId)
                            .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + alunoId));
                    div.setAluno(aluno);

                    List<Exercicio> exercicios = exerciciosIds.stream()
                            .map(eid -> exercicioRepository.findById(eid)
                                    .orElseThrow(() -> new ResourceNotFoundException("Exercicio não encontrado: " + eid)))
                            .collect(Collectors.toList());
                    div.setExercicios(exercicios);

                    return divisaoTreinoRepository.save(div);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Divisão não encontrada: " + id));
    }

    public void excluir(Long id) {
        divisaoTreinoRepository.deleteById(id);
    }
}
