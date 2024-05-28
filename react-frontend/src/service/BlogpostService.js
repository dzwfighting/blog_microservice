import axios from "axios";

const POST_BASE_SERVICE_URL = "http://localhost:8086/blogpost-service/api/posts";
const POST_ID = 1;

class BlogpostService {
    getPost() {
        return axios.get(POST_BASE_SERVICE_URL + '/' + POST_ID);
    }
}