import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './sys-dict.reducer';
import { ISysDict } from 'app/shared/model/sys-dict.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysDictProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SysDict = (props: ISysDictProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { sysDictList, match, loading } = props;
  return (
    <div>
      <h2 id="sys-dict-heading">
        <Translate contentKey="appointmentApp.sysDict.home.title">Sys Dicts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.sysDict.home.createLabel">Create new Sys Dict</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {sysDictList && sysDictList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.type">Type</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.code">Code</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.value">Value</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.parentId">Parent Id</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.desc">Desc</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.extend1">Extend 1</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.extend2">Extend 2</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.extend3">Extend 3</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.sysDict.lastModifiedDate">Last Modified Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {sysDictList.map((sysDict, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${sysDict.id}`} color="link" size="sm">
                      {sysDict.id}
                    </Button>
                  </td>
                  <td>{sysDict.name}</td>
                  <td>{sysDict.type}</td>
                  <td>{sysDict.code}</td>
                  <td>{sysDict.value}</td>
                  <td>{sysDict.parentId}</td>
                  <td>{sysDict.desc}</td>
                  <td>{sysDict.extend1}</td>
                  <td>{sysDict.extend2}</td>
                  <td>{sysDict.extend3}</td>
                  <td>
                    <TextFormat type="date" value={sysDict.createdDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={sysDict.lastModifiedDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${sysDict.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${sysDict.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${sysDict.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="appointmentApp.sysDict.home.notFound">No Sys Dicts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ sysDict }: IRootState) => ({
  sysDictList: sysDict.entities,
  loading: sysDict.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SysDict);
