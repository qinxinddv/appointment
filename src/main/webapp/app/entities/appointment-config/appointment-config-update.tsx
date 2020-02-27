import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './appointment-config.reducer';
import { IAppointmentConfig } from 'app/shared/model/appointment-config.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAppointmentConfigUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentConfigUpdate = (props: IAppointmentConfigUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { appointmentConfigEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/appointment-config');
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
        ...appointmentConfigEntity,
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
          <h2 id="appointmentApp.appointmentConfig.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.appointmentConfig.home.createOrEditLabel">Create or edit a AppointmentConfig</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : appointmentConfigEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="appointment-config-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="appointment-config-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="periodLabel" for="appointment-config-period">
                  <Translate contentKey="appointmentApp.appointmentConfig.period">Period</Translate>
                </Label>
                <AvField id="appointment-config-period" type="text" name="period" />
              </AvGroup>
              <AvGroup>
                <Label id="numLabel" for="appointment-config-num">
                  <Translate contentKey="appointmentApp.appointmentConfig.num">Num</Translate>
                </Label>
                <AvField id="appointment-config-num" type="string" className="form-control" name="num" />
              </AvGroup>
              <AvGroup>
                <Label id="busiTypeLabel" for="appointment-config-busiType">
                  <Translate contentKey="appointmentApp.appointmentConfig.busiType">Busi Type</Translate>
                </Label>
                <AvInput
                  id="appointment-config-busiType"
                  type="select"
                  className="form-control"
                  name="busiType"
                  value={(!isNew && appointmentConfigEntity.busiType) || 'PERSON'}
                >
                  <option value="PERSON">{translate('appointmentApp.BusiTypeEnum.PERSON')}</option>
                  <option value="ORG">{translate('appointmentApp.BusiTypeEnum.ORG')}</option>
                  <option value="LAW">{translate('appointmentApp.BusiTypeEnum.LAW')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/appointment-config" replace color="info">
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
  appointmentConfigEntity: storeState.appointmentConfig.entity,
  loading: storeState.appointmentConfig.loading,
  updating: storeState.appointmentConfig.updating,
  updateSuccess: storeState.appointmentConfig.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentConfigUpdate);
