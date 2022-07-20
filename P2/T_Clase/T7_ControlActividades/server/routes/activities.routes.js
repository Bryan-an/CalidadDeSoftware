import { Router } from "express";
import {
  getActivities,
  getActivity,
  createActivity,
  updateActivity,
  deleteActivity,
} from "../controllers/activities.controllers.js";

const router = Router();
router.route("/").get(getActivities).post(createActivity);
router
  .route("/:id")
  .get(getActivity)
  .put(updateActivity)
  .delete(deleteActivity);

export default router;
