import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './black-key.reducer';
import { IBlackKey } from 'app/shared/model/black-key.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBlackKeyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BlackKey = (props: IBlackKeyProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { blackKeyList, match, loading } = props;
  return (
    <div>
      <h2 id="black-key-heading">
        <Translate contentKey="appointmentApp.blackKey.home.title">Black Keys</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.blackKey.home.createLabel">Create new Black Key</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {blackKeyList && blackKeyList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.blackKey.key">Key</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {blackKeyList.map((blackKey, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${blackKey.id}`} color="link" size="sm">
                      {blackKey.id}
                    </Button>
                  </td>
                  <td>{blackKey.key}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${blackKey.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${blackKey.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${blackKey.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="appointmentApp.blackKey.home.notFound">No Black Keys found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ blackKey }: IRootState) => ({
  blackKeyList: blackKey.entities,
  loading: blackKey.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BlackKey);
