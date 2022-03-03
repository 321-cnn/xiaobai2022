<template>
  <div style="line-height: 60px; border-bottom: 1px solid #42b983; display: flex">
    <div style="flex: 1; ">
      <span :class="collapseBtnClass" style="cursor: pointer ;font-size: 18px; margin-right: 10px" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="block">
      <el-image
          style="width: 40px; height: 40px; "
          :src="url"
      >
      </el-image>
    </div>
    <el-dropdown style="margin-left: 15px" >
              <span style="cursor: pointer;color: #409EFF;">
              王小虎<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean,
    url: String,
  }
}
</script>

<style scoped>
.block{
  border: 1px #78a6ea solid;
  width:43px;
  max-height: 43px;
  margin-top: 10px;
  box-shadow: 1px 1px 5px #409EFF inset;
}
</style>