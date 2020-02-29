package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.AppointmentConfig;
import com.bank.repository.AppointmentConfigRepository;
import com.bank.service.custom.AppointmentConfigService;
import com.bank.service.dto.AppointmentConfigDTO;
import com.bank.service.mapper.AppointmentConfigMapper;
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
 * Integration tests for the {@link AppointmentConfigResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class AppointmentConfigResourceIT {

    private static final String DEFAULT_PERIOD = "AAAAAAAAAA";
    private static final String UPDATED_PERIOD = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM = 1;
    private static final Integer UPDATED_NUM = 2;

    private static final BusiTypeEnum DEFAULT_BUSI_TYPE = BusiTypeEnum.PERSON;
    private static final BusiTypeEnum UPDATED_BUSI_TYPE = BusiTypeEnum.ORG;

    @Autowired
    private AppointmentConfigRepository appointmentConfigRepository;

    @Autowired
    private AppointmentConfigMapper appointmentConfigMapper;

    @Autowired
    private AppointmentConfigService appointmentConfigService;

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

    private MockMvc restAppointmentConfigMockMvc;

    private AppointmentConfig appointmentConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppointmentConfigResource appointmentConfigResource = new AppointmentConfigResource(appointmentConfigService);
        this.restAppointmentConfigMockMvc = MockMvcBuilders.standaloneSetup(appointmentConfigResource)
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
    public static AppointmentConfig createEntity(EntityManager em) {
        AppointmentConfig appointmentConfig = new AppointmentConfig()
            .period(DEFAULT_PERIOD)
            .num(DEFAULT_NUM)
            .busiType(DEFAULT_BUSI_TYPE);
        return appointmentConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AppointmentConfig createUpdatedEntity(EntityManager em) {
        AppointmentConfig appointmentConfig = new AppointmentConfig()
            .period(UPDATED_PERIOD)
            .num(UPDATED_NUM)
            .busiType(UPDATED_BUSI_TYPE);
        return appointmentConfig;
    }

    @BeforeEach
    public void initTest() {
        appointmentConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointmentConfig() throws Exception {
        int databaseSizeBeforeCreate = appointmentConfigRepository.findAll().size();

        // Create the AppointmentConfig
        AppointmentConfigDTO appointmentConfigDTO = appointmentConfigMapper.toDto(appointmentConfig);
        restAppointmentConfigMockMvc.perform(post("/api/appointment-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentConfig in the database
        List<AppointmentConfig> appointmentConfigList = appointmentConfigRepository.findAll();
        assertThat(appointmentConfigList).hasSize(databaseSizeBeforeCreate + 1);
        AppointmentConfig testAppointmentConfig = appointmentConfigList.get(appointmentConfigList.size() - 1);
        assertThat(testAppointmentConfig.getPeriod()).isEqualTo(DEFAULT_PERIOD);
        assertThat(testAppointmentConfig.getNum()).isEqualTo(DEFAULT_NUM);
        assertThat(testAppointmentConfig.getBusiType()).isEqualTo(DEFAULT_BUSI_TYPE);
    }

    @Test
    @Transactional
    public void createAppointmentConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentConfigRepository.findAll().size();

        // Create the AppointmentConfig with an existing ID
        appointmentConfig.setId(1L);
        AppointmentConfigDTO appointmentConfigDTO = appointmentConfigMapper.toDto(appointmentConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentConfigMockMvc.perform(post("/api/appointment-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentConfig in the database
        List<AppointmentConfig> appointmentConfigList = appointmentConfigRepository.findAll();
        assertThat(appointmentConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppointmentConfigs() throws Exception {
        // Initialize the database
        appointmentConfigRepository.saveAndFlush(appointmentConfig);

        // Get all the appointmentConfigList
        restAppointmentConfigMockMvc.perform(get("/api/appointment-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointmentConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].period").value(hasItem(DEFAULT_PERIOD)))
            .andExpect(jsonPath("$.[*].num").value(hasItem(DEFAULT_NUM)))
            .andExpect(jsonPath("$.[*].busiType").value(hasItem(DEFAULT_BUSI_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getAppointmentConfig() throws Exception {
        // Initialize the database
        appointmentConfigRepository.saveAndFlush(appointmentConfig);

        // Get the appointmentConfig
        restAppointmentConfigMockMvc.perform(get("/api/appointment-configs/{id}", appointmentConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appointmentConfig.getId().intValue()))
            .andExpect(jsonPath("$.period").value(DEFAULT_PERIOD))
            .andExpect(jsonPath("$.num").value(DEFAULT_NUM))
            .andExpect(jsonPath("$.busiType").value(DEFAULT_BUSI_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppointmentConfig() throws Exception {
        // Get the appointmentConfig
        restAppointmentConfigMockMvc.perform(get("/api/appointment-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointmentConfig() throws Exception {
        // Initialize the database
        appointmentConfigRepository.saveAndFlush(appointmentConfig);

        int databaseSizeBeforeUpdate = appointmentConfigRepository.findAll().size();

        // Update the appointmentConfig
        AppointmentConfig updatedAppointmentConfig = appointmentConfigRepository.findById(appointmentConfig.getId()).get();
        // Disconnect from session so that the updates on updatedAppointmentConfig are not directly saved in db
        em.detach(updatedAppointmentConfig);
        updatedAppointmentConfig
            .period(UPDATED_PERIOD)
            .num(UPDATED_NUM)
            .busiType(UPDATED_BUSI_TYPE);
        AppointmentConfigDTO appointmentConfigDTO = appointmentConfigMapper.toDto(updatedAppointmentConfig);

        restAppointmentConfigMockMvc.perform(put("/api/appointment-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentConfigDTO)))
            .andExpect(status().isOk());

        // Validate the AppointmentConfig in the database
        List<AppointmentConfig> appointmentConfigList = appointmentConfigRepository.findAll();
        assertThat(appointmentConfigList).hasSize(databaseSizeBeforeUpdate);
        AppointmentConfig testAppointmentConfig = appointmentConfigList.get(appointmentConfigList.size() - 1);
        assertThat(testAppointmentConfig.getPeriod()).isEqualTo(UPDATED_PERIOD);
        assertThat(testAppointmentConfig.getNum()).isEqualTo(UPDATED_NUM);
        assertThat(testAppointmentConfig.getBusiType()).isEqualTo(UPDATED_BUSI_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointmentConfig() throws Exception {
        int databaseSizeBeforeUpdate = appointmentConfigRepository.findAll().size();

        // Create the AppointmentConfig
        AppointmentConfigDTO appointmentConfigDTO = appointmentConfigMapper.toDto(appointmentConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppointmentConfigMockMvc.perform(put("/api/appointment-configs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentConfig in the database
        List<AppointmentConfig> appointmentConfigList = appointmentConfigRepository.findAll();
        assertThat(appointmentConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppointmentConfig() throws Exception {
        // Initialize the database
        appointmentConfigRepository.saveAndFlush(appointmentConfig);

        int databaseSizeBeforeDelete = appointmentConfigRepository.findAll().size();

        // Delete the appointmentConfig
        restAppointmentConfigMockMvc.perform(delete("/api/appointment-configs/{id}", appointmentConfig.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AppointmentConfig> appointmentConfigList = appointmentConfigRepository.findAll();
        assertThat(appointmentConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
