import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBlackKey, defaultValue } from 'app/shared/model/black-key.model';

export const ACTION_TYPES = {
  FETCH_BLACKKEY_LIST: 'blackKey/FETCH_BLACKKEY_LIST',
  FETCH_BLACKKEY: 'blackKey/FETCH_BLACKKEY',
  CREATE_BLACKKEY: 'blackKey/CREATE_BLACKKEY',
  UPDATE_BLACKKEY: 'blackKey/UPDATE_BLACKKEY',
  DELETE_BLACKKEY: 'blackKey/DELETE_BLACKKEY',
  RESET: 'blackKey/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBlackKey>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BlackKeyState = Readonly<typeof initialState>;

// Reducer

export default (state: BlackKeyState = initialState, action): BlackKeyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BLACKKEY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BLACKKEY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BLACKKEY):
    case REQUEST(ACTION_TYPES.UPDATE_BLACKKEY):
    case REQUEST(ACTION_TYPES.DELETE_BLACKKEY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BLACKKEY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BLACKKEY):
    case FAILURE(ACTION_TYPES.CREATE_BLACKKEY):
    case FAILURE(ACTION_TYPES.UPDATE_BLACKKEY):
    case FAILURE(ACTION_TYPES.DELETE_BLACKKEY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLACKKEY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BLACKKEY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BLACKKEY):
    case SUCCESS(ACTION_TYPES.UPDATE_BLACKKEY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BLACKKEY):
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

const apiUrl = 'api/black-keys';

// Actions

export const getEntities: ICrudGetAllAction<IBlackKey> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BLACKKEY_LIST,
  payload: axios.get<IBlackKey>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBlackKey> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BLACKKEY,
    payload: axios.get<IBlackKey>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBlackKey> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BLACKKEY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBlackKey> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BLACKKEY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBlackKey> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BLACKKEY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
