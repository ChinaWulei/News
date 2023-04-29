<template>
  <div class="detail">
    <el-backtop :right="100" :bottom="100" />
    <div style="height: auto; width: 50%; margin-left: 25%; background-color: white;">
      <h1 style="width: 100%;  word-wrap:break-word; word-break:break-all; color: #333; font-weight: 700; font-size: 39px;">{{ntitle}}</h1>
      <div style="padding: 5px; margin-top: 15px;border-bottom: 1px solid #f2f2f2; color: #b7b7b7; font-size: 14px; margin-top: 25px; display: flex; justify-content: space-between;">
        <div>{{year}}年{{month}}月{{day}}日&nbsp;&nbsp;{{clock}}&nbsp;&nbsp;&nbsp;&nbsp;用户: {{pname}}</div>
        <div>点击量： {{nclick}}</div>
      </div>
      <div v-html="ncontent" class="content"   style=" color: #333;  font-size: 20px;"></div>
      <div style="margin-top: 20px; display: flex; justify-content: center;"><img src="/src/views/photos/img_16.png" alt=""></div>
    </div>

    <div v-if="this.nstate == '已审核'">
      <div class="remark" style=" height: auto; width: 50%; margin-left: 25%; margin-top: 50px; display: flex; justify-content: space-between; align-items: center;">
        <div style="font-size: 26px; color: #2a2a2a;">网友评论 <span style="    color: #ccc;font-size: 14px;">文明上网理性发言，请遵守新闻评论服务协议</span></div>
        <div style="font-size: 14px; color: #379be9;">{{remarks.length}}条评论</div>
      </div>
      <div style=" height: 10%; width: 50%; margin-left: 25%; margin-top: 10px; display: flex;">
        <div  v-if="typeof(this.pid) == 'undefined' || this.pid == null" style=" width: 13%; display: flex; height: 100%;">     <!-- 用户未登录 发表评论 -->
          <el-avatar :size="100" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar>
        </div>
        <div v-else style=" width: 13%; display: flex; height: 100%;" >                                                     <!-- 用户已登录 发表评论 -->
          <el-avatar :size="100" src="src/views/photos/img_2.png"></el-avatar>
        </div>
        <div v-show="typeof(this.pid) == 'undefined' || this.pid == null" style=" width: 60%; display: flex;  height: 100%; margin-left: 20px; ">          <!-- 用户未登录 -->
          <el-input
              type="textarea"
              :rows="3"
              placeholder="说点什么吧..."
              v-model="remark"
              style="font-size: 18px;">
          </el-input>
          <el-button type="primary" style="height: 95px; width: 20%; font-size: 30px;" @click="toLogin">登录</el-button>
        </div>
        <div v-show="typeof(this.pid) != 'undefined' && this.pid != null" style=" width: 85%; display: flex;  height: 100%; margin-left: 20px; ">                            <!-- 用户已登录 -->
          <el-input
              type="textarea"
              :rows="3"
              placeholder="说点什么吧...(评论需经过管理员审核后,才会给其他人看到)"
              v-model="remark"
              style="font-size: 18px;">
          </el-input>
          <el-button type="primary" style="height: 95px; width: 20%; font-size: 30px;" @click="toRemark">评论</el-button>
        </div>

      </div>


      <div style=" height: 5%; width: 50%; margin-left: 25%; margin-top: 50px; display: flex; justify-content: space-between;  border-bottom: 1px solid #f0f0f0;">

        <div style="font-size: 20px; color: #2a2a2a;">全部评论</div>
        <div style=" width: 20%; font-size: 14px; color: #999;">
          <span  style="text-decoration: none; " @click="changeMoren" :class="{'active' : this.remarkNewOrHot == '默认' , 'notActive': this.remarkNewOrHot != '默认'}">默认</span>&nbsp;&nbsp;/&nbsp;&nbsp;
          <span  style="text-decoration: none; " @click="changeNew" :class="{'active' : this.remarkNewOrHot == '最新' , 'notActive': this.remarkNewOrHot != '最新'}">最新</span>
        </div>
      </div>

      <!-- 具体评论列表   -->
      <div style=" margin-top: 30px; width: 50%; margin-left: 25%; ">
        <div v-if="this.remarkNewOrHot == '默认'" style=" display: flex;padding: 0 0  30px; border-bottom: 1px solid #f0f0f0; margin-top: 30px;"  v-for="remark in remarks">
          <div class="img" v-if="parseInt(9*Math.random())==1" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_5.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==2" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_6.png"></el-avatar></div>
          <div class="img" v-else-if=" parseInt(9*Math.random())==3" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_7.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==4" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_8.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==5" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_9.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==6" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_10.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==7" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_11.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==8" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_12.png"></el-avatar></div>
          <div class="img" v-else style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_13.png"></el-avatar></div>

          <div style="display: flex; justify-content: end; height: auto; width: 92%; flex-direction: column; " >
            <div class="pname" style="font-size: 12px ;font-weight: 600; color: #379be9;">{{remark.person.pname}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #999; font-size: 12px;">{{remark.rtime}}</span></div>
            <div class="remark" style="font-size: 14px">{{remark.rcontent}}</div>
          </div>
        </div>
        <div v-else style=" display: flex;padding: 0 0  30px; border-bottom: 1px solid #f0f0f0; margin-top: 30px;"  v-for="remark in newRemarks">
          <div class="img" v-if="parseInt(9*Math.random())==1" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_5.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==2" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_6.png"></el-avatar></div>
          <div class="img" v-else-if=" parseInt(9*Math.random())==3" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_7.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==4" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_8.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==5" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_9.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==6" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_10.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==7" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_11.png"></el-avatar></div>
          <div class="img" v-else-if="parseInt(9*Math.random())==8" style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_12.png"></el-avatar></div>
          <div class="img" v-else style="width: 9%;display: flex; align-items: flex-start"><el-avatar :size="70" src="src/views/photos/img_13.png"></el-avatar></div>

          <div style="display: flex; justify-content: end; height: auto; width: 92%; flex-direction: column; " >
            <div class="pname" style="font-size: 12px ;font-weight: 600; color: #379be9;">{{remark.person.pname}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #999; font-size: 12px;">{{remark.rtime}}</span></div>
            <div class="remark" style="font-size: 14px">{{remark.rcontent}}</div>
          </div>
        </div>
      </div>

      <div style=" margin-top: 30px; width: 50%; margin-left: 25%;  ,font-size: 13px;  color: #9499A0; text-align: center;">
        没有更多评论
      </div>
    </div>

  </div>
</template>

<script>
import http from "../../network/http";

export default {
  name: "Detail",

  mounted() {
    this.nid = this.$route.query.nid
    this.nstate = this.$route.query.nstate;
    let admin = this.$route.query.admin
    if(admin == 'admin') {                //管理员在审核评论时点进来该新闻
      http.get("/admin/searchNewByNid" , {nid: this.nid}).then(res => {
        let name = this.$route.query.pname
        let time = this.$route.query.rtime
        let content = this.$route.query.rcontent
        let obj = {rcontent: content, person: {pname: name} , rtime: time}
        this.remarks.push(obj)
        this.ntitle = res.data.ntitle
        this.ncontent = res.data.ncontent
        this.ntime = res.data.ntime
        this.pname = res.data.person.pname
        this.year = this.ntime.substr(0 , 4);
        this.month = this.ntime.substr(5 , 2);
        this.day = this.ntime.substr(8 , 2);
        this.clock = this.ntime.substr(11 , 5);
        let test = this.ncontent.replace(/\n|\r\n/g, '<br>');
        let result = test.replace(/ /g, ' &nbsp')
        this.ncontent = result
      })
    }
    else {
      this.pid = this.$route.query.pid
      this.ntitle = this.$route.query.ntitle
      this.pname = this.$route.query.pname
      this.ntime = this.$route.query.ntime;
      this.year = this.ntime.substr(0 , 4);
      this.month = this.ntime.substr(5 , 2);
      this.day = this.ntime.substr(8 , 2);
      this.clock = this.ntime.substr(11 , 5);
      http.get("/person/searchNewsBynid" , {nid: this.nid}).then(res => {
        this.ncontent = res.data.ncontent;
        this.remarks = res.data.remark;
        this.nlike = res.data.nlike;
        this.nclick = res.data.nclick;
        let test = this.ncontent.replace(/\n|\r\n/g, '<br>');
        let result = test.replace(/ /g, ' &nbsp')
        this.ncontent = result
      })
    }


  },
  data() {
    return {
      pid: 0,
      nid: 0,
      ntitle: '',
      nlike: 0,
      nclick: 0,
      ncontent: '',
      ntime: '',
      pname: '',
      year: '',
      month: '',
      day: '',
      clock: '',
      nstate: '',
      remark: '',
      remarks: [],
      newRemarks: [],          //按评论时间排序的数组
      remarkNewOrHot: '默认'
    }
  },
  methods: {
    toLogin() {
      this.$router.push({
        name: 'login',
        query: {
          pname: this.pname,
          ncontent: this.ncontent,
          ntitle: this.ntitle,
          ntime: this.ntime,
          nstate: '已审核',
          nid: this.nid,
        }
      })
    },
    toRemark() {               /*发表评论*/
      let that = this
      if(this.remark == '' || this.remark.trim().length == 0)
        this.$message.error("你还没有评论！")
      else if(this.remark.length > 83)
        this.$message.error("评论字数过长！")
      else {
        http.post("/person/toRemark" , {rcontent: this.remark , rpid: this.pid , rnid: this.nid}).then(res =>{
          that.$message.success("发送成功")
          that.remark = ''
        })
      }
    },
    changeMoren() {
      if(this.remarkNewOrHot == '默认')
        return ;
      this.remarkNewOrHot = '默认'
    },
    changeNew() {
      if(this.remarkNewOrHot == '最新')
        return;
      this.newRemarks = this.remarks.slice()
      this.newRemarks.sort(function(a, b) {
        return b.rtime < a.rtime? -1 : 1
      })
      this.remarkNewOrHot = '最新'
      console.log(this.newRemarks)
      console.log(this.remarks)
    }

  }
}
</script>

<style scoped>
  .detail {
    height: 100%;
    width: 100%;
  }

  .active {
    color: #2a2a2a; cursor: auto;
  }

  .notActive {
    color: #999;
    cursor: pointer;
  }
</style>