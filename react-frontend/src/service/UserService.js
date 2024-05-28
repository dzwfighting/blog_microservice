import axios from 'axios'

const USER_SERVICE_BASE_URL = "http://localhost:8086/user-service/api/users"
const USER_ID = 8;
class UserService {

    getUser() {
        return axios.get(USER_SERVICE_BASE_URL + '/id/' + USER_ID)
            .then(response => response.data)
            .catch(error => {
                console.log("There was an error when fetching user data");
                throw error;
            });
    }
}

export default new UserService();