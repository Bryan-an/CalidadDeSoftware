import { createContext } from "react";
import { Activity } from "../services";

export interface ContextValue {
  activities: Activity[] | null;
  loadActivities: (searchText: string) => Promise<void>;
  deleteActivity: (id: number) => Promise<void>;
  updateActivity: (activity: Activity) => Promise<void>;
  createActivity: (activity: Activity) => Promise<void>;
  getActivity: (id: number) => Promise<Activity | undefined>;
}

export const ActivitiesContext = createContext({} as ContextValue);
