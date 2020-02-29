package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.Org;
import com.bank.repository.OrgRepository;
import com.bank.service.OrgService;
import com.bank.service.dto.OrgDTO;
import com.bank.service.mapper.OrgMapper;
import com.bank.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.bank.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrgResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class OrgResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private OrgRepository orgRepository;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private OrgService orgService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOrgMockMvc;

    private Org org;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrgResource orgResource = new OrgResource(orgService);
        this.restOrgMockMvc = MockMvcBuilders.standaloneSetup(orgResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Org createEntity(EntityManager em) {
        Org org = new Org()
            .name(DEFAULT_NAME)
            .addr(DEFAULT_ADDR)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return org;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Org createUpdatedEntity(EntityManager em) {
        Org org = new Org()
            .name(UPDATED_NAME)
            .addr(UPDATED_ADDR)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return org;
    }

    @BeforeEach
    public void initTest() {
        org = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrg() throws Exception {
        int databaseSizeBeforeCreate = orgRepository.findAll().size();

        // Create the Org
        OrgDTO orgDTO = orgMapper.toDto(org);
        restOrgMockMvc.perform(post("/api/orgs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orgDTO)))
            .andExpect(status().isCreated());

        // Validate the Org in the database
        List<Org> orgList = orgRepository.findAll();
        assertThat(orgList).hasSize(databaseSizeBeforeCreate + 1);
        Org testOrg = orgList.get(orgList.size() - 1);
        assertThat(testOrg.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOrg.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testOrg.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testOrg.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createOrgWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orgRepository.findAll().size();

        // Create the Org with an existing ID
        org.setId(1L);
        OrgDTO orgDTO = orgMapper.toDto(org);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrgMockMvc.perform(post("/api/orgs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orgDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Org in the database
        List<Org> orgList = orgRepository.findAll();
        assertThat(orgList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrgs() throws Exception {
        // Initialize the database
        orgRepository.saveAndFlush(org);

        // Get all the orgList
        restOrgMockMvc.perform(get("/api/orgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(org.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)));
    }
    
    @Test
    @Transactional
    public void getOrg() throws Exception {
        // Initialize the database
        orgRepository.saveAndFlush(org);

        // Get the org
        restOrgMockMvc.perform(get("/api/orgs/{id}", org.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(org.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE));
    }

    @Test
    @Transactional
    public void getNonExistingOrg() throws Exception {
        // Get the org
        restOrgMockMvc.perform(get("/api/orgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrg() throws Exception {
        // Initialize the database
        orgRepository.saveAndFlush(org);

        int databaseSizeBeforeUpdate = orgRepository.findAll().size();

        // Update the org
        Org updatedOrg = orgRepository.findById(org.getId()).get();
        // Disconnect from session so that the updates on updatedOrg are not directly saved in db
        em.detach(updatedOrg);
        updatedOrg
            .name(UPDATED_NAME)
            .addr(UPDATED_ADDR)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        OrgDTO orgDTO = orgMapper.toDto(updatedOrg);

        restOrgMockMvc.perform(put("/api/orgs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orgDTO)))
            .andExpect(status().isOk());

        // Validate the Org in the database
        List<Org> orgList = orgRepository.findAll();
        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
        Org testOrg = orgList.get(orgList.size() - 1);
        assertThat(testOrg.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOrg.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testOrg.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testOrg.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingOrg() throws Exception {
        int databaseSizeBeforeUpdate = orgRepository.findAll().size();

        // Create the Org
        OrgDTO orgDTO = orgMapper.toDto(org);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrgMockMvc.perform(put("/api/orgs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orgDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Org in the database
        List<Org> orgList = orgRepository.findAll();
        assertThat(orgList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrg() throws Exception {
        // Initialize the database
        orgRepository.saveAndFlush(org);

        int databaseSizeBeforeDelete = orgRepository.findAll().size();

        // Delete the org
        restOrgMockMvc.perform(delete("/api/orgs/{id}", org.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Org> orgList = orgRepository.findAll();
        assertThat(orgList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
