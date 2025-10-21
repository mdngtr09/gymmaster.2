package com.academia.gymmaster.repository;

import com.academia.gymmaster.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByEmail(String email);
}
