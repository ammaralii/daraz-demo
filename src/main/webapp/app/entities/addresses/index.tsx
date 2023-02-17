import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Addresses from './addresses';
import AddressesDetail from './addresses-detail';
import AddressesUpdate from './addresses-update';
import AddressesDeleteDialog from './addresses-delete-dialog';

const AddressesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Addresses />} />
    <Route path="new" element={<AddressesUpdate />} />
    <Route path=":id">
      <Route index element={<AddressesDetail />} />
      <Route path="edit" element={<AddressesUpdate />} />
      <Route path="delete" element={<AddressesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AddressesRoutes;
