import React from 'react';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/addresses">
        Addresses
      </MenuItem>
      <MenuItem icon="asterisk" to="/cars">
        Cars
      </MenuItem>
      <MenuItem icon="asterisk" to="/categories">
        Categories
      </MenuItem>
      <MenuItem icon="asterisk" to="/colors">
        Colors
      </MenuItem>
      <MenuItem icon="asterisk" to="/customers">
        Customers
      </MenuItem>
      <MenuItem icon="asterisk" to="/daraz-users">
        Daraz Users
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-delivery">
        Order Delivery
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-details">
        Order Details
      </MenuItem>
      <MenuItem icon="asterisk" to="/orders">
        Orders
      </MenuItem>
      <MenuItem icon="asterisk" to="/payment-methods">
        Payment Methods
      </MenuItem>
      <MenuItem icon="asterisk" to="/product-details">
        Product Details
      </MenuItem>
      <MenuItem icon="asterisk" to="/products">
        Products
      </MenuItem>
      <MenuItem icon="asterisk" to="/roles">
        Roles
      </MenuItem>
      <MenuItem icon="asterisk" to="/shipping-details">
        Shipping Details
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
