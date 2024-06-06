import React, {useEffect, useState} from "react";
import {useUser} from "../service/UserContext";
import {Navigate} from "react-router-dom";

const PrivateRoute = ({element}) => {
  const { user } = useUser();
  console.log("in privateRoute, the current user is: " + JSON.stringify(user));

  return user ? element : <Navigate to="/"/>
}

export default PrivateRoute;