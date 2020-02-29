import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './org.reducer';
import { IOrg } from 'app/shared/model/org.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrgDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrgDetail = (props: IOrgDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { orgEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.org.detail.title">Org</Translate> [<b>{orgEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="appointmentApp.org.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="appointmentApp.org.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{orgEntity.name}</dd>
          <dt>
            <span id="addr">
              <Translate contentKey="appointmentApp.org.addr">Addr</Translate>
            </span>
            <UncontrolledTooltip target="addr">
              <Translate contentKey="appointmentApp.org.help.addr" />
            </UncontrolledTooltip>
          </dt>
          <dd>{orgEntity.addr}</dd>
          <dt>
            <span id="latitude">
              <Translate contentKey="appointmentApp.org.latitude">Latitude</Translate>
            </span>
            <UncontrolledTooltip target="latitude">
              <Translate contentKey="appointmentApp.org.help.latitude" />
            </UncontrolledTooltip>
          </dt>
          <dd>{orgEntity.latitude}</dd>
          <dt>
            <span id="longitude">
              <Translate contentKey="appointmentApp.org.longitude">Longitude</Translate>
            </span>
            <UncontrolledTooltip target="longitude">
              <Translate contentKey="appointmentApp.org.help.longitude" />
            </UncontrolledTooltip>
          </dt>
          <dd>{orgEntity.longitude}</dd>
        </dl>
        <Button tag={Link} to="/org" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/org/${orgEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ org }: IRootState) => ({
  orgEntity: org.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrgDetail);
