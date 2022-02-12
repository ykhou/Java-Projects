<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="id" label="ID" width="60"></el-table-column>
      <el-table-column prop="name" label="name" width="150"></el-table-column>
      <el-table-column prop="author" label="author" width="150"></el-table-column>
      <el-table-column prop="price" label="price" width="60"></el-table-column>

      <el-table-column label="Operations">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)"
          >Edit</el-button>
          <el-button
              size="mini" type="danger" @click="handleDelete(scope.row)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :page-size= pageSize
        :total= total
        @current-change="page">

    </el-pagination>
  </div>

</template>

<script lang="ts">

export default {
  // 默认
  created() {
    const _this = this
    axios.get('http://localhost:8081/book/findAll/1/6').then(function (resp) {
      // console.log(resp)
      _this.tableData = resp.data.content
      _this.total = resp.data.totalElements
      _this.pageSize = resp.data.size
    })
  },
  data() {
    return {
      total: null,
      pageSize: null,
      tableData: null
    }
  },
  methods: {
    page(currentPage) {
      const _this = this
      axios.get('http://localhost:8081/book/findAll/'+currentPage+'/6').then(function (resp) {
        _this.tableData = resp.data.content
        _this.total = resp.data.totalElements
      })
    },
    handleEdit(row) {
      this.$router.push({
        path: '/Update',
        query: {
          id: row.id
        }
      })
    },
    handleDelete(row) {
      const _this = this
      axios.delete('http://localhost:8081/book/delete/'+row.id).then(function (resp) {
        if (resp.data = 'success') {
          _this.$alert('《'+row.name+'》删除成功','消息', {
            confirmButtonText: '确定',
            callback: action => {
              window.location.reload()
            }
          })
        }
      })
    },
  },
}
</script>