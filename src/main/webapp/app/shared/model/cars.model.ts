import { IColors } from 'app/shared/model/colors.model';

export interface ICars {
  id?: number;
  caruid?: number;
  name?: string;
  colors?: IColors[] | null;
}

export const defaultValue: Readonly<ICars> = {};
