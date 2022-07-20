import * as dotenv from "dotenv";
dotenv.config();
import express from "express";
import indexRoutes from "./routes/index.routes.js";
import activitiesRouter from "./routes/activities.routes.js";

const app = express();
app.use(express.json());
app.use(indexRoutes);
app.use("/api/v1/activities", activitiesRouter);

app.listen(process.env.PORT, () => {
  console.log(`Server is listening in port ${process.env.PORT}`);
});
