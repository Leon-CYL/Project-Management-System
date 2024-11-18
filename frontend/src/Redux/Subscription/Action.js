import api from "@/config/api";
import * as actionTypes from "./ActionType";

export const getUserSubscription = (jwt) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.GET_USER_SUBSCRIPTION_REQUEST });
    try {
      const response = await api.get(`api/subscriptions/user`, {
        headers: {
          Authorization: `Bearer ${jwt}`,
        },
      });
      dispatch({
        type: actionTypes.GET_USER_SUBSCRIPTION_SUCCESS,
        payload: response.data,
      });
    } catch (error) {
      dispatch({
        type: actionTypes.GET_USER_SUBSCRIPTION_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const upgradeSubscription = ({ planType }) => {
  return async (dispatch) => {
    console.log("Upgrade Plan Type: ", planType);
    dispatch({ type: actionTypes.UPGRADE_SUBSCRIPTION_REQUEST });
    try {
      const response = await api.patch(`api/subscriptions/upgrade`, null, {
        params: {
          planType: planType,
        },
      });
      dispatch({
        type: actionTypes.UPGRADE_SUBSCRIPTION_SUCCESS,
        payload: response.data,
      });
      console.log("Payload on Upgrade Success: ", response.data);
    } catch (error) {
      dispatch({
        type: actionTypes.UPGRADE_SUBSCRIPTION_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};
