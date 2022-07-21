import { axiosInstance } from "../config";

export interface Activity {
  id?: number;
  start_date: string;
  end_date: string;
  activity: string;
  observation: string | null;
}

export const createActivityRequest = async (activity: Activity) =>
  axiosInstance.post<Activity>("/activities", activity);

export const getActivitiesRequest = async (searchText: string) =>
  axiosInstance.get<Activity[]>("/activities", {
    params: { searchText },
  });
export const getActivityRequest = async (id: number) =>
  axiosInstance.get<Activity>(`/activities/${id}`);

export const updateActivityRequest = async (activity: Activity) =>
  axiosInstance.put<Activity>(`/activities/${activity.id}`, activity);

export const deleteActivityRequest = async (id: number) =>
  axiosInstance.delete<Activity>(`/activities/${id}`);
