package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.*; // for static metamodels
import com.venturedive.daraz.domain.PaymentMethods;
import com.venturedive.daraz.repository.PaymentMethodsRepository;
import com.venturedive.daraz.service.criteria.PaymentMethodsCriteria;
import com.venturedive.daraz.service.dto.PaymentMethodsDTO;
import com.venturedive.daraz.service.mapper.PaymentMethodsMapper;
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
 * Service for executing complex queries for {@link PaymentMethods} entities in the database.
 * The main input is a {@link PaymentMethodsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaymentMethodsDTO} or a {@link Page} of {@link PaymentMethodsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaymentMethodsQueryService extends QueryService<PaymentMethods> {

    private final Logger log = LoggerFactory.getLogger(PaymentMethodsQueryService.class);

    private final PaymentMethodsRepository paymentMethodsRepository;

    private final PaymentMethodsMapper paymentMethodsMapper;

    public PaymentMethodsQueryService(PaymentMethodsRepository paymentMethodsRepository, PaymentMethodsMapper paymentMethodsMapper) {
        this.paymentMethodsRepository = paymentMethodsRepository;
        this.paymentMethodsMapper = paymentMethodsMapper;
    }

    /**
     * Return a {@link List} of {@link PaymentMethodsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentMethodsDTO> findByCriteria(PaymentMethodsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PaymentMethods> specification = createSpecification(criteria);
        return paymentMethodsMapper.toDto(paymentMethodsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaymentMethodsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentMethodsDTO> findByCriteria(PaymentMethodsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PaymentMethods> specification = createSpecification(criteria);
        return paymentMethodsRepository.findAll(specification, page).map(paymentMethodsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaymentMethodsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PaymentMethods> specification = createSpecification(criteria);
        return paymentMethodsRepository.count(specification);
    }

    /**
     * Function to convert {@link PaymentMethodsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PaymentMethods> createSpecification(PaymentMethodsCriteria criteria) {
        Specification<PaymentMethods> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PaymentMethods_.id));
            }
            if (criteria.getCardNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCardNumber(), PaymentMethods_.cardNumber));
            }
            if (criteria.getCardHolderName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCardHolderName(), PaymentMethods_.cardHolderName));
            }
            if (criteria.getExpirationDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getExpirationDate(), PaymentMethods_.expirationDate));
            }
            if (criteria.getCustomerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCustomerId(),
                            root -> root.join(PaymentMethods_.customer, JoinType.LEFT).get(Customers_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
