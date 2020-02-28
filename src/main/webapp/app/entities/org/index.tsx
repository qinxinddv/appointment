import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Org from './org';
import OrgDetail from './org-detail';
import OrgUpdate from './org-update';
import OrgDeleteDialog from './org-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OrgDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OrgUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OrgUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OrgDetail} />
      <ErrorBoundaryRoute path={match.url} component={Org} />
    </Switch>
  </>
);

export default Routes;
