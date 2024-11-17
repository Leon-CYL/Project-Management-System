import api from "@/config/api";
import * as actionTypes from "./ActionType";

export const fetchIssues = (id) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_ISSUES_REQUEST });
    try {
      const response = await api.get(`api/issues/project/${id}`);
      dispatch({
        type: actionTypes.FETCH_ISSUES_SUCCESS,
        issues: response.data,
      });
      console.log("Create Issue ", response.data);
    } catch (error) {
      dispatch({
        type: actionTypes.FETCH_ISSUES_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const fetchIssuesById = (id) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.FETCH_ISSUE_BY_ID_REQUEST });
    try {
      const response = await api.get(`api/issues/${id}`);
      dispatch({
        type: actionTypes.FETCH_ISSUES_SUCCESS,
        issues: response.data,
      });
    } catch (error) {
      dispatch({
        type: actionTypes.FETCH_ISSUE_BY_ID_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const updateIssuesStatus = ({ id, status }) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.UPDATE_ISSUE_STATE_REQUEST });
    try {
      const response = await api.put(
        `api/issues/project/${id}/status/${status}`
      );
      dispatch({
        type: actionTypes.UPDATE_ISSUE_STATE_SUCCESS,
        issues: response.data,
      });
    } catch (error) {
      dispatch({
        type: actionTypes.UPDATE_ISSUE_STATE_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const assignedUserToIssues = ({ issueId, userId }) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.ASSIGNED_ISSUE_TO_REQUEST });
    try {
      const response = await api.put(
        `api/issues/${issueId}/assignee/${userId}`
      );
      dispatch({
        type: actionTypes.ASSIGNED_ISSUE_TO_SUCCESS,
        issue: response.data,
      });
    } catch (error) {
      dispatch({
        type: actionTypes.ASSIGNED_ISSUE_TO_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const createIssue = (issueData) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.CREATE_ISSUE_REQUEST });
    try {
      const response = await api.post("api/issues", issueData);
      dispatch({
        type: actionTypes.CREATE_ISSUE_SUCCESS,
        issue: response.data,
      });
      console.log("Issue Created", response.data);
    } catch (error) {
      dispatch({
        type: actionTypes.CREATE_ISSUE_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};

export const deleteIssue = (issueId) => {
  return async (dispatch) => {
    dispatch({ type: actionTypes.DELETE_ISSUE_REQUEST });
    try {
      const response = await api.delete(`api/issues/${issueId}`);
      dispatch({
        type: actionTypes.DELETE_ISSUE_SUCCESS,
        issueId,
      });
      console.log("Issue deleted", response.data);
    } catch (error) {
      dispatch({
        type: actionTypes.DELETE_ISSUE_FAILURE,
        error: error.message,
      });
      console.log(error);
    }
  };
};
