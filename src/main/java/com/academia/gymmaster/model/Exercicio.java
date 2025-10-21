package com.academia.gymmaster.model;

import jakarta.persistence.*;

@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String grupoMuscular;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getGrupoMuscular() { return grupoMuscular; }
    public void setGrupoMuscular(String grupoMuscular) { this.grupoMuscular = grupoMuscular; }
}
