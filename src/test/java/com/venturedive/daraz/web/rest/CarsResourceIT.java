package com.venturedive.daraz.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.venturedive.daraz.IntegrationTest;
import com.venturedive.daraz.domain.Cars;
import com.venturedive.daraz.domain.Colors;
import com.venturedive.daraz.repository.CarsRepository;
import com.venturedive.daraz.service.criteria.CarsCriteria;
import com.venturedive.daraz.service.dto.CarsDTO;
import com.venturedive.daraz.service.mapper.CarsMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CarsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CarsResourceIT {

    private static final Integer DEFAULT_CARUID = 1;
    private static final Integer UPDATED_CARUID = 2;
    private static final Integer SMALLER_CARUID = 1 - 1;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cars";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private CarsMapper carsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCarsMockMvc;

    private Cars cars;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cars createEntity(EntityManager em) {
        Cars cars = new Cars().caruid(DEFAULT_CARUID).name(DEFAULT_NAME);
        return cars;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cars createUpdatedEntity(EntityManager em) {
        Cars cars = new Cars().caruid(UPDATED_CARUID).name(UPDATED_NAME);
        return cars;
    }

    @BeforeEach
    public void initTest() {
        cars = createEntity(em);
    }

    @Test
    @Transactional
    void createCars() throws Exception {
        int databaseSizeBeforeCreate = carsRepository.findAll().size();
        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);
        restCarsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isCreated());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeCreate + 1);
        Cars testCars = carsList.get(carsList.size() - 1);
        assertThat(testCars.getCaruid()).isEqualTo(DEFAULT_CARUID);
        assertThat(testCars.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createCarsWithExistingId() throws Exception {
        // Create the Cars with an existing ID
        cars.setId(1L);
        CarsDTO carsDTO = carsMapper.toDto(cars);

        int databaseSizeBeforeCreate = carsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCarsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCaruidIsRequired() throws Exception {
        int databaseSizeBeforeTest = carsRepository.findAll().size();
        // set the field null
        cars.setCaruid(null);

        // Create the Cars, which fails.
        CarsDTO carsDTO = carsMapper.toDto(cars);

        restCarsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isBadRequest());

        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = carsRepository.findAll().size();
        // set the field null
        cars.setName(null);

        // Create the Cars, which fails.
        CarsDTO carsDTO = carsMapper.toDto(cars);

        restCarsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isBadRequest());

        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCars() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList
        restCarsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cars.getId().intValue())))
            .andExpect(jsonPath("$.[*].caruid").value(hasItem(DEFAULT_CARUID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getCars() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get the cars
        restCarsMockMvc
            .perform(get(ENTITY_API_URL_ID, cars.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cars.getId().intValue()))
            .andExpect(jsonPath("$.caruid").value(DEFAULT_CARUID))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getCarsByIdFiltering() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        Long id = cars.getId();

        defaultCarsShouldBeFound("id.equals=" + id);
        defaultCarsShouldNotBeFound("id.notEquals=" + id);

        defaultCarsShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCarsShouldNotBeFound("id.greaterThan=" + id);

        defaultCarsShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCarsShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsEqualToSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid equals to DEFAULT_CARUID
        defaultCarsShouldBeFound("caruid.equals=" + DEFAULT_CARUID);

        // Get all the carsList where caruid equals to UPDATED_CARUID
        defaultCarsShouldNotBeFound("caruid.equals=" + UPDATED_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsInShouldWork() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid in DEFAULT_CARUID or UPDATED_CARUID
        defaultCarsShouldBeFound("caruid.in=" + DEFAULT_CARUID + "," + UPDATED_CARUID);

        // Get all the carsList where caruid equals to UPDATED_CARUID
        defaultCarsShouldNotBeFound("caruid.in=" + UPDATED_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsNullOrNotNull() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid is not null
        defaultCarsShouldBeFound("caruid.specified=true");

        // Get all the carsList where caruid is null
        defaultCarsShouldNotBeFound("caruid.specified=false");
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid is greater than or equal to DEFAULT_CARUID
        defaultCarsShouldBeFound("caruid.greaterThanOrEqual=" + DEFAULT_CARUID);

        // Get all the carsList where caruid is greater than or equal to UPDATED_CARUID
        defaultCarsShouldNotBeFound("caruid.greaterThanOrEqual=" + UPDATED_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid is less than or equal to DEFAULT_CARUID
        defaultCarsShouldBeFound("caruid.lessThanOrEqual=" + DEFAULT_CARUID);

        // Get all the carsList where caruid is less than or equal to SMALLER_CARUID
        defaultCarsShouldNotBeFound("caruid.lessThanOrEqual=" + SMALLER_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsLessThanSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid is less than DEFAULT_CARUID
        defaultCarsShouldNotBeFound("caruid.lessThan=" + DEFAULT_CARUID);

        // Get all the carsList where caruid is less than UPDATED_CARUID
        defaultCarsShouldBeFound("caruid.lessThan=" + UPDATED_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByCaruidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where caruid is greater than DEFAULT_CARUID
        defaultCarsShouldNotBeFound("caruid.greaterThan=" + DEFAULT_CARUID);

        // Get all the carsList where caruid is greater than SMALLER_CARUID
        defaultCarsShouldBeFound("caruid.greaterThan=" + SMALLER_CARUID);
    }

    @Test
    @Transactional
    void getAllCarsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where name equals to DEFAULT_NAME
        defaultCarsShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the carsList where name equals to UPDATED_NAME
        defaultCarsShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCarsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where name in DEFAULT_NAME or UPDATED_NAME
        defaultCarsShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the carsList where name equals to UPDATED_NAME
        defaultCarsShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCarsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where name is not null
        defaultCarsShouldBeFound("name.specified=true");

        // Get all the carsList where name is null
        defaultCarsShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllCarsByNameContainsSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where name contains DEFAULT_NAME
        defaultCarsShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the carsList where name contains UPDATED_NAME
        defaultCarsShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCarsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        // Get all the carsList where name does not contain DEFAULT_NAME
        defaultCarsShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the carsList where name does not contain UPDATED_NAME
        defaultCarsShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllCarsByColorIsEqualToSomething() throws Exception {
        Colors color;
        if (TestUtil.findAll(em, Colors.class).isEmpty()) {
            carsRepository.saveAndFlush(cars);
            color = ColorsResourceIT.createEntity(em);
        } else {
            color = TestUtil.findAll(em, Colors.class).get(0);
        }
        em.persist(color);
        em.flush();
        cars.addColor(color);
        carsRepository.saveAndFlush(cars);
        Long colorId = color.getId();

        // Get all the carsList where color equals to colorId
        defaultCarsShouldBeFound("colorId.equals=" + colorId);

        // Get all the carsList where color equals to (colorId + 1)
        defaultCarsShouldNotBeFound("colorId.equals=" + (colorId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCarsShouldBeFound(String filter) throws Exception {
        restCarsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cars.getId().intValue())))
            .andExpect(jsonPath("$.[*].caruid").value(hasItem(DEFAULT_CARUID)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restCarsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCarsShouldNotBeFound(String filter) throws Exception {
        restCarsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCarsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCars() throws Exception {
        // Get the cars
        restCarsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCars() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        int databaseSizeBeforeUpdate = carsRepository.findAll().size();

        // Update the cars
        Cars updatedCars = carsRepository.findById(cars.getId()).get();
        // Disconnect from session so that the updates on updatedCars are not directly saved in db
        em.detach(updatedCars);
        updatedCars.caruid(UPDATED_CARUID).name(UPDATED_NAME);
        CarsDTO carsDTO = carsMapper.toDto(updatedCars);

        restCarsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
        Cars testCars = carsList.get(carsList.size() - 1);
        assertThat(testCars.getCaruid()).isEqualTo(UPDATED_CARUID);
        assertThat(testCars.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, carsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(carsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCarsWithPatch() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        int databaseSizeBeforeUpdate = carsRepository.findAll().size();

        // Update the cars using partial update
        Cars partialUpdatedCars = new Cars();
        partialUpdatedCars.setId(cars.getId());

        partialUpdatedCars.caruid(UPDATED_CARUID).name(UPDATED_NAME);

        restCarsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCars.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCars))
            )
            .andExpect(status().isOk());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
        Cars testCars = carsList.get(carsList.size() - 1);
        assertThat(testCars.getCaruid()).isEqualTo(UPDATED_CARUID);
        assertThat(testCars.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateCarsWithPatch() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        int databaseSizeBeforeUpdate = carsRepository.findAll().size();

        // Update the cars using partial update
        Cars partialUpdatedCars = new Cars();
        partialUpdatedCars.setId(cars.getId());

        partialUpdatedCars.caruid(UPDATED_CARUID).name(UPDATED_NAME);

        restCarsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCars.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCars))
            )
            .andExpect(status().isOk());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
        Cars testCars = carsList.get(carsList.size() - 1);
        assertThat(testCars.getCaruid()).isEqualTo(UPDATED_CARUID);
        assertThat(testCars.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, carsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(carsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(carsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCars() throws Exception {
        int databaseSizeBeforeUpdate = carsRepository.findAll().size();
        cars.setId(count.incrementAndGet());

        // Create the Cars
        CarsDTO carsDTO = carsMapper.toDto(cars);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCarsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(carsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cars in the database
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCars() throws Exception {
        // Initialize the database
        carsRepository.saveAndFlush(cars);

        int databaseSizeBeforeDelete = carsRepository.findAll().size();

        // Delete the cars
        restCarsMockMvc
            .perform(delete(ENTITY_API_URL_ID, cars.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cars> carsList = carsRepository.findAll();
        assertThat(carsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
