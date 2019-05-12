package com.ordersys.repository;

import com.ordersys.model.Demand;
import com.ordersys.model.dto.DemandDetailsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface DemandRepository extends JpaRepository<Demand,Integer> {
    Collection<Demand> findByProjectId(Integer id);

    @Query("select new com.ordersys.model.dto.DemandDetailsDto(d,o,p) from Demand d " +
            "left join Project p on d.projectId = p.id " +
            "left join Order o on p.orderId = o.id")
    Page<DemandDetailsDto> findAllDetailed(Pageable pageable);
}
