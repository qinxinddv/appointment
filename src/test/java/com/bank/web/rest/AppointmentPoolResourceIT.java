package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.AppointmentPool;
import com.bank.repository.AppointmentPoolRepository;
import com.bank.service.AppointmentPoolService;
import com.bank.service.dto.AppointmentPoolDTO;
import com.bank.service.mapper.AppointmentPoolMapper;
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

import com.bank.domain.enumeration.BusiTypeEnum;
/**
 * Integration tests for the {@link AppointmentPoolResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class AppointmentPoolResourceIT {

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD = "BBBBBBBBBB";

    private static final Integer DEFAULT_TOTAL_NUM = 1;
    private static final Integer UPDATED_TOTAL_NUM = 2;

    private static final Integer DEFAULT_LEFT_NUM = 1;
    private static final Integer UPDATED_LEFT_NUM = 2;

    private static final BusiTypeEnum DEFAULT_BUSI_TYPE = BusiTypeEnum.PERSON;
    private static final BusiTypeEnum UPDATED_BUSI_TYPE = BusiTypeEnum.ORG;

    @Autowired
    private AppointmentPoolRepository appointmentPoolRepository;

    @Autowired
    private AppointmentPoolMapper appointmentPoolMapper;

    @Autowired
    private AppointmentPoolService appointmentPoolService;

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

    private MockMvc restAppointmentPoolMockMvc;

    private AppointmentPool appointmentPool;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppointmentPoolResource appointmentPoolResource = new AppointmentPoolResource(appointmentPoolService);
        this.restAppointmentPoolMockMvc = MockMvcBuilders.standaloneSetup(appointmentPoolResource)
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
    public static AppointmentPool createEntity(EntityManager em) {
        AppointmentPool appointmentPool = new AppointmentPool()
            .date(DEFAULT_DATE)
            .period(DEFAULT_PERIOD)
            .totalNum(DEFAULT_TOTAL_NUM)
            .leftNum(DEFAULT_LEFT_NUM)
            .busiType(DEFAULT_BUSI_TYPE);
        return appointmentPool;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppointmentPool createUpdatedEntity(EntityManager em) {
        AppointmentPool appointmentPool = new AppointmentPool()
            .date(UPDATED_DATE)
            .period(UPDATED_PERIOD)
            .totalNum(UPDATED_TOTAL_NUM)
            .leftNum(UPDATED_LEFT_NUM)
            .busiType(UPDATED_BUSI_TYPE);
        return appointmentPool;
    }

    @BeforeEach
    public void initTest() {
        appointmentPool = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointmentPool() throws Exception {
        int databaseSizeBeforeCreate = appointmentPoolRepository.findAll().size();

        // Create the AppointmentPool
        AppointmentPoolDTO appointmentPoolDTO = appointmentPoolMapper.toDto(appointmentPool);
        restAppointmentPoolMockMvc.perform(post("/api/appointment-pools")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentPoolDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentPool in the database
        List<AppointmentPool> appointmentPoolList = appointmentPoolRepository.findAll();
        assertThat(appointmentPoolList).hasSize(databaseSizeBeforeCreate + 1);
        AppointmentPool testAppointmentPool = appointmentPoolList.get(appointmentPoolList.size() - 1);
        assertThat(testAppointmentPool.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAppointmentPool.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testAppointmentPool.getTotalNum()).isEqualTo(DEFAULT_TOTAL_NUM);
        assertThat(testAppointmentPool.getLeftNum()).isEqualTo(DEFAULT_LEFT_NUM);
        assertThat(testAppointmentPool.getBusiType()).isEqualTo(DEFAULT_BUSI_TYPE);
    }

    @Test
    @Transactional
    public void createAppointmentPoolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentPoolRepository.findAll().size();

        // Create the AppointmentPool with an existing ID
        appointmentPool.setId(1L);
        AppointmentPoolDTO appointmentPoolDTO = appointmentPoolMapper.toDto(appointmentPool);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentPoolMockMvc.perform(post("/api/appointment-pools")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentPoolDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentPool in the database
        List<AppointmentPool> appointmentPoolList = appointmentPoolRepository.findAll();
        assertThat(appointmentPoolList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppointmentPools() throws Exception {
        // Initialize the database
        appointmentPoolRepository.saveAndFlush(appointmentPool);

        // Get all the appointmentPoolList
        restAppointmentPoolMockMvc.perform(get("/api/appointment-pools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointmentPool.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].period").value(hasItem(DEFAULT_PERIOD)))
            .andExpect(jsonPath("$.[*].totalNum").value(hasItem(DEFAULT_TOTAL_NUM)))
            .andExpect(jsonPath("$.[*].leftNum").value(hasItem(DEFAULT_LEFT_NUM)))
            .andExpect(jsonPath("$.[*].busiType").value(hasItem(DEFAULT_BUSI_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getAppointmentPool() throws Exception {
        // Initialize the database
        appointmentPoolRepository.saveAndFlush(appointmentPool);

        // Get the appointmentPool
        restAppointmentPoolMockMvc.perform(get("/api/appointment-pools/{id}", appointmentPool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appointmentPool.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.period").value(DEFAULT_PERIOD))
            .andExpect(jsonPath("$.totalNum").value(DEFAULT_TOTAL_NUM))
            .andExpect(jsonPath("$.leftNum").value(DEFAULT_LEFT_NUM))
            .andExpect(jsonPath("$.busiType").value(DEFAULT_BUSI_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppointmentPool() throws Exception {
        // Get the appointmentPool
        restAppointmentPoolMockMvc.perform(get("/api/appointment-pools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointmentPool() throws Exception {
        // Initialize the database
        appointmentPoolRepository.saveAndFlush(appointmentPool);

        int databaseSizeBeforeUpdate = appointmentPoolRepository.findAll().size();

        // Update the appointmentPool
        AppointmentPool updatedAppointmentPool = appointmentPoolRepository.findById(appointmentPool.getId()).get();
        // Disconnect from session so that the updates on updatedAppointmentPool are not directly saved in db
        em.detach(updatedAppointmentPool);
        updatedAppointmentPool
            .date(UPDATED_DATE)
            .period(UPDATED_PERIOD)
            .totalNum(UPDATED_TOTAL_NUM)
            .leftNum(UPDATED_LEFT_NUM)
            .busiType(UPDATED_BUSI_TYPE);
        AppointmentPoolDTO appointmentPoolDTO = appointmentPoolMapper.toDto(updatedAppointmentPool);

        restAppointmentPoolMockMvc.perform(put("/api/appointment-pools")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentPoolDTO)))
            .andExpect(status().isOk());

        // Validate the AppointmentPool in the database
        List<AppointmentPool> appointmentPoolList = appointmentPoolRepository.findAll();
        assertThat(appointmentPoolList).hasSize(databaseSizeBeforeUpdate);
        AppointmentPool testAppointmentPool = appointmentPoolList.get(appointmentPoolList.size() - 1);
        assertThat(testAppointmentPool.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAppointmentPool.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testAppointmentPool.getTotalNum()).isEqualTo(UPDATED_TOTAL_NUM);
        assertThat(testAppointmentPool.getLeftNum()).isEqualTo(UPDATED_LEFT_NUM);
        assertThat(testAppointmentPool.getBusiType()).isEqualTo(UPDATED_BUSI_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointmentPool() throws Exception {
        int databaseSizeBeforeUpdate = appointmentPoolRepository.findAll().size();

        // Create the AppointmentPool
        AppointmentPoolDTO appointmentPoolDTO = appointmentPoolMapper.toDto(appointmentPool);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppointmentPoolMockMvc.perform(put("/api/appointment-pools")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentPoolDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentPool in the database
        List<AppointmentPool> appointmentPoolList = appointmentPoolRepository.findAll();
        assertThat(appointmentPoolList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppointmentPool() throws Exception {
        // Initialize the database
        appointmentPoolRepository.saveAndFlush(appointmentPool);

        int databaseSizeBeforeDelete = appointmentPoolRepository.findAll().size();

        // Delete the appointmentPool
        restAppointmentPoolMockMvc.perform(delete("/api/appointment-pools/{id}", appointmentPool.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppointmentPool> appointmentPoolList = appointmentPoolRepository.findAll();
        assertThat(appointmentPoolList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
