/* eslint-disable react-hooks/exhaustive-deps */
import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { TableRowComponent } from "../components";
import { ActivitiesContext } from "../context";

export const ActivitiesTablePage = () => {
  const { activities, loadActivities } = useContext(ActivitiesContext);
  const [searchText, setSearchText] = useState<string>("");
  const navigate = useNavigate();

  useEffect(() => {
    loadActivities(searchText);
  }, [searchText]);

  return (
    <div className="bg-gray-100">
      {activities === null ? (
        <div className="flex justify-center">
          <p className="text-3xl font-extrabold text-center">Cargando...</p>
        </div>
      ) : (
        <>
          <div className="flex justify-center bg-gray-100">
            <input
              type="text"
              id="searchText"
              className="rounded-lg px-2 w-6/12 border-slate-200 border-2 outline-none"
            />
            <button
              onClick={() =>
                setSearchText(
                  (document.getElementById("searchText") as HTMLInputElement)
                    .value
                )
              }
              className="bg-sky-600 hover:bg-sky-700 p-2 text-white rounded-md mx-2"
            >
              Buscar
            </button>
            <button
              onClick={() => navigate("/crear")}
              className="bg-lime-500 hover:bg-lime-600 p-2 text-white rounded-md"
            >
              Agregar
            </button>
          </div>
          {activities.length === 0 ? (
            <div className="flex justify-center p-20">
              <p className="text-3xl font-bold">Sin actividades</p>
            </div>
          ) : (
            <div className="pt-10 bg-gray-100">
              <table className="table-auto">
                <thead className="bg-slate-700">
                  <tr>
                    <th className="text-white p-5 text-center">ID</th>
                    <th className="text-white p-5 text-center">FECHA FIN</th>
                    <th className="text-white p-5 text-center">FECHA INICIO</th>
                    <th className="text-white p-5 text-center">ACTIVIDAD</th>
                    <th className="text-white p-5 text-center">OBSERVACIÓN</th>
                    <th className="text-white p-5 text-center">ACCIONES</th>
                  </tr>
                </thead>
                <tbody>
                  {activities.map((activity, index) => (
                    <TableRowComponent
                      activity={activity}
                      key={index}
                      className={
                        index % 2 === 0
                          ? "bg-zinc-400 text-white"
                          : "bg-zinc-200"
                      }
                    />
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </>
      )}
    </div>
  );
};
