<template>
  <div class="main">
    <div class="register" v-if="this.paccount == ''">
      <div  style=" height: 60%; display: flex; flex-direction: column; justify-content: space-around;">
        <div style="text-align: center;    border-bottom: 1px solid #EEEEEE;width: 80%; margin-left: 10%; ">
          <h1 style="color: #333333;">账号注册</h1>
        </div>
        <div style="display: flex;  justify-content: space-between ">
          <div style="width: 30%; text-align: end;"><h2><span style="color: #f56c6c;">*</span>用户名</h2></div>
          <div style="width: 60%;">
            <el-input :prefix-icon="User" v-model="pname" placeholder="请输入用户名" size="large" style="width: 90%; "> </el-input>
          </div>
        </div>
        <div style="display: flex; justify-content: space-between;">
          <div style="width: 30%;text-align: end"><h2><span style="color: #f56c6c;">*</span>密码</h2></div>
          <div style="width: 60%;">
            <el-input :prefix-icon="Lock" placeholder="请输入密码" v-model="ppassword"  size="large" show-password  style="width: 90%; "></el-input>
          </div>
        </div>
        <div style="display: flex; justify-content: space-between;">
          <div style="width: 30%;text-align: end"><h2><span style="color: #f56c6c;">*</span>年龄</h2></div>
          <div style="width: 60%;">
            <el-input placeholder="请输入年龄" v-model="page"  size="large"   style="width: 90%; "></el-input>
          </div>
        </div>
        <div style="display: flex; justify-content: space-between;">
          <div style="width: 30%;text-align: end"><h2><span style="color: #f56c6c;">*</span>性别</h2></div>
          <div style="width: 60%;">
            <el-radio v-model="psex" label="男">男</el-radio>
            <el-radio v-model="psex" label="女">女</el-radio>
          </div>
        </div>

      </div>
      <div style="height: 15%; display: flex;  margin-top: 2%; justify-content: center;">
        <el-button type="primary" style="height: 100%; width: 40%; font-size: 20px;" @click="toRegister" > 注册 </el-button>
      </div>
      <div style="display: flex;  justify-content: center; font-size:16px; color: #666666; align-items: center;">
        <div style="width: 40%; display: flex; align-items: center;">
          已有账户？<a href="#" @click="toLogin" style="text-decoration: none; color: #3880DE;">去登录</a>
        </div>
      </div>
    </div>
    <div v-else class="success" >
      <h2>恭喜您！注册成功</h2>
      <h2>您的新账号为<span style="color: blue;">{{this.paccount}}</span>,请记住！</h2>
      <h2><a href="#" @click="toLogin" style="text-decoration: none;">返回登录</a></h2>
    </div>
  </div>
</template>

<script>
import http from "../../network/http";

export default {
  name: "Register",
  data() {
    return {
      pname: "",
      ppassword: "",
      page: "",
      psex: '男',
      paccount: '',
    }
  },
  methods: {
    toRegister() {
      if(this.pname == '' || this.pname.indexOf(' ') != -1 || this.ppassword == '' || this.ppassword.indexOf(' ') != -1 || this.page == '' ){
        if(this.pname == '')
          this.$message.error('用户名不能为空！');
        else if(this.pname.indexOf(' ') != -1)
          this.$message.error('用户名不能包含空格！');
        else if(this.ppassword == '')
          this.$message.error('密码不能为空！');
        else if(this.ppassword.indexOf(' ') != -1)
          this.$message.error('密码不能包含空格！');
        else
          this.$message.error('年龄为必填项！');
      }
      else {
        let that = this
        http.get("person/register" , {pname: this.pname , ppassword: this.ppassword , psex: this.psex , page: this.page}).then((res) => {
          that.paccount = res.data.paccount;
        })
      }
    },
    toLogin() {
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>
.main {
  background-color: red;
  height: 100%;
  background-image: url("../photos/img.png");
  background-position: center center;
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
  background-color: #464646;
  border: 1px solid transparent;
  display: flex;
  justify-content: center;
  align-items: center;
}

.register {
  height: 50%;
  width: 40%;
  background-color: #ffffff;
  border-radius: 10px;
}


.success {
  height: 50%;
  width: 40%;
  background-color: #ffffff;
  border-radius: 10px;
  text-align: center;
}

.logo {
  background-color: #E85B3F;
  font-size: 12px;
  color: #fff;
  border-radius: 8px;
  padding: 5px 10px;
}



</style>