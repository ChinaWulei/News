<template>
  <div class="myIssue" >

    <div v-if="typeof(this.pid) != 'undefined' && this.pid != null" style="height: 100%;">     <!--用户已登录-->

      <div v-if="this.allNews.length != 0" style=" height: 100%; border-top: 40px solid white;">
        <div>
          <el-select v-model="value" placeholder="请选择"   @change="changeValue" style="width: 10%; ">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

          <el-select v-model="value2" placeholder="请选择"   @change="changeValue2" style="margin-left: 20px; width: 10%; " >
            <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled">
            </el-option>
          </el-select>
        </div>

        <div v-if="this.value == '全部'" style="margin-top: 20px;">     <!-- 展示全部新闻 -->
          <div>
            <div style="display: flex; text-align: center; font-size: 20px; font-weight: 700;" >
              <div style="flex: 2;">标题</div>
              <div style="flex: 1">发布时间</div>
              <div style="flex: 1">审核状态</div>
            </div>
            <div v-for="(news , index) in allNews" v-show="(this.page-1)*16 <=index && index<this.page*16" style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; align-items: center;  text-align: center;" >
              <a href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid)" style="flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">{{news.ntitle}}</a>
              <div style="flex: 1">{{news.ntime}}</div>
              <div v-if="news.nstate == '未审核'" style=" display: flex; justify-content: center; flex: 1;"><el-tag type="danger" style="width: 20%; font-size: 20px;">{{news.nstate}}</el-tag></div>
              <div v-if="news.nstate == '已审核'" style=" display: flex; justify-content: center;  flex: 1;"><el-tag type="success" style="width: 20%; font-size: 20px;">{{news.nstate}}</el-tag></div>
            </div>
          </div>
        </div>
        <div v-else-if="this.value == '已审核'" style="margin-top: 20px;">     <!-- 展示已审核新闻 -->
          <div style="display: flex; text-align: center; font-size: 20px; font-weight: 700;" >
            <div style="flex: 2;">标题</div>
            <div style="flex: 1">发布时间</div>
            <div style="flex: 1">审核状态</div>
          </div>
          <div v-if="this.checked.length != 0">
            <div v-for="(news , index) in checked" v-show="(this.page-1)*9 <=index && index<this.page*9"  style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex;  align-items: center; text-align: center;" >
              <a href="#"  style="flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid)">{{news.ntitle}}</a>
              <div style="flex: 1">{{news.ntime}}</div>
              <div style="flex: 1; display: flex; justify-content: center;"><el-tag type="success" style="width: 20%; font-size: 20px;">{{news.nstate}}</el-tag></div>
            </div>
          </div>
          <div v-else style=" height: 100%">
            <el-empty :image-size="300" description="请前往发布再来吧！">
              <el-button type="primary" size="large" @click="toIssue">前往发布</el-button>
            </el-empty>
          </div>
        </div>
        <div v-else style="margin-top: 20px;">                           <!-- 展示未审核新闻 -->
          <div style="display: flex; text-align: center; font-size: 20px; font-weight: 700;" >
            <div style="flex: 2;">标题</div>
            <div style="flex: 1">发布时间</div>
            <div style="flex: 1">审核状态</div>
          </div>
          <div v-if="this.notCheck.length != 0">
            <div v-for="(news , index) in notCheck" v-show="(this.page-1)*9 <=index && index<(this.page)*9"  style="font-size: 20px; border-top-color: rgb(230, 230, 230); border-top-style: solid;  border-bottom-color: rgb(230, 230, 230); border-bottom-style: solid; display: flex; justify-content: space-between; align-items: center;text-align: center;" >
              <a  style="flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"  href="#"  class="news" @click="toDetail(news.ncontent ,  news.ntitle , news.ntime , news.nstate , news.nid)">{{news.ntitle}}</a>
              <div style="flex: 1">{{news.ntime}}</div>
              <div style="width: 40%; display: flex; justify-content: center; flex: 1;"><el-tag type="danger" style="width: 20%; font-size: 20px;">{{news.nstate}}</el-tag></div>
            </div>
          </div>
          <div v-else>
            <el-empty :image-size="300" description="请前往发布再来吧！">
              <el-button type="primary" size="large" @click="toIssue">前往发布</el-button>
            </el-empty>
          </div>
        </div>
      </div>
      <div v-else style="height:100%; display: flex ; align-items: center; justify-content: center; ">
        <el-empty :image-size="300" description="您还未发布一条新闻哦！请前往发布再来吧">
          <el-button type="primary" size="large" @click="toIssue">前往发布</el-button>
        </el-empty>
      </div>

    </div>

    <div v-else class="notLogin">                                         <!--用户未登录-->
      您还未登录！请前往 <a href="#" @click="toLogin" style="text-decoration: none;">登录</a>
    </div>

    <div  v-if="this.value == '全部' " style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="16"
          :current-page="page"
          layout="prev, pager, next"
          :total="allNews.length">
      </el-pagination> 共{{allNewsTimeNew.length}}条
    </div>
    <div  v-else-if="this.value == '未审核' " style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center " >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="9"
          :current-page="page"
          layout="prev, pager, next"
          :total="notCheck.length">
      </el-pagination> 共{{notCheck.length}}条
    </div>
    <div  v-else="this.value == '已审核' " style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :current-page="page"
          :page-size="9"
          layout="prev, pager, next"
          :total="checked.length">
      </el-pagination> 共{{checked.length}}条
    </div>
  </div>
