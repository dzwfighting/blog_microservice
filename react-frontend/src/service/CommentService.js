import axios from "axios";

const COMMENT_BASE_SERVICE_URL = "http://localhost:8086/comment-service/api/comments";
// const COMMENT_ID = 1;

class CommentService {
    getCommentById(id) {
        console.log("move into CommentService,the id is: " + id);
        const res =  axios.get(COMMENT_BASE_SERVICE_URL + '/' + id)
            .then(response => response.data)
            .catch(error => {
                console.log("Exist error when get comment by Id")
                throw error;
            })
        // console.log("get Comment By Id: " + JSON.stringify(res));
        return res;
    }
    postComment(reviewer, title, body, postId) {
        const comment = {reviewer, title, body, postId}
        console.log("in CommentService, will add a comment, the comment is: " + JSON.stringify(comment));
        return axios.post(COMMENT_BASE_SERVICE_URL, comment)
            .then(response => {
                console.log("add comment success, the res is: " + JSON.stringify(response.data.commentDTO))
                return response.data.commentDTO
            })
            .catch(error => {
                console.log("Exist error when add a comment in database");
                throw error;
            });
    }

}

export default new CommentService();