import { pool } from "../db.js";
import { StatusCodes } from "http-status-codes";

const getActivities = async (req, res) => {
  const { searchText } = req.query;

  if (typeof searchText !== "string") {
    return res.sendStatus(StatusCodes.BAD_REQUEST);
  }

  const [rows] = await pool.query(
    "SELECT * FROM activities WHERE start_date LIKE ? OR end_date LIKE ?",
    [`%${searchText}%`, `%${searchText}%`]
  );

  res.status(StatusCodes.OK).json(rows);
};

const getActivity = async (req, res) => {
  const { id } = req.params;

  const [rows] = await pool.query("SELECT * FROM activities WHERE id = ?", [
    id,
  ]);

  if (rows.length === 0) {
    return res.sendStatus(StatusCodes.NOT_FOUND);
  }

  res.status(StatusCodes.OK).json(rows[0]);
};

const createActivity = async (req, res) => {
  const {
    body: { start_date, end_date, activity, observation },
  } = req;

  if (!start_date || !end_date || !activity) {
    return res.sendStatus(StatusCodes.BAD_REQUEST);
  }

  const [rows] = await pool.query(
    "INSERT INTO activities (start_date, end_date, activity, observation) VALUES(?,?,?,?)",
    [start_date, end_date, activity, observation]
  );

  res.status(StatusCodes.CREATED).json({
    id: rows.insertId,
    start_date,
    end_date,
    activity,
    observation,
  });
};

const updateActivity = async (req, res) => {
  const {
    body: { start_date, end_date, activity, observation },
  } = req;

  const { id } = req.params;

  if (!start_date || !end_date || !activity) {
    return res.sendStatus(StatusCodes.BAD_REQUEST);
  }

  const [rows] = await pool.query(
    "UPDATE activities SET start_date = ?, end_date = ?, activity = ?, observation = ? WHERE id = ?",
    [start_date, end_date, activity, observation, id]
  );

  if (rows.affectedRows === 0) {
    return res.sendStatus(StatusCodes.NOT_FOUND);
  }

  res.status(StatusCodes.OK).json({
    id,
    start_date,
    end_date,
    activity,
    observation,
  });
};

const deleteActivity = async (req, res) => {
  const { id } = req.params;

  const [rows] = await pool.query("SELECT * FROM activities WHERE id = ?", [
    id,
  ]);

  if (rows.length === 0) {
    return res.sendStatus(StatusCodes.NOT_FOUND);
  }

  await pool.query("DELETE FROM activities WHERE id = ?", [id]);

  res.status(StatusCodes.OK).json(rows[0]);
};

export {
  getActivities,
  getActivity,
  createActivity,
  updateActivity,
  deleteActivity,
};
