import { Router } from "express";
import { ping } from "../controllers/index.controllers.js";

const router = Router();

router.route("/ping").get(ping);

export default router;
