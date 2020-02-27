import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './community.reducer';
import { ICommunity } from 'app/shared/model/community.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICommunityProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Community = (props: ICommunityProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { communityList, match, loading } = props;
  return (
    <div>
      <h2 id="community-heading">
        <Translate contentKey="appointmentApp.community.home.title">Communities</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="appointmentApp.community.home.createLabel">Create new Community</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {communityList && communityList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.addr">Addr</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.state">State</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.communityStateEnum">Community State Enum</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="appointmentApp.community.lastModifiedDate">Last Modified Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {communityList.map((community, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${community.id}`} color="link" size="sm">
                      {community.id}
                    </Button>
                  </td>
                  <td>{community.name}</td>
                  <td>{community.addr}</td>
                  <td>{community.state}</td>
                  <td>
                    <Translate contentKey={`appointmentApp.CommunityStateEnum.${community.communityStateEnum}`} />
                  </td>
                  <td>
                    <TextFormat type="date" value={community.createdDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={community.lastModifiedDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${community.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${community.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${community.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="appointmentApp.community.home.notFound">No Communities found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ community }: IRootState) => ({
  communityList: community.entities,
  loading: community.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Community);