</template>

<script>
import http from "../../../network/http";

export default {
  name: "MyIssue",
  props: ["pid"],
  mounted() {
    let  that = this
    http.get("/person/myRelease" , {pid: this.pid}).then(res => {
      that.allNewsTimeNew = res.data.news;
      this.allNewsTimeOld = this.allNewsTimeNew.slice();
      this.allNewsTimeOld.reverse();
      that.allNews = that.allNewsTimeNew;
      that.pname = res.data.pname;
      that.length = that.allNewsTimeNew.length
      for(let item of that.allNewsTimeNew) {
        if(item.nstate == '未审核')
          that.notCheck.push(item)
        else
          that.checked.push(item)
      }
      this.checkedTimeNew = this.checked
      this.checkedTimeOld = this.checked.slice().reverse()
      this.notCheckedTimeNew = this.notCheck
      this.notCheckedTimeOld = this.notCheck.slice().reverse()
    } , error => {
      this.$message.error('请前往登录');

    })

  },
  data() {
    return {
      notCheckLength: 0,    //未审核新闻列表长度
      checkedLength: 0,      //已审核新闻列表长度
      notCheck: [],        //未审核新闻列表
      notCheckedTimeNew: [],
      notCheckedTimeOld: [],
      notCheckedClickMany: [],
      notCheckedClickLow: [],
      checked: [],
      checkedTimeNew: [],
      checkedTimeOld: [],
      checkedClickMany: [],
      checkedClickLow: [],
      allNews: [],
      allNewsTimeNew: [],
      allNewsTimeOld: [],
      allNewsClickMany: [],
      allNewsClickLow: [],
      pageMyIssue: [],
      page: 1,
      pname: '',
      options: [{
        value: '全部',
        label: '全部'
      }, {
        value: '已审核',
        label: '已审核'
      }, {
        value: '未审核',
        label: '未审核'
      }],
      options2: [{
        value: '最新发布',
        label: '最新发布'
      }, {
        value: '最早发布',
        label: '最早发布'
      }],
      value: '全部',
      value2: '最新发布'
    }
  },
  methods: {

    toLogin() {
      this.$router.push("/login")
    },
    toIssue() {
      this.$emit("change" , 2)
    },
    toDetail(ncontent , ntitle , ntime , nstate , nid) {
      const href = this.$router.resolve({
        name: 'detail',
        query: {
          pid: this.pid,
          pname: this.pname,
          ntitle: ntitle,
          ntime: ntime,
          nstate: nstate,
          nid: nid,
        }
      });
      window.open(href.href , '_blank');
    },
    handleCurrentChange(page) {
      this.page = page;
    },
    changeValue(value) {
      this.page = 1
      this.value2 = '最新发布'
      if(value == '未审核') {
        this.options2[2].disabled = true
        this.options2[3].disabled = true
      }
      else {
        delete this.options2[2].disabled
        delete this.options2[3].disabled
      }

    },
    changeValue2(value2) {
      this.page = 1
      if(this.value == '全部') {
        if(value2 == '最新发布')
          this.allNews = this.allNewsTimeNew
        else if(value2 == '最早发布')
          this.allNews = this.allNewsTimeOld

      }
      else if(this.value == '已审核') {
        if(value2 == '最新发布')
          this.checked = this.checkedTimeNew
        else if(value2 == '最早发布')
          this.checked = this.checkedTimeOld
      }
      else {
        if(value2 == '最新发布')
          this.notCheck = this.notCheckedTimeNew
        else
          this.notCheck = this.notCheckedTimeOld
       /* else if(value2 == '点击量多')
          http.get("/person/myReleaseNotCheckedClickMany" , {"pid" : this.pid}).then(res => {
            this.notCheck = res.data.news;
          })
        else
          http.get("/person/myReleaseNotCheckedClickLow" , {"pid" : this.pid}).then(res => {
            this.notCheck = res.data.news;
          })*/
      }


    }
  },
}
</script>

<style scoped>

.myIssue {
  margin-left: 3%;
  height: 100%;

}


.notLogin {
  margin-top: 2%;
  font-size: 20px;

}

a {
  color: #333; text-decoration: none;
}

a:hover {
  color: #409EFF;
}


</style>