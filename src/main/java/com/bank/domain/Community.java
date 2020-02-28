package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.bank.domain.enumeration.CommunityStateEnum;

/**
 * 小区表
 */
@Entity
@Table(name = "community")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 小区名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 小区地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 小区状态
     */
    @Column(name = "state")
    private String state;

    /**
     * 小区状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "community_state_enum")
    private CommunityStateEnum communityStateEnum;

    @OneToMany(mappedBy = "community")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Appointment> appointments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Community name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public Community addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getState() {
        return state;
    }

    public Community state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CommunityStateEnum getCommunityStateEnum() {
        return communityStateEnum;
    }

    public Community communityStateEnum(CommunityStateEnum communityStateEnum) {
        this.communityStateEnum = communityStateEnum;
        return this;
    }

    public void setCommunityStateEnum(CommunityStateEnum communityStateEnum) {
        this.communityStateEnum = communityStateEnum;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public Community appointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    public Community addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setCommunity(this);
        return this;
    }

    public Community removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setCommunity(null);
        return this;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Community)) {
            return false;
        }
        return id != null && id.equals(((Community) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Community{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", state='" + getState() + "'" +
            ", communityStateEnum='" + getCommunityStateEnum() + "'" +
            "}";
    }
}
