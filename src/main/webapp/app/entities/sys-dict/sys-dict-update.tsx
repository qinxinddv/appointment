import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
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
    props.history.push('/sys-dict' + props.location.search);
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
                <UncontrolledTooltip target="nameLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.name" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="sys-dict-type">
                  <Translate contentKey="appointmentApp.sysDict.type">Type</Translate>
                </Label>
                <AvField id="sys-dict-type" type="text" name="type" />
                <UncontrolledTooltip target="typeLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.type" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="codeLabel" for="sys-dict-code">
                  <Translate contentKey="appointmentApp.sysDict.code">Code</Translate>
                </Label>
                <AvField id="sys-dict-code" type="text" name="code" />
                <UncontrolledTooltip target="codeLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.code" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="valueLabel" for="sys-dict-value">
                  <Translate contentKey="appointmentApp.sysDict.value">Value</Translate>
                </Label>
                <AvField id="sys-dict-value" type="text" name="value" />
                <UncontrolledTooltip target="valueLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.value" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="parentIdLabel" for="sys-dict-parentId">
                  <Translate contentKey="appointmentApp.sysDict.parentId">Parent Id</Translate>
                </Label>
                <AvField id="sys-dict-parentId" type="string" className="form-control" name="parentId" />
                <UncontrolledTooltip target="parentIdLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.parentId" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="descLabel" for="sys-dict-desc">
                  <Translate contentKey="appointmentApp.sysDict.desc">Desc</Translate>
                </Label>
                <AvField id="sys-dict-desc" type="text" name="desc" />
                <UncontrolledTooltip target="descLabel">
                  <Translate contentKey="appointmentApp.sysDict.help.desc" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="extend1Label" for="sys-dict-extend1">
                  <Translate contentKey="appointmentApp.sysDict.extend1">Extend 1</Translate>
                </Label>
                <AvField id="sys-dict-extend1" type="text" name="extend1" />
                <UncontrolledTooltip target="extend1Label">
                  <Translate contentKey="appointmentApp.sysDict.help.extend1" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="extend2Label" for="sys-dict-extend2">
                  <Translate contentKey="appointmentApp.sysDict.extend2">Extend 2</Translate>
                </Label>
                <AvField id="sys-dict-extend2" type="text" name="extend2" />
                <UncontrolledTooltip target="extend2Label">
                  <Translate contentKey="appointmentApp.sysDict.help.extend2" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="extend3Label" for="sys-dict-extend3">
                  <Translate contentKey="appointmentApp.sysDict.extend3">Extend 3</Translate>
                </Label>
                <AvField id="sys-dict-extend3" type="text" name="extend3" />
                <UncontrolledTooltip target="extend3Label">
                  <Translate contentKey="appointmentApp.sysDict.help.extend3" />
                </UncontrolledTooltip>
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
