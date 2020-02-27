import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './appointment.reducer';
import { IAppointment } from 'app/shared/model/appointment.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Appointment = (props: IAppointmentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { appointmentList, match, loading } = props;
  return (
    <div>
      <h2 id="appointment-heading">
        <Translate contentKey="appointmentApp.appointment.home.title">Appointments</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.appointment.home.createLabel">Create new Appointment</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {appointmentList && appointmentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.idCard">Id Card</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.mobile">Mobile</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.timePeriodCode">Time Period Code</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.timePeriodValue">Time Period Value</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.busiType">Busi Type</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.lastModifiedDate">Last Modified Date</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointment.community">Community</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {appointmentList.map((appointment, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${appointment.id}`} color="link" size="sm">
                      {appointment.id}
                    </Button>
                  </td>
                  <td>{appointment.idCard}</td>
                  <td>{appointment.name}</td>
                  <td>{appointment.mobile}</td>
                  <td>{appointment.timePeriodCode}</td>
                  <td>{appointment.timePeriodValue}</td>
                  <td>
                    <Translate contentKey={`appointmentApp.BusiTypeEnum.${appointment.busiType}`} />
                  </td>
                  <td>
                    <TextFormat type="date" value={appointment.createdDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={appointment.lastModifiedDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {appointment.communityId ? <Link to={`community/${appointment.communityId}`}>{appointment.communityId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${appointment.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointment.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointment.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="appointmentApp.appointment.home.notFound">No Appointments found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ appointment }: IRootState) => ({
  appointmentList: appointment.entities,
  loading: appointment.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Appointment);
