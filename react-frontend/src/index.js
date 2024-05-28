import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
// import './index.css';
import UserComponent from "./components/UserComponent";
import App from './App';

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />
    },
    {
        path: "/user",
        element: <UserComponent />
    }
])

ReactDOM
    .createRoot(document.getElementById('root'))
    .render(
      <React.StrictMode>
        <RouterProvider router={router}/>
      </React.StrictMode>
);

