import { IProducts } from 'app/shared/model/products.model';

export interface IProductDetails {
  id?: number;
  description?: string;
  imageUrl?: string;
  isavailable?: boolean;
  product?: IProducts;
}

export const defaultValue: Readonly<IProductDetails> = {
  isavailable: false,
};
