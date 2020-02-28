import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
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
            <UncontrolledTooltip target="idCard">
              <Translate contentKey="appointmentApp.appointment.help.idCard" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.idCard}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="appointmentApp.appointment.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="appointmentApp.appointment.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.name}</dd>
          <dt>
            <span id="mobile">
              <Translate contentKey="appointmentApp.appointment.mobile">Mobile</Translate>
            </span>
            <UncontrolledTooltip target="mobile">
              <Translate contentKey="appointmentApp.appointment.help.mobile" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.mobile}</dd>
          <dt>
            <span id="addr">
              <Translate contentKey="appointmentApp.appointment.addr">Addr</Translate>
            </span>
            <UncontrolledTooltip target="addr">
              <Translate contentKey="appointmentApp.appointment.help.addr" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.addr}</dd>
          <dt>
            <span id="timePeriodCode">
              <Translate contentKey="appointmentApp.appointment.timePeriodCode">Time Period Code</Translate>
            </span>
            <UncontrolledTooltip target="timePeriodCode">
              <Translate contentKey="appointmentApp.appointment.help.timePeriodCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.timePeriodCode}</dd>
          <dt>
            <span id="timePeriodValue">
              <Translate contentKey="appointmentApp.appointment.timePeriodValue">Time Period Value</Translate>
            </span>
            <UncontrolledTooltip target="timePeriodValue">
              <Translate contentKey="appointmentApp.appointment.help.timePeriodValue" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.timePeriodValue}</dd>
          <dt>
            <span id="busiType">
              <Translate contentKey="appointmentApp.appointment.busiType">Busi Type</Translate>
            </span>
            <UncontrolledTooltip target="busiType">
              <Translate contentKey="appointmentApp.appointment.help.busiType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.busiType}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="appointmentApp.appointment.state">State</Translate>
            </span>
            <UncontrolledTooltip target="state">
              <Translate contentKey="appointmentApp.appointment.help.state" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.state}</dd>
          <dt>
            <span id="opnion">
              <Translate contentKey="appointmentApp.appointment.opnion">Opnion</Translate>
            </span>
            <UncontrolledTooltip target="opnion">
              <Translate contentKey="appointmentApp.appointment.help.opnion" />
            </UncontrolledTooltip>
          </dt>
          <dd>{appointmentEntity.opnion}</dd>
          <dt>
            <span id="applyTime">
              <Translate contentKey="appointmentApp.appointment.applyTime">Apply Time</Translate>
            </span>
            <UncontrolledTooltip target="applyTime">
              <Translate contentKey="appointmentApp.appointment.help.applyTime" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            <TextFormat value={appointmentEntity.applyTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="opnionTime">
              <Translate contentKey="appointmentApp.appointment.opnionTime">Opnion Time</Translate>
            </span>
            <UncontrolledTooltip target="opnionTime">
              <Translate contentKey="appointmentApp.appointment.help.opnionTime" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            <TextFormat value={appointmentEntity.opnionTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <Translate contentKey="appointmentApp.appointment.org">Org</Translate>
          </dt>
          <dd>{appointmentEntity.orgId ? appointmentEntity.orgId : ''}</dd>
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
