package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

}
