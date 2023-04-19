<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" status-icon size="small" label-position="top" class="ruleForm">
      <el-form-item label="用户名称" label-width="auto" prop="username" required>
        <el-input v-model="form.username" style="width: 40%" />
      </el-form-item>
      <el-form-item label="手机号码" label-width="auto" prop="phone" required>
        <el-input v-model="form.phone" style="width: 40%" />
      </el-form-item>
      <el-form-item label="电子邮箱" label-width="auto" prop="email" required>
        <el-input v-model="form.email" style="width: 40%" />
      </el-form-item>
      <el-form-item label="角色" required>
        <el-select v-model="adminUser.role" placeholder="请选择用户所属角色">
          <el-option v-for="role in form.roles" :label="role.roleDesc" :value="role.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="密码" prop="pass" required>
        <el-input v-model="form.pass" type="password" style="width: 40%" autocomplete="off" />
      </el-form-item>
      <el-form-item label="确认密码" style="width: 40%" prop="password" required>
        <el-input v-model="form.password" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item label="状态" required>
        <el-switch
          v-model="adminUser.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          width="50"
        /> {{ adminUser.status ? '启用' : '禁用' }}
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import { createAdminUser, queryAllRoles } from '@/api/conference_room'
import { success, error } from '@/utils/message'

export default {
  data() {
    const validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('用户名不能为空'))
      } else if (value.length < 1 || value.length > 10) {
        callback(new Error('名称长度在 1 到 10 个字符'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.form.password !== '') {
          this.$refs.form.validateField('password')
        }
        callback()
      }
    }
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    const validatePhone = (rule, value, callback) => {
      const reg = /^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/
      if (!value) {
        callback(new Error('手机号码不能为空'))
      } else if (value.length !== 11) {
        callback(new Error('请输入长度为11位的手机号码'))
      } else if (!reg.test(value)) {
        callback(new Error('手机号码格式不正确'))
      } else {
        callback()
      }
    }
    return {
      form: {
        username: '',
        pass: '',
        password: '',
        email: '',
        phone: '',
        role: '',
        roles: []
      },
      rules: {
        username: [
          { validator: validateName, trigger: 'blur' }
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],
        phone: [
          { validator: validatePhone, type: 'number', trigger: 'blur' }
        ]
      },
      adminUser: {
        id: '',
        username: '',
        password: '',
        role: '',
        phone: '',
        email: '',
        status: true,
        authorities: []
      }
    }
  },
  created() {
    this.init()
  },
  methods: {
    onSubmit() {
      this.adminUser.username = this.form.username
      this.adminUser.phone = this.form.phone
      this.adminUser.email = this.form.email
      this.adminUser.password = this.form.password
      this.adminUser.status = this.adminUser.status ? 1 : 0
      console.log('adminUser: ', this.adminUser)
      createAdminUser(this.adminUser).then(response => {
        // console.log(response)
        if (response.status === 200) {
          success('新增成功')
          this.resetForm('form')
        } else {
          error('新增失败')
        }
      })
    },
    init() {
      queryAllRoles().then(response => {
        console.log(response)
        this.form.roles = response.data.data
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.onSubmit()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="scss" scoped>

.ruleForm {
  margin: 100px 100px;
}
</style>
