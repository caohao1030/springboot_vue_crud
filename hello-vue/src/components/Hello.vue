<template>
  <div id="hello">
     <el-table
      :data="users"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="学号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>


export default {
  name: 'Hello',
  components: {
    
  },
  data() {
      return {
          users: [
              {id: 1, name: '张三', age: 23, address: '江苏无锡'},
              {id: 2, name: '张三', age: 23, address: '江苏无锡'},
              {id: 3, name: '张三', age: 23, address: '江苏无锡'},
              {id: 4, name: '张三', age: 23, address: '江苏无锡'},
          ]
      }
  }
}
</script>

<style>

</style>
