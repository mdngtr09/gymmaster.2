/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymmaster.model;

/**
 *
 * @author mdngt
 */
public class Aluno {
    private final String nome;
    private final String plano; 
    private final double valorBase;

    public Aluno(String nome, String plano, double valorBase) {
        this.nome = nome;
        this.plano = plano;
        this.valorBase = valorBase;
    }

    public String getNome() {
        return nome;
    }

    public String getPlano() {
        return plano;
    }

    public double calcularMensalidade() {
        return switch (plano.toLowerCase()) {
            case "premium" -> valorBase * 1.5;
            case "intermediário" -> valorBase * 1.2;
            case "básico" -> valorBase;
            default -> valorBase;
        };
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + " | Plano: " + plano + " | Mensalidade: R$ " + calcularMensalidade();
    }
}
