package com.nativa.ngp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nativa.ngp.entity.MarcaEntity;

@Repository
public interface IMarcaRepository extends JpaRepository<MarcaEntity, Long> {

}
