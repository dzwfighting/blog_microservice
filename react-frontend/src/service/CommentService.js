import axios from "axios";

const COMMENT_BASE_SERVICE_URL = "http://localhost:8086/comment-service/api/comments";
const COMMENT_ID = 1;

class CommentService {
    getComment() {
        return axios.get(COMMENT_BASE_SERVICE_URL + "/" + COMMENT_ID);
    }
}