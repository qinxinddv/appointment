import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BlackKey from './black-key';
import BlackKeyDetail from './black-key-detail';
import BlackKeyUpdate from './black-key-update';
import BlackKeyDeleteDialog from './black-key-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={BlackKeyDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BlackKeyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BlackKeyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BlackKeyDetail} />
      <ErrorBoundaryRoute path={match.url} component={BlackKey} />
    </Switch>
  </>
);

export default Routes;
