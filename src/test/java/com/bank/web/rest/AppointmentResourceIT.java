package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.Appointment;
import com.bank.repository.AppointmentRepository;
import com.bank.service.AppointmentService;
import com.bank.service.dto.AppointmentDTO;
import com.bank.service.mapper.AppointmentMapper;
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
import java.time.*;
import java.util.List;

import static com.bank.web.rest.TestUtil.sameInstant;
import static com.bank.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bank.domain.enumeration.BusiTypeEnum;
import com.bank.domain.enumeration.AppointStateEnum;
/**
 * Integration tests for the {@link AppointmentResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class AppointmentResourceIT {

    private static final String DEFAULT_ID_CARD = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDR = "AAAAAAAAAA";
    private static final String UPDATED_ADDR = "BBBBBBBBBB";

    private static final String DEFAULT_TEMPERATURE = "AAAAAAAAAA";
    private static final String UPDATED_TEMPERATURE = "BBBBBBBBBB";

    private static final String DEFAULT_SYMPTOM = "AAAAAAAAAA";
    private static final String UPDATED_SYMPTOM = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_PERIOD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_PERIOD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TIME_PERIOD_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_TIME_PERIOD_VALUE = "BBBBBBBBBB";

    private static final BusiTypeEnum DEFAULT_BUSI_TYPE = BusiTypeEnum.PERSON;
    private static final BusiTypeEnum UPDATED_BUSI_TYPE = BusiTypeEnum.ORG;

    private static final AppointStateEnum DEFAULT_STATE = AppointStateEnum.UNDO;
    private static final AppointStateEnum UPDATED_STATE = AppointStateEnum.DO;

    private static final String DEFAULT_OPNION = "AAAAAAAAAA";
    private static final String UPDATED_OPNION = "BBBBBBBBBB";

    private static final LocalDateTime DEFAULT_APPLY_TIME = LocalDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_APPLY_TIME = LocalDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final LocalDateTime DEFAULT_OPNION_TIME = LocalDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_OPNION_TIME = LocalDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private AppointmentService appointmentService;

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

    private MockMvc restAppointmentMockMvc;

    private Appointment appointment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppointmentResource appointmentResource = new AppointmentResource(appointmentService);
        this.restAppointmentMockMvc = MockMvcBuilders.standaloneSetup(appointmentResource)
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
    public static Appointment createEntity(EntityManager em) {
        Appointment appointment = new Appointment()
            .idCard(DEFAULT_ID_CARD)
            .name(DEFAULT_NAME)
            .mobile(DEFAULT_MOBILE)
            .addr(DEFAULT_ADDR)
            .temperature(DEFAULT_TEMPERATURE)
            .symptom(DEFAULT_SYMPTOM)
            .timePeriodCode(DEFAULT_TIME_PERIOD_CODE)
            .timePeriodValue(DEFAULT_TIME_PERIOD_VALUE)
            .busiType(DEFAULT_BUSI_TYPE)
            .state(DEFAULT_STATE)
            .opnion(DEFAULT_OPNION)
            .applyTime(DEFAULT_APPLY_TIME)
            .opnionTime(DEFAULT_OPNION_TIME)
            .date(DEFAULT_DATE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return appointment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Appointment createUpdatedEntity(EntityManager em) {
        Appointment appointment = new Appointment()
            .idCard(UPDATED_ID_CARD)
            .name(UPDATED_NAME)
            .mobile(UPDATED_MOBILE)
            .addr(UPDATED_ADDR)
            .temperature(UPDATED_TEMPERATURE)
            .symptom(UPDATED_SYMPTOM)
            .timePeriodCode(UPDATED_TIME_PERIOD_CODE)
            .timePeriodValue(UPDATED_TIME_PERIOD_VALUE)
            .busiType(UPDATED_BUSI_TYPE)
            .state(UPDATED_STATE)
            .opnion(UPDATED_OPNION)
            .applyTime(UPDATED_APPLY_TIME)
            .opnionTime(UPDATED_OPNION_TIME)
            .date(UPDATED_DATE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return appointment;
    }

    @BeforeEach
    public void initTest() {
        appointment = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointment() throws Exception {
        int databaseSizeBeforeCreate = appointmentRepository.findAll().size();

        // Create the Appointment
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);
        restAppointmentMockMvc.perform(post("/api/appointments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeCreate + 1);
        Appointment testAppointment = appointmentList.get(appointmentList.size() - 1);
        assertThat(testAppointment.getIdCard()).isEqualTo(DEFAULT_ID_CARD);
        assertThat(testAppointment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAppointment.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testAppointment.getAddr()).isEqualTo(DEFAULT_ADDR);
        assertThat(testAppointment.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testAppointment.getSymptom()).isEqualTo(DEFAULT_SYMPTOM);
        assertThat(testAppointment.getTimePeriodCode()).isEqualTo(DEFAULT_TIME_PERIOD_CODE);
        assertThat(testAppointment.getTimePeriodValue()).isEqualTo(DEFAULT_TIME_PERIOD_VALUE);
        assertThat(testAppointment.getBusiType()).isEqualTo(DEFAULT_BUSI_TYPE);
        assertThat(testAppointment.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testAppointment.getOpnion()).isEqualTo(DEFAULT_OPNION);
        assertThat(testAppointment.getApplyTime()).isEqualTo(DEFAULT_APPLY_TIME);
        assertThat(testAppointment.getOpnionTime()).isEqualTo(DEFAULT_OPNION_TIME);
        assertThat(testAppointment.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testAppointment.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testAppointment.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createAppointmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentRepository.findAll().size();

        // Create the Appointment with an existing ID
        appointment.setId(1L);
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentMockMvc.perform(post("/api/appointments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAppointments() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        // Get all the appointmentList
        restAppointmentMockMvc.perform(get("/api/appointments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointment.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCard").value(hasItem(DEFAULT_ID_CARD)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].addr").value(hasItem(DEFAULT_ADDR)))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE)))
            .andExpect(jsonPath("$.[*].symptom").value(hasItem(DEFAULT_SYMPTOM)))
            .andExpect(jsonPath("$.[*].timePeriodCode").value(hasItem(DEFAULT_TIME_PERIOD_CODE)))
            .andExpect(jsonPath("$.[*].timePeriodValue").value(hasItem(DEFAULT_TIME_PERIOD_VALUE)))
            .andExpect(jsonPath("$.[*].busiType").value(hasItem(DEFAULT_BUSI_TYPE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].opnion").value(hasItem(DEFAULT_OPNION)))
            .andExpect(jsonPath("$.[*].applyTime").value(hasItem(sameInstant(DEFAULT_APPLY_TIME))))
            .andExpect(jsonPath("$.[*].opnionTime").value(hasItem(sameInstant(DEFAULT_OPNION_TIME))))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)));
    }

    @Test
    @Transactional
    public void getAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        // Get the appointment
        restAppointmentMockMvc.perform(get("/api/appointments/{id}", appointment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(appointment.getId().intValue()))
            .andExpect(jsonPath("$.idCard").value(DEFAULT_ID_CARD))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.addr").value(DEFAULT_ADDR))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE))
            .andExpect(jsonPath("$.symptom").value(DEFAULT_SYMPTOM))
            .andExpect(jsonPath("$.timePeriodCode").value(DEFAULT_TIME_PERIOD_CODE))
            .andExpect(jsonPath("$.timePeriodValue").value(DEFAULT_TIME_PERIOD_VALUE))
            .andExpect(jsonPath("$.busiType").value(DEFAULT_BUSI_TYPE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.opnion").value(DEFAULT_OPNION))
            .andExpect(jsonPath("$.applyTime").value(sameInstant(DEFAULT_APPLY_TIME)))
            .andExpect(jsonPath("$.opnionTime").value(sameInstant(DEFAULT_OPNION_TIME)))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE));
    }

    @Test
    @Transactional
    public void getNonExistingAppointment() throws Exception {
        // Get the appointment
        restAppointmentMockMvc.perform(get("/api/appointments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        int databaseSizeBeforeUpdate = appointmentRepository.findAll().size();

        // Update the appointment
        Appointment updatedAppointment = appointmentRepository.findById(appointment.getId()).get();
        // Disconnect from session so that the updates on updatedAppointment are not directly saved in db
        em.detach(updatedAppointment);
        updatedAppointment
            .idCard(UPDATED_ID_CARD)
            .name(UPDATED_NAME)
            .mobile(UPDATED_MOBILE)
            .addr(UPDATED_ADDR)
            .temperature(UPDATED_TEMPERATURE)
            .symptom(UPDATED_SYMPTOM)
            .timePeriodCode(UPDATED_TIME_PERIOD_CODE)
            .timePeriodValue(UPDATED_TIME_PERIOD_VALUE)
            .busiType(UPDATED_BUSI_TYPE)
            .state(UPDATED_STATE)
            .opnion(UPDATED_OPNION)
            .applyTime(UPDATED_APPLY_TIME)
            .opnionTime(UPDATED_OPNION_TIME)
            .date(UPDATED_DATE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(updatedAppointment);

        restAppointmentMockMvc.perform(put("/api/appointments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isOk());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeUpdate);
        Appointment testAppointment = appointmentList.get(appointmentList.size() - 1);
        assertThat(testAppointment.getIdCard()).isEqualTo(UPDATED_ID_CARD);
        assertThat(testAppointment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAppointment.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testAppointment.getAddr()).isEqualTo(UPDATED_ADDR);
        assertThat(testAppointment.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testAppointment.getSymptom()).isEqualTo(UPDATED_SYMPTOM);
        assertThat(testAppointment.getTimePeriodCode()).isEqualTo(UPDATED_TIME_PERIOD_CODE);
        assertThat(testAppointment.getTimePeriodValue()).isEqualTo(UPDATED_TIME_PERIOD_VALUE);
        assertThat(testAppointment.getBusiType()).isEqualTo(UPDATED_BUSI_TYPE);
        assertThat(testAppointment.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testAppointment.getOpnion()).isEqualTo(UPDATED_OPNION);
        assertThat(testAppointment.getApplyTime()).isEqualTo(UPDATED_APPLY_TIME);
        assertThat(testAppointment.getOpnionTime()).isEqualTo(UPDATED_OPNION_TIME);
        assertThat(testAppointment.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testAppointment.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testAppointment.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointment() throws Exception {
        int databaseSizeBeforeUpdate = appointmentRepository.findAll().size();

        // Create the Appointment
        AppointmentDTO appointmentDTO = appointmentMapper.toDto(appointment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAppointmentMockMvc.perform(put("/api/appointments")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(appointmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Appointment in the database
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAppointment() throws Exception {
        // Initialize the database
        appointmentRepository.saveAndFlush(appointment);

        int databaseSizeBeforeDelete = appointmentRepository.findAll().size();

        // Delete the appointment
        restAppointmentMockMvc.perform(delete("/api/appointments/{id}", appointment.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Appointment> appointmentList = appointmentRepository.findAll();
        assertThat(appointmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
