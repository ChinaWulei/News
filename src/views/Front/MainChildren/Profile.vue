<template>
  <div style="width: 100%; height: 100%;">
    <div style=" height: 10%; margin-left: 25%;">
      <el-button type="primary"  style=" height: 100%; width: 100px;" @click="changePname">修改用户名</el-button>
      <el-button type="primary" style="margin-left: 300px; height: 100%; width: 100px;" @click="changePpassword">修改密码</el-button>
    </div>
    <div style=" width: 70%; height: 58%;   margin-left: 25%; display: flex; flex-direction: column; justify-content: space-between;     font-size: 25px; font-weight: 700;}">
      <div style=" display: flex; height: 20%; align-items: center;">
        <div style="width: 34%;">用户名:</div>
        <div >{{pname}}</div>
      </div>
      <div style=" display: flex; height: 20%;  align-items: center;">
        <div style="width: 34%;">账号:</div>
        <div>{{paccount}}</div>
      </div>
      <div style=" display: flex; height: 20%;  align-items: center;">
        <div style="width: 34%;">密码:</div>
        <div style="">*********</div>
      </div>
      <div style=" display: flex; height: 20%;  align-items: center;">
        <div style="width: 34%;">性别:</div>
        <div>{{psex}}</div>
      </div>
      <div style=" display: flex; height: 20%;  align-items: center;">
        <div style="width: 34%;">年龄:</div>
        <div>{{page}}</div>
      </div>
    </div>

    <div style=" width: 70%; height: 20%; margin-left: 25%; display: flex;  align-items: center   ">
      <el-button type="primary" style="margin-left: 20%; height: 60px;" size="big" @click="toLogin">退出登录</el-button>
    </div>

    <div v-if="ischangePname" style="position: fixed; height: 50%; width: 50%; top: 25%; left: 25%; background-color: white; border: 5px solid grey;" >
      <div style="text-align: right; width: 100%; height: 10%; font-size: 30px;">
        <span style="cursor: pointer" @click="changePname">X</span>
      </div>
      <div style="width: 50%; margin-left: 25%; margin-top: 10%;">
        <el-input v-model="newPname" placeholder="请输入新用户名" ></el-input>
      </div>
      <div style="width: 50%; margin-left: 25%; margin-top: 10%; text-align: center">
        <el-button type="primary" @click="successChangePname">修改</el-button>
        <el-button type="danger" @click="changePname">取消</el-button>
      </div>
    </div>

    <div v-if="ischangePpassword" style="position: fixed; height: 50%; width: 50%; top: 25%; left: 25%; background-color: white; border: 5px solid grey;" >
      <div style="text-align: right; width: 100%; height: 10%; font-size: 30px;">
        <span style="cursor: pointer" @click="changePpassword">X</span>
      </div>
      <div style="width: 50%; margin-left: 25%; margin-top: 10%;  height: 30%; display: flex; flex-direction: column; justify-content: space-around;">
        <el-input v-model="oldPpassword" placeholder="请输入旧密码"></el-input>
        <el-input v-model="newPpassword" placeholder="请输入新密码"></el-input>
      </div>
      <div style="width: 50%; margin-left: 25%; margin-top: 10%; text-align: center">
        <el-button type="primary" @click="successChangePpassword">修改</el-button>
        <el-button type="danger" @click="changePpassword">取消</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "Profile",
  props: ['pid'],
  mounted() {
    let that = this;
    http.get("/person/searchPersonBypid" , {pid: this.pid}).then(res => {
      that.pname = res.data.pname
      that.paccount = res.data.paccount
      that.ppassword = res.data.ppassword
      that.psex = res.data.psex
      that.page = res.data.page
    })

  },
  data() {
    return {
      pname: '',
      paccount: '',
      ppassword: '',
      psex: '',
      page : 0,
      ischangePpassword: false,
      ischangePname: false,
      newPname: '',
      oldPpassword: '',
      newPpassword: '',
    }
  },
  methods: {

    toLogin() {
      var token = sessionStorage.getItem("token")

      if(token != '' && token != null) {
        sessionStorage.removeItem("token")
        http.get("/person/logout" , {token: token}).then(res => {

        })
      }
      this.$router.push("/login")

    },
    changePname() {
      this.ischangePname = !this.ischangePname;
    },
    changePpassword() {
      this.ischangePpassword = !this.ischangePpassword;
      this.oldPpassword = '';
      this.newPpassword = '';
    },
    successChangePname() {
      if (this.newPname.length < 15)
        http.get("/person/changePname" , {pid: this.pid , pname: this.newPname}).then(res => {
          this.pname = this.newPname
          this.$message.success("修改成功")
          this.ischangePname = !this.ischangePname;
        })
      else {
        this.$message.info("用户名长度过长！")
      }
    },
    successChangePpassword() {
      if(this.oldPpassword == this.ppassword) {      //旧密码正确
        if(this.newPpassword.indexOf(" ")!=-1)                //密码含有空格
          this.$message.info("密码不能含有空格")
        else if(this.newPpassword.length > 15)           //密码长度过长
          this.$message.info("密码长度不宜过长")
        else if(this.newPpassword.length == 0)
          this.$message.info("请输入新密码")            //未输入新密码
        else
          http.get("/person/changePpassword" , {pid: this.pid , ppassword: this.newPpassword}).then(res => {
            this.ppassword = this.newPpassword
            this.ischangePpassword = !this.ischangePpassword
            this.$message.success("修改成功")
          })
      }
      else {                //旧密码不正确
        this.$message.info("旧密码不正确")
      }
    }

  }
}
</script>

<style scoped>

</style>