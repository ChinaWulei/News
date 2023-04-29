<template>
  <div class="allNews">
    <div  style="height: 100%;">

      <div v-if="this.allNews.length != 0" style=" height: 100%; border-top: 40px solid white;">
        <div style="font-size: 20px;">         <!--筛选器-->
          <el-select v-model="value" placeholder="请选择"   @change="changeValue" style="font-size: 25px; width: 160px;" size="mini" >
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div v-if="this.value == '最新发布'" style="margin-top: 20px;">     <!-- 展示全部新闻  最新发布 -->
          <div>
            <div v-for="(news , index) in allNews" v-show="(this.page-1)*7 <=index && index<this.page*7"  style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex;  align-items: center; justify-content: space-between ; margin-top: 2%;" >
              <a href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid )">{{news.ntitle}}</a>
              <div style="margin-left: 200px; ">
                <el-badge :value="news.remarkNum" class="item">
                  <el-button size="big"  style="height: 50px; font-size: 25px;">评论</el-button>
                </el-badge>
              </div>
              <div style=" height: 100%;  display: flex; align-items: center;">{{modifyTime(news.ntime)}}</div>
            </div>
          </div>
        </div>
        <div v-else-if="this.value == '最早发布'" style="margin-top: 20px;">     <!-- 展示全部新闻  最早发布 -->
          <div>
            <div v-for="(news , index) in dateNews" v-show="(this.page-1)*7 <=index && index<this.page*7"  style="font-size: 20px; justify-content: space-between ; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; align-items: center; margin-top: 2%;" >
              <a href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid )">{{news.ntitle}}</a>
              <div style="margin-left: 200px; ">
                <el-badge :value="news.remarkNum" class="item">
                  <el-button size="big"  style="height: 50px; font-size: 25px;">评论</el-button>
                </el-badge>
              </div>
              <div style=" height: 100%;  display: flex; align-items: center;">{{modifyTime(news.ntime)}}</div>
            </div>
          </div>
        </div>
        <div v-else-if="this.value == '评论量多'" style="margin-top: 20px;">                          <!-- 展示全部新闻  评论量多 -->
          <div v-for="(news , index) in remarkManyNews" v-show="(this.page-1)*7 <=index && index<(this.page)*7"  style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; justify-content: space-between; align-items: center; margin-top: 2%;" >
            <a href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid )">{{news.ntitle}}</a>
            <div style="margin-left: 200px; ">
              <el-badge :value="news.remarkNum" class="item">
                <el-button size="big"  style="height: 50px; font-size: 25px;">评论</el-button>
              </el-badge>
            </div>
            <div style=" height: 100%;  display: flex; align-items: center;">{{modifyTime(news.ntime)}}</div>
          </div>
        </div>
        <div v-else style="margin-top: 20px;">                          <!-- 展示全部新闻  评论量少 -->
          <div v-for="(news , index) in remarkLowNews" v-show="(this.page-1)*7 <=index && index<(this.page)*7"  style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; justify-content: space-between; align-items: center; margin-top: 2%;" >
            <a href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid )">{{news.ntitle}}</a>
            <div style="margin-left: 200px; ">
              <el-badge :value="news.remarkNum" class="item">
                <el-button size="big"  style="height: 50px; font-size: 25px;">评论</el-button>
              </el-badge>
            </div>
            <div style=" height: 100%;  display: flex; align-items: center;">{{modifyTime(news.ntime)}}</div>
          </div>
        </div>
      </div>
      <div v-else style="height:100%; display: flex ; align-items: center; justify-content: center; ">
        <el-empty :image-size="300" description="当前没有任何新闻哦！稍后再来吧！">
        </el-empty>
      </div>

    </div>


    <div style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          :current-page="page"
          @current-change="handleCurrentChange"
          :page-size="7"
          layout="prev, pager, next"
          :total="this.allNews.length">
      </el-pagination> 共{{this.allNews.length}}条
    </div>


  </div>

</template>

<script>
import http from "../../../network/http";
import {popoverProps} from "element-plus";


export default {
  name: "AllNews",
  props: ['pid'],
  mounted() {
    let  that = this
    http.get("/person/allNews" , ).then(res => {
      that.allNews = res.data;
      console.log(that.allNews)
      that.dateNews =  that.allNews.slice()
      that.dateNews.reverse();

    })

  },
  data() {
    return {
      allNews: [],
      dateNews: [],
      remarkManyNews: [],
      remarkLowNews: [],
      year: '',
      month: '',
      day: '',
      clock: '',
      options: [{
       value: '最新发布',
       label: '最新发布'
        }, {
       value: '最早发布',
       label: '最早发布'
     }, {
       value: '评论量多',
       label: '评论量多'
     }, {
        value: '评论量少',
        label: '评论量少'
      }],
      value: '最新发布',
      page: 1,
      pname: ''
    }

  },
  methods: {
    changeValue(value) {          //筛选器变了
      this.page = 1;
      if(value == '评论量多')
        http.get("/person/getAllNewsByRemarkManyOrder" , ).then(res => {
          this.remarkManyNews = res.data
        })
      if(value == '评论量少')
        http.get("/person/getAllNewsByRemarkLowOrder" , ).then(res => {
          this.remarkLowNews = res.data
        })

    },
    modifyTime(ntime) {
      this.year = ntime.substr(0 , 4);
      this.month = ntime.substr(5 , 2);
      this.day = ntime.substr(8 , 2);
      this.clock = ntime.substr(11 , 5);
      return this.year + "年" + this.month + "月" + this.day + "日" + " " + this.clock
    },
    handleCurrentChange(page) {     //页数变了
      this.page = page
    },
    toDetail(ncontent , ntitle , ntime , nstate , nid ) {
      let that = this
      http.get("/person/searchpnameBynid" , {nid: nid}).then(res => {
        that.pname = res.data;
        const href = that.$router.resolve({
          name: 'detail',
          query: {
            pid: that.pid,
            pname: that.pname,
            ntitle: ntitle,
            ntime: ntime,
            nstate: '已审核',
            nid: nid,
          }
        });
        window.open(href.href , '_blank');
      })


    },
  },



}
</script>

<style scoped>

.allNews {
  margin-left: 3%;
  height: 100%;
}

a {
  color: #333; text-decoration: none;
}

a:hover {
  color: #409EFF;
}

</style>