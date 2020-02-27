import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './community.reducer';
import { ICommunity } from 'app/shared/model/community.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICommunityDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CommunityDetail = (props: ICommunityDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { communityEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.community.detail.title">Community</Translate> [<b>{communityEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="appointmentApp.community.name">Name</Translate>
            </span>
          </dt>
          <dd>{communityEntity.name}</dd>
          <dt>
            <span id="addr">
              <Translate contentKey="appointmentApp.community.addr">Addr</Translate>
            </span>
          </dt>
          <dd>{communityEntity.addr}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="appointmentApp.community.state">State</Translate>
            </span>
          </dt>
          <dd>{communityEntity.state}</dd>
          <dt>
            <span id="communityStateEnum">
              <Translate contentKey="appointmentApp.community.communityStateEnum">Community State Enum</Translate>
            </span>
          </dt>
          <dd>{communityEntity.communityStateEnum}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="appointmentApp.community.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={communityEntity.createdDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="appointmentApp.community.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={communityEntity.lastModifiedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
        </dl>
        <Button tag={Link} to="/community" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/community/${communityEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ community }: IRootState) => ({
  communityEntity: community.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CommunityDetail);
