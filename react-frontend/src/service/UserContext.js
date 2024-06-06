import React, { createContext, useContext, useState, useEffect } from "react";
import UserService from "./UserService";

const UserContext = createContext(null);

export const useUser = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        if (storedUser) setUser(JSON.parse(storedUser));
        console.log("already store in localstorage: " + storedUser);
    }, []);

    const login = async (userData) => {
        if (userData == null) throw "the userData is empty"
        const checkUser = await UserService.getUserByEmail(userData.email);
        console.log("the checkUser is: " + JSON.stringify(checkUser))
        if (!checkUser) throw "This email is not exist, please register"
        if (checkUser && userData.password != checkUser.password) throw "the password is not match, please try again! "
        console.log("after login, the checkUserData is: " + JSON.stringify(userData))
        localStorage.setItem('user', JSON.stringify(userData));
        setUser(userData);
        return userData;
    }

    const logout = () => {
      localStorage.removeItem('user');
      setUser(null);
    }

    return <UserContext.Provider value={{user, login, logout}}>
        {children}
    </UserContext.Provider>
}

