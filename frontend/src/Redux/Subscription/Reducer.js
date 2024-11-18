import * as actionTypes from "./ActionType";

const initialState = {
  userSubscription: null, // Keep null or an empty object to start
  loading: false,
  error: null,
};

const SubscriptionReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.GET_USER_SUBSCRIPTION_REQUEST:
    case actionTypes.UPGRADE_SUBSCRIPTION_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };

    case actionTypes.GET_USER_SUBSCRIPTION_SUCCESS:
    case actionTypes.UPGRADE_SUBSCRIPTION_SUCCESS:
      console.log("Reducer: Action Payload: ", action.payload);
      return {
        ...state,
        userSubscription: action.payload,
        loading: false,
        error: null,
      };

    case actionTypes.GET_USER_SUBSCRIPTION_FAILURE:
    case actionTypes.UPGRADE_SUBSCRIPTION_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.error,
      };

    default:
      return state;
  }
};

export default SubscriptionReducer;
