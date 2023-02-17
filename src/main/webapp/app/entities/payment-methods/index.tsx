import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentMethods from './payment-methods';
import PaymentMethodsDetail from './payment-methods-detail';
import PaymentMethodsUpdate from './payment-methods-update';
import PaymentMethodsDeleteDialog from './payment-methods-delete-dialog';

const PaymentMethodsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentMethods />} />
    <Route path="new" element={<PaymentMethodsUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentMethodsDetail />} />
      <Route path="edit" element={<PaymentMethodsUpdate />} />
      <Route path="delete" element={<PaymentMethodsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentMethodsRoutes;
