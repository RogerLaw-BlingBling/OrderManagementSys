package com.ordersys.repository;

import com.ordersys.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    @Query("select c from Contract c join Order o on o.id = c.orderId where o.orderId = ?1")
    Collection<Contract> findAllByOrderId(String orderId);
}
