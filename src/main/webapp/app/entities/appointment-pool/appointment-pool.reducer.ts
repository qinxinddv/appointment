import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAppointmentPool, defaultValue } from 'app/shared/model/appointment-pool.model';

export const ACTION_TYPES = {
  FETCH_APPOINTMENTPOOL_LIST: 'appointmentPool/FETCH_APPOINTMENTPOOL_LIST',
  FETCH_APPOINTMENTPOOL: 'appointmentPool/FETCH_APPOINTMENTPOOL',
  CREATE_APPOINTMENTPOOL: 'appointmentPool/CREATE_APPOINTMENTPOOL',
  UPDATE_APPOINTMENTPOOL: 'appointmentPool/UPDATE_APPOINTMENTPOOL',
  DELETE_APPOINTMENTPOOL: 'appointmentPool/DELETE_APPOINTMENTPOOL',
  RESET: 'appointmentPool/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAppointmentPool>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AppointmentPoolState = Readonly<typeof initialState>;

// Reducer

export default (state: AppointmentPoolState = initialState, action): AppointmentPoolState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_APPOINTMENTPOOL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_APPOINTMENTPOOL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_APPOINTMENTPOOL):
    case REQUEST(ACTION_TYPES.UPDATE_APPOINTMENTPOOL):
    case REQUEST(ACTION_TYPES.DELETE_APPOINTMENTPOOL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_APPOINTMENTPOOL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_APPOINTMENTPOOL):
    case FAILURE(ACTION_TYPES.CREATE_APPOINTMENTPOOL):
    case FAILURE(ACTION_TYPES.UPDATE_APPOINTMENTPOOL):
    case FAILURE(ACTION_TYPES.DELETE_APPOINTMENTPOOL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPOINTMENTPOOL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_APPOINTMENTPOOL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_APPOINTMENTPOOL):
    case SUCCESS(ACTION_TYPES.UPDATE_APPOINTMENTPOOL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_APPOINTMENTPOOL):
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

const apiUrl = 'api/appointment-pools';

// Actions

export const getEntities: ICrudGetAllAction<IAppointmentPool> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_APPOINTMENTPOOL_LIST,
  payload: axios.get<IAppointmentPool>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAppointmentPool> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_APPOINTMENTPOOL,
    payload: axios.get<IAppointmentPool>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAppointmentPool> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_APPOINTMENTPOOL,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAppointmentPool> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_APPOINTMENTPOOL,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAppointmentPool> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_APPOINTMENTPOOL,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
