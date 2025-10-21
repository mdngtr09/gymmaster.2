/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gymmaster;

/**
 *
 * @author mdngt
 */

import com.mycompany.gymmaster.model.Aluno;
import com.mycompany.gymmaster.service.AlunoService;
import java.util.Scanner;

public class Gymmaster {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoService alunoService = new AlunoService();

        while (true) {
            System.out.println("\n=== Sistema da Academia GymMaster ===");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Calcular mensalidade");
            System.out.println("4. Buscar aluno por nome");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do aluno: ");
                    String nome = scanner.nextLine();
                    System.out.print("Plano (básico/intermediário/premium): ");
                    String plano = scanner.nextLine();
                    System.out.print("Valor base (R$): ");
                    double valorBase = scanner.nextDouble();
                    scanner.nextLine();

                    Aluno novoAluno = new Aluno(nome, plano, valorBase);
                    alunoService.cadastrarAluno(novoAluno);
                    System.out.println("✅ Aluno cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Lista de alunos ---");
                    for (Aluno a : alunoService.listarAlunos()) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    System.out.print("Digite o nome do aluno: ");
                    String nomeBusca = scanner.nextLine();
                    double mensalidade = alunoService.calcularMensalidade(nomeBusca);
                    if (mensalidade > 0) {
                        System.out.println("Mensalidade de " + nomeBusca + ": R$ " + mensalidade);
                    } else {
                        System.out.println("Aluno não encontrado!");
                    }
                    break;

                case 4:
                    System.out.print("Digite o nome do aluno: ");
                    String nomeProcurado = scanner.nextLine();
                    Aluno aluno = alunoService.buscarPorNome(nomeProcurado);
                    if (aluno != null) {
                        System.out.println("Aluno encontrado: " + aluno);
                    } else {
                        System.out.println("Aluno não encontrado!");
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}

