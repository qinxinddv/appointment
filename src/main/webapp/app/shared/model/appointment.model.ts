import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';
import { YesNoEnum } from 'app/shared/model/enumerations/yes-no-enum.model';

export interface IAppointment {
  id?: number;
  idCard?: string;
  name?: string;
  mobile?: string;
  timePeriodCode?: string;
  timePeriodValue?: string;
  busiType?: BusiTypeEnum;
  state?: YesNoEnum;
  opnion?: string;
  communityId?: number;
}

export const defaultValue: Readonly<IAppointment> = {};
