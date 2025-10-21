package com.academia.gymmaster.repository;

import com.academia.gymmaster.model.DivisaoTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisaoTreinoRepository extends JpaRepository<DivisaoTreino, Long> {
}
