import { pool } from "../db.js";

const ping = async (req, res) => {
  const [rows] = await pool.query("SELECT 1 + 1 as result");
  res.json(rows[0]);
};

export { ping };
