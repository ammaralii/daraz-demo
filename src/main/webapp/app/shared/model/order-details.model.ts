import { IOrders } from 'app/shared/model/orders.model';
import { IProducts } from 'app/shared/model/products.model';

export interface IOrderDetails {
  id?: number;
  quantity?: number | null;
  amount?: number | null;
  order?: IOrders;
  product?: IProducts;
}

export const defaultValue: Readonly<IOrderDetails> = {};
