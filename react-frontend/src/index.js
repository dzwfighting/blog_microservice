import React from 'react';
import ReactDOM from 'react-dom/client';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {ChakraProvider} from "@chakra-ui/react";
// import './index.css';
import UserComponent from "./components/UserComponent";
import SignupComponent from "./components/SignupComponent";
import LoginComponent from "./components/LoginComponent";

import Posts from './components/posts/Posts';
import SideBar from "./components/communal/SideBar";
import PostDetail from "./components/posts/PostDetail";
import { UserProvider } from "./service/UserContext"
import PrivateRoute from "./components/PrivateRoute";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Posts />
    },
    {
        path: "/user",
        element: <PrivateRoute element={<UserComponent />}/>
    },
    {
        path: "/signup",
        element: <SignupComponent/>
    },
    {
        path: "/login",
        element: <LoginComponent/>
    },
    {
        path: "/product/detail",
        element: <PostDetail/>
    }
])

ReactDOM
    .createRoot(document.getElementById('root'))
    .render(
        <ChakraProvider>
            <React.StrictMode>
                <UserProvider>
                    <RouterProvider router={router}/>
                </UserProvider>
            </React.StrictMode>
        </ChakraProvider>
);

