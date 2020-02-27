import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './appointment-pool.reducer';
import { IAppointmentPool } from 'app/shared/model/appointment-pool.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAppointmentPoolUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentPoolUpdate = (props: IAppointmentPoolUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { appointmentPoolEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/appointment-pool');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...appointmentPoolEntity,
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
          <h2 id="appointmentApp.appointmentPool.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.appointmentPool.home.createOrEditLabel">Create or edit a AppointmentPool</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : appointmentPoolEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="appointment-pool-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="appointment-pool-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="appointment-pool-date">
                  <Translate contentKey="appointmentApp.appointmentPool.date">Date</Translate>
                </Label>
                <AvField id="appointment-pool-date" type="text" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="periodLabel" for="appointment-pool-period">
                  <Translate contentKey="appointmentApp.appointmentPool.period">Period</Translate>
                </Label>
                <AvField id="appointment-pool-period" type="text" name="period" />
              </AvGroup>
              <AvGroup>
                <Label id="totalNumLabel" for="appointment-pool-totalNum">
                  <Translate contentKey="appointmentApp.appointmentPool.totalNum">Total Num</Translate>
                </Label>
                <AvField id="appointment-pool-totalNum" type="string" className="form-control" name="totalNum" />
              </AvGroup>
              <AvGroup>
                <Label id="leftNumLabel" for="appointment-pool-leftNum">
                  <Translate contentKey="appointmentApp.appointmentPool.leftNum">Left Num</Translate>
                </Label>
                <AvField id="appointment-pool-leftNum" type="string" className="form-control" name="leftNum" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="appointment-pool-type">
                  <Translate contentKey="appointmentApp.appointmentPool.type">Type</Translate>
                </Label>
                <AvField id="appointment-pool-type" type="text" name="type" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/appointment-pool" replace color="info">
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
  appointmentPoolEntity: storeState.appointmentPool.entity,
  loading: storeState.appointmentPool.loading,
  updating: storeState.appointmentPool.updating,
  updateSuccess: storeState.appointmentPool.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentPoolUpdate);
