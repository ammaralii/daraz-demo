package com.venturedive.daraz.repository;

import com.venturedive.daraz.domain.Cars;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cars entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarsRepository extends JpaRepository<Cars, Long>, JpaSpecificationExecutor<Cars> {}
