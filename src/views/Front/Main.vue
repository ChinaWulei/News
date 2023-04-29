<template>
  <div class="main">
    <div class="leftNav">
      <a href="#" :class="{isActive: this.isActive == 1}" @click="clickMe(1)">全部新闻</a>
      <a href="#" :class="{isActive: this.isActive == 2}" @click="clickMe(2)">我要发布</a>
      <a href="#" :class="{isActive: this.isActive == 3}" @click="clickMe(3)">我的发布</a>
      <a href="#" :class="{isActive: this.isActive == 4}" @click="clickMe(4)">我的评论</a>
      <a href="#" :class="{isActive: this.isActive == 5}" @click="clickMe(5)">个人资料</a>
    </div>

    <div class="centent">
      <div style="height: 7%;background-color: rgb(72 145 126 / 68%); display: flex; align-items: center; justify-content: right">
       <div  v-if="typeof(this.pid) != 'undefined' && this.pid != null" >    <!-- 已登录 -->
         <el-tooltip class="item" effect="dark" content="查看个人资料" placement="bottom">
           <a href="#" @click="clickPhoto" ><el-avatar :size="50" :src="circleUrl"></el-avatar></a>
         </el-tooltip>
       </div>
       <div v-else>                                                       <!-- 未登录 -->
         <el-tooltip class="item" effect="dark" content="点击登录" placement="bottom">
           <a href="#" @click="toLogin"><el-avatar :size="50" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar></a>
         </el-tooltip>
       </div>
      </div>
      <div style="margin-top: 1%; margin-left: 2%; font-size: 20px; height: 3%;">
        >&nbsp;{{this.url}}
      </div>

      <div class="mainContent">
        <div class="zhuti" v-if="this.isActive == 1">
          <AllNews  :pid="this.pid"></AllNews>
        </div>

        <div class="zhuti" v-else-if="this.isActive == 2">
          <ToIssue :pid="this.pid"></ToIssue>
        </div>

        <div class="zhuti" v-else-if="this.isActive == 3">
          <MyIssue :pid="this.pid" @change="change"></MyIssue>
        </div>

        <div class="zhuti" v-else-if="this.isActive == 4">
          <MyRemarks :pid="this.pid"></MyRemarks>
        </div>

        <div class="zhuti" v-else>
          <Profile :pid="this.pid"></Profile>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
import AllNews from "./MainChildren/AllNews.vue";
import ToIssue from "./MainChildren/ToIssue.vue";
import MyIssue from "./MainChildren/MyIssue.vue";
import MyRemarks from "./MainChildren/MyRemarks.vue";
import Profile from "./MainChildren/Profile.vue";


export default {
  name: "main",
  components: {MyRemarks, Profile, MyIssue, ToIssue, AllNews },
  data() {
    return {
      pid: 0,
      isActive: 1,
      allNews: [],
      circleUrl: 'src/views/photos/img_2.png',
      url: '全部新闻'
    }
  },
  mounted() {
    this.pid = this.$route.params.pid
    alert(this.pid)
  },
  methods: {
    clickMe(num) {

      this.isActive = num;
      if(num == 1)
        this.url = '全部新闻'
      else if(num == 2)
        this.url = '我要发布'
      else if(num == 3)
        this.url = '我的发布'
      else if(num == 4)
        this.url = '我的评论'
      else
        this.url = '个人资料'
    },
    clickPhoto() {
      this.isActive = 5;
      this.url = '个人资料'
    },
    toLogin() {
      this.$router.push("/login")
    },
    change(val) {
      this.isActive = val;
    },
  }
}
</script>

<style scoped>

  .main {
    display: flex;
    height: 100%;

  }

  .leftNav{
    flex: 1;
    display: flex;
    flex-direction: column;
    background-color: rgba(53,55,68,1);
    width: 40%;
  }

  .leftNav a {
    text-decoration: none;
    flex: 1;
    color: #ffffff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 30px;

  }

  .leftNav a:hover {
    color: yellow;
    background-color: #409eff;
  }


  .nav div {
    flex: 1;
  }

  .centent {
    flex: 10;
    background-color: white;
  }

  .isActive {
    background-color: #409eff;
  }
  .mainContent {
    height: 84%;
  }

  .zhuti {
    height: 100%;
  }


</style>