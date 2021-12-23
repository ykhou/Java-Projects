<template>
  <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="120px"
      class="demo-ruleForm"
      style="width: 60%">
    <el-form-item label="book id">
      <el-input v-model="ruleForm.id" readonly></el-input>
    </el-form-item>
    <el-form-item label="book name" prop="name">
      <el-input v-model="ruleForm.name"></el-input>
    </el-form-item>
    <el-form-item label="author" prop="author">
      <el-input v-model="ruleForm.author"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">
        Create</el-button>
      <el-button @click="resetForm('ruleForm')">
        Reset</el-button>

    </el-form-item>
  </el-form>
</template>

<script lang="ts">

export default {
  data() {
    return {
      ruleForm: {
        id: '',
        name: '',
        author: '',
      },
      rules: {
        name: [
          {
            required: true,
            message: 'Please input book name',
            trigger: 'blur', // 失去焦点时触发
          }
        ],
        author: [
          {
            required: true,
            message: 'Please input author',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate(valid => {
        if (valid) {
          axios.post('http://localhost:8181/book/save', this.ruleForm).then(function (resp) {
            if (resp.data = 'success') {
              _this.$alert('《'+_this.ruleForm.name+'》修改成功','消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/BookManage')
                }
              })
            }
          })
        } else {
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
  },
  created() {
    const _this = this
    axios.get('http://localhost:8181/book/findById/'+this.$route.query.id).then(function (resp) {
      _this.ruleForm = resp.data
    })
  }
}
</script>
