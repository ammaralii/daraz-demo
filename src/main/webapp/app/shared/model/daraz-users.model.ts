import { IAddresses } from 'app/shared/model/addresses.model';
import { IOrderDelivery } from 'app/shared/model/order-delivery.model';
import { IRoles } from 'app/shared/model/roles.model';

export interface IDarazUsers {
  id?: number;
  fullName?: string;
  email?: string;
  phone?: string;
  manager?: IDarazUsers | null;
  addressesUsers?: IAddresses[] | null;
  darazusersManagers?: IDarazUsers[] | null;
  orderdeliveryDeliverymanagers?: IOrderDelivery[] | null;
  orderdeliveryDeliveryboys?: IOrderDelivery[] | null;
  roles?: IRoles[] | null;
}

export const defaultValue: Readonly<IDarazUsers> = {};
