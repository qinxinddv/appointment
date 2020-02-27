import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICommunity, defaultValue } from 'app/shared/model/community.model';

export const ACTION_TYPES = {
  FETCH_COMMUNITY_LIST: 'community/FETCH_COMMUNITY_LIST',
  FETCH_COMMUNITY: 'community/FETCH_COMMUNITY',
  CREATE_COMMUNITY: 'community/CREATE_COMMUNITY',
  UPDATE_COMMUNITY: 'community/UPDATE_COMMUNITY',
  DELETE_COMMUNITY: 'community/DELETE_COMMUNITY',
  RESET: 'community/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICommunity>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CommunityState = Readonly<typeof initialState>;

// Reducer

export default (state: CommunityState = initialState, action): CommunityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COMMUNITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMMUNITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COMMUNITY):
    case REQUEST(ACTION_TYPES.UPDATE_COMMUNITY):
    case REQUEST(ACTION_TYPES.DELETE_COMMUNITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_COMMUNITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMMUNITY):
    case FAILURE(ACTION_TYPES.CREATE_COMMUNITY):
    case FAILURE(ACTION_TYPES.UPDATE_COMMUNITY):
    case FAILURE(ACTION_TYPES.DELETE_COMMUNITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMMUNITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMMUNITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMMUNITY):
    case SUCCESS(ACTION_TYPES.UPDATE_COMMUNITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMMUNITY):
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

const apiUrl = 'api/communities';

// Actions

export const getEntities: ICrudGetAllAction<ICommunity> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COMMUNITY_LIST,
  payload: axios.get<ICommunity>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICommunity> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMMUNITY,
    payload: axios.get<ICommunity>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICommunity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMMUNITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICommunity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMMUNITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICommunity> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMMUNITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
