<template>
  <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
    <el-form :model="form">
      <el-form-item label="活动名称" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="活动区域" :label-width="formLabelWidth">
        <el-select v-model="form.region" placeholder="请选择活动区域">
          <el-option label="区域一" value="shanghai"></el-option>
          <el-option label="区域二" value="beijing"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

export default {
  name: 'Login',
  inject: ['reload'],
  components: {
  },
  data() {
    return {
      form: this.$form.createForm(this),
      model: {},
      labelCol: {
        xs: { span: 24 },
        sm: { span: 4 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 }
      },
      title: '登录',
      width: 600,
      visible: false,
      disableSubmit: false,
      loading: false
    }
  },
  computed: {},
  methods: {
    show() {
      this.visible = true
    },
    handleCancel() {
      this.visible = false
    },
    handleOk() {
      this.visible = false
    },
    handleLogin() {
      // 触发表单验证
      this.form.validateFields((err, values) => {
        if (!err) {
          const formData = Object.assign(this.model, values)
          // console.log(formData)
          this.loading = true
          this.$store.dispatch('user/login', formData)
            .then(() => {
              this.visible = false
              // this.$router.push({ path: '/index' })
              this.reload()
              this.loading = false
            })
            .catch(() => {
              this.loading = false
            })
        } else {
          return false
        }
      })
    },
    register() {
      this.visible = false
      this.$refs.articleRegisterModal.register()
    }
  }
}
</script>
