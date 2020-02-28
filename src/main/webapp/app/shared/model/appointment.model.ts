import { Moment } from 'moment';
import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';
import { YesNoEnum } from 'app/shared/model/enumerations/yes-no-enum.model';

export interface IAppointment {
  id?: number;
  idCard?: string;
  name?: string;
  mobile?: string;
  addr?: string;
  timePeriodCode?: string;
  timePeriodValue?: string;
  busiType?: BusiTypeEnum;
  state?: YesNoEnum;
  opnion?: string;
  applyTime?: Moment;
  opnionTime?: Moment;
  date?: string;
  orgId?: number;
}

export const defaultValue: Readonly<IAppointment> = {};
