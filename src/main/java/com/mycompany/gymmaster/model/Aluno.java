package com.mycompany.gymmaster.model;

public class Aluno {
    private String nome;
    private String plano;
    private double valorBase;

    public Aluno(String nome, String plano, double valorBase) {
        this.nome = nome;
        this.plano = plano;
        this.valorBase = valorBase;
    }

    public String getNome() {
        return nome;
    }

    public double calcularMensalidade() {
        return switch (plano.toLowerCase()) {
            case "premium" -> valorBase * 1.5;
            case "intermediário" -> valorBase * 1.2;
            case "básico" -> valorBase;
            default -> valorBase;
        };
    }

    // Método simples só para teste unitário
    public double dobrarValorBase() {
        return valorBase * 2;
    }

    @Override
    public String toString() {
        return nome + " - Plano: " + plano + " - Base: R$" + valorBase;
    }
}
