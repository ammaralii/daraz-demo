import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DarazUsers from './daraz-users';
import DarazUsersDetail from './daraz-users-detail';
import DarazUsersUpdate from './daraz-users-update';
import DarazUsersDeleteDialog from './daraz-users-delete-dialog';

const DarazUsersRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DarazUsers />} />
    <Route path="new" element={<DarazUsersUpdate />} />
    <Route path=":id">
      <Route index element={<DarazUsersDetail />} />
      <Route path="edit" element={<DarazUsersUpdate />} />
      <Route path="delete" element={<DarazUsersDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DarazUsersRoutes;
