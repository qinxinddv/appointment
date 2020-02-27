import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysDict from './sys-dict';
import Appointment from './appointment';
import Community from './community';
import AppointmentConfig from './appointment-config';
import AppointmentPool from './appointment-pool';
import BlackKey from './black-key';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}sys-dict`} component={SysDict} />
      <ErrorBoundaryRoute path={`${match.url}appointment`} component={Appointment} />
      <ErrorBoundaryRoute path={`${match.url}community`} component={Community} />
      <ErrorBoundaryRoute path={`${match.url}appointment-config`} component={AppointmentConfig} />
      <ErrorBoundaryRoute path={`${match.url}appointment-pool`} component={AppointmentPool} />
      <ErrorBoundaryRoute path={`${match.url}black-key`} component={BlackKey} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
