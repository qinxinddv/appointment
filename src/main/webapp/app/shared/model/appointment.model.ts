import { Moment } from 'moment';
import { SymptomEnum } from 'app/shared/model/enumerations/symptom-enum.model';
import { BusiTypeEnum } from 'app/shared/model/enumerations/busi-type-enum.model';
import { AppointStateEnum } from 'app/shared/model/enumerations/appoint-state-enum.model';

export interface IAppointment {
  id?: number;
  idCard?: string;
  name?: string;
  mobile?: string;
  addr?: string;
  temperature?: string;
  symptom?: SymptomEnum;
  timePeriodCode?: string;
  timePeriodValue?: string;
  busiType?: BusiTypeEnum;
  state?: AppointStateEnum;
  opnion?: string;
  applyTime?: Moment;
  opnionTime?: Moment;
  date?: string;
  orgId?: number;
}

export const defaultValue: Readonly<IAppointment> = {};
