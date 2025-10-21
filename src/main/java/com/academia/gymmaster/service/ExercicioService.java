package com.academia.gymmaster.service;

import com.academia.gymmaster.model.Exercicio;
import com.academia.gymmaster.repository.ExercicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public List<Exercicio> listarTodos() {
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> buscarPorId(Long id) {
        return exercicioRepository.findById(id);
    }

    public Exercicio salvar(Exercicio exercicio) {
        return exercicioRepository.save(exercicio);
    }

    public Exercicio atualizar(Long id, Exercicio exercicioAtualizado) {
        return exercicioRepository.findById(id)
                .map(exercicio -> {
                    exercicio.setNome(exercicioAtualizado.getNome());
                    exercicio.setGrupoMuscular(exercicioAtualizado.getGrupoMuscular());
                    exercicio.setDescricao(exercicioAtualizado.getDescricao());
                    return exercicioRepository.save(exercicio);
                })
                .orElseThrow(() -> new RuntimeException("Exercício não encontrado" + id));
    }

    public void excluir(Long id) {
        exercicioRepository.deleteById(id);
    }
}
