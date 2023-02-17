import { ICategories } from 'app/shared/model/categories.model';
import { IOrderDetails } from 'app/shared/model/order-details.model';
import { IProductDetails } from 'app/shared/model/product-details.model';

export interface IProducts {
  id?: number;
  name?: string;
  category?: ICategories;
  orderdetailsProducts?: IOrderDetails[] | null;
  productdetailsProducts?: IProductDetails[] | null;
}

export const defaultValue: Readonly<IProducts> = {};
