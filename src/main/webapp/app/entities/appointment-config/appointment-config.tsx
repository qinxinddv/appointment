import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './appointment-config.reducer';
import { IAppointmentConfig } from 'app/shared/model/appointment-config.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAppointmentConfigProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AppointmentConfig = (props: IAppointmentConfigProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { appointmentConfigList, match, loading } = props;
  return (
    <div>
      <h2 id="appointment-config-heading">
        <Translate contentKey="appointmentApp.appointmentConfig.home.title">Appointment Configs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.appointmentConfig.home.createLabel">Create new Appointment Config</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {appointmentConfigList && appointmentConfigList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentConfig.period">Period</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentConfig.num">Num</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.appointmentConfig.busiType">Busi Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {appointmentConfigList.map((appointmentConfig, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${appointmentConfig.id}`} color="link" size="sm">
                      {appointmentConfig.id}
                    </Button>
                  </td>
                  <td>{appointmentConfig.period}</td>
                  <td>{appointmentConfig.num}</td>
                  <td>
                    <Translate contentKey={`appointmentApp.BusiTypeEnum.${appointmentConfig.busiType}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${appointmentConfig.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointmentConfig.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${appointmentConfig.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="appointmentApp.appointmentConfig.home.notFound">No Appointment Configs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ appointmentConfig }: IRootState) => ({
  appointmentConfigList: appointmentConfig.entities,
  loading: appointmentConfig.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AppointmentConfig);
