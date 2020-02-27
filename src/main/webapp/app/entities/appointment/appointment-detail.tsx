import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './appointment.reducer';
import { IAppointment } from 'app/shared/model/appointment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AppointmentDetail = (props: IAppointmentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { appointmentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.appointment.detail.title">Appointment</Translate> [<b>{appointmentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="idCard">
              <Translate contentKey="appointmentApp.appointment.idCard">Id Card</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.idCard}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="appointmentApp.appointment.name">Name</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.name}</dd>
          <dt>
            <span id="mobile">
              <Translate contentKey="appointmentApp.appointment.mobile">Mobile</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.mobile}</dd>
          <dt>
            <span id="timePeriodCode">
              <Translate contentKey="appointmentApp.appointment.timePeriodCode">Time Period Code</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.timePeriodCode}</dd>
          <dt>
            <span id="timePeriodValue">
              <Translate contentKey="appointmentApp.appointment.timePeriodValue">Time Period Value</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.timePeriodValue}</dd>
          <dt>
            <span id="busiType">
              <Translate contentKey="appointmentApp.appointment.busiType">Busi Type</Translate>
            </span>
          </dt>
          <dd>{appointmentEntity.busiType}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="appointmentApp.appointment.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={appointmentEntity.createdDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="appointmentApp.appointment.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={appointmentEntity.lastModifiedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <Translate contentKey="appointmentApp.appointment.community">Community</Translate>
          </dt>
          <dd>{appointmentEntity.communityId ? appointmentEntity.communityId : ''}</dd>
        </dl>
        <Button tag={Link} to="/appointment" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/appointment/${appointmentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ appointment }: IRootState) => ({
  appointmentEntity: appointment.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentDetail);
