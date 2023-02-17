package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.*; // for static metamodels
import com.venturedive.daraz.domain.ProductDetails;
import com.venturedive.daraz.repository.ProductDetailsRepository;
import com.venturedive.daraz.service.criteria.ProductDetailsCriteria;
import com.venturedive.daraz.service.dto.ProductDetailsDTO;
import com.venturedive.daraz.service.mapper.ProductDetailsMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ProductDetails} entities in the database.
 * The main input is a {@link ProductDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProductDetailsDTO} or a {@link Page} of {@link ProductDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProductDetailsQueryService extends QueryService<ProductDetails> {

    private final Logger log = LoggerFactory.getLogger(ProductDetailsQueryService.class);

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductDetailsMapper productDetailsMapper;

    public ProductDetailsQueryService(ProductDetailsRepository productDetailsRepository, ProductDetailsMapper productDetailsMapper) {
        this.productDetailsRepository = productDetailsRepository;
        this.productDetailsMapper = productDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link ProductDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProductDetailsDTO> findByCriteria(ProductDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProductDetails> specification = createSpecification(criteria);
        return productDetailsMapper.toDto(productDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProductDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDetailsDTO> findByCriteria(ProductDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProductDetails> specification = createSpecification(criteria);
        return productDetailsRepository.findAll(specification, page).map(productDetailsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProductDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProductDetails> specification = createSpecification(criteria);
        return productDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link ProductDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProductDetails> createSpecification(ProductDetailsCriteria criteria) {
        Specification<ProductDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProductDetails_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ProductDetails_.description));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), ProductDetails_.imageUrl));
            }
            if (criteria.getIsavailable() != null) {
                specification = specification.and(buildSpecification(criteria.getIsavailable(), ProductDetails_.isavailable));
            }
            if (criteria.getProductId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProductId(),
                            root -> root.join(ProductDetails_.product, JoinType.LEFT).get(Products_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
