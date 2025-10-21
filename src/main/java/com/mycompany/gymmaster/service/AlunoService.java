/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gymmaster.service;

/**
 *
 * @author mdngt
 */
import java.util.ArrayList;
import java.util.List;
import com.mycompany.gymmaster.model.Aluno;
        
public class AlunoService {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public Aluno buscarPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno;
            }
        }
        return null;
    }

    public double calcularMensalidade(String nome) {
        Aluno aluno = buscarPorNome(nome);
        if (aluno != null) {
            return aluno.calcularMensalidade();
        }
        return 0;
    }

    public List<Aluno> listarAlunos() {
        return alunos;
    }
}