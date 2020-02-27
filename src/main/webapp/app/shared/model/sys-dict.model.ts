import { Moment } from 'moment';

export interface ISysDict {
  id?: number;
  name?: string;
  type?: string;
  code?: string;
  value?: string;
  parentId?: number;
  desc?: string;
  extend1?: string;
  extend2?: string;
  extend3?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
}

export const defaultValue: Readonly<ISysDict> = {};
