import api from "@/config/api";
import {
  ACCEPT_INVITATION_FAILURE,
  ACCEPT_INVITATION_REQUEST,
  ACCEPT_INVITATION_SUCCESS,
  CREATE_PROJECT_FAILURE,
  CREATE_PROJECT_REQUEST,
  CREATE_PROJECT_SUCCESS,
  DELETE_PROJECT_FAILURE,
  DELETE_PROJECT_REQUEST,
  DELETE_PROJECT_SUCCESS,
  FETCH_PROJECT_BY_ID_FAILURE,
  FETCH_PROJECT_BY_ID_REQUEST,
  FETCH_PROJECT_BY_ID_SUCCESS,
  FETCH_PROJECT_FAILURE,
  FETCH_PROJECT_REQUEST,
  FETCH_PROJECT_SUCCESS,
  INVITE_TO_FAILURE,
  INVITE_TO_REQUEST,
  INVITE_TO_SUCCESS,
  SEARCH_PROJECT_FAILURE,
  SEARCH_PROJECT_REQUEST,
  SEARCH_PROJECT_SUCCESS,
} from "./ActionType";

export const fetchProjects =
  ({ category, tag }) =>
  async (dispatch) => {
    dispatch({ type: FETCH_PROJECT_REQUEST });
    try {
      const response = await api.get(`/api/projects`, {
        params: { category, tag },
      });
      console.log("All projects", response.data);
      dispatch({ type: FETCH_PROJECT_SUCCESS, projects: response.data });
    } catch (error) {
      dispatch({
        type: FETCH_PROJECT_FAILURE,
        error: error.message,
      });
      console.log("error", error);
    }
  };

export const searchProjects = (keyword) => async (dispatch) => {
  dispatch({ type: SEARCH_PROJECT_REQUEST });
  try {
    const response = await api.get(`/api/projects/search?keyword=` + keyword);
    console.log("Search projects", response.data);
    dispatch({ type: SEARCH_PROJECT_SUCCESS, projects: response.data });
  } catch (error) {
    dispatch({
      type: SEARCH_PROJECT_FAILURE,
      error: error.message,
    });
    console.log("error", error);
  }
};

export const createProject = (projectData) => async (dispatch) => {
  dispatch({ type: CREATE_PROJECT_REQUEST });
  try {
    const response = await api.post(`/api/projects`, projectData);
    console.log("Create projects", response.data);
    dispatch({ type: CREATE_PROJECT_SUCCESS, project: response.data });
  } catch (error) {
    dispatch({
      type: CREATE_PROJECT_FAILURE,
      error: error.message,
    });
    console.log("error", error);
  }
};

export const fetchProjectById = (id) => async (dispatch) => {
  dispatch({ type: FETCH_PROJECT_BY_ID_REQUEST });
  try {
    const { data } = await api.get("/api/projects/" + id);
    console.log("Fetch project By ID", data);
    dispatch({ type: FETCH_PROJECT_BY_ID_SUCCESS, project: data });
  } catch (error) {
    dispatch({
      type: FETCH_PROJECT_BY_ID_FAILURE,
      error: error.message,
    });
    console.log("error", error);
  }
};

export const deleteProject = (projectId) => async (dispatch) => {
  dispatch({ type: DELETE_PROJECT_REQUEST });
  try {
    const data = await api.delete(`/api/projects/${projectId}`);
    console.log("Delete projects", data);
    dispatch({ type: DELETE_PROJECT_SUCCESS, projectId });
  } catch (error) {
    dispatch({
      type: DELETE_PROJECT_FAILURE,
      error: error.message,
    });
    console.log("error", error);
  }
};

export const inviteToProject =
  ({ email, projectId }) =>
  async (dispatch) => {
    dispatch({ type: INVITE_TO_REQUEST });
    try {
      const data = await api.post(`/api/projects/invite`, {
        email,
        projectId,
      });
      console.log("Invite projects", data);
      dispatch({ type: INVITE_TO_SUCCESS });
    } catch (error) {
      dispatch({
        type: INVITE_TO_FAILURE,
        error: error.message,
      });
      console.log("error", error);
    }
  };

export const acceptInvitation =
  ({ invitationToken, navigate }) =>
  async (dispatch) => {
    dispatch({ type: ACCEPT_INVITATION_REQUEST });
    try {
      const data = await api.get("/api/projects/accept_invitation", {
        params: {
          token: invitationToken,
        },
      });
      navigate("/project" + data.projectId);
      console.log("accept invitation", data);
      dispatch({ type: ACCEPT_INVITATION_SUCCESS });
    } catch (error) {
      dispatch({
        type: ACCEPT_INVITATION_FAILURE,
        error: error.message,
      });
      console.log("error", error);
    }
  };
