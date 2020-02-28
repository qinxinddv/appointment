package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.Community;
import com.bank.repository.CommunityRepository;
import com.bank.service.CommunityService;
import com.bank.service.dto.CommunityDTO;
import com.bank.service.mapper.CommunityMapper;
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

import com.bank.domain.enumeration.CommunityStateEnum;
/**
 * Integration tests for the {@link CommunityResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class CommunityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final CommunityStateEnum DEFAULT_COMMUNITY_STATE_ENUM = CommunityStateEnum.NORMAL;
    private static final CommunityStateEnum UPDATED_COMMUNITY_STATE_ENUM = CommunityStateEnum.FORBID;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CommunityService communityService;

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

    private MockMvc restCommunityMockMvc;

    private Community community;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CommunityResource communityResource = new CommunityResource(communityService);
        this.restCommunityMockMvc = MockMvcBuilders.standaloneSetup(communityResource)
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
    public static Community createEntity(EntityManager em) {
        Community community = new Community()
            .name(DEFAULT_NAME)
            .addr(DEFAULT_ADDR)
            .state(DEFAULT_STATE)
            .communityStateEnum(DEFAULT_COMMUNITY_STATE_ENUM);
        return community;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Community createUpdatedEntity(EntityManager em) {
        Community community = new Community()
            .name(UPDATED_NAME)
            .addr(UPDATED_ADDR)
            .state(UPDATED_STATE)
            .communityStateEnum(UPDATED_COMMUNITY_STATE_ENUM);
        return community;
    }

    @BeforeEach
    public void initTest() {
        community = createEntity(em);
    }

    @Test
    @Transactional
    public void createCommunity() throws Exception {
        int databaseSizeBeforeCreate = communityRepository.findAll().size();

        // Create the Community
        CommunityDTO communityDTO = communityMapper.toDto(community);
        restCommunityMockMvc.perform(post("/api/communities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communityDTO)))
            .andExpect(status().isCreated());

        // Validate the Community in the database
        List<Community> communityList = communityRepository.findAll();
        assertThat(communityList).hasSize(databaseSizeBeforeCreate + 1);
        Community testCommunity = communityList.get(communityList.size() - 1);
        assertThat(testCommunity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCommunity.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testCommunity.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testCommunity.getCommunityStateEnum()).isEqualTo(DEFAULT_COMMUNITY_STATE_ENUM);
    }

    @Test
    @Transactional
    public void createCommunityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = communityRepository.findAll().size();

        // Create the Community with an existing ID
        community.setId(1L);
        CommunityDTO communityDTO = communityMapper.toDto(community);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunityMockMvc.perform(post("/api/communities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Community in the database
        List<Community> communityList = communityRepository.findAll();
        assertThat(communityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCommunities() throws Exception {
        // Initialize the database
        communityRepository.saveAndFlush(community);

        // Get all the communityList
        restCommunityMockMvc.perform(get("/api/communities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(community.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].communityStateEnum").value(hasItem(DEFAULT_COMMUNITY_STATE_ENUM.toString())));
    }
    
    @Test
    @Transactional
    public void getCommunity() throws Exception {
        // Initialize the database
        communityRepository.saveAndFlush(community);

        // Get the community
        restCommunityMockMvc.perform(get("/api/communities/{id}", community.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(community.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.communityStateEnum").value(DEFAULT_COMMUNITY_STATE_ENUM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCommunity() throws Exception {
        // Get the community
        restCommunityMockMvc.perform(get("/api/communities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCommunity() throws Exception {
        // Initialize the database
        communityRepository.saveAndFlush(community);

        int databaseSizeBeforeUpdate = communityRepository.findAll().size();

        // Update the community
        Community updatedCommunity = communityRepository.findById(community.getId()).get();
        // Disconnect from session so that the updates on updatedCommunity are not directly saved in db
        em.detach(updatedCommunity);
        updatedCommunity
            .name(UPDATED_NAME)
            .addr(UPDATED_ADDR)
            .state(UPDATED_STATE)
            .communityStateEnum(UPDATED_COMMUNITY_STATE_ENUM);
        CommunityDTO communityDTO = communityMapper.toDto(updatedCommunity);

        restCommunityMockMvc.perform(put("/api/communities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communityDTO)))
            .andExpect(status().isOk());

        // Validate the Community in the database
        List<Community> communityList = communityRepository.findAll();
        assertThat(communityList).hasSize(databaseSizeBeforeUpdate);
        Community testCommunity = communityList.get(communityList.size() - 1);
        assertThat(testCommunity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCommunity.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testCommunity.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testCommunity.getCommunityStateEnum()).isEqualTo(UPDATED_COMMUNITY_STATE_ENUM);
    }

    @Test
    @Transactional
    public void updateNonExistingCommunity() throws Exception {
        int databaseSizeBeforeUpdate = communityRepository.findAll().size();

        // Create the Community
        CommunityDTO communityDTO = communityMapper.toDto(community);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunityMockMvc.perform(put("/api/communities")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(communityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Community in the database
        List<Community> communityList = communityRepository.findAll();
        assertThat(communityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCommunity() throws Exception {
        // Initialize the database
        communityRepository.saveAndFlush(community);

        int databaseSizeBeforeDelete = communityRepository.findAll().size();

        // Delete the community
        restCommunityMockMvc.perform(delete("/api/communities/{id}", community.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Community> communityList = communityRepository.findAll();
        assertThat(communityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
