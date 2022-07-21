import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { ActivitiesContext } from "../context";
import { Activity } from "../services";
import "../App.css";

interface Props {
  activity: Activity;
  className: string;
}

export const TableRowComponent = ({
  activity: { id, start_date, end_date, activity, observation },
  className,
}: Props) => {
  const { deleteActivity } = useContext(ActivitiesContext);
  const navigate = useNavigate();

  const handleDelete = async () => {
    id !== undefined && (await deleteActivity(id));
  };

  return (
    <tr className={className}>
      <td className="p-2">{id}</td>
      <td className="p-2">{start_date}</td>
      <td className="p-2">{end_date}</td>
      <td className="p-2">{activity}</td>
      <td className="p-2">{observation}</td>
      <td className="flex justify-evenly p-1">
        <button
          onClick={() => navigate(`/editar/${id}`)}
          className="text-3xl p-1"
        >
          <span>&#9998;</span>
        </button>
        <button onClick={handleDelete} className="text-3xl p-1">
          <i className="gg-trash"></i>
        </button>
      </td>
    </tr>
  );
};
