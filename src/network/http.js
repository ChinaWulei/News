import API from "./axios";
const http = {
    get(url, params) {
        const config = {
            method: "get",
            url: url
        }
        if (params) {
            config.params = params
        }
        return API(config)
    },
    post(url, params) {
        const config = {
            method: "post",
            url: url,
        }
        if (params) {
            config.data = params;
            console.log('传递过来的参数========' + params)
        }
        return API(config)
    }
}
export default http