import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sys-dict.reducer';
import { ISysDict } from 'app/shared/model/sys-dict.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISysDictDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SysDictDetail = (props: ISysDictDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { sysDictEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="appointmentApp.sysDict.detail.title">SysDict</Translate> [<b>{sysDictEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="appointmentApp.sysDict.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="appointmentApp.sysDict.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.name}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="appointmentApp.sysDict.type">Type</Translate>
            </span>
            <UncontrolledTooltip target="type">
              <Translate contentKey="appointmentApp.sysDict.help.type" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.type}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="appointmentApp.sysDict.code">Code</Translate>
            </span>
            <UncontrolledTooltip target="code">
              <Translate contentKey="appointmentApp.sysDict.help.code" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.code}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="appointmentApp.sysDict.value">Value</Translate>
            </span>
            <UncontrolledTooltip target="value">
              <Translate contentKey="appointmentApp.sysDict.help.value" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.value}</dd>
          <dt>
            <span id="parentId">
              <Translate contentKey="appointmentApp.sysDict.parentId">Parent Id</Translate>
            </span>
            <UncontrolledTooltip target="parentId">
              <Translate contentKey="appointmentApp.sysDict.help.parentId" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.parentId}</dd>
          <dt>
            <span id="desc">
              <Translate contentKey="appointmentApp.sysDict.desc">Desc</Translate>
            </span>
            <UncontrolledTooltip target="desc">
              <Translate contentKey="appointmentApp.sysDict.help.desc" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.desc}</dd>
          <dt>
            <span id="extend1">
              <Translate contentKey="appointmentApp.sysDict.extend1">Extend 1</Translate>
            </span>
            <UncontrolledTooltip target="extend1">
              <Translate contentKey="appointmentApp.sysDict.help.extend1" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.extend1}</dd>
          <dt>
            <span id="extend2">
              <Translate contentKey="appointmentApp.sysDict.extend2">Extend 2</Translate>
            </span>
            <UncontrolledTooltip target="extend2">
              <Translate contentKey="appointmentApp.sysDict.help.extend2" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.extend2}</dd>
          <dt>
            <span id="extend3">
              <Translate contentKey="appointmentApp.sysDict.extend3">Extend 3</Translate>
            </span>
            <UncontrolledTooltip target="extend3">
              <Translate contentKey="appointmentApp.sysDict.help.extend3" />
            </UncontrolledTooltip>
          </dt>
          <dd>{sysDictEntity.extend3}</dd>
        </dl>
        <Button tag={Link} to="/sys-dict" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/sys-dict/${sysDictEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ sysDict }: IRootState) => ({
  sysDictEntity: sysDict.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SysDictDetail);
