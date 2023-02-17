import dayjs from 'dayjs';
import { ICustomers } from 'app/shared/model/customers.model';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { IOrderDetails } from 'app/shared/model/order-details.model';
import { IShippingDetails } from 'app/shared/model/shipping-details.model';

export interface IOrders {
  id?: number;
  orderDate?: string;
  totalAmount?: number | null;
  customer?: ICustomers;
  orderdeliveryOrders?: IOrderDelivery[] | null;
  orderdetailsOrders?: IOrderDetails[] | null;
  shippingdetailsOrders?: IShippingDetails[] | null;
}

export const defaultValue: Readonly<IOrders> = {};
