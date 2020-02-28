import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOrg, defaultValue } from 'app/shared/model/org.model';

export const ACTION_TYPES = {
  FETCH_ORG_LIST: 'org/FETCH_ORG_LIST',
  FETCH_ORG: 'org/FETCH_ORG',
  CREATE_ORG: 'org/CREATE_ORG',
  UPDATE_ORG: 'org/UPDATE_ORG',
  DELETE_ORG: 'org/DELETE_ORG',
  RESET: 'org/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOrg>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type OrgState = Readonly<typeof initialState>;

// Reducer

export default (state: OrgState = initialState, action): OrgState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ORG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ORG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ORG):
    case REQUEST(ACTION_TYPES.UPDATE_ORG):
    case REQUEST(ACTION_TYPES.DELETE_ORG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ORG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ORG):
    case FAILURE(ACTION_TYPES.CREATE_ORG):
    case FAILURE(ACTION_TYPES.UPDATE_ORG):
    case FAILURE(ACTION_TYPES.DELETE_ORG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10)
      };
    case SUCCESS(ACTION_TYPES.FETCH_ORG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ORG):
    case SUCCESS(ACTION_TYPES.UPDATE_ORG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ORG):
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

const apiUrl = 'api/orgs';

// Actions

export const getEntities: ICrudGetAllAction<IOrg> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_ORG_LIST,
    payload: axios.get<IOrg>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IOrg> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ORG,
    payload: axios.get<IOrg>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IOrg> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ORG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOrg> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ORG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOrg> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ORG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
