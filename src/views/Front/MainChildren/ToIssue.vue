<template>
  <div v-if="typeof(this.pid) != 'undefined' && this.pid != null" class="toIssue">   <!-- 用户已登录 -->
    <div style=" height: 100%; width: 20% ">
      <div style="display: flex ;height: 20%; font-size: 20px; align-items: center; justify-content: right; color: red;  ">*标题</div>
      <div style="display: flex ;height: 40%; font-size: 20px; align-items: center; justify-content: right; color: red;">*正文</div>
    </div>
    <div style=" width: 75%; ">
      <div style="height: 20%; ">
        <el-input
          style="width: 80%; height: 100%; display: flex; align-items: center; font-size: 20px; font-weight: bolder;"
          type="textarea"
          :rows="1"
          placeholder="请输入标题（不超过40个字符 ）"
          v-model="title"
          maxlength="40"
          show-word-limit>
        </el-input>
      </div>
      <div style="display: flex ;height: 40%; font-size: 20px; align-items: center;">
        <el-input
            style="width: 80%; height: 100%; display: flex; align-items: center; font-size: 16px;"
            type="textarea"
            :rows="10"
            placeholder="请输入正文（不超过4000个字符 ）"
            v-model="content"
            maxlength="4000"
            show-word-limit>
        </el-input>
      </div>
      <div style="height: 20%">
        <el-button type="primary" round style="height: 60%; width: 40%; font-size: 30px;" @click="toRelease">发布</el-button>
      </div>
    </div>
  </div>
  <div v-else class="notLogin" >
    您还未登录！请前往 <a href="#" @click="toLogin" style="text-decoration: none;">登录</a>
  </div>
</template>

<script>
import http from "../../../network/http";
export default {
  name: "ToIssue",
  data() {
    return {
      title: '',
      content: '',
    }
  },
  mounted() {
    http.post("person/release" , {}).then(res => {
    } , error => {
    })

  },
  methods: {
    toRelease() {
      if(this.title == '') {
        this.$message.error('标题不能为空');
      }
      else if(this.content == '') {
        this.$message.error('正文不能为空');
      }
      else if(this.title.trim().length == 0 || this.content.trim().length == 0) {
        this.$message.error('标题或正文格式不正确');
      }
      else {
        let that = this
        http.post("/person/release" , {pid: this.pid , ncontent: this.content , ntitle: this.title}).then(res => {
          that.$message.success('发布成功');
          that.content = '';
          that.title = '';
        })

      }
    },

    toLogin() {
      this.$router.push("/login")
    }

  },
  props: ['pid']
}
</script>

<style scoped>

  .toIssue , .notLogin{
    margin-left: 3%;
    height: 100%;
    display: flex;
  }

  .toIssue {
    justify-content: space-between;
  }

  .notLogin {
    margin-top: 2%;
    font-size: 20px;
  }

</style>