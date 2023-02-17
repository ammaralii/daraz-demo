package com.venturedive.daraz.web.rest;

import com.venturedive.daraz.repository.OrderDeliveryRepository;
import com.venturedive.daraz.service.OrderDeliveryQueryService;
import com.venturedive.daraz.service.OrderDeliveryService;
import com.venturedive.daraz.service.criteria.OrderDeliveryCriteria;
import com.venturedive.daraz.service.dto.OrderDeliveryDTO;
import com.venturedive.daraz.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.venturedive.daraz.domain.OrderDelivery}.
 */
@RestController
@RequestMapping("/api")
public class OrderDeliveryResource {

    private final Logger log = LoggerFactory.getLogger(OrderDeliveryResource.class);

    private static final String ENTITY_NAME = "orderDelivery";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderDeliveryService orderDeliveryService;

    private final OrderDeliveryRepository orderDeliveryRepository;

    private final OrderDeliveryQueryService orderDeliveryQueryService;

    public OrderDeliveryResource(
        OrderDeliveryService orderDeliveryService,
        OrderDeliveryRepository orderDeliveryRepository,
        OrderDeliveryQueryService orderDeliveryQueryService
    ) {
        this.orderDeliveryService = orderDeliveryService;
        this.orderDeliveryRepository = orderDeliveryRepository;
        this.orderDeliveryQueryService = orderDeliveryQueryService;
    }

    /**
     * {@code POST  /order-deliveries} : Create a new orderDelivery.
     *
     * @param orderDeliveryDTO the orderDeliveryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderDeliveryDTO, or with status {@code 400 (Bad Request)} if the orderDelivery has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-deliveries")
    public ResponseEntity<OrderDeliveryDTO> createOrderDelivery(@Valid @RequestBody OrderDeliveryDTO orderDeliveryDTO)
        throws URISyntaxException {
        log.debug("REST request to save OrderDelivery : {}", orderDeliveryDTO);
        if (orderDeliveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderDelivery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderDeliveryDTO result = orderDeliveryService.save(orderDeliveryDTO);
        return ResponseEntity
            .created(new URI("/api/order-deliveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-deliveries/:id} : Updates an existing orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to save.
     * @param orderDeliveryDTO the orderDeliveryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDeliveryDTO,
     * or with status {@code 400 (Bad Request)} if the orderDeliveryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderDeliveryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-deliveries/{id}")
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OrderDeliveryDTO orderDeliveryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderDelivery : {}, {}", id, orderDeliveryDTO);
        if (orderDeliveryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderDeliveryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderDeliveryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderDeliveryDTO result = orderDeliveryService.update(orderDeliveryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, orderDeliveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /order-deliveries/:id} : Partial updates given fields of an existing orderDelivery, field will ignore if it is null
     *
     * @param id the id of the orderDeliveryDTO to save.
     * @param orderDeliveryDTO the orderDeliveryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderDeliveryDTO,
     * or with status {@code 400 (Bad Request)} if the orderDeliveryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderDeliveryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderDeliveryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/order-deliveries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderDeliveryDTO> partialUpdateOrderDelivery(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OrderDeliveryDTO orderDeliveryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderDelivery partially : {}, {}", id, orderDeliveryDTO);
        if (orderDeliveryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderDeliveryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderDeliveryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderDeliveryDTO> result = orderDeliveryService.partialUpdate(orderDeliveryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, orderDeliveryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-deliveries} : get all the orderDeliveries.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderDeliveries in body.
     */
    @GetMapping("/order-deliveries")
    public ResponseEntity<List<OrderDeliveryDTO>> getAllOrderDeliveries(
        OrderDeliveryCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get OrderDeliveries by criteria: {}", criteria);
        Page<OrderDeliveryDTO> page = orderDeliveryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-deliveries/count} : count all the orderDeliveries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/order-deliveries/count")
    public ResponseEntity<Long> countOrderDeliveries(OrderDeliveryCriteria criteria) {
        log.debug("REST request to count OrderDeliveries by criteria: {}", criteria);
        return ResponseEntity.ok().body(orderDeliveryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /order-deliveries/:id} : get the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderDeliveryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-deliveries/{id}")
    public ResponseEntity<OrderDeliveryDTO> getOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to get OrderDelivery : {}", id);
        Optional<OrderDeliveryDTO> orderDeliveryDTO = orderDeliveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderDeliveryDTO);
    }

    /**
     * {@code DELETE  /order-deliveries/:id} : delete the "id" orderDelivery.
     *
     * @param id the id of the orderDeliveryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-deliveries/{id}")
    public ResponseEntity<Void> deleteOrderDelivery(@PathVariable Long id) {
        log.debug("REST request to delete OrderDelivery : {}", id);
        orderDeliveryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
