import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Addresses from './addresses';
import Cars from './cars';
import Categories from './categories';
import Colors from './colors';
import Customers from './customers';
import DarazUsers from './daraz-users';
import OrderDelivery from './order-delivery';
import OrderDetails from './order-details';
import Orders from './orders';
import PaymentMethods from './payment-methods';
import ProductDetails from './product-details';
import Products from './products';
import Roles from './roles';
import ShippingDetails from './shipping-details';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="addresses/*" element={<Addresses />} />
        <Route path="cars/*" element={<Cars />} />
        <Route path="categories/*" element={<Categories />} />
        <Route path="colors/*" element={<Colors />} />
        <Route path="customers/*" element={<Customers />} />
        <Route path="daraz-users/*" element={<DarazUsers />} />
        <Route path="order-delivery/*" element={<OrderDelivery />} />
        <Route path="order-details/*" element={<OrderDetails />} />
        <Route path="orders/*" element={<Orders />} />
        <Route path="payment-methods/*" element={<PaymentMethods />} />
        <Route path="product-details/*" element={<ProductDetails />} />
        <Route path="products/*" element={<Products />} />
        <Route path="roles/*" element={<Roles />} />
        <Route path="shipping-details/*" element={<ShippingDetails />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
