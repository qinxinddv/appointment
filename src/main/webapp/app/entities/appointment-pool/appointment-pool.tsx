import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './appointment-pool.reducer';
import { IAppointmentPool } from 'app/shared/model/appointment-pool.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentPoolProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AppointmentPool = (props: IAppointmentPoolProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { appointmentPoolList, match, loading } = props;
  return (
    <div>
      <h2 id="appointment-pool-heading">
        <Translate contentKey="appointmentApp.appointmentPool.home.title">Appointment Pools</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.appointmentPool.home.createLabel">Create new Appointment Pool</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {appointmentPoolList && appointmentPoolList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentPool.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentPool.period">Period</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentPool.totalNum">Total Num</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentPool.leftNum">Left Num</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentPool.type">Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {appointmentPoolList.map((appointmentPool, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${appointmentPool.id}`} color="link" size="sm">
                      {appointmentPool.id}
                    </Button>
                  </td>
                  <td>{appointmentPool.date}</td>
                  <td>{appointmentPool.period}</td>
                  <td>{appointmentPool.totalNum}</td>
                  <td>{appointmentPool.leftNum}</td>
                  <td>{appointmentPool.type}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${appointmentPool.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointmentPool.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointmentPool.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="appointmentApp.appointmentPool.home.notFound">No Appointment Pools found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ appointmentPool }: IRootState) => ({
  appointmentPoolList: appointmentPool.entities,
  loading: appointmentPool.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentPool);
