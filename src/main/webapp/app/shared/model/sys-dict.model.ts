import { Moment } from 'moment';

export interface ISysDict {
  id?: number;
  name?: string;
  type?: string;
  code?: string;
  value?: string;
  parentId?: number;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
}

export const defaultValue: Readonly<ISysDict> = {};
