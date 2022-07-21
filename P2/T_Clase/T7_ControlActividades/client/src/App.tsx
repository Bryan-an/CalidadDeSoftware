import React from "react";
import { Route, Routes } from "react-router-dom";
import { HeaderComponent } from "./components";
import { ActivitiesTablePage, ActivityFormPage, NotFoundPage } from "./pages";

function App() {
  return (
    <div className="bg-gray-100">
      <HeaderComponent />
      <div className=" flex justify-center pt-10 bg-gray-100 h-screen">
        <Routes>
          <Route path="/" element={<ActivitiesTablePage />} />
          <Route path="/crear" element={<ActivityFormPage />} />
          <Route path="/editar/:id" element={<ActivityFormPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
