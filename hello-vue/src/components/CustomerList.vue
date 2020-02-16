<template>
  <div id="customerList">
    <el-form :inline="true" :model="search" class="demo-form-inline">
  <el-form-item label="用户名">
    <el-input  v-model='search.username' placeholder="请输入用户名"></el-input>
  </el-form-item>
  <el-form-item label="地址">
    <el-input  v-model='search.address' placeholder="请输入地址"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="searchAction">查询</el-button>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="add">
  <i class="el-icon-upload el-icon-plus"></i>
  新增
</el-button>
  </el-form-item>
    </el-form>

    <el-table :data="customers" border style="width: 100%;">
      <el-table-column prop="id" label="ID" width="150"></el-table-column>
      <el-table-column prop="username" label="姓名" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
      <el-table-column label="性别" width="120">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.sex == '0' ? '男' : '女' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会员类型" width="120">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.type == '0' ? '普通会员' : scope.row.type == '1' ? 'VIP' : '超级vip' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="地址" width="300"></el-table-column>
      <el-table-column label="头像">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" height="60px" />
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteClick(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
     <div class="block"> 
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="limit"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>

    <el-dialog title="编辑用户" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="customer" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="customer.username"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="customer.age"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="customer.sex">
            <el-radio label="0">男</el-radio>
            <el-radio label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="会员类型">
          <el-radio-group v-model="customer.type">
            <el-radio label="0">普通会员</el-radio>
            <el-radio label="1">vip</el-radio>
            <el-radio label="2">超级vip</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="customer.address"></el-input>
        </el-form-item>
        <el-form-item label="头像">
          <!-- action:上传路径 -->
          <el-upload
            class="avatar-uploader"
            action="/api/upload/avatar"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="customer.avatar" :src="customer.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "CustomerList",
  components: {},
  data() {
    return {
      total: 0, 
      limit: 5,
      currentPage: 1,
      search: {
        username: "",
        address: ""
      },
      customers: [], //客户列表数据
      dialogVisible: false, //客户编辑弹框的标识
      customer: {
        id: "",
        username: "",
        age: "",
        sex: "",
        address: "",
        avatar: ""
      } //当前用户数据
    };
  },
  methods: {
    deleteClick(row){
      this.$confirm('是否确认删除, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        .then(() => {
          this.$axios
          .get(`/customer/delete/${row.id}`)
          .then(res => {
            this.fetchData();
            this.$message({
            type: "success",
            message: "删除成功"
          });
          })
          .catch(error => {
            this.$message({
            type: "success",
            message: "删除失败，请稍后重试"
          });
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    handleSizeChange(val) {
      this.limit=val;
        // console.log(`${val} items per page`);
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.currentPage=val;
        // console.log(`current page: ${val}`);
         this.fetchData();
      },
    searchAction() {
      this.fetchData();  
    },
    fetchData() {
      //向后台发起请求，获取数据
      //http://localhost:8081/api/customer/list
      this.$axios
        .get(`/customer/list?username=${this.search.username}&address=${this.search.address}&page=${this.currentPage}&limit=${this.limit}`,)
        .then(res => {
          //console.log('success...'+JSON.stringify(res));
          let responseData = res.data;
          this.customers = responseData.data.customers;
          this.total= responseData.data.total;
        })
        .catch(error => {
          console.log("error..." + JSON.stringify(error));
        });
    },
    handleClick(row) {
      this.dialogVisible = true;
      //console.log(row);
      this.customer = {
        id: row.id,
        username: row.username,
        age: row.age,
        sex: row.sex,
        type: row.type,
        address: row.address,
        avatar: row.avatar
      }
    },
    onSubmit() {
      //this.customer
      if (this.customer.id == "") {
        //新增
        this.$axios
          .post("/customer/add", this.customer)
          .then(res => {
            //刷新页面
            console.log("add..res..." + JSON.stringify(res));
            if (res.data.status == 1) {
              //关闭弹出层
              this.dialogVisible = false;
              //刷新
              this.fetchData();
            }
          })
          .catch(error => {});
      }else{
        this.$axios
          .post("/customer/update", this.customer)
          .then(res => {
            //刷新页面
            console.log("add..res..." + JSON.stringify(res));
            if (res.data.status == 1) {
              //关闭弹出层
              this.dialogVisible = false;
              //刷新
              this.fetchData();
            }
          })
          .catch(error => {});
      }
    },
    add() {
      this.customer = {
        id: "",
        username: "",
        age: "",
        sex: "",
        address: "",
        avatar: ""
      };
      this.dialogVisible = true;
    },
    //上传成功回调
    handleAvatarSuccess(res, file) {
      //this.imageUrl = URL.createObjectURL(file.raw);
      console.log("res==>" + JSON.stringify(res));
      //console.log('file==>'+JSON.stringify(file));
      this.customer.avatar = res.data;
      console.log("---->" + JSON.stringify(this.customer));
    },
    //文件上传之前触发
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    }
  },
  created() {
    this.fetchData();
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
