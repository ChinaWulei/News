<template>
  <div style=" width: 94%; height: 100%; margin-left: 4%; margin-top: 20px;">
    <div v-if="typeof(this.pid) != 'undefined' && this.pid != null" style="height: 100%;">     <!--用户已登录-->

      <div v-if="this.myRemarks.length != 0" style=" height: 100%; border-top: 40px solid white;">     <!-- 用户发表过评论 -->
        <div>
          <el-select v-model="value" placeholder="请选择"   @change="changeValue">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div v-if="this.value == '已审核'" style="margin-top: 20px;">     <!-- 展示已审核评论 -->
          <div v-if="this.checked.length != 0">
            <div v-for="(remark , index) in checked" v-show="(this.page-1)*9 <=index && index<this.page*9" style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; justify-content: space-between; align-items: center; margin-top: 2%;" >
              <a href="#" @click="toDetail(remark.person.pname ,remark.news.nid , remark.news.ntitle , remark.news.ntime)"  class="news" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 40%; height: 30px; ">{{remark.rcontent}}</a>
              <div  style="width: 40%; display: flex; justify-content: end;"><el-tag type="success" style="width: 20%; font-size: 20px;">{{remark.rstate}}</el-tag></div>
            </div>
          </div>
          <div v-else style=" height: 100%">
            <el-empty :image-size="300" description="您的评论都还未审核哦！">
              <el-button type="primary" size="large" >前往发布</el-button>
            </el-empty>
          </div>
        </div>
        <div v-else style="margin-top: 20px;">     <!-- 展示未审核评论 -->
          <div v-if="this.notChecked.length != 0">
            <div v-for="(remark , index) in notChecked" v-show="(this.page-1)*9 <=index && index<this.page*9" style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; justify-content: space-between; align-items: center; margin-top: 2%;" >
              <a href="#"  @click="toDetail(remark.person.pname ,remark.news.nid , remark.news.ntitle , remark.news.ntime)" class="news" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 40%; height: 30px;">{{remark.rcontent}}</a>
              <div  style="width: 40%; display: flex; justify-content: end;"><el-tag type="danger" style="width: 20%; font-size: 20px;">{{remark.rstate}}</el-tag></div>
            </div>
          </div>
          <div v-else style=" height: 100%">
            <el-empty :image-size="300" description="您的评论都已经审核过了哦！">
              <el-button type="primary" size="large">前往发布</el-button>
            </el-empty>
          </div>
        </div>

      </div>
      <div v-else style="height:100%; display: flex ; align-items: center; justify-content: center; ">   <!-- 该用户未发表评论 -->
        <el-empty :image-size="300" description="您还未发表过一条评论哦！请前往发表再来吧">
          <el-button type="primary" size="large" >前往发布</el-button>
        </el-empty>
      </div>

    </div>
    <div v-else class="notLogin" style="font-size: 20px;">                                         <!--用户未登录-->
      您还未登录！请前往 <a href="#" @click="toLogin" style="text-decoration: none;">登录</a>
    </div>

    <div  v-if="this.value == '已审核' " style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="9"
          layout="prev, pager, next"
          :total="checked.length">
      </el-pagination> 共{{checked.length}}条
    </div>
    <div  v-else  style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="9"
          layout="prev, pager, next"
          :total="notChecked.length">
      </el-pagination> 共{{notChecked.length}}条
    </div>

  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "MyRemarks",
  props: ['pid'],
  mounted() {
    let that =this
    http.get("/person/searchRemarksBypid" , {pid: this.pid}).then(res => {

      that.myRemarks = res.data;

      for(let remark of that.myRemarks) {
        if(remark.rstate == "已审核") {
          that.checked.push(remark)

        }
        else {
          that.notChecked.push(remark)
        }
      }



    })

  },
  data() {
    return {
      options: [{
        value: '已审核',
        label: '已审核'
      }, {
        value: '未审核',
        label: '未审核'
      }],
      value: '已审核',
      page: 1,
      myRemarks: [],
      checked: [],
      notChecked: []
    }
  },
  methods: {

    toLogin() {
      this.$router.push("/login")
    },
    handleCurrentChange(page) {
      this.page = page;
    },
    changeValue(value) {
      this.page = 1
    },
    toDetail(pname , nid , ntitle , ntime ) {    //点击评论进入新闻详情页

      const href = this.$router.resolve({
        name: 'detail',
        query: {
          pid: this.pid,
          pname: pname,
          ntitle: ntitle,
          ntime: ntime,
          nstate: '已审核',
          nid: nid,
        }
      });
      window.open(href.href , '_blank');
    },

  }

}
</script>

<style scoped>

  a {
    text-decoration: none;
  }

</style>