<template>
  <div style="width: 100%; height: 100%; ">
    <div style="display: flex; background-color: red; height: 5%; justify-content: space-between; font-size: 25px; font-weight: 700; text-align: center">
      <div style="flex: 1" >排名</div>
      <div style="flex: 1" >用户</div>
      <div style="flex: 1" >年龄</div>
      <div style="flex: 1" >性别</div>
      <div style="flex: 1;" >新闻发布量</div>
    </div>

    <div  v-show="(this.page-1)*16 <=index && index<(this.page)*16" v-for="(person , index) in persons" style="display: flex;  height: 5%; justify-content: space-between; font-size: 20px;  text-align: center; border-top: 1px solid black;" >
      <div style="flex: 1" :class="{'num1': index==0 , 'num2': index==1 , 'num3': index==2 , 'notThree': index >= 3 }">{{index+1}}</div>
      <div style="flex: 1" >{{person.pname}}</div>
      <div style="flex: 1" >{{person.page}}</div>
      <div style="flex: 1" >{{person.psex}}</div>
      <div style="flex: 1;" >{{person.newsNum}}</div>
    </div>

    <div  style="position: fixed; bottom: 20px; display: flex; justify-content: center; width: 85%; align-items: center" >
      <el-pagination
          background
          :hide-on-single-page="false"
          @current-change="handleCurrentChange"
          :page-size="16"
          layout="prev, pager, next"
          :total="persons.length">
      </el-pagination> 共{{this.persons.length}}个用户
    </div>

  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "seeUsers",

  mounted() {
    http.get("/admin/newsPrize" , {}).then(res => {
      this.persons = res.data
    })
  },

  data() {
    return {
      persons: [],
      page : 1,
    }
  },

  methods: {
    handleCurrentChange(page) {
      this.page = page;
    },
  },



}
</script>

<style scoped>


  .num1 {
    color: #fe2d46;
  }

  .num2 {
    color: #f60;
  }

  .num3 {
    color: #faa90e;
  }

  .notThree {
    color: #9195a3;
  }

</style>