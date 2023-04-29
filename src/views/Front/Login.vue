<template>
  <div class="main">

      <div class="login"  >
       <div style=" height: 60%; display: flex; flex-direction: column; justify-content: space-between;">
        <div style="text-align: center;    border-bottom: 1px solid #EEEEEE;width: 80%; margin-left: 10%; display: flex; justify-content: space-around;">
          <a href="#" ><h1 style="color: #333333;" @click="methodToLogin(1)">账号登陆</h1></a>

          <a href="#"><h1 style="color: #333333;" @click="methodToLogin(2)">手机号登陆</h1></a>
        </div>
        <div style="display: flex; flex-direction: column; justify-content: space-around; height: 60%;" v-show="this.loginMethod == 1">
          <div style="display: flex;  justify-content: space-between; ">
            <div style="width: 30%; text-align: end;"><h2>账号</h2></div>
            <div style="width: 60%;">
              <el-input :prefix-icon="User" v-model="account" placeholder="请输入账号" size="large" style="width: 90%;" id="account" > </el-input>
            </div>
          </div>
          <div style="display: flex; justify-content: space-between;">
            <div style="width: 30%;text-align: end"><h2>密码</h2></div>
            <div style="width: 60%;">
              <el-input :prefix-icon="Lock" placeholder="请输入密码" v-model="password"  size="large" show-password id="password" style="width: 90%; "></el-input>
            </div>
          </div>
        </div>
         <!--   采用手机登录        -->
         <div style="display: flex; flex-direction: column; justify-content: space-around; height: 70%;  align-items: center;" v-show="this.loginMethod == 2">
           <div style="width: 60%">
             <div style="background-color: #fef2f2; font-size: 20px; display: flex; align-items: center;" v-show="this.flag == 2"><Warning style="width: 20px; height: 20px;"></Warning>{{loginRemind}}</div>
             <div style="display: flex; justify-content: space-between;  ">
               <div style="width: 30%; "><h2>手机号</h2></div>
               <div style="width: 60%;">
                 <el-input  v-model="phone" placeholder="请输入手机号码" size="large" style="width: 100%;" id="account" >
                   <template #prefix>
                     <el-icon class="el-input__icon" size="large"><Cellphone /></el-icon>
                   </template>
                 </el-input>
               </div>
             </div>
           </div>

           <div style="width: 60%;">
             <div style="display: flex;  justify-content: space-between; width: 100%;">
              <div style="width: 30%; "><h2>验证码</h2></div>
              <div style="width: 60%;">
                <el-input placeholder="请输入验证码" v-model="code"  size="large"  id="password" style="width: 100%; ">
                  <template #prefix>
                    <el-icon class="el-input__icon" size="large"><Message /></el-icon>
                  </template>
                  <template #append>
                    <a href="#" style="color: black" @click="getCode(this.phone)" v-if="this.isSend == 0">
                      <span >{{remindMsg}}</span>
                    </a>
                    <span v-else>{{sec}}秒后重发</span>
                  </template>
                </el-input>
             </div>
           </div>
         </div>
         </div>
       </div>
        <div style="height: 15%; display: flex;  margin-top: 2%; justify-content: center;">
          <el-button v-if="canLogin()" type="info" style="height: 100%; width: 40%; font-size: 20px;"> 登录 </el-button>
          <el-button v-else type="primary" style="height: 100%; width: 40%; font-size: 20px;"  @click="login">登录</el-button>
        </div>
        <div style="display: flex;  justify-content: center; font-size:16px; color: #666666; align-items: center;">
          <div style="width: 40%; display: flex; align-items: center;">
            没有账户？<a href="#" @click="toRegister" style="text-decoration: none; color: #3880DE;">去注册</a>
            <img src="../photos/img_1.png" alt=""><div class="logo" style="display: inline;">注册免费送6张发稿劵</div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import {useRouter} from "vue-router";

import http from "../../network/http";
import {Search,Edit,Check,Message,Star,User,Lock, Cellphone} from "@element-plus/icons-vue";


export default {
  name: "Login",
  components: {Cellphone},
  mounted() {
    this.pname = this.$route.query.pname
    this.ncontent = this.$route.query.ncontent
    this.ntime = this.$route.query.ntime
    this.ntitle = this.$route.query.ntitle
    this.nid = this.$route.query.nid
  },
  setup(){
    const router = useRouter();
    function goTo(path) {
      router.push({
        path: path
      })
    }

    return {
      Search,
      User,
      Lock,
    }
  },
  data() {
    return {
      ntitle: '',
      ncontent: '',
      ntime: '',
      pname: '',
      nid: 0,
      account: "",
      password: "",
      loginMethod: 1,
      phone: "",
      code: "",
      rightCode: '',              //正确的验证码
      remindMsg: "获取验证码",
      sec: 120,
      flag: 1,
      isSend: 0,        //是否已经发送验证码  0代表还没发
      loginRemind: "手机号码格式不正确，请重新输入"
    }
  },

  methods: {
    canLogin() {
      if(this.loginMethod == 1) {
        if(this.account == '' || this.password == '')
          return true
        else
          return false
      }
      else {
        if(this.phone == '' || this.code == '')
          return true
        else
          return false
      }
    },
    getCode(phoneNum) {
      var re = /^1[3,4,5,6,7,8,9][0-9]{9}$/;
      var result = re.test(phoneNum);
      if(!result) {      //手机号格式不正确
        this.flag = 2
        return false
      }
      else {          //手机号格式正确，点击发送验证码
        this.isSend = 1;
        this.remindMsg = "重新发送"
        let that = this
        http.get("/person/sendMsg/"+this.phone , {}).then(res => {

        })
        var timer = setInterval(function() {
          that.sec--
          if(that.sec == 0) {
            clearInterval(timer)
            that.isSend = 0
            that.sec = 120
          }
        } , 1000)
        return true
      }

    },
    methodToLogin(num) {
      this.loginMethod = num;
      if(num == 1) {
        this.phone = '';
        this.code = '';
      }
      else {
        this.account = ''
        this.password = ''
      }

    },
    login() {
      if(this.loginMethod == 1) {             //采用账号密码登录
        if(this.account == "" || this.password == "")
          this.$message.error('账号或密码不能为空！');
        else if(this.account.indexOf(' ') != -1 || this.password.indexOf(' ') != -1 )
          this.$message.error('账号密码不能含有空格！');
        else {

          let that = this
          http.get("/person/login" , {paccount: this.account, ppassword: this.password}).then((res)=> {
            if(res.data == '' || res.data.person == '')       //登录失败
              that.$message.error('账号密码错误');
            else {                 //登陆成功
              sessionStorage.setItem("token" , res.data.token)
              if(typeof(that.pname)== 'undefined' || that.pname == null)       //登陆成功后跳到主页
                that.$router.push({
                  name: 'main',
                  params: {
                    pid: res.data.pid
                  }
                });
              else                                //登陆成功后直接返回某一条新闻详情页
                that.$router.push({
                  name: 'detail',
                  query: {
                    pid:  res.data.pid,
                    pname: that.pname,
                    ncontent: that.ncontent,
                    ntitle: that.ntitle,
                    ntime: that.ntime,
                    nstate: '已审核',
                    nid: that.nid,
                  }
                });
            }
          })
        }
      }
      else {                      //采用手机号登录
        var re = /^1[3,4,5,6,7,8,9][0-9]{9}$/;
        var result = re.test(this.phone);
        if(result) {         //手机号格式正确
          http.get("/person/testCode" , {phone: this.phone , code: this.code}).then(res => {
            if(res.data.result == "true") {
              var pid = res.data.pid;
              var token = res.data.token
              sessionStorage.setItem("token" , token)
              this.$router.push({
                name: "main",
                params: {
                  pid: pid
                }
              })
            }

            else {
              this.flag = 2;
              this.loginRemind = "验证码错误"
            }

          })
        }
        else {          //手机号格式不正确
          this.flag = 2;
          this.loginRemind = '手机号码格式不正确，请重新输入';
        }

      }
    },

    toRegister() {
      this.$router.push("/register")

    },


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

.login {
  height: 50%;
  width: 40%;
  background-color: #ffffff;
  border-radius: 10px;
}

.logo {
  background-color: #E85B3F;
  font-size: 12px;
  color: #fff;
  border-radius: 8px;
  padding: 5px 10px;
}

a {
  text-decoration: none;
}




</style>