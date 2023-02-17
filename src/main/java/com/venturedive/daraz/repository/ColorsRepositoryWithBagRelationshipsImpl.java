package com.venturedive.daraz.repository;

import com.venturedive.daraz.domain.Colors;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ColorsRepositoryWithBagRelationshipsImpl implements ColorsRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Colors> fetchBagRelationships(Optional<Colors> colors) {
        return colors.map(this::fetchCars);
    }

    @Override
    public Page<Colors> fetchBagRelationships(Page<Colors> colors) {
        return new PageImpl<>(fetchBagRelationships(colors.getContent()), colors.getPageable(), colors.getTotalElements());
    }

    @Override
    public List<Colors> fetchBagRelationships(List<Colors> colors) {
        return Optional.of(colors).map(this::fetchCars).orElse(Collections.emptyList());
    }

    Colors fetchCars(Colors result) {
        return entityManager
            .createQuery("select colors from Colors colors left join fetch colors.cars where colors is :colors", Colors.class)
            .setParameter("colors", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Colors> fetchCars(List<Colors> colors) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, colors.size()).forEach(index -> order.put(colors.get(index).getId(), index));
        List<Colors> result = entityManager
            .createQuery("select distinct colors from Colors colors left join fetch colors.cars where colors in :colors", Colors.class)
            .setParameter("colors", colors)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
