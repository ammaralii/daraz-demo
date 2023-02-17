package com.venturedive.daraz.repository;

import com.venturedive.daraz.domain.Colors;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ColorsRepositoryWithBagRelationships {
    Optional<Colors> fetchBagRelationships(Optional<Colors> colors);

    List<Colors> fetchBagRelationships(List<Colors> colors);

    Page<Colors> fetchBagRelationships(Page<Colors> colors);
}
