import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IOrg } from 'app/shared/model/org.model';
import { getEntities as getOrgs } from 'app/entities/org/org.reducer';
import { getEntity, updateEntity, createEntity, reset } from './appointment.reducer';
import { IAppointment } from 'app/shared/model/appointment.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAppointmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentUpdate = (props: IAppointmentUpdateProps) => {
  const [orgId, setOrgId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { appointmentEntity, orgs, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/appointment' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getOrgs();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.applyTime = convertDateTimeToServer(values.applyTime);
    values.opnionTime = convertDateTimeToServer(values.opnionTime);

    if (errors.length === 0) {
      const entity = {
        ...appointmentEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="appointmentApp.appointment.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.appointment.home.createOrEditLabel">Create or edit a Appointment</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : appointmentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="appointment-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="appointment-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="idCardLabel" for="appointment-idCard">
                  <Translate contentKey="appointmentApp.appointment.idCard">Id Card</Translate>
                </Label>
                <AvField id="appointment-idCard" type="text" name="idCard" />
                <UncontrolledTooltip target="idCardLabel">
                  <Translate contentKey="appointmentApp.appointment.help.idCard" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="nameLabel" for="appointment-name">
                  <Translate contentKey="appointmentApp.appointment.name">Name</Translate>
                </Label>
                <AvField id="appointment-name" type="text" name="name" />
                <UncontrolledTooltip target="nameLabel">
                  <Translate contentKey="appointmentApp.appointment.help.name" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="mobileLabel" for="appointment-mobile">
                  <Translate contentKey="appointmentApp.appointment.mobile">Mobile</Translate>
                </Label>
                <AvField id="appointment-mobile" type="text" name="mobile" />
                <UncontrolledTooltip target="mobileLabel">
                  <Translate contentKey="appointmentApp.appointment.help.mobile" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addrLabel" for="appointment-addr">
                  <Translate contentKey="appointmentApp.appointment.addr">Addr</Translate>
                </Label>
                <AvField id="appointment-addr" type="text" name="addr" />
                <UncontrolledTooltip target="addrLabel">
                  <Translate contentKey="appointmentApp.appointment.help.addr" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="temperatureLabel" for="appointment-temperature">
                  <Translate contentKey="appointmentApp.appointment.temperature">Temperature</Translate>
                </Label>
                <AvField id="appointment-temperature" type="text" name="temperature" />
                <UncontrolledTooltip target="temperatureLabel">
                  <Translate contentKey="appointmentApp.appointment.help.temperature" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="symptomLabel" for="appointment-symptom">
                  <Translate contentKey="appointmentApp.appointment.symptom">Symptom</Translate>
                </Label>
                <AvField id="appointment-symptom" type="text" name="symptom" />
                <UncontrolledTooltip target="symptomLabel">
                  <Translate contentKey="appointmentApp.appointment.help.symptom" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="timePeriodCodeLabel" for="appointment-timePeriodCode">
                  <Translate contentKey="appointmentApp.appointment.timePeriodCode">Time Period Code</Translate>
                </Label>
                <AvField id="appointment-timePeriodCode" type="text" name="timePeriodCode" />
                <UncontrolledTooltip target="timePeriodCodeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.timePeriodCode" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="timePeriodValueLabel" for="appointment-timePeriodValue">
                  <Translate contentKey="appointmentApp.appointment.timePeriodValue">Time Period Value</Translate>
                </Label>
                <AvField id="appointment-timePeriodValue" type="text" name="timePeriodValue" />
                <UncontrolledTooltip target="timePeriodValueLabel">
                  <Translate contentKey="appointmentApp.appointment.help.timePeriodValue" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="busiTypeLabel" for="appointment-busiType">
                  <Translate contentKey="appointmentApp.appointment.busiType">Busi Type</Translate>
                </Label>
                <AvInput
                  id="appointment-busiType"
                  type="select"
                  className="form-control"
                  name="busiType"
                  value={(!isNew && appointmentEntity.busiType) || 'PERSON'}
                >
                  <option value="PERSON">{translate('appointmentApp.BusiTypeEnum.PERSON')}</option>
                  <option value="ORG">{translate('appointmentApp.BusiTypeEnum.ORG')}</option>
                  <option value="LAW">{translate('appointmentApp.BusiTypeEnum.LAW')}</option>
                </AvInput>
                <UncontrolledTooltip target="busiTypeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.busiType" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="stateLabel" for="appointment-state">
                  <Translate contentKey="appointmentApp.appointment.state">State</Translate>
                </Label>
                <AvInput
                  id="appointment-state"
                  type="select"
                  className="form-control"
                  name="state"
                  value={(!isNew && appointmentEntity.state) || 'UNDO'}
                >
                  <option value="UNDO">{translate('appointmentApp.AppointStateEnum.UNDO')}</option>
                  <option value="DO">{translate('appointmentApp.AppointStateEnum.DO')}</option>
                  <option value="OUTTIME">{translate('appointmentApp.AppointStateEnum.OUTTIME')}</option>
                </AvInput>
                <UncontrolledTooltip target="stateLabel">
                  <Translate contentKey="appointmentApp.appointment.help.state" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="opnionLabel" for="appointment-opnion">
                  <Translate contentKey="appointmentApp.appointment.opnion">Opnion</Translate>
                </Label>
                <AvField id="appointment-opnion" type="text" name="opnion" />
                <UncontrolledTooltip target="opnionLabel">
                  <Translate contentKey="appointmentApp.appointment.help.opnion" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="applyTimeLabel" for="appointment-applyTime">
                  <Translate contentKey="appointmentApp.appointment.applyTime">Apply Time</Translate>
                </Label>
                <AvInput
                  id="appointment-applyTime"
                  type="datetime-local"
                  className="form-control"
                  name="applyTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.appointmentEntity.applyTime)}
                />
                <UncontrolledTooltip target="applyTimeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.applyTime" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="opnionTimeLabel" for="appointment-opnionTime">
                  <Translate contentKey="appointmentApp.appointment.opnionTime">Opnion Time</Translate>
                </Label>
                <AvInput
                  id="appointment-opnionTime"
                  type="datetime-local"
                  className="form-control"
                  name="opnionTime"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.appointmentEntity.opnionTime)}
                />
                <UncontrolledTooltip target="opnionTimeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.opnionTime" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateLabel" for="appointment-date">
                  <Translate contentKey="appointmentApp.appointment.date">Date</Translate>
                </Label>
                <AvField id="appointment-date" type="text" name="date" />
                <UncontrolledTooltip target="dateLabel">
                  <Translate contentKey="appointmentApp.appointment.help.date" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="latitudeLabel" for="appointment-latitude">
                  <Translate contentKey="appointmentApp.appointment.latitude">Latitude</Translate>
                </Label>
                <AvField id="appointment-latitude" type="text" name="latitude" />
                <UncontrolledTooltip target="latitudeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.latitude" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="longitudeLabel" for="appointment-longitude">
                  <Translate contentKey="appointmentApp.appointment.longitude">Longitude</Translate>
                </Label>
                <AvField id="appointment-longitude" type="text" name="longitude" />
                <UncontrolledTooltip target="longitudeLabel">
                  <Translate contentKey="appointmentApp.appointment.help.longitude" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label for="appointment-org">
                  <Translate contentKey="appointmentApp.appointment.org">Org</Translate>
                </Label>
                <AvInput id="appointment-org" type="select" className="form-control" name="orgId">
                  <option value="" key="0" />
                  {orgs
                    ? orgs.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/appointment" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  orgs: storeState.org.entities,
  appointmentEntity: storeState.appointment.entity,
  loading: storeState.appointment.loading,
  updating: storeState.appointment.updating,
  updateSuccess: storeState.appointment.updateSuccess
});

const mapDispatchToProps = {
  getOrgs,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentUpdate);
