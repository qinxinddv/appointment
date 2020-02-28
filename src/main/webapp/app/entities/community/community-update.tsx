import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './community.reducer';
import { ICommunity } from 'app/shared/model/community.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICommunityUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CommunityUpdate = (props: ICommunityUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { communityEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/community' + props.location.search);
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
        ...communityEntity,
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
          <h2 id="appointmentApp.community.home.createOrEditLabel">
            <Translate contentKey="appointmentApp.community.home.createOrEditLabel">Create or edit a Community</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : communityEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="community-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="community-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="community-name">
                  <Translate contentKey="appointmentApp.community.name">Name</Translate>
                </Label>
                <AvField id="community-name" type="text" name="name" />
                <UncontrolledTooltip target="nameLabel">
                  <Translate contentKey="appointmentApp.community.help.name" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addrLabel" for="community-addr">
                  <Translate contentKey="appointmentApp.community.addr">Addr</Translate>
                </Label>
                <AvField id="community-addr" type="text" name="addr" />
                <UncontrolledTooltip target="addrLabel">
                  <Translate contentKey="appointmentApp.community.help.addr" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="stateLabel" for="community-state">
                  <Translate contentKey="appointmentApp.community.state">State</Translate>
                </Label>
                <AvField id="community-state" type="text" name="state" />
                <UncontrolledTooltip target="stateLabel">
                  <Translate contentKey="appointmentApp.community.help.state" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="communityStateEnumLabel" for="community-communityStateEnum">
                  <Translate contentKey="appointmentApp.community.communityStateEnum">Community State Enum</Translate>
                </Label>
                <AvInput
                  id="community-communityStateEnum"
                  type="select"
                  className="form-control"
                  name="communityStateEnum"
                  value={(!isNew && communityEntity.communityStateEnum) || 'NORMAL'}
                >
                  <option value="NORMAL">{translate('appointmentApp.CommunityStateEnum.NORMAL')}</option>
                  <option value="FORBID">{translate('appointmentApp.CommunityStateEnum.FORBID')}</option>
                </AvInput>
                <UncontrolledTooltip target="communityStateEnumLabel">
                  <Translate contentKey="appointmentApp.community.help.communityStateEnum" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/community" replace color="info">
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
  communityEntity: storeState.community.entity,
  loading: storeState.community.loading,
  updating: storeState.community.updating,
  updateSuccess: storeState.community.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CommunityUpdate);
