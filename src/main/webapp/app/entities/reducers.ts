import addresses from 'app/entities/addresses/addresses.reducer';
import cars from 'app/entities/cars/cars.reducer';
import categories from 'app/entities/categories/categories.reducer';
import colors from 'app/entities/colors/colors.reducer';
import customers from 'app/entities/customers/customers.reducer';
import darazUsers from 'app/entities/daraz-users/daraz-users.reducer';
import orderDelivery from 'app/entities/order-delivery/order-delivery.reducer';
import orderDetails from 'app/entities/order-details/order-details.reducer';
import orders from 'app/entities/orders/orders.reducer';
import paymentMethods from 'app/entities/payment-methods/payment-methods.reducer';
import productDetails from 'app/entities/product-details/product-details.reducer';
import products from 'app/entities/products/products.reducer';
import roles from 'app/entities/roles/roles.reducer';
import shippingDetails from 'app/entities/shipping-details/shipping-details.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  addresses,
  cars,
  categories,
  colors,
  customers,
  darazUsers,
  orderDelivery,
  orderDetails,
  orders,
  paymentMethods,
  productDetails,
  products,
  roles,
  shippingDetails,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
