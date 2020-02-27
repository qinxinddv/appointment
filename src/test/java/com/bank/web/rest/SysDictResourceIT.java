package com.bank.web.rest;

import com.bank.AppointmentApp;
import com.bank.domain.SysDict;
import com.bank.repository.SysDictRepository;
import com.bank.service.SysDictService;
import com.bank.service.dto.SysDictDTO;
import com.bank.service.mapper.SysDictMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.bank.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SysDictResource} REST controller.
 */
@SpringBootTest(classes = AppointmentApp.class)
public class SysDictResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final Long DEFAULT_PARENT_ID = 1L;
    private static final Long UPDATED_PARENT_ID = 2L;

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_MODIFIED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFIED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private SysDictService sysDictService;

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

    private MockMvc restSysDictMockMvc;

    private SysDict sysDict;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysDictResource sysDictResource = new SysDictResource(sysDictService);
        this.restSysDictMockMvc = MockMvcBuilders.standaloneSetup(sysDictResource)
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
    public static SysDict createEntity(EntityManager em) {
        SysDict sysDict = new SysDict()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .code(DEFAULT_CODE)
            .value(DEFAULT_VALUE)
            .parentId(DEFAULT_PARENT_ID)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        return sysDict;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysDict createUpdatedEntity(EntityManager em) {
        SysDict sysDict = new SysDict()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .code(UPDATED_CODE)
            .value(UPDATED_VALUE)
            .parentId(UPDATED_PARENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        return sysDict;
    }

    @BeforeEach
    public void initTest() {
        sysDict = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysDict() throws Exception {
        int databaseSizeBeforeCreate = sysDictRepository.findAll().size();

        // Create the SysDict
        SysDictDTO sysDictDTO = sysDictMapper.toDto(sysDict);
        restSysDictMockMvc.perform(post("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sysDictDTO)))
            .andExpect(status().isCreated());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeCreate + 1);
        SysDict testSysDict = sysDictList.get(sysDictList.size() - 1);
        assertThat(testSysDict.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSysDict.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSysDict.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testSysDict.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testSysDict.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testSysDict.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSysDict.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createSysDictWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysDictRepository.findAll().size();

        // Create the SysDict with an existing ID
        sysDict.setId(1L);
        SysDictDTO sysDictDTO = sysDictMapper.toDto(sysDict);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysDictMockMvc.perform(post("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sysDictDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSysDicts() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        // Get all the sysDictList
        restSysDictMockMvc.perform(get("/api/sys-dicts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysDict.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getSysDict() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        // Get the sysDict
        restSysDictMockMvc.perform(get("/api/sys-dicts/{id}", sysDict.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sysDict.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID.intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSysDict() throws Exception {
        // Get the sysDict
        restSysDictMockMvc.perform(get("/api/sys-dicts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysDict() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        int databaseSizeBeforeUpdate = sysDictRepository.findAll().size();

        // Update the sysDict
        SysDict updatedSysDict = sysDictRepository.findById(sysDict.getId()).get();
        // Disconnect from session so that the updates on updatedSysDict are not directly saved in db
        em.detach(updatedSysDict);
        updatedSysDict
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .code(UPDATED_CODE)
            .value(UPDATED_VALUE)
            .parentId(UPDATED_PARENT_ID)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        SysDictDTO sysDictDTO = sysDictMapper.toDto(updatedSysDict);

        restSysDictMockMvc.perform(put("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sysDictDTO)))
            .andExpect(status().isOk());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeUpdate);
        SysDict testSysDict = sysDictList.get(sysDictList.size() - 1);
        assertThat(testSysDict.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSysDict.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSysDict.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testSysDict.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testSysDict.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testSysDict.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSysDict.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSysDict() throws Exception {
        int databaseSizeBeforeUpdate = sysDictRepository.findAll().size();

        // Create the SysDict
        SysDictDTO sysDictDTO = sysDictMapper.toDto(sysDict);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysDictMockMvc.perform(put("/api/sys-dicts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sysDictDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysDict in the database
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysDict() throws Exception {
        // Initialize the database
        sysDictRepository.saveAndFlush(sysDict);

        int databaseSizeBeforeDelete = sysDictRepository.findAll().size();

        // Delete the sysDict
        restSysDictMockMvc.perform(delete("/api/sys-dicts/{id}", sysDict.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysDict> sysDictList = sysDictRepository.findAll();
        assertThat(sysDictList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
