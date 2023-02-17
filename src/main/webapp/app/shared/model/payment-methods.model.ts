import { ICustomers } from 'app/shared/model/customers.model';

export interface IPaymentMethods {
  id?: number;
  cardNumber?: string;
  cardHolderName?: string;
  expirationDate?: string;
  customer?: ICustomers;
}

export const defaultValue: Readonly<IPaymentMethods> = {};
