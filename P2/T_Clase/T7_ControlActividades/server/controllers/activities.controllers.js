import { pool } from "../db.js";
import { StatusCodes } from "http-status-codes";

const getActivities = async (req, res) => {
  try {
    const { start_date, end_date } = req.body;

    if (typeof start_date !== "string" || typeof end_date !== "string") {
      return res.sendStatus(StatusCodes.BAD_REQUEST);
    }

    const [rows] = await pool.query(
      "SELECT * FROM activities WHERE start_date LIKE ? OR end_date LIKE ?",
      [`%${start_date}%`, `%${end_date}%`]
    );

    res.status(StatusCodes.OK).json(rows);
  } catch (error) {
    res.status(StatusCodes.INTERNAL_SERVER_ERROR).json(error);
  }
};

const getActivity = async (req, res) => {
  try {
    const { id } = req.params;

    const [rows] = await pool.query("SELECT * FROM activities WHERE id = ?", [
      id,
    ]);

    if (rows.length === 0) {
      return res.sendStatus(StatusCodes.NOT_FOUND);
    }

    res.status(StatusCodes.OK).json(rows[0]);
  } catch (error) {
    res.status(StatusCodes.INTERNAL_SERVER_ERROR).json(error);
  }
};

const createActivity = async (req, res) => {
  try {
    const {
      body: { start_date, end_date, activity, observation },
    } = req;

    if (!start_date || !end_date || !activity || !observation) {
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
  } catch (error) {
    res.status(StatusCodes.INTERNAL_SERVER_ERROR).json(error);
  }
};

const updateActivity = async (req, res) => {
  try {
    const {
      body: { start_date, end_date, activity, observation },
    } = req;

    const { id } = req.params;

    if (!start_date || !end_date || !activity || !observation) {
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
  } catch (error) {
    res.status(StatusCodes.INTERNAL_SERVER_ERROR).json(error);
  }
};

const deleteActivity = async (req, res) => {
  try {
    const { id } = req.params;

    const [rows] = await pool.query("SELECT * FROM activities WHERE id = ?", [
      id,
    ]);

    if (rows.length === 0) {
      return res.sendStatus(StatusCodes.NOT_FOUND);
    }

    await pool.query("DELETE FROM activities WHERE id = ?", [id]);

    res.status(StatusCodes.OK).json(rows[0]);
  } catch (error) {
    res.status(StatusCodes.INTERNAL_SERVER_ERROR).json(error);
  }
};

export {
  getActivities,
  getActivity,
  createActivity,
  updateActivity,
  deleteActivity,
};
