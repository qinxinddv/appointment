import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISysDict, defaultValue } from 'app/shared/model/sys-dict.model';

export const ACTION_TYPES = {
  FETCH_SYSDICT_LIST: 'sysDict/FETCH_SYSDICT_LIST',
  FETCH_SYSDICT: 'sysDict/FETCH_SYSDICT',
  CREATE_SYSDICT: 'sysDict/CREATE_SYSDICT',
  UPDATE_SYSDICT: 'sysDict/UPDATE_SYSDICT',
  DELETE_SYSDICT: 'sysDict/DELETE_SYSDICT',
  RESET: 'sysDict/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISysDict>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SysDictState = Readonly<typeof initialState>;

// Reducer

export default (state: SysDictState = initialState, action): SysDictState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SYSDICT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SYSDICT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SYSDICT):
    case REQUEST(ACTION_TYPES.UPDATE_SYSDICT):
    case REQUEST(ACTION_TYPES.DELETE_SYSDICT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SYSDICT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SYSDICT):
    case FAILURE(ACTION_TYPES.CREATE_SYSDICT):
    case FAILURE(ACTION_TYPES.UPDATE_SYSDICT):
    case FAILURE(ACTION_TYPES.DELETE_SYSDICT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSDICT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SYSDICT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SYSDICT):
    case SUCCESS(ACTION_TYPES.UPDATE_SYSDICT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SYSDICT):
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

const apiUrl = 'api/sys-dicts';

// Actions

export const getEntities: ICrudGetAllAction<ISysDict> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SYSDICT_LIST,
  payload: axios.get<ISysDict>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISysDict> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SYSDICT,
    payload: axios.get<ISysDict>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISysDict> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SYSDICT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISysDict> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SYSDICT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISysDict> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SYSDICT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
