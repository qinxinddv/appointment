import { Moment } from 'moment';
import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';

export interface IAppointment {
  id?: number;
  idCard?: string;
  name?: string;
  mobile?: string;
  timePeriodCode?: string;
  timePeriodValue?: string;
  busiType?: BusiTypeEnum;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  communityId?: number;
}

export const defaultValue: Readonly<IAppointment> = {};
