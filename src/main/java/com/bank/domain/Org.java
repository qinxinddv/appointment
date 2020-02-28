package com.bank.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 机构表
 */
@Entity
@Table(name = "org")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机构名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 地址
     */
    @Column(name = "addr")
    private String addr;

    /**
     * 坐标
     */
    @Column(name = "coordinate")
    private String coordinate;

    @OneToMany(mappedBy = "org")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "org")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AppointmentPool> appointmentPools = new HashSet<>();

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

    public Org name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public Org addr(String addr) {
        this.addr = addr;
        return this;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public Org coordinate(String coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public Org appointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    public Org addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setOrg(this);
        return this;
    }

    public Org removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
        appointment.setOrg(null);
        return this;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<AppointmentPool> getAppointmentPools() {
        return appointmentPools;
    }

    public Org appointmentPools(Set<AppointmentPool> appointmentPools) {
        this.appointmentPools = appointmentPools;
        return this;
    }

    public Org addAppointmentPool(AppointmentPool appointmentPool) {
        this.appointmentPools.add(appointmentPool);
        appointmentPool.setOrg(this);
        return this;
    }

    public Org removeAppointmentPool(AppointmentPool appointmentPool) {
        this.appointmentPools.remove(appointmentPool);
        appointmentPool.setOrg(null);
        return this;
    }

    public void setAppointmentPools(Set<AppointmentPool> appointmentPools) {
        this.appointmentPools = appointmentPools;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Org)) {
            return false;
        }
        return id != null && id.equals(((Org) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Org{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", addr='" + getAddr() + "'" +
            ", coordinate='" + getCoordinate() + "'" +
            "}";
    }
}
