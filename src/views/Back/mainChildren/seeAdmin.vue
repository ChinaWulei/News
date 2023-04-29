<template>
  <div style="height: 100%; width: 100%; display: flex; font-weight: 700; font-size: 20px;">
    <div style="width: 40%; height: 70%; margin-left: 30%; margin-top: 5%; display: flex; flex-direction: column; justify-content: space-between">
      <div style="flex: 1; display: flex;">
        <div style="flex: 1; align-items: center; display: flex">姓名:</div>
        <div style="flex: 1; align-items: center; display: flex">林茂彬</div>
      </div>
      <div style="flex: 1; display: flex;">
        <div style="flex: 1; align-items: center; display: flex; ">账号:</div>
        <div style="flex: 1; align-items: center; display: flex">13724200556</div>
      </div>
      <div style="flex: 1; display: flex;">
        <div style="flex: 1; align-items: center; display: flex">性别:</div>
        <div style="flex: 1; align-items: center; display: flex">男</div>
      </div>
      <div style="flex: 1; display: flex;">
        <div style="flex: 1; align-items: center; display: flex">待审核新闻量:</div>
        <div style="flex: 1; align-items: center; display: flex">13</div>
      </div>
      <div style="flex: 1; display: flex;">
        <div style="flex: 1; align-items: center; display: flex">已审核新闻量:</div>
        <div style="flex: 1; align-items: center; display: flex">254</div>
      </div>
      <div style="display: flex; flex: 1; align-items: center; justify-content: end; width: 50%;"><el-button @click="toLogin" type="primary" size="large" style="width: 50%;">退出</el-button></div>
    </div>



  </div>
</template>

<script>
export default {
  name: "seeAdmin",
  methods: {
    toLogin() {
      this.$router.push("/AdminLogin")
    }
  }
}
</script>

<style scoped>

</style>