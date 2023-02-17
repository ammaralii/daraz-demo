package com.venturedive.daraz.web.rest;

import com.venturedive.daraz.repository.ColorsRepository;
import com.venturedive.daraz.service.ColorsQueryService;
import com.venturedive.daraz.service.ColorsService;
import com.venturedive.daraz.service.criteria.ColorsCriteria;
import com.venturedive.daraz.service.dto.ColorsDTO;
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
 * REST controller for managing {@link com.venturedive.daraz.domain.Colors}.
 */
@RestController
@RequestMapping("/api")
public class ColorsResource {

    private final Logger log = LoggerFactory.getLogger(ColorsResource.class);

    private static final String ENTITY_NAME = "colors";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ColorsService colorsService;

    private final ColorsRepository colorsRepository;

    private final ColorsQueryService colorsQueryService;

    public ColorsResource(ColorsService colorsService, ColorsRepository colorsRepository, ColorsQueryService colorsQueryService) {
        this.colorsService = colorsService;
        this.colorsRepository = colorsRepository;
        this.colorsQueryService = colorsQueryService;
    }

    /**
     * {@code POST  /colors} : Create a new colors.
     *
     * @param colorsDTO the colorsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new colorsDTO, or with status {@code 400 (Bad Request)} if the colors has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/colors")
    public ResponseEntity<ColorsDTO> createColors(@Valid @RequestBody ColorsDTO colorsDTO) throws URISyntaxException {
        log.debug("REST request to save Colors : {}", colorsDTO);
        if (colorsDTO.getId() != null) {
            throw new BadRequestAlertException("A new colors cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ColorsDTO result = colorsService.save(colorsDTO);
        return ResponseEntity
            .created(new URI("/api/colors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /colors/:id} : Updates an existing colors.
     *
     * @param id the id of the colorsDTO to save.
     * @param colorsDTO the colorsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated colorsDTO,
     * or with status {@code 400 (Bad Request)} if the colorsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the colorsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/colors/{id}")
    public ResponseEntity<ColorsDTO> updateColors(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ColorsDTO colorsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Colors : {}, {}", id, colorsDTO);
        if (colorsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, colorsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!colorsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ColorsDTO result = colorsService.update(colorsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, colorsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /colors/:id} : Partial updates given fields of an existing colors, field will ignore if it is null
     *
     * @param id the id of the colorsDTO to save.
     * @param colorsDTO the colorsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated colorsDTO,
     * or with status {@code 400 (Bad Request)} if the colorsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the colorsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the colorsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/colors/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ColorsDTO> partialUpdateColors(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ColorsDTO colorsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Colors partially : {}, {}", id, colorsDTO);
        if (colorsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, colorsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!colorsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ColorsDTO> result = colorsService.partialUpdate(colorsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, colorsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /colors} : get all the colors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of colors in body.
     */
    @GetMapping("/colors")
    public ResponseEntity<List<ColorsDTO>> getAllColors(
        ColorsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Colors by criteria: {}", criteria);
        Page<ColorsDTO> page = colorsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /colors/count} : count all the colors.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/colors/count")
    public ResponseEntity<Long> countColors(ColorsCriteria criteria) {
        log.debug("REST request to count Colors by criteria: {}", criteria);
        return ResponseEntity.ok().body(colorsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /colors/:id} : get the "id" colors.
     *
     * @param id the id of the colorsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the colorsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/colors/{id}")
    public ResponseEntity<ColorsDTO> getColors(@PathVariable Long id) {
        log.debug("REST request to get Colors : {}", id);
        Optional<ColorsDTO> colorsDTO = colorsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(colorsDTO);
    }

    /**
     * {@code DELETE  /colors/:id} : delete the "id" colors.
     *
     * @param id the id of the colorsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/colors/{id}")
    public ResponseEntity<Void> deleteColors(@PathVariable Long id) {
        log.debug("REST request to delete Colors : {}", id);
        colorsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
