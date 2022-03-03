<template>
    <el-container style="height: 100vh;">
      <el-aside :width=" widthSize+'px'" style="background-color: rgb(238, 241, 246); ">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>
      <el-container height="100%">
        <el-header>
          <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse" :url="url"/>
        </el-header>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manage',
  components: {Header, Aside},
  data(){
    return {
      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      widthSize:'200',
      logoTextShow: true,
      url: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
    }
  },
  methods: {
    collapse() { //点击收缩按钮触发
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {//缩
        this.widthSize = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.logoTextShow = false;
      } else {//收
        this.widthSize = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
        this.logoTextShow = true;
      }
    },
  }
}
</script>

<style>

</style>