import React, {useState, useEffect} from 'react';
import UserService from "../service/UserService";

const UserComponent = () => {
    const [userData, setUserData] = useState(null);
    const [loading, setLoading] = useState(true);

    const fetchUserData = async () => {
        setLoading(true);
        console.log("the initial userData is: " + userData);
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
      User Component
  </div>
}

export default UserComponent;