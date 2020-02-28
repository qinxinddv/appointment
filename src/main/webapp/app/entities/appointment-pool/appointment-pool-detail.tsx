import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './appointment-pool.reducer';
import { IAppointmentPool } from 'app/shared/model/appointment-pool.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentPoolDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentPoolDetail = (props: IAppointmentPoolDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { appointmentPoolEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.appointmentPool.detail.title">AppointmentPool</Translate> [<b>{appointmentPoolEntity.id}</b>
          ]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="appointmentApp.appointmentPool.date">Date</Translate>
            </span>
            <UncontrolledTooltip target="date">
              <Translate contentKey="appointmentApp.appointmentPool.help.date" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentPoolEntity.date}</dd>
          <dt>
            <span id="period">
              <Translate contentKey="appointmentApp.appointmentPool.period">Period</Translate>
            </span>
            <UncontrolledTooltip target="period">
              <Translate contentKey="appointmentApp.appointmentPool.help.period" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentPoolEntity.period}</dd>
          <dt>
            <span id="totalNum">
              <Translate contentKey="appointmentApp.appointmentPool.totalNum">Total Num</Translate>
            </span>
            <UncontrolledTooltip target="totalNum">
              <Translate contentKey="appointmentApp.appointmentPool.help.totalNum" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentPoolEntity.totalNum}</dd>
          <dt>
            <span id="leftNum">
              <Translate contentKey="appointmentApp.appointmentPool.leftNum">Left Num</Translate>
            </span>
            <UncontrolledTooltip target="leftNum">
              <Translate contentKey="appointmentApp.appointmentPool.help.leftNum" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentPoolEntity.leftNum}</dd>
          <dt>
            <span id="busiType">
              <Translate contentKey="appointmentApp.appointmentPool.busiType">Busi Type</Translate>
            </span>
            <UncontrolledTooltip target="busiType">
              <Translate contentKey="appointmentApp.appointmentPool.help.busiType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentPoolEntity.busiType}</dd>
          <dt>
            <Translate contentKey="appointmentApp.appointmentPool.org">Org</Translate>
          </dt>
          <dd>{appointmentPoolEntity.orgId ? appointmentPoolEntity.orgId : ''}</dd>
        </dl>
        <Button tag={Link} to="/appointment-pool" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/appointment-pool/${appointmentPoolEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ appointmentPool }: IRootState) => ({
  appointmentPoolEntity: appointmentPool.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentPoolDetail);
