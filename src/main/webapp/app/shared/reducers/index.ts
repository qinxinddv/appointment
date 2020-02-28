import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
import sessions, { SessionsState } from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import sysDict, {
  SysDictState
} from 'app/entities/sys-dict/sys-dict.reducer';
// prettier-ignore
import appointment, {
  AppointmentState
} from 'app/entities/appointment/appointment.reducer';
// prettier-ignore
import community, {
  CommunityState
} from 'app/entities/community/community.reducer';
// prettier-ignore
import appointmentConfig, {
  AppointmentConfigState
} from 'app/entities/appointment-config/appointment-config.reducer';
// prettier-ignore
import appointmentPool, {
  AppointmentPoolState
} from 'app/entities/appointment-pool/appointment-pool.reducer';
// prettier-ignore
import blackKey, {
  BlackKeyState
} from 'app/entities/black-key/black-key.reducer';
// prettier-ignore
import org, {
  OrgState
} from 'app/entities/org/org.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sessions: SessionsState;
  readonly sysDict: SysDictState;
  readonly appointment: AppointmentState;
  readonly community: CommunityState;
  readonly appointmentConfig: AppointmentConfigState;
  readonly appointmentPool: AppointmentPoolState;
  readonly blackKey: BlackKeyState;
  readonly org: OrgState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  sysDict,
  appointment,
  community,
  appointmentConfig,
  appointmentPool,
  blackKey,
  org,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
