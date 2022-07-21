import React, { useState } from "react";
import {
  Activity,
  createActivityRequest,
  deleteActivityRequest,
  getActivitiesRequest,
  getActivityRequest,
  updateActivityRequest,
} from "../services";
import { ActivitiesContext, ContextValue } from "./ActivitiesContext";

interface Props {
  children: JSX.Element;
}

export const ActivitiesProvider = ({ children }: Props) => {
  const [activities, setActivities] = useState<Activity[] | null>(null);

  const loadActivities: ContextValue["loadActivities"] = async (
    searchText: string
  ) => {
    try {
      const { data } = await getActivitiesRequest(searchText);
      setActivities(data);
    } catch (error) {
      alert("Error al cargar las actividades");
      console.log(error);
    }
  };

  const getActivity: ContextValue["getActivity"] = async (id: number) => {
    const { data } = await getActivityRequest(id);
    return data;
  };

  const deleteActivity: ContextValue["deleteActivity"] = async (id: number) => {
    try {
      const { data } = await deleteActivityRequest(id);
      console.log(data);

      setActivities(
        (prevActivities) =>
          prevActivities &&
          prevActivities.filter((activity) => activity.id !== id)
      );
    } catch (error) {
      alert(`Error al eliminar la actividad`);
      console.log(error);
    }
  };

  const createActivity: ContextValue["createActivity"] = async (
    activity: Activity
  ) => {
    try {
      const { data } = await createActivityRequest(activity);
      console.log(data);

      setActivities(
        (prevActivities) => prevActivities && [...prevActivities, activity]
      );

      alert("Actividad creada");
    } catch (error) {
      alert(`Error al crear la actividad`);
      console.log(error);
    }
  };

  const updateActivity: ContextValue["updateActivity"] = async (
    activity: Activity
  ) => {
    try {
      const { data } = await updateActivityRequest(activity);
      console.log(data);

      setActivities(
        (prevActivities) =>
          prevActivities &&
          prevActivities.map((act) => {
            if (act.id === activity.id) {
              return data;
            }

            return act;
          })
      );

      alert("Actividad actualizada");
    } catch (error) {
      alert(`Error al actualizar la actividad`);
      console.log(error);
    }
  };

  const contextValue: ContextValue = {
    activities,
    loadActivities,
    deleteActivity,
    updateActivity,
    createActivity,
    getActivity,
  };

  return (
    <ActivitiesContext.Provider value={contextValue}>
      {children}
    </ActivitiesContext.Provider>
  );
};
