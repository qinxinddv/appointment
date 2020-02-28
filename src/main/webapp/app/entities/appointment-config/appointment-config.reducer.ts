import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAppointmentConfig, defaultValue } from 'app/shared/model/appointment-config.model';

export const ACTION_TYPES = {
  FETCH_APPOINTMENTCONFIG_LIST: 'appointmentConfig/FETCH_APPOINTMENTCONFIG_LIST',
  FETCH_APPOINTMENTCONFIG: 'appointmentConfig/FETCH_APPOINTMENTCONFIG',
  CREATE_APPOINTMENTCONFIG: 'appointmentConfig/CREATE_APPOINTMENTCONFIG',
  UPDATE_APPOINTMENTCONFIG: 'appointmentConfig/UPDATE_APPOINTMENTCONFIG',
  DELETE_APPOINTMENTCONFIG: 'appointmentConfig/DELETE_APPOINTMENTCONFIG',
  RESET: 'appointmentConfig/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAppointmentConfig>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type AppointmentConfigState = Readonly<typeof initialState>;

// Reducer

export default (state: AppointmentConfigState = initialState, action): AppointmentConfigState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_APPOINTMENTCONFIG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_APPOINTMENTCONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_APPOINTMENTCONFIG):
    case REQUEST(ACTION_TYPES.UPDATE_APPOINTMENTCONFIG):
    case REQUEST(ACTION_TYPES.DELETE_APPOINTMENTCONFIG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_APPOINTMENTCONFIG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_APPOINTMENTCONFIG):
    case FAILURE(ACTION_TYPES.CREATE_APPOINTMENTCONFIG):
    case FAILURE(ACTION_TYPES.UPDATE_APPOINTMENTCONFIG):
    case FAILURE(ACTION_TYPES.DELETE_APPOINTMENTCONFIG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPOINTMENTCONFIG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPOINTMENTCONFIG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_APPOINTMENTCONFIG):
    case SUCCESS(ACTION_TYPES.UPDATE_APPOINTMENTCONFIG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_APPOINTMENTCONFIG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/appointment-configs';

// Actions

export const getEntities: ICrudGetAllAction<IAppointmentConfig> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_APPOINTMENTCONFIG_LIST,
    payload: axios.get<IAppointmentConfig>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IAppointmentConfig> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_APPOINTMENTCONFIG,
    payload: axios.get<IAppointmentConfig>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAppointmentConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_APPOINTMENTCONFIG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAppointmentConfig> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_APPOINTMENTCONFIG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAppointmentConfig> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_APPOINTMENTCONFIG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
