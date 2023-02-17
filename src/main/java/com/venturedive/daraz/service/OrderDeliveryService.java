package com.venturedive.daraz.service;

import com.venturedive.daraz.domain.OrderDelivery;
import com.venturedive.daraz.repository.OrderDeliveryRepository;
import com.venturedive.daraz.service.dto.OrderDeliveryDTO;
import com.venturedive.daraz.service.mapper.OrderDeliveryMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderDelivery}.
 */
@Service
@Transactional
public class OrderDeliveryService {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryService.class);

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryMapper orderDeliveryMapper;

    public OrderDeliveryService(OrderDeliveryRepository orderDeliveryRepository, OrderDeliveryMapper orderDeliveryMapper) {
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryMapper = orderDeliveryMapper;
    }

    /**
     * Save a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDeliveryDTO save(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to save OrderDelivery : {}", orderDeliveryDTO);
        OrderDelivery orderDelivery = orderDeliveryMapper.toEntity(orderDeliveryDTO);
        orderDelivery = orderDeliveryRepository.save(orderDelivery);
        return orderDeliveryMapper.toDto(orderDelivery);
    }

    /**
     * Update a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderDeliveryDTO update(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to update OrderDelivery : {}", orderDeliveryDTO);
        OrderDelivery orderDelivery = orderDeliveryMapper.toEntity(orderDeliveryDTO);
        orderDelivery = orderDeliveryRepository.save(orderDelivery);
        return orderDeliveryMapper.toDto(orderDelivery);
    }

    /**
     * Partially update a orderDelivery.
     *
     * @param orderDeliveryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderDeliveryDTO> partialUpdate(OrderDeliveryDTO orderDeliveryDTO) {
        log.debug("Request to partially update OrderDelivery : {}", orderDeliveryDTO);

        return orderDeliveryRepository
            .findById(orderDeliveryDTO.getId())
            .map(existingOrderDelivery -> {
                orderDeliveryMapper.partialUpdate(existingOrderDelivery, orderDeliveryDTO);

                return existingOrderDelivery;
            })
            .map(orderDeliveryRepository::save)
            .map(orderDeliveryMapper::toDto);
    }

    /**
     * Get all the orderDeliveries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderDeliveryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderDeliveries");
        return orderDeliveryRepository.findAll(pageable).map(orderDeliveryMapper::toDto);
    }

    /**
     * Get one orderDelivery by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderDeliveryDTO> findOne(Long id) {
        log.debug("Request to get OrderDelivery : {}", id);
        return orderDeliveryRepository.findById(id).map(orderDeliveryMapper::toDto);
    }

    /**
     * Delete the orderDelivery by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderDelivery : {}", id);
        orderDeliveryRepository.deleteById(id);
    }
}
