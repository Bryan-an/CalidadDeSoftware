import { createPool } from "mysql2/promise";

export const pool = createPool({
  host: "localhost",
  port: 3306,
  user: "root",
  password: "",
  database: "calidad_p2_t7_control_actividades",
});
