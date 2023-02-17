import { IProducts } from 'app/shared/model/products.model';

export interface ICategories {
  id?: number;
  name?: string;
  detail?: string;
  productsCategories?: IProducts[] | null;
}

export const defaultValue: Readonly<ICategories> = {};
