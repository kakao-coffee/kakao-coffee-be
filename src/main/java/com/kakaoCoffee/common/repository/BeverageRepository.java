package com.kakaoCoffee.common.repository;

import com.kakaoCoffee.common.entity.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long>, BeverageRepositoryCustom {

    List<Beverage> findAll();

}
