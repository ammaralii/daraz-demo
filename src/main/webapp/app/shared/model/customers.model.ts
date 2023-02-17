import { IOrders } from 'app/shared/model/orders.model';
import { IPaymentMethods } from 'app/shared/model/payment-methods.model';

export interface ICustomers {
  id?: number;
  fullName?: string;
  email?: string;
  phone?: string;
  ordersCustomers?: IOrders[] | null;
  paymentmethodsCustomers?: IPaymentMethods[] | null;
}

export const defaultValue: Readonly<ICustomers> = {};
