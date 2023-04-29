<template>
  <div style="height: 100%; width: 100%;">
    <div style="height: 10%; background-color: #545C64; border-bottom: 1px solid white; display: flex; justify-content: space-between;">
      <div class="logo" style="height: 100%; width: 7%; font-size: 80px; display: flex; align-items: center;">
        <el-icon><Grid /></el-icon>
      </div>
      <div class="photo" style="display:flex; align-items: center;">
        <el-tooltip class="item" effect="dark" content="查看个人资料" placement="bottom-end">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" size="large" style="cursor: pointer;"/>
        </el-tooltip>
      </div>
    </div>
    <div style="display:flex; height: 90%; justify-content: space-between;">

      <div style="width: 10%; background-color: #545c64; height: 100%;">
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="1-1"
            default-openeds="[1 ,2]"
            text-color="#fff"
            @open="handleOpen"
        >
          <el-sub-menu index="1">
            <template #title>
              <span><el-icon><User /></el-icon>用户管理</span>
            </template>
            <el-menu-item-group title="发布管理">
              <el-menu-item index="1-1" @click="manageNews">新闻管理</el-menu-item>
              <el-menu-item index="1-2" @click="manageRemarks">评论管理</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="用户排行榜">
              <el-menu-item index="1-3" @click="newsPrize">新闻发布量</el-menu-item>
              <el-menu-item index="1-4" @click="remarkPrize">评论发布量</el-menu-item>
            </el-menu-item-group>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <span><el-icon><Avatar /></el-icon>管理员信息</span>
            </template>
            <el-menu-item-group title="资料查看">
              <el-menu-item index="2-1" @click="seeAdmin">个人信息</el-menu-item>
            </el-menu-item-group>

          </el-sub-menu>
        </el-menu>


      </div>

      <div style="width: 88%; ">
        <div style=" height: 5%; display: flex; align-items: center; ">      <!-- 面包屑 -->
          {{path}}
        </div>

        <div v-if="this.path=='用户管理 / 发布管理 / 新闻管理'" style=" height: 95%;">
          <ManageNews></ManageNews>
        </div>
        <div v-else-if="this.path=='用户管理 / 发布管理 / 评论管理'" style=" height: 95%; background-color: pink;">
          <ManageRemarks></ManageRemarks>
        </div>
        <div v-else-if="this.path=='用户管理 / 用户资料 / 查看用户信息'" style="background-color: blue; height: 95%;">
          <SeeUsers></SeeUsers>
        </div>
        <div v-else-if="this.path=='用户管理 / 用户排行榜 / 新闻发布量'" style=" height: 95%;">
          <newsPrize></newsPrize>
        </div>
        <div v-else-if="this.path=='用户管理 / 用户排行榜 / 评论发布量'" style=" height: 95%;">
          <remarksPrize></remarksPrize>
        </div>
        <div v-else style=" height: 95%;">
          <see-admin></see-admin>
        </div>


      </div>

    </div>
  </div>
</template>

<script>

import ManageNews from "./mainChildren/manageNews.vue";
import ManageRemarks from "./mainChildren/manageRemarks.vue";
import SeeUsers from "./mainChildren/newsPrize.vue";
import SeeAdmin from "./mainChildren/seeAdmin.vue";
import newsPrize from "./mainChildren/newsPrize.vue";
import remarksPrize from "./mainChildren/remarksPrize.vue";

export default {
  name: "AdminMain",
  components: {SeeAdmin, SeeUsers, ManageRemarks, ManageNews , newsPrize , remarksPrize},

  mounted() {
    this.aid = this.$route.query.aid
    this.aname = this.$route.query.aname
    this.aaccount = this.$route.query.aaccount
    this.asex = this.$route.query.asex
  },

  data() {
    return {
      aid: 0,
      aname: '',
      aaccount: '',
      asex: '',
      path: '用户管理 / 发布管理 / 新闻管理'
    }

  },

  methods: {
    manageNews() {
      this.path = '用户管理 / 发布管理 / 新闻管理'
    },
    manageRemarks() {
      this.path = '用户管理 / 发布管理 / 评论管理'
    },
    newsPrize() {
      this.path = '用户管理 / 用户排行榜 / 新闻发布量'
    },
    remarkPrize() {
      this.path = '用户管理 / 用户排行榜 / 评论发布量'
    },
    seeAdmin() {
      this.path = '管理员信息 / 资料查看 / 个人信息'
    },
    handleOpen() {

    }
  },

}
</script>

<style scoped>

</style>