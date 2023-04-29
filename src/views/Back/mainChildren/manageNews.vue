<template>
  <div class="managneNews" style="height: 100%; ">
    <div class="select">       <!--选择器-->
      <el-select v-model="value" placeholder="请选择"   @change="changeValue" style="font-size: 25px; width: 160px;" size="mini" >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <el-select v-model="value2" placeholder="请选择"   @change="changeValue2" style="font-size: 25px; width: 160px;" size="mini" >
        <el-option
            v-for="item in options2"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div class="nav" style="display: flex; justify-content: space-between; font-size: 25px; font-weight: 700;  ">
      <div style="flex: 2">标题</div>
      <div style="flex: 1">用户</div>
      <div style="flex: 1">时间</div>
      <div style="flex: 1">操作</div>
    </div>

    <div style="font-size: 20px; font-weight: 700; border-bottom: 1px solid black; border-left: 1px solid black; border-right: 1px solid black; " v-if="this.value == '未审核'" >            <!-- 查看未审核新闻列表 -->
      <div v-if="this.notChecked.length !=0" style="border-top: 2px solid black; width: 100%; display: flex; justify-content: space-between" v-for="(news , index) in notChecked" v-show="(this.page-1)*18 <=index && index<(this.page)*18">
        <div class="titleFather" style=" text-align: center;flex: 2; border-right: 1px solid black ; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
          <a href="#" style="text-decoration: none;" @click="toDetail(news.person.pname , news.nid , news.ntitle , news.ncontent , news.ntime)">{{news.ntitle}}</a>
        </div>
        <div style="text-align: center; flex: 1; border-right: 1px solid black ;">{{news.person.pname}}</div>
        <div style="text-align: center; flex: 1; border-right: 1px solid black ;">{{news.ntime}}</div>
        <div style=" text-align: center; flex: 1; border-right: 1px solid black ;" ><el-button @click="Manage('审核通过' , index , news.nid)">审核通过</el-button></div>
      </div>

      <div v-else style="border-top: 2px solid black; width: 100%; display: flex; justify-content: center; ">
        <el-empty :image-size="300" description="一分耕耘，一分收获。暂无未审核的新闻">
        </el-empty>
      </div>
    </div>

    <div style="font-size: 20px; font-weight: 700; border-bottom: 1px solid black; border-left: 1px solid black; border-right: 1px solid black;" v-else>             <!-- 查看已审核新闻列表 -->
      <div v-if="this.checked.length != 0" style="border-top: 2px solid black; width: 100%; display: flex; justify-content: space-between" v-for="(news , index) in checked" v-show="(this.page-1)*18 <=index && index<(this.page)*18">
        <div class="titleFather" style="border-right: 1px solid black ; text-align: center;flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
          <a href="#" style="text-decoration: none;" @click="toDetail(news.person.pname , news.nid , news.ntitle , news.ncontent , news.ntime)">{{news.ntitle}}</a>
        </div>
        <div style="border-right: 1px solid black ;  text-align: center; flex: 1">{{news.person.pname}}</div>
        <div style="text-align: center; flex: 1; border-right: 1px solid black ;">{{news.ntime}}</div>
        <div style="border-right: 1px solid black ;  text-align: center; flex: 1" ><el-button @click="Manage('撤销审核' , index , news.nid)">撤销审核</el-button></div>
      </div>

      <div v-else style="border-top: 2px solid black; width: 100%; display: flex; justify-content: center" >
        <el-empty :image-size="300" description="您暂时还没有审核过的新闻哦！快前去审核新闻吧！">

        </el-empty>
      </div>
    </div>

    <div  v-if="this.value == '未审核' " style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="18"
          :current-page="page"
          layout="prev, pager, next"
          :total="notChecked.length">
      </el-pagination> 共{{notChecked.length}}条
    </div>
    <div  v-else style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="18"
          :current-page="page"
          layout="prev, pager, next"
          :total="checked.length">
      </el-pagination> 共{{checked.length}}条
    </div>


  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "manageNews",
  mounted() {

    http.get("admin/allNews" , {}).then(res => {
      this.allNews = res.data;
      for(let item of this.allNews){
        if(item.nstate == '未审核')
          this.notChecked.push(item)
        else
          this.checked.push(item)
      }
      this.notChecked.sort(function(a, b) {
        return b.ntime < a.ntime? -1 : 1
      })

    })

  },

  data() {
    return {
      allNews: [],
      notChecked: [],
      checked: [],
      page: 1,
      options: [{
        value: '未审核',
        label: '未审核'
        }, {
        value: '已审核',
        label: '已审核'
        }],
      options2: [{
        value: '最新发布',
        label: '最新发布'
      }, {
        value: '最早发布',
        label: '最早发布'
      }],
      value: '未审核',
      value2: '最新发布',
    }
  },

  methods: {

    toDetail(pname , nid , ntitle , ncontent , ntime) {    //点击新闻标题进入新闻详情页

      const href = this.$router.resolve({
        name: 'detail',
        query: {
          pname: pname,
          ncontent: ncontent,
          ntitle: ntitle,
          ntime: ntime,
          nstate: '未审核',
          nid: nid,
        }
      });
      window.open(href.href , '_blank');
    },
    Manage(value , index , nid) {
      if(value == '审核通过') {
        let obj = this.notChecked.splice(index, 1);
        this.checked.push(obj)
        http.get("/admin/newsChecked" , {nid: nid}).then(res => {
          this.$message.success("通过审核成功！")
        })
      }
      else {
        let obj = this.checked.splice(index, 1);
        this.notChecked.push(obj)
        http.get("/admin/newsNotChecked" , {nid: nid}).then(res => {
          this.$message.success("撤销审核成功！")
        })
      }

    },
    handleCurrentChange(page) {
      this.page = page;
    },
    changeValue(value) {
      this.page = 1
      if(value == '未审核') {

        if(this.value2 == '最新发布')
          this.notChecked.sort(function(a, b) {
            return b.ntime < a.ntime? -1 : 1
          })
        else
          this.notChecked.sort(function(a, b) {
            return b.ntime > a.ntime? -1 : 1
          })

      }
      else {

        if(this.value2 == '最新发布')
          this.checked.sort(function(a, b) {
            return b.ntime < a.ntime? -1 : 1
          })
        else
          this.checked.sort(function(a, b) {
            return b.ntime > a.ntime? -1 : 1
          })

      }
    },
    changeValue2(value2) {
      this.page = 1
      if(value2 == '最新发布') {
        if(this.value == '已审核')
          this.checked.sort(function(a, b) {
            return b.ntime < a.ntime? -1 : 1
          })
        else
          this.notChecked.sort(function(a, b) {
            return b.ntime < a.ntime? -1 : 1
          })
      }
      else {
        if(this.value == '已审核')
          this.checked.sort(function(a, b) {
            return b.ntime > a.ntime? -1 : 1
          })
        else
          this.notChecked.sort(function(a, b) {
            return b.ntime > a.ntime? -1 : 1
          })
      }


    }

  }

}
</script>

<style scoped>

  .nav div {
    text-align: center;
  }

  .titleFather a{
    color: black;
  }

  a:hover {
    color: blue;
  }

</style>