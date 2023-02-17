import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Colors from './colors';
import ColorsDetail from './colors-detail';
import ColorsUpdate from './colors-update';
import ColorsDeleteDialog from './colors-delete-dialog';

const ColorsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Colors />} />
    <Route path="new" element={<ColorsUpdate />} />
    <Route path=":id">
      <Route index element={<ColorsDetail />} />
      <Route path="edit" element={<ColorsUpdate />} />
      <Route path="delete" element={<ColorsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ColorsRoutes;
