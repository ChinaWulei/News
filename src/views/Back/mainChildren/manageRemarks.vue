<template>
  <div class="managneNews" style="height: 100%;">
    <div class="select">       <!--选择器-->
      <el-select v-model="value" placeholder="请选择"   @change="changeValue" style="font-size: 25px; width: 160px;" size="mini" >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
    </div>
    <div class="nav" style="display: flex; justify-content: space-between; font-size: 25px; font-weight: 700;  ">
      <div style="flex: 2">评论</div>
      <div style="flex: 1">用户</div>
      <div style="flex: 1">操作</div>
    </div>

    <div style="font-size: 20px; font-weight: 700; " v-if="this.value == '未审核'">            <!-- 查看未审核新闻列表 -->
      <div v-if="this.notChecked.length !=0" style="border-top: 2px solid black; width: 100%; display: flex; justify-content: space-between" v-for="(remark , index) in notChecked" v-show="(this.page-1)*18 <=index && index<(this.page)*18">
        <div class="titleFather" style=" text-align: center;flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
          <a href="#" style="text-decoration: none;" @click="toDetail(remark.news.nid , remark.rcontent , remark.person.pname , remark.rtime )">{{remark.rcontent}}</a>
        </div>
        <div style="text-align: center; flex: 1">{{remark.person.pname}}</div>
        <div style="text-align: center; flex: 1" @click="Manage('审核通过' , index , remark.rid)"><el-button>审核通过</el-button></div>
      </div>

      <div v-else style="border-top: 2px solid black; width: 100%; display: flex; justify-content: center; ">
        <el-empty :image-size="300" description="一分耕耘，一分收获。暂无未审核的新闻">
        </el-empty>
      </div>
    </div>

    <div style="font-size: 20px; font-weight: 700; " v-else>             <!-- 查看已审核评论列表 -->
      <div v-if="this.checked.length != 0" style="border-top: 2px solid black; width: 100%; display: flex; justify-content: space-between" v-for="(remark , index) in checked" v-show="(this.page-1)*18 <=index && index<(this.page)*18">
        <div class="titleFather" style="text-align: center;flex: 2; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
          <a href="#" style="text-decoration: none;" @click="toDetail(remark.news.nid , remark.rcontent , remark.person.pname , remark.rtime )">{{remark.rcontent}}</a>
        </div>
        <div style="text-align: center; flex: 1">{{remark.person.pname}}</div>
        <div style="text-align: center; flex: 1"  @click="Manage('撤销审核' , index , remark.rid)"><el-button>撤销审核</el-button></div>
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
          layout="prev, pager, next"
          :total="checked.length">
      </el-pagination> 共{{checked.length}}条
    </div>


  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "manageRemarks",
  mounted() {


    http.get("admin/allRemarks" , {}).then(res => {
      this.allRemarks = res.data;
      for(let item of this.allRemarks){
        if(item.rstate == '未审核')
          this.notChecked.push(item)
        else
          this.checked.push(item)
      }

    })

  },

  data() {
    return {
      allRemarks: [],
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
      value: '未审核'
    }
  },

  methods: {

    toDetail(nid , rcontent , pname , rtime ) {    //点击评论进入新闻详情页

      const href = this.$router.resolve({
        name: 'detail',
        query: {
          admin: 'admin',
          nstate: '已审核',
          pid: 6,
          nid: nid,
          rtime: rtime,
          rcontent: rcontent,
          pname: pname,

        }
      });
      window.open(href.href , '_blank');
    },
    Manage(value , index , rid) {
      if(value == '审核通过') {
        var obj = this.notChecked.splice(index, 1);
        this.checked.push(obj)
        http.get("/admin/remarkChecked" , {rid: rid}).then(res => {
          this.$message.success("通过审核成功！")
        })
      }
      else {
        var obj = this.checked.splice(index, 1);
        this.notChecked.push(obj)
        alert(this.checked.length)
        http.get("/admin/remarkNotChecked" , {rid: rid}).then(res => {
          this.$message.success("撤销审核成功！")
        })
      }

    },
    handleCurrentChange(page) {
      this.page = page;
    },
    changeValue(value) {
      this.page = 1
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