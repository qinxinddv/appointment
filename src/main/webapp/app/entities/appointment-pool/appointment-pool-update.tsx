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
import { getEntity, updateEntity, createEntity, reset } from './appointment-pool.reducer';
import { IAppointmentPool } from 'app/shared/model/appointment-pool.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAppointmentPoolUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentPoolUpdate = (props: IAppointmentPoolUpdateProps) => {
  const [orgId, setOrgId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { appointmentPoolEntity, orgs, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/appointment-pool' + props.location.search);
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
                <UncontrolledTooltip target="dateLabel">
                  <Translate contentKey="appointmentApp.appointmentPool.help.date" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="periodLabel" for="appointment-pool-period">
                  <Translate contentKey="appointmentApp.appointmentPool.period">Period</Translate>
                </Label>
                <AvField id="appointment-pool-period" type="text" name="period" />
                <UncontrolledTooltip target="periodLabel">
                  <Translate contentKey="appointmentApp.appointmentPool.help.period" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="totalNumLabel" for="appointment-pool-totalNum">
                  <Translate contentKey="appointmentApp.appointmentPool.totalNum">Total Num</Translate>
                </Label>
                <AvField id="appointment-pool-totalNum" type="string" className="form-control" name="totalNum" />
                <UncontrolledTooltip target="totalNumLabel">
                  <Translate contentKey="appointmentApp.appointmentPool.help.totalNum" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="leftNumLabel" for="appointment-pool-leftNum">
                  <Translate contentKey="appointmentApp.appointmentPool.leftNum">Left Num</Translate>
                </Label>
                <AvField id="appointment-pool-leftNum" type="string" className="form-control" name="leftNum" />
                <UncontrolledTooltip target="leftNumLabel">
                  <Translate contentKey="appointmentApp.appointmentPool.help.leftNum" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="busiTypeLabel" for="appointment-pool-busiType">
                  <Translate contentKey="appointmentApp.appointmentPool.busiType">Busi Type</Translate>
                </Label>
                <AvInput
                  id="appointment-pool-busiType"
                  type="select"
                  className="form-control"
                  name="busiType"
                  value={(!isNew && appointmentPoolEntity.busiType) || 'PERSON'}
                >
                  <option value="PERSON">{translate('appointmentApp.BusiTypeEnum.PERSON')}</option>
                  <option value="ORG">{translate('appointmentApp.BusiTypeEnum.ORG')}</option>
                  <option value="LAW">{translate('appointmentApp.BusiTypeEnum.LAW')}</option>
                </AvInput>
                <UncontrolledTooltip target="busiTypeLabel">
                  <Translate contentKey="appointmentApp.appointmentPool.help.busiType" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label for="appointment-pool-org">
                  <Translate contentKey="appointmentApp.appointmentPool.org">Org</Translate>
                </Label>
                <AvInput id="appointment-pool-org" type="select" className="form-control" name="orgId">
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
  orgs: storeState.org.entities,
  appointmentPoolEntity: storeState.appointmentPool.entity,
  loading: storeState.appointmentPool.loading,
  updating: storeState.appointmentPool.updating,
  updateSuccess: storeState.appointmentPool.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentPoolUpdate);
