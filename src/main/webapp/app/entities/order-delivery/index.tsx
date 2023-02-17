import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderDelivery from './order-delivery';
import OrderDeliveryDetail from './order-delivery-detail';
import OrderDeliveryUpdate from './order-delivery-update';
import OrderDeliveryDeleteDialog from './order-delivery-delete-dialog';

const OrderDeliveryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderDelivery />} />
    <Route path="new" element={<OrderDeliveryUpdate />} />
    <Route path=":id">
      <Route index element={<OrderDeliveryDetail />} />
      <Route path="edit" element={<OrderDeliveryUpdate />} />
      <Route path="delete" element={<OrderDeliveryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderDeliveryRoutes;
