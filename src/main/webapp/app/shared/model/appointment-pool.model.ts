export interface IAppointmentPool {
  id?: number;
  date?: string;
  period?: string;
  totalNum?: number;
  leftNum?: number;
  type?: string;
}

export const defaultValue: Readonly<IAppointmentPool> = {};
