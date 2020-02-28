import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';

export interface IAppointmentPool {
  id?: number;
  date?: string;
  period?: string;
  totalNum?: number;
  leftNum?: number;
  busiType?: BusiTypeEnum;
  orgId?: number;
}

export const defaultValue: Readonly<IAppointmentPool> = {};
