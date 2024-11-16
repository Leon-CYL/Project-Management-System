import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import authReducer from "./Auth/Reducer";
import projectReducer from "./Project/Reducer";
import ChatReducer from "./Chat/Reducer";
import CommentReducer from "./Comment/Reducer";
import IssueReducer from "./Issue/Reducer";
import SubscriptionReducer from "./Subscription/Reducer";

const rootReducer = combineReducers({
  auth: authReducer,
  project: projectReducer,
  chat: ChatReducer,
  comment: CommentReducer,
  issue: IssueReducer,
  subscription: SubscriptionReducer,
});
export const store = legacy_createStore(rootReducer, applyMiddleware(thunk));
