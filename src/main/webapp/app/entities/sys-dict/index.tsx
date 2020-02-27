import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SysDict from './sys-dict';
import SysDictDetail from './sys-dict-detail';
import SysDictUpdate from './sys-dict-update';
import SysDictDeleteDialog from './sys-dict-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SysDictDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SysDictUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SysDictUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SysDictDetail} />
      <ErrorBoundaryRoute path={match.url} component={SysDict} />
    </Switch>
  </>
);

export default Routes;
