package com.venturedive.daraz.repository;

import com.venturedive.daraz.domain.DarazUsers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DarazUsers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DarazUsersRepository extends JpaRepository<DarazUsers, Long>, JpaSpecificationExecutor<DarazUsers> {}
