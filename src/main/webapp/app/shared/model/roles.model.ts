import { IDarazUsers } from 'app/shared/model/daraz-users.model';

export interface IRoles {
  id?: number;
  rolePrId?: number;
  name?: string;
  users?: IDarazUsers[] | null;
}

export const defaultValue: Readonly<IRoles> = {};
