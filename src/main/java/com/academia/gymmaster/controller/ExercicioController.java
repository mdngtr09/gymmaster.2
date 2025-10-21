package com.academia.gymmaster.controller;

import com.academia.gymmaster.model.Exercicio;
import com.academia.gymmaster.service.ExercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercicios")
@CrossOrigin(origins = "*")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @GetMapping
    public List<Exercicio> listarTodos() {
        return exercicioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable Long id) {
        return exercicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exercicio salvar(@RequestBody Exercicio exercicio) {
        return exercicioService.salvar(exercicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizar(@PathVariable Long id, @RequestBody Exercicio exercicioAtualizado) {
        try {
            return ResponseEntity.ok(exercicioService.atualizar(id, exercicioAtualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        exercicioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
