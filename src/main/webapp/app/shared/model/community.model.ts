import { Moment } from 'moment';
import { IAppointment } from 'app/shared/model/appointment.model';
import { CommunityStateEnum } from 'app/shared/model/enumerations/community-state-enum.model';

export interface ICommunity {
  id?: number;
  name?: string;
  addr?: string;
  state?: string;
  communityStateEnum?: CommunityStateEnum;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  appointments?: IAppointment[];
}

export const defaultValue: Readonly<ICommunity> = {};
