import axios from "axios";

const POST_BASE_SERVICE_URL = "http://localhost:8086/blogpost-service/api/posts";
const POST_ID = 1;

class BlogpostService {
    savePost(author, title, description, category) {
        return axios.post(POST_BASE_SERVICE_URL, {author, title, description, category})
    }
    getPost() {
        return axios.get(POST_BASE_SERVICE_URL + '/' + POST_ID);
    }

    getPosts() {
        return axios.get(POST_BASE_SERVICE_URL);
    }

    deletePostById(id) {
        return axios.delete(POST_BASE_SERVICE_URL + '/' + id);
    }
}

export default new BlogpostService();