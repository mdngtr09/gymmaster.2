package com.academia.gymmaster.service;

import com.academia.gymmaster.model.Plano;
import com.academia.gymmaster.exception.ResourceNotFoundException;
import com.academia.gymmaster.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    // Injeção via construtor (melhor prática)
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> listarTodos() {
        return planoRepository.findAll();
    }

    public Plano buscarPorId(Long id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com id: " + id));
    }

    public Plano salvar(Plano plano) {
        return planoRepository.save(plano);
    }

    public Plano atualizar(Long id, Plano planoAtualizado) {
        Plano plano = buscarPorId(id);

        plano.setNome(planoAtualizado.getNome());
        plano.setPreco(planoAtualizado.getPreco());
        plano.setDescricao(planoAtualizado.getDescricao());
        plano.setDuracaoMeses(planoAtualizado.getDuracaoMeses());

        return planoRepository.save(plano);
    }

    public void excluir(Long id) {
        buscarPorId(id); // garante que lança exceção se não existir
        planoRepository.deleteById(id);
    }
}
