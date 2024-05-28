import axios from "axios";

const CATEGORY_BASE_SERVICE_URL = "http://localhost:8086/category-service/api/categories";
const CATEGORY_ID = 1;

class categoryService {
    getCategory() {
        return axios.get(CATEGORY_BASE_SERVICE_URL + "/" + CATEGORY_ID);
    }
}