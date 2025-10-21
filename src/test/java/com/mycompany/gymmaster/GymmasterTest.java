/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.gymmaster;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.mycompany.gymmaster.model.Aluno;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mdngt
 */


public class GymmasterTest {

    @Test
    public void testCalcularMensalidade() {
        Aluno a1 = new Aluno("João", "básico", 100);
        assertEquals(100, a1.calcularMensalidade(), 0.01);

        Aluno a2 = new Aluno("Maria", "intermediário", 100);
        assertEquals(120, a2.calcularMensalidade(), 0.01);

        Aluno a3 = new Aluno("Carlos", "premium", 100);
        assertEquals(150, a3.calcularMensalidade(), 0.01);
    }

    @Test
    public void testDobrarValorBase() {
        Aluno aluno = new Aluno("Teste", "básico", 50);
        assertEquals(100, aluno.dobrarValorBase(), 0.01);
    }

    @Test
    public void testGetNome() {
        Aluno aluno = new Aluno("Fernanda", "básico", 90);
        assertEquals("Fernanda", aluno.getNome());
    }
}
