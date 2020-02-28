import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './appointment-config.reducer';
import { IAppointmentConfig } from 'app/shared/model/appointment-config.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentConfigDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentConfigDetail = (props: IAppointmentConfigDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { appointmentConfigEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.appointmentConfig.detail.title">AppointmentConfig</Translate> [
          <b>{appointmentConfigEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="period">
              <Translate contentKey="appointmentApp.appointmentConfig.period">Period</Translate>
            </span>
            <UncontrolledTooltip target="period">
              <Translate contentKey="appointmentApp.appointmentConfig.help.period" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentConfigEntity.period}</dd>
          <dt>
            <span id="num">
              <Translate contentKey="appointmentApp.appointmentConfig.num">Num</Translate>
            </span>
            <UncontrolledTooltip target="num">
              <Translate contentKey="appointmentApp.appointmentConfig.help.num" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentConfigEntity.num}</dd>
          <dt>
            <span id="busiType">
              <Translate contentKey="appointmentApp.appointmentConfig.busiType">Busi Type</Translate>
            </span>
            <UncontrolledTooltip target="busiType">
              <Translate contentKey="appointmentApp.appointmentConfig.help.busiType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentConfigEntity.busiType}</dd>
        </dl>
        <Button tag={Link} to="/appointment-config" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/appointment-config/${appointmentConfigEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ appointmentConfig }: IRootState) => ({
  appointmentConfigEntity: appointmentConfig.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentConfigDetail);
