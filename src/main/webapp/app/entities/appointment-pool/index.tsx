import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AppointmentPool from './appointment-pool';
import AppointmentPoolDetail from './appointment-pool-detail';
import AppointmentPoolUpdate from './appointment-pool-update';
import AppointmentPoolDeleteDialog from './appointment-pool-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AppointmentPoolDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AppointmentPoolUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AppointmentPoolUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AppointmentPoolDetail} />
      <ErrorBoundaryRoute path={match.url} component={AppointmentPool} />
    </Switch>
  </>
);

export default Routes;
