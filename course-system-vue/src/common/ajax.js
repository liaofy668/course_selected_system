import Config from "./config";
import axios from "axios";
import ElementUI from "element-ui";
// import { rmSync } from "fs";

export const vueInstance = {
  instance: null,
  store: null
};

let runningRequest = 0;

function addRequestCount() {
  runningRequest++;
  if (runningRequest > 0 && vueInstance.store != null) {
    vueInstance.store.state.loading = true;
  }
}

function subtractRequestCount() {
  runningRequest--;
  if (runningRequest <= 0 && vueInstance.store != null) {
    vueInstance.store.state.loading = false;
  }
}

function innerMessage(type, message) {
  ElementUI.Message({
    showClose: true,
    duration: 2000,
    message: message,
    type: type
  });
}

export function exportInnerMessage(type, message) {
  ElementUI.Message({
    showClose: true,
    duration: 2000,
    message: message,
    type: type
  });
}

const axiosInstance = axios.create({
  baseURL: Config.backEndUrl,
  timeout: 30000,
  withCredentials: true
  // 是否携带凭证
});

axiosInstance.interceptors.response.use(
  // 例的响应拦截器（response interceptor）设置了两个处理函数：一个用于处理成功响应，另一个用于处理错误响应。
  response => {
    let data = response.data;
    // innerMessage('info','kkk');
    subtractRequestCount();
    return data;
  },
  error => {
    let code = error.response.code;
    let message = error.response.message;
    let data = error.response.data;
    subtractRequestCount();

    if (code === -1) {
      innerMessage("info", "需要登录");
      setTimeout(() => (window.location.href = "/login"), 250);
      return Promise.reject(new Error("NO_LOGIN"));
    } else if (code === 1) {
      innerMessage("error", "错误: " + message);
      return Promise.reject(new Error("FAIL"));
    } else if (code === 2) {
      innerMessage("info", "您不是此角色");
      setTimeout(() => (window.location.href = "/login"), 250);
      return Promise.reject(new Error("ERROR_ROLE"));
    } else if (code === 3) {
      innerMessage("info", "您没有此权限");
      setTimeout(() => (window.location.href = "/login"), 250);
      return Promise.reject(new Error("NO_PERMISSION"));
    } else if (code === 4) {
      let errorMessage = "请求参数错误";
      if (data.length > 0) {
        errorMessage = "参数错误: " + data[0].message;
      }
      innerMessage("error", errorMessage);
      return Promise.reject(new Error("INVALID_PARAMETER"));
    } else if (code === 10) {
      innerMessage("error", "内部服务器错误");
      return Promise.reject(new Error("INTERNAL_SERVER_ERROR"));
    }

    if (typeof error.response === "undefined") {
      innerMessage("error", "与后端服务器通信失败");
      vueInstance.instance.$router.push({ name: "login" });
    } else if (error.response.status >= 400) {
      innerMessage("error", "HTTP错误: " + error.response.status);
    }
    return Promise.reject(error);
  }
);

const dataHandle = (response) => {
  return response;
};

const innerGet = function() {
  addRequestCount();
  return axiosInstance.get.apply(this, arguments).then(dataHandle);
};

const innerPost = function() {
  // alert("发送请求")
  addRequestCount();
  return axiosInstance.post.apply(this, arguments).then(dataHandle);
};

const innerDelete = function() {
  addRequestCount();
  return axiosInstance.delete.apply(this, arguments).then(dataHandle);
};
// 使用 .then() 处理成功响应，并将响应数据传递给 dataHandle 函数。

const innerPut = function() {
  addRequestCount();
  return axiosInstance.put.apply(this, arguments).then(dataHandle);
};

export const pureGet = function(url) {
  return innerGet(url);
};

export const pureDelete = function(url) {
  return innerDelete(url);
};

export const get = function(url, params) {
  return innerGet(url, {
    params: params
  });
};

export const post = function(url, data) {
  return innerPost(url, data, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};

export const del = function(url, params) {
  return innerDelete(url, {
    params: params
  });
};

export const put = function(url, data) {
  return innerPut(url, data, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};
