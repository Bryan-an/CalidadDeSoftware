import React, { useContext, useEffect, useState } from "react";
import { Form, Formik } from "formik";
import * as Yup from "yup";
import { Activity } from "../services";
import { ActivitiesContext } from "../context";
import { useNavigate, useParams } from "react-router-dom";

export const ActivityFormPage = () => {
  const { createActivity, getActivity, updateActivity } =
    useContext(ActivitiesContext);
  const [activity, setActivity] = useState<Activity>({
    start_date: "",
    end_date: "",
    activity: "",
    observation: "",
  });
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();

  useEffect(() => {
    const loadActivity = async () => {
      try {
        if (id) {
          const data = await getActivity(parseInt(id));
          data &&
            setActivity({
              start_date: data.start_date.split("T")[0],
              end_date: data.end_date.split("T")[0],
              activity: data.activity,
              observation: data.observation,
            });
        }
      } catch (error) {
        alert("Error al editar la actividad");
        console.log(error);
      }
    };

    loadActivity();
  }, []);

  return (
    <div className="w-4/12 bg-gray-100">
      <h2 className="text-3xl font-extrabold mb-4 text-center">
        {id ? "Editar Actividad" : "Crear Actividad"}
      </h2>
      <Formik<Activity>
        initialValues={activity}
        enableReinitialize={true}
        validationSchema={Yup.object({
          start_date: Yup.date().required("Campo requerido"),
          end_date: Yup.date().required("Campo requerido"),
          activity: Yup.string()
            .trim()
            .max(100, "La actividad no puede contener más de 100 caracteres")
            .required("Campo requerido"),
          observation: Yup.string().trim(),
        })}
        onSubmit={async (values: Activity) => {
          if (!values.observation) {
            values.observation = null;
          }

          try {
            if (id) {
              await updateActivity({ id: parseInt(id), ...values });
            } else {
              await createActivity(values);
            }
            navigate("/");
          } catch (error) {
            console.error(error);
          }
        }}
      >
        {({
          handleChange,
          handleBlur,
          handleSubmit,
          isSubmitting,
          touched,
          errors,
          values,
        }) => (
          <Form
            onSubmit={handleSubmit}
            className="flex justify-center flex-col"
          >
            <label htmlFor="start_date" className="text-lg py-2">
              Fecha de inicio
            </label>
            <input
              type="date"
              name="start_date"
              onChange={handleChange}
              onBlur={handleBlur}
              value={values.start_date}
              className="mt-1 block w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm shadow-sm placeholder-slate-400
              focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
              disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
              invalid:border-pink-500 invalid:text-pink-600
              focus:invalid:border-pink-500 focus:invalid:ring-pink-500"
            />
            {touched.start_date && errors.start_date ? (
              <div className="text-sm py-2 text-red-600">
                {errors.start_date}
              </div>
            ) : null}

            <label htmlFor="end_date" className="text-lg py-2">
              Fecha de fin
            </label>
            <input
              type="date"
              name="end_date"
              onChange={handleChange}
              onBlur={handleBlur}
              value={values.end_date}
              className="mt-1 block w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm shadow-sm placeholder-slate-400
              focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
              disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
              invalid:border-pink-500 invalid:text-pink-600
              focus:invalid:border-pink-500 focus:invalid:ring-pink-500"
            />
            {touched.end_date && errors.end_date ? (
              <div className="text-sm py-2 text-red-600">{errors.end_date}</div>
            ) : null}

            <label htmlFor="activity" className="text-lg py-2">
              Actividad
            </label>
            <input
              type="text"
              name="activity"
              onChange={handleChange}
              onBlur={handleBlur}
              value={values.activity}
              className="mt-1 block w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm shadow-sm placeholder-slate-400
              focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
              disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
              invalid:border-pink-500 invalid:text-pink-600
              focus:invalid:border-pink-500 focus:invalid:ring-pink-500"
            />
            {touched.activity && errors.activity ? (
              <div className="text-sm py-2 text-red-600">{errors.activity}</div>
            ) : null}

            <label htmlFor="observation" className="text-lg py-2">
              Observación
            </label>
            <textarea
              name="observation"
              rows={3}
              onChange={handleChange}
              onBlur={handleBlur}
              value={values.observation || ""}
              className="mt-1 block w-full px-3 py-2 bg-white border border-slate-300 rounded-md text-sm shadow-sm placeholder-slate-400
              focus:outline-none focus:border-sky-500 focus:ring-1 focus:ring-sky-500
              disabled:bg-slate-50 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none
              invalid:border-pink-500 invalid:text-pink-600
              focus:invalid:border-pink-500 focus:invalid:ring-pink-500"
            ></textarea>
            {touched.observation && errors.observation ? (
              <div className="text-sm py-2 text-red-600">
                {errors.observation}
              </div>
            ) : null}

            <div className="flex justify-center p-5">
              <button
                type="submit"
                disabled={isSubmitting}
                className="bg-sky-600 hover:bg-sky-700 p-2 text-white rounded-md my-5 w-28 mx-2"
              >
                {isSubmitting
                  ? id
                    ? "Actualizando..."
                    : "Creando..."
                  : id
                  ? "Actualizar"
                  : "Crear"}
              </button>
              <button
                type="button"
                onClick={() => navigate("/")}
                className="bg-orange-600 hover:bg-orange-700 p-2 text-white rounded-md my-5 w-28 mx-2"
              >
                Cancelar
              </button>
            </div>
          </Form>
        )}
      </Formik>
    </div>
  );
};
