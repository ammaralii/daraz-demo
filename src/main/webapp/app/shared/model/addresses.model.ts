import { IDarazUsers } from 'app/shared/model/daraz-users.model';

export interface IAddresses {
  id?: number;
  street?: string;
  city?: string;
  state?: string;
  user?: IDarazUsers;
}

export const defaultValue: Readonly<IAddresses> = {};
