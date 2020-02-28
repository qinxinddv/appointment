import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './org.reducer';
import { IOrg } from 'app/shared/model/org.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrgUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrgUpdate = (props: IOrgUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { orgEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/org' + props.location.search);
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
        ...orgEntity,
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
          <h2 id="appointmentApp.org.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.org.home.createOrEditLabel">Create or edit a Org</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : orgEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="org-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="org-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="org-name">
                  <Translate contentKey="appointmentApp.org.name">Name</Translate>
                </Label>
                <AvField id="org-name" type="text" name="name" />
                <UncontrolledTooltip target="nameLabel">
                  <Translate contentKey="appointmentApp.org.help.name" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addrLabel" for="org-addr">
                  <Translate contentKey="appointmentApp.org.addr">Addr</Translate>
                </Label>
                <AvField id="org-addr" type="text" name="addr" />
                <UncontrolledTooltip target="addrLabel">
                  <Translate contentKey="appointmentApp.org.help.addr" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="coordinateLabel" for="org-coordinate">
                  <Translate contentKey="appointmentApp.org.coordinate">Coordinate</Translate>
                </Label>
                <AvField id="org-coordinate" type="text" name="coordinate" />
                <UncontrolledTooltip target="coordinateLabel">
                  <Translate contentKey="appointmentApp.org.help.coordinate" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/org" replace color="info">
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
  orgEntity: storeState.org.entity,
  loading: storeState.org.loading,
  updating: storeState.org.updating,
  updateSuccess: storeState.org.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrgUpdate);
