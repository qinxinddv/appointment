import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AppointmentConfig from './appointment-config';
import AppointmentConfigDetail from './appointment-config-detail';
import AppointmentConfigUpdate from './appointment-config-update';
import AppointmentConfigDeleteDialog from './appointment-config-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AppointmentConfigDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AppointmentConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AppointmentConfigUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AppointmentConfigDetail} />
      <ErrorBoundaryRoute path={match.url} component={AppointmentConfig} />
    </Switch>
  </>
);

export default Routes;
