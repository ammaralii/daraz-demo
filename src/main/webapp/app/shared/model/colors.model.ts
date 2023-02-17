import { ICars } from 'app/shared/model/cars.model';

export interface IColors {
  id?: number;
  coloruid?: number;
  name?: string;
  cars?: ICars[] | null;
}

export const defaultValue: Readonly<IColors> = {};
