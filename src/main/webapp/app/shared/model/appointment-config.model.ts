import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';

export interface IAppointmentConfig {
  id?: number;
  period?: string;
  num?: number;
  busiType?: BusiTypeEnum;
}

export const defaultValue: Readonly<IAppointmentConfig> = {};
