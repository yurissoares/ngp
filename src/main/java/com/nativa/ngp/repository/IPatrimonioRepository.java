package com.nativa.ngp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nativa.ngp.entity.PatrimonioEntity;

@Repository
public interface IPatrimonioRepository extends JpaRepository<PatrimonioEntity, Long> {

}
