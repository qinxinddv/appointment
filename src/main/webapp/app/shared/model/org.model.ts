import { IAppointment } from 'app/shared/model/appointment.model';
import { IAppointmentPool } from 'app/shared/model/appointment-pool.model';

export interface IOrg {
  id?: number;
  name?: string;
  addr?: string;
  latitude?: string;
  longitude?: string;
  appointments?: IAppointment[];
  appointmentPools?: IAppointmentPool[];
}

export const defaultValue: Readonly<IOrg> = {};
