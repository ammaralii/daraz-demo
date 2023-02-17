import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Cars from './cars';
import CarsDetail from './cars-detail';
import CarsUpdate from './cars-update';
import CarsDeleteDialog from './cars-delete-dialog';

const CarsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Cars />} />
    <Route path="new" element={<CarsUpdate />} />
    <Route path=":id">
      <Route index element={<CarsDetail />} />
      <Route path="edit" element={<CarsUpdate />} />
      <Route path="delete" element={<CarsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CarsRoutes;
