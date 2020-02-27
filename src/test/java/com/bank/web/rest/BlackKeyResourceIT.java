package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.BlackKey;
import com.bank.repository.BlackKeyRepository;
import com.bank.service.BlackKeyService;
import com.bank.service.dto.BlackKeyDTO;
import com.bank.service.mapper.BlackKeyMapper;
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
 * Integration tests for the {@link BlackKeyResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class BlackKeyResourceIT {

    private static final String DEFAULT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_KEY = "BBBBBBBBBB";

    @Autowired
    private BlackKeyRepository blackKeyRepository;

    @Autowired
    private BlackKeyMapper blackKeyMapper;

    @Autowired
    private BlackKeyService blackKeyService;

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

    private MockMvc restBlackKeyMockMvc;

    private BlackKey blackKey;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlackKeyResource blackKeyResource = new BlackKeyResource(blackKeyService);
        this.restBlackKeyMockMvc = MockMvcBuilders.standaloneSetup(blackKeyResource)
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
    public static BlackKey createEntity(EntityManager em) {
        BlackKey blackKey = new BlackKey()
            .key(DEFAULT_KEY);
        return blackKey;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BlackKey createUpdatedEntity(EntityManager em) {
        BlackKey blackKey = new BlackKey()
            .key(UPDATED_KEY);
        return blackKey;
    }

    @BeforeEach
    public void initTest() {
        blackKey = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlackKey() throws Exception {
        int databaseSizeBeforeCreate = blackKeyRepository.findAll().size();

        // Create the BlackKey
        BlackKeyDTO blackKeyDTO = blackKeyMapper.toDto(blackKey);
        restBlackKeyMockMvc.perform(post("/api/black-keys")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackKeyDTO)))
            .andExpect(status().isCreated());

        // Validate the BlackKey in the database
        List<BlackKey> blackKeyList = blackKeyRepository.findAll();
        assertThat(blackKeyList).hasSize(databaseSizeBeforeCreate + 1);
        BlackKey testBlackKey = blackKeyList.get(blackKeyList.size() - 1);
        assertThat(testBlackKey.getKey()).isEqualTo(DEFAULT_KEY);
    }

    @Test
    @Transactional
    public void createBlackKeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blackKeyRepository.findAll().size();

        // Create the BlackKey with an existing ID
        blackKey.setId(1L);
        BlackKeyDTO blackKeyDTO = blackKeyMapper.toDto(blackKey);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlackKeyMockMvc.perform(post("/api/black-keys")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackKeyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BlackKey in the database
        List<BlackKey> blackKeyList = blackKeyRepository.findAll();
        assertThat(blackKeyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBlackKeys() throws Exception {
        // Initialize the database
        blackKeyRepository.saveAndFlush(blackKey);

        // Get all the blackKeyList
        restBlackKeyMockMvc.perform(get("/api/black-keys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blackKey.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY)));
    }
    
    @Test
    @Transactional
    public void getBlackKey() throws Exception {
        // Initialize the database
        blackKeyRepository.saveAndFlush(blackKey);

        // Get the blackKey
        restBlackKeyMockMvc.perform(get("/api/black-keys/{id}", blackKey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(blackKey.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY));
    }

    @Test
    @Transactional
    public void getNonExistingBlackKey() throws Exception {
        // Get the blackKey
        restBlackKeyMockMvc.perform(get("/api/black-keys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlackKey() throws Exception {
        // Initialize the database
        blackKeyRepository.saveAndFlush(blackKey);

        int databaseSizeBeforeUpdate = blackKeyRepository.findAll().size();

        // Update the blackKey
        BlackKey updatedBlackKey = blackKeyRepository.findById(blackKey.getId()).get();
        // Disconnect from session so that the updates on updatedBlackKey are not directly saved in db
        em.detach(updatedBlackKey);
        updatedBlackKey
            .key(UPDATED_KEY);
        BlackKeyDTO blackKeyDTO = blackKeyMapper.toDto(updatedBlackKey);

        restBlackKeyMockMvc.perform(put("/api/black-keys")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackKeyDTO)))
            .andExpect(status().isOk());

        // Validate the BlackKey in the database
        List<BlackKey> blackKeyList = blackKeyRepository.findAll();
        assertThat(blackKeyList).hasSize(databaseSizeBeforeUpdate);
        BlackKey testBlackKey = blackKeyList.get(blackKeyList.size() - 1);
        assertThat(testBlackKey.getKey()).isEqualTo(UPDATED_KEY);
    }

    @Test
    @Transactional
    public void updateNonExistingBlackKey() throws Exception {
        int databaseSizeBeforeUpdate = blackKeyRepository.findAll().size();

        // Create the BlackKey
        BlackKeyDTO blackKeyDTO = blackKeyMapper.toDto(blackKey);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlackKeyMockMvc.perform(put("/api/black-keys")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(blackKeyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BlackKey in the database
        List<BlackKey> blackKeyList = blackKeyRepository.findAll();
        assertThat(blackKeyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlackKey() throws Exception {
        // Initialize the database
        blackKeyRepository.saveAndFlush(blackKey);

        int databaseSizeBeforeDelete = blackKeyRepository.findAll().size();

        // Delete the blackKey
        restBlackKeyMockMvc.perform(delete("/api/black-keys/{id}", blackKey.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BlackKey> blackKeyList = blackKeyRepository.findAll();
        assertThat(blackKeyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
