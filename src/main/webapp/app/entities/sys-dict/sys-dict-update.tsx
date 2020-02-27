import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './sys-dict.reducer';
import { ISysDict } from 'app/shared/model/sys-dict.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISysDictUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SysDictUpdate = (props: ISysDictUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { sysDictEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/sys-dict');
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
        ...sysDictEntity,
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
          <h2 id="appointmentApp.sysDict.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.sysDict.home.createOrEditLabel">Create or edit a SysDict</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : sysDictEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="sys-dict-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="sys-dict-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="sys-dict-name">
                  <Translate contentKey="appointmentApp.sysDict.name">Name</Translate>
                </Label>
                <AvField id="sys-dict-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="sys-dict-type">
                  <Translate contentKey="appointmentApp.sysDict.type">Type</Translate>
                </Label>
                <AvField id="sys-dict-type" type="text" name="type" />
              </AvGroup>
              <AvGroup>
                <Label id="codeLabel" for="sys-dict-code">
                  <Translate contentKey="appointmentApp.sysDict.code">Code</Translate>
                </Label>
                <AvField id="sys-dict-code" type="text" name="code" />
              </AvGroup>
              <AvGroup>
                <Label id="valueLabel" for="sys-dict-value">
                  <Translate contentKey="appointmentApp.sysDict.value">Value</Translate>
                </Label>
                <AvField id="sys-dict-value" type="text" name="value" />
              </AvGroup>
              <AvGroup>
                <Label id="parentIdLabel" for="sys-dict-parentId">
                  <Translate contentKey="appointmentApp.sysDict.parentId">Parent Id</Translate>
                </Label>
                <AvField id="sys-dict-parentId" type="string" className="form-control" name="parentId" />
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="sys-dict-createdDate">
                  <Translate contentKey="appointmentApp.sysDict.createdDate">Created Date</Translate>
                </Label>
                <AvField id="sys-dict-createdDate" type="date" className="form-control" name="createdDate" />
              </AvGroup>
              <AvGroup>
                <Label id="lastModifiedDateLabel" for="sys-dict-lastModifiedDate">
                  <Translate contentKey="appointmentApp.sysDict.lastModifiedDate">Last Modified Date</Translate>
                </Label>
                <AvField id="sys-dict-lastModifiedDate" type="date" className="form-control" name="lastModifiedDate" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/sys-dict" replace color="info">
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
  sysDictEntity: storeState.sysDict.entity,
  loading: storeState.sysDict.loading,
  updating: storeState.sysDict.updating,
  updateSuccess: storeState.sysDict.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SysDictUpdate);