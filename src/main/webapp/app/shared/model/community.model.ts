import { IAppointment } from 'app/shared/model/appointment.model';
import { CommunityStateEnum } from 'app/shared/model/enumerations/community-state-enum.model';

export interface ICommunity {
  id?: number;
  name?: string;
  addr?: string;
  state?: string;
  communityStateEnum?: CommunityStateEnum;
  appointments?: IAppointment[];
}

export const defaultValue: Readonly<ICommunity> = {};
