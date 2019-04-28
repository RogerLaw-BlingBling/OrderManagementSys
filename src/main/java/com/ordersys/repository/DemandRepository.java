package com.ordersys.repository;

import com.ordersys.model.Demand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandRepository extends JpaRepository<Demand,Integer> {
}
