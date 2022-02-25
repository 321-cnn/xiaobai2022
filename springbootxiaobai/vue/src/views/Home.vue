<template>
    <el-container style="height: 100vh;">
      <el-aside :width=" widthSize+'px'" style="background-color: rgb(238, 241, 246); ">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
                 background-color="rgb(48,65,86)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition="false"
                 :collapse="isCollapse"
        >
          <div style="height: 60px; line-height: 60px;text-align: center">
            <img src="../assets/logo.png" style="width: 20px;position: relative;top: 5px;margin-right: 5px;" alt="网不行啊！！">
            <b style="color: white" v-show="logoTextShow">后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container height="100%">
        <el-header style="line-height: 60px; border-bottom: 1px solid #42b983; display: flex">
          <div style="flex: 1; font-size: 22px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>
          <div class="block" width="42px" height="42px" style="border: 1px darkslategrey solid;">
            <el-image
                style="width: 40px; height: 40px; position: center"
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
        </el-header>

        <el-main>
          <div class="pb-10">
            <el-breadcrumb separator-class="el-icon-arrow-right">
              <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
              <el-breadcrumb-item>用户管理</el-breadcrumb-item>
              <el-breadcrumb-item>用户列表</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="pb-10">
            <el-input style="width: 200px;" aria-placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width: 200px;" aria-placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5" v-model="email"></el-input>
            <el-input style="width: 200px;" aria-placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </div>
          <div class="pb-10">
            <el-button type="primary" @click="handleAdd" class="mr-5">新增 <i class="el-icon-circle-plus-outline"></i> </el-button>
            <el-popconfirm
                title="确定删除这批数据吗？"
                icon="el-icon-info"
                icon-color="red"
                @confirm="handleDelBatch"
            >
              <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i> </el-button>
            </el-popconfirm>
            <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom-left"></i> </el-button>
            <el-button type="primary" class="ml-5">导出 <i class="el-icon-top-right"></i> </el-button>
          </div>

          <el-table :data="tableData" border :row-class-name="tableRowClassName" :header-cell-class-name="tableHeaderClassName" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="ID" width="100px"></el-table-column>
            <el-table-column prop="username" label="姓名" width="120px"></el-table-column>
            <el-table-column prop="password" label="密码" width="120px"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120px"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="150px"></el-table-column>
            <el-table-column prop="phone" label="电话" width="150px"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    title="这条信息确定删除吗？"
                    @confirm="handleDelete(scope.row.id)"
                >
                  <el-button type="danger" slot="reference" class="ml-5">删除 <i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>

          <div class="block pt-10" style="height: 60px">
            <!--size-change 处理页码大小; current-change 当前页变动时候触发的事件;  -->
            <!--page-sizes接受一个整型数组，数组元素为展示的选择每页显示个数的选项   -->
            <el-pagination  style="height:60px; line-height: 60px; font-size: 16px"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page= "pageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size=pageSize
                :hide-on-single-page="value"
                layout="total, sizes, prev, pager, next, jumper"
                :total=total>
            </el-pagination>
          </div>

          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form size="small" :model="formRules" :rules="rules">
              <el-form-item label="姓名" label-width="15%" prop="username">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="密码" label-width="15%">
                <el-input v-model="form.password" autocomplete="off" show-password></el-input>
              </el-form-item>
              <el-form-item label="昵称" label-width="15%">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" label-width="15%">
                <el-input v-model="form.email" autocomplete="off" validate-event></el-input>
              </el-form-item>
              <el-form-item label="电话" label-width="15%">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址" label-width="15%">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
            </div>
          </el-dialog>

        </el-main>
      </el-container>
    </el-container>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'Home',
  data(){
    return {
      tableData: [],
      total: 0,
      pageNum:1,
      pageSize:10,
      username:"",
      email:"",
      address:"",
      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      widthSize:'200',
      logoTextShow: true,
      value: false,
      dialogFormVisible: false,
      url: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      multipleSelection: [],
      form: {},
      formRules: {
        username: '',
      },
      rules: {
        username: [
          { required: true, message: '姓名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },

  methods: {
    collapse(){ //点击收缩按钮触发
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse){//缩
        this.widthSize = 64;
        this.collapseBtnClass = 'el-icon-s-unfold';
        this.logoTextShow = false;
      }else {//收
        this.widthSize = 200;
        this.collapseBtnClass = 'el-icon-s-fold';
        this.logoTextShow = true;
      }
    },
    tableRowClassName({row, rowIndex}){//调节表格颜色
      if (rowIndex === 1 || rowIndex === 5 || rowIndex === 9) {
        return 'warning-row';
      } else if (rowIndex === 3 || rowIndex === 7) {
        return 'success-row';
      }
      return '';
    },
    /*validateField(){
      inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
          inputErrorMessage: '邮箱格式不正确'
    },*/
    load() {  //分页加载
      request.get("/user/page" ,{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },
    saveOrUpdate(){
      request.post("/user/saveOrUpdate" ,this.form).then(res =>{
        if (res){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$message.error("保存失败")
          this.dialogFormVisible = false
          this.load()
        }
      })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleDelete(index) {
      console.log(index);
      request.delete("/user/remove/"+index).then(res =>{
        if (res){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
          this.load()
        }
      })
    },
    handleSelectionChange(val){
      this.multipleSelection = val;
    },
    handleDelBatch(){
      let ids = this.multipleSelection.map(v => v.id)  //将一个含对象的数组 [{} {} {}] 转为 整数数组 [ 1, 2, 3 ]
      request.post("/user/remove/batch" ,ids).then(res => {
        if (res){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
          this.load()
        }
      })
    },
    reset(){
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form = {}
    },
    handleSizeChange(pageSize) {//分页
      console.log(pageSize);
      this.pageSize = pageSize;
      this.load()
    },
    handleCurrentChange(pageNum){//分页
      console.log(pageNum);
      this.pageNum = pageNum;
      this.load()
    },
    tableHeaderClassName(row, column, rowIndex, columnIndex){
      console.log(row, column, rowIndex, columnIndex);
      return 'table-header'
    },
  },
}
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}
.el-table .table-header{
  background: #e1dede !important;
  font-size: 16px;
  color: #090808;
}
</style>