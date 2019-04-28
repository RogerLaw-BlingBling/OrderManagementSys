package com.ordersys.repository;

import com.ordersys.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
