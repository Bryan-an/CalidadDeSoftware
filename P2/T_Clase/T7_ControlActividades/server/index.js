import * as dotenv from "dotenv";
dotenv.config();
import express from "express";
import cors from "cors";
import "express-async-errors";
import indexRoutes from "./routes/index.routes.js";
import activitiesRouter from "./routes/activities.routes.js";
import { errorHandlerMiddleware } from "./middlewares/error_handler.js";
import { dirname, join } from "path";
import { fileURLToPath } from "url";

const app = express();
const __dirname = dirname(fileURLToPath(import.meta.url));
console.log(__dirname);

app.use(cors());
app.use(express.json());

app.use(indexRoutes);
app.use("/api/v1/activities", activitiesRouter);

app.use(errorHandlerMiddleware);

app.use(express.static(join(__dirname, "../client/build")));

app.listen(process.env.PORT, () => {
  console.log(`Server is listening in port ${process.env.PORT}`);
});
