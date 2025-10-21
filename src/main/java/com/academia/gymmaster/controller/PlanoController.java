package com.academia.gymmaster.controller;

import com.academia.gymmaster.model.Plano;
import com.academia.gymmaster.service.PlanoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    // Listar todos os planos
    @GetMapping
    public ResponseEntity<List<Plano>> listarTodos() {
        return ResponseEntity.ok(planoService.listarTodos());
    }

    // Buscar plano por ID
    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable Long id) {
        Plano plano = planoService.buscarPorId(id);
        return ResponseEntity.ok(plano);
    }

    // Criar novo plano
    @PostMapping
    public ResponseEntity<Plano> salvar(@RequestBody Plano plano) {
        Plano novoPlano = planoService.salvar(plano);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPlano);
    }

    // Atualizar plano existente
    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizar(@PathVariable Long id, @RequestBody Plano planoAtualizado) {
        Plano plano = planoService.atualizar(id, planoAtualizado);
        return ResponseEntity.ok(plano);
    }

    // Excluir plano
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        planoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
