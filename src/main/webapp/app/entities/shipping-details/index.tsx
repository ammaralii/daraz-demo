import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ShippingDetails from './shipping-details';
import ShippingDetailsDetail from './shipping-details-detail';
import ShippingDetailsUpdate from './shipping-details-update';
import ShippingDetailsDeleteDialog from './shipping-details-delete-dialog';

const ShippingDetailsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ShippingDetails />} />
    <Route path="new" element={<ShippingDetailsUpdate />} />
    <Route path=":id">
      <Route index element={<ShippingDetailsDetail />} />
      <Route path="edit" element={<ShippingDetailsUpdate />} />
      <Route path="delete" element={<ShippingDetailsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ShippingDetailsRoutes;
