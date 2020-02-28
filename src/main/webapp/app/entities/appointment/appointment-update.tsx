import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICommunity } from 'app/shared/model/community.model';
import { getEntities as getCommunities } from 'app/entities/community/community.reducer';
import { getEntity, updateEntity, createEntity, reset } from './appointment.reducer';
import { IAppointment } from 'app/shared/model/appointment.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAppointmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentUpdate = (props: IAppointmentUpdateProps) => {
  const [communityId, setCommunityId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { appointmentEntity, communities, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/appointment' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCommunities();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
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
                  value={(!isNew && appointmentEntity.state) || 'YES'}
                >
                  <option value="YES">{translate('appointmentApp.YesNoEnum.YES')}</option>
                  <option value="NO">{translate('appointmentApp.YesNoEnum.NO')}</option>
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
                <Label for="appointment-community">
                  <Translate contentKey="appointmentApp.appointment.community">Community</Translate>
                </Label>
                <AvInput id="appointment-community" type="select" className="form-control" name="communityId">
                  <option value="" key="0" />
                  {communities
                    ? communities.map(otherEntity => (
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
  communities: storeState.community.entities,
  appointmentEntity: storeState.appointment.entity,
  loading: storeState.appointment.loading,
  updating: storeState.appointment.updating,
  updateSuccess: storeState.appointment.updateSuccess
});

const mapDispatchToProps = {
  getCommunities,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentUpdate);
