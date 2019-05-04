package com.ordersys.repository;

import com.ordersys.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DemandRepository extends JpaRepository<Demand,Integer> {
    Collection<Demand> findByProjectId(Integer id);
}
