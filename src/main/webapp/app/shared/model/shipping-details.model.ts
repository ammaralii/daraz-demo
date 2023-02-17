import dayjs from 'dayjs';
import { IOrders } from 'app/shared/model/orders.model';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { ShippingMethod } from 'app/shared/model/enumerations/shipping-method.model';

export interface IShippingDetails {
  id?: number;
  shippingAddress?: string;
  shippingMethod?: ShippingMethod;
  estimatedDeliveryDate?: string;
  order?: IOrders;
  orderdeliveryShippingaddresses?: IOrderDelivery[] | null;
}

export const defaultValue: Readonly<IShippingDetails> = {};
