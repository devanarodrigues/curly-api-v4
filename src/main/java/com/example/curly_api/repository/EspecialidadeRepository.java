package com.example.curly_api.repository;

import com.example.curly_api.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    List<Especialidade> findByUserId(String userId);

}
