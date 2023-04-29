import axios from "axios";


const API = axios.create({
    baseURL:'http://localhost:8080/',
    timeout: 2000,

})

API.interceptors.request.use(function (config) {
    let token = sessionStorage.getItem("token")
    if(token) {
        config.headers['Auth'] = token;//将token添加至请求头对象的Auth属性中
    }

    return config

} , function(error) {
    this.$router.push("/login")
    return Promise.reject(error)
})

// 响应拦截器
API.interceptors.response.use(
    response => {
        // 如果返回的状态码为201，说明接口请求成功，可以正常拿到数据
        // 否则的话抛出错误
        if (response.status === 200) {
            return response
        } else {
            return Promise.reject(response.data);
        }
    },
    error => {

        // 做一些自己的逻辑处理
        switch (error.response.status) {
            case 401:
                window.location.href = "/login"
                break;
            default:
                break;
        }
        return Promise.reject(error);
    });

export default API