package com.academia.gymmaster.service;

import com.academia.gymmaster.exception.ResourceNotFoundException;
import com.academia.gymmaster.model.Aluno;
import com.academia.gymmaster.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setEmail(alunoAtualizado.getEmail());
                    aluno.setIdade(alunoAtualizado.getIdade()); // Atualiza apenas os campos do formulário
                    return alunoRepository.save(aluno);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com ID: " + id));
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }
}
