<template>
  <div class="loginbody">
    <div style="width: 24%; margin-left: 38%;  height: 60%;">
      <div class="logintext">
        <h2>Welcome</h2>
      </div>
      <div style="height: 39%; display: flex; flex-direction: column; justify-content: space-between">
        <div><el-input v-model="aaccount" placeholder="账号"></el-input> </div>
        <div><el-input v-model="apassword" placeholder="密码" show-password></el-input></div>
        <div style="display: flex; justify-content: space-between; align-items: center">
          <el-checkbox v-model="checked" @change="remenber">记住密码</el-checkbox>
          <div>
            <span class="shou" @click="forgetpas">忘记密码？</span>
          </div>

        </div>
        <!-- 登录和注册按钮     -->
        <div style="display: flex; justify-content: center">
          <div style=" width: 30%; height: 100%; display: flex; justify-content: space-between">
            <el-button type="primary" @click.native.prevent="login">登录</el-button>
            <el-button class="shou" @click="register">注册</el-button>
          </div>
        </div>
      </div>

      <div>

      </div>
    </div>
  </div>
</template>

<script>
import http from "../../network/http";

export default {
  name: "AdminLogin",
  data() {
    return {
      aid: 0,
      aname: '',
      asex: '',
      aaccount: '',
      apassword: '',
      checked: false,
    };
  },

  mounted() {

  },
  methods: {
    login()
    {
      http.get("admin/login", {aaccount: this.aaccount, apassword: this.apassword}).then(res => {
        if (res.data == '' || typeof (res.data) == 'undefined')
          this.$message({
            message: "账户名或密码错误",
            type: "error",
            showClose: true,
          });
        else {
          this.aaccount = res.data.aaccount;
          this.asex = res.data.asex;
          this.aname = res.data.aname;
          this.aid = res.data.aid;
          this.$message({
            message: "登录成功啦",
            type: "success",
            showClose: true,
          });
          this.$router.push({
            name: "AdminMain",
            query: {
              aid: this.aid,
              aaccount: this.aaccount,
              asex: this.asex,
              aname: this.aname
            }
          })
        }
      })
    },
    register()
    {
      this.$message({
        type: "info",
        message: "功能尚未开发额😥",
        showClose: true
      })
    }
  },

}
</script>

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: url("/src/views/photos/back/img.png");
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  display: flex;
  align-items: center;
}


.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata {
  width: 400px;
  height: 300px;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}

.shou:hover {
  color: #409EFF;
}
</style>