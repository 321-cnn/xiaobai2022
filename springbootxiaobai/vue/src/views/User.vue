<template>
  <div>

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
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Vue",
  data() {
    return{
      tableData: [],
      total: 0,
      pageNum:1,
      pageSize:10,
      username:"",
      email:"",
      address:"",
      value: false,
      dialogFormVisible: false,
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
    };
  },

  created() {
    this.load();
  },

  methods: {
    tableRowClassName({row, rowIndex}){//调节表格颜色
      if (rowIndex === 1 || rowIndex === 5 || rowIndex === 9) {
        return 'warning-row';
      } else if (rowIndex === 3 || rowIndex === 7) {
        return 'success-row';
      }
      return '';
    },
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

<style scoped>
.el-table .warning-row {
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}
.el-table .table-header{
  background: #e1dede !important;
  font-size: 16px;
  color: red;
}
</style>