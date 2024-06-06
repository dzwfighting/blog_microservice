import React, {useState, useEffect} from 'react';
import UserService from "../service/UserService";
import {useNavigate} from "react-router-dom"
import SideBar from "./communal/SideBar";

const UserComponent = () => {
    const [userData, setUserData] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const fetchUserData = async () => {
        setLoading(true);
        try {
            const getUserData = await UserService.getUser();
            await setUserData(getUserData);
            console.log(await userData);
        } catch (e) {
            console.log(e);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        console.log("in userEffect to get userData: " + JSON.stringify(userData))
        if (!userData) fetchUserData();
    }, [userData])

    if (loading) {
        return <div>Loading...</div>
    }

  return <div>
      <SideBar />
      User Component
  </div>
}

export default UserComponent;