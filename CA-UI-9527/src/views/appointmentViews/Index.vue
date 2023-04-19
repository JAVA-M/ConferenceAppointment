<template>
  <div id="Joe" class="june_main">
    <WebHeader @toLogin="toLogin"/>
    <WebContainer />
<!--    <router-view />-->
    <div class="joe_footer">
      <div class="joe_container">
        <div class="item">
          <strong class="joe_run__day">Conference-Appointment</strong> ©2022 Created by <strong class="joe_run__day">ManXingFu</strong>
          UED
        </div>
        <!--        <div class="item run">-->
        <!--          <span>已运行{{ runTime }} <strong class="joe_run__day">985</strong> 天 <strong class="joe_run__hour">17</strong> 时 <strong-->
        <!--            class="joe_run__minute">23</strong> 分 <strong class="joe_run__second">17</strong> 秒</span>-->
        <!--        </div>-->
        <div class="item">
          <a
             href="https://beian.miit.gov.cn/#/Integrated/index"
             style="cursor:pointer"
             target="_blank">粵ICP备2022081501号</a>
        </div>
      </div>
    </div>
    <div class="joe_action">
      <a-back-top>
        <div class="joe_action_item scroll active">
          <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="25" height="25">
            <path
              d="M725.902 498.916c18.205-251.45-93.298-410.738-205.369-475.592l-6.257-3.982-6.258 3.414c-111.502 64.853-224.711 224.142-204.8 475.59-55.751 53.476-80.214 116.623-80.214 204.8v15.36l179.2-35.27c11.378 40.39 58.596 69.973 113.21 69.973 54.613 0 101.262-29.582 112.64-68.836l180.337 36.41v-15.36c-.569-89.885-25.031-153.6-82.489-206.507zM571.733 392.533c-33.564 31.29-87.04 28.445-118.329-5.12s-28.444-87.04 5.12-117.76c33.565-31.289 87.04-28.444 118.33 5.12s28.444 86.471-5.12 117.76zm-56.32 368.64c-35.84 0-64.284 29.014-64.284 64.285 0 35.84 54.044 182.613 64.284 182.613s64.285-146.773 64.285-182.613c0-35.271-29.014-64.285-64.285-64.285z"></path>
          </svg>
        </div>
      </a-back-top>
    </div>
    <el-dialog title="登录" width="40%" :center="true" :visible.sync="loginFormVisible">
      <hr size="1">
      <el-form ref="form" label-position="top" :rules="loginRules" :model="form">
        <el-form-item label="用户名" prop="username" require>
          <el-input v-model="form.username" style="width: 80%;border-radius: 5px;"/>
        </el-form-item>
        <el-form-item label="密码" prop="password" required>
          <el-input v-model="form.password" placeholder="请输入密码" style="width: 80%;border-radius: 5px;" type="password" show-password />
        </el-form-item>
        <el-form-item label="验证码" prop="captcha" required>
          <el-input v-model="form.captcha" placeholder="请输入验证码" style="width: 80%;border-radius: 5px;" />
          <img :src="imageSrc" style="width: 20%;cursor: pointer" @click="refreshCode" title="点击刷新验证码">
        </el-form-item>
      </el-form>
      <a-button type="primary" ghost style="width: 30%;margin-left: 20%" @click="handleLogin" @keydown.enter="handleLogin" :loading="loading">
        登录
      </a-button>
      <a-button type="primary" ghost style="width: 30%;margin-left: 10px" @click="toRegister" :loading="loading">
        注册
      </a-button>
    </el-dialog>

    <el-dialog title="注册" width="40%" :center="true" :visible.sync="registerFormVisible">
      <hr size="1">
      <el-form ref="registerForm" :model="registerForm" :rules="rules" status-icon size="small" label-position="top" class="ruleForm">
        <el-form-item label="用户名称" label-width="auto" prop="username" required>
          <el-input v-model="registerForm.username" style="width: 70%" />
        </el-form-item>
        <el-form-item label="手机号码" label-width="auto" prop="phone" required>
          <el-input v-model="registerForm.phone" style="width: 70%" />
        </el-form-item>
        <el-form-item label="电子邮箱" label-width="auto" prop="email" required>
          <el-input v-model="registerForm.email" style="width: 70%" />
          <el-button @click="getVerification" type="primary" size="mini" style="margin-left: 20px" round>获取验证码</el-button>
        </el-form-item>
        <el-form-item label="验证码" prop="code" required>
          <el-input v-model="registerForm.code" style="width: 70%" />
        </el-form-item>
        <el-form-item label="密码" prop="pass" required>
          <el-input v-model="registerForm.pass" type="password" style="width: 70%" autocomplete="off" show-password />
        </el-form-item>
        <el-form-item label="确认密码" prop="password" required>
          <el-input v-model="registerForm.password" style="width: 70%" type="password" autocomplete="off" show-password />
        </el-form-item>
<!--        <el-form-item label="状态" required>
          <el-switch
            v-model="adminUser.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            width="50"
            disabled
          /> {{ adminUser.status ? '启用' : '禁用' }}
        </el-form-item>-->
      </el-form>
      <a-button type="primary" ghost style="width: 30%;margin-left: 20%" @click="resetForm('registerForm')" :loading="loading">
        重置
      </a-button>
      <a-button type="primary" ghost style="width: 30%;margin-left: 10px" @click="register('registerForm')" :loading="loading">
        注册
      </a-button>
    </el-dialog>
  </div>
</template>

<script>
import WebHeader from '@/views/appointmentViews/header/WebHeader'
import WebContainer from '@/views/appointmentViews/container/WebContainer'
import { warning, success, error } from '@/utils/message'
import WebLogin from '@/views/appointmentViews/details/WebLogin'
import { createUser, login, queryNameIsRepeat, refreshVerifyCode } from '@/api/user'
import { setToken } from '@/utils/auth'
export default {
  name: 'Index',
  inject: ['reload'],
  components: {
    WebHeader,
    WebContainer,
    WebLogin
  },
  data() {
    const validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('用户名不能为空'))
      } else if (value.length < 2 || value.length > 15) {
        callback(new Error('名称长度在 2 到 15 个字符'))
      } else if (!this.reg.test(value)) {
        callback(new Error('用户名只能与数字、字母、下划线组成'))
      } else {
        queryNameIsRepeat(value).then(response => {
          // console.log(response)
          if (response.data.data) { // 返回true 代表该用户名已存在
            callback(new Error('用户名已存在'))
          } else {
            callback()
          }
        })
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if (value.length < 3 || value.length > 20){
        callback(new Error('密码长度介于3~20'))
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
      } else if (value !== this.registerForm.pass) {
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
    // 登录验证
    const validateLoginName = (rule, value, callback) => {
      console.log(value)
      if (!value) {
        callback(new Error('用户名不能为空'))
      } else if (value.length < 2 || value.length > 15) {
        callback(new Error('名称长度在 2 到 15 个字符'))
      } else if (!this.reg.test(value)) {
        callback(new Error('用户名只能与数字、字母、下划线组成'))
      } else {
        callback()
      }
    }
    return {
      reg: /^[a-zA-Z0-9_]+$/,
      runTime: '',
      loginFormVisible: false,
      registerFormVisible: false,
      formLabelWidth: 600,
      loading: false,
      form: {
        username: '',
        password: '',
        captcha: '',
        webId: ''
      },
      registerForm: {
        username: '',
        pass: '',
        password: '',
        email: '',
        phone: '',
        code: undefined,
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
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
        ]
      },
      loginRules: {
        username: [
          { validator: validateLoginName, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        captcha: [
          { required: true, message: '请输入验证码', trigger: 'blur' }
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
      },
      verifyCode: 'http://localhost:10010/api/refresh/verifyCode',
      imageSrc: ''
    }
  },
  created() {
  },

  methods: {
    toLogin() {
      this.loginFormVisible = true
      this.refreshCode()
    },
    toRegister() {
      this.loading = true
      this.registerFormVisible = true
      this.loginFormVisible = false
      this.loading = false
    },
    handleLogin() {
      this.form.webId = window.localStorage.getItem('webId')
      login(this.form).then(response => {
        // console.log(response)
        const { data, msg } = response.data
        if (!data) {
          error(msg)
        } else {
          this.reload()
          success(msg)
          setToken(data)
          this.loginFormVisible = false
        }
      }).catch(e => {
        console.log('登录异常: ' + e)
        error('用户名或密码错误，请重新输入!')
      })
    },
    register(formName) {
      // 向后端注册
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.adminUser.username = this.registerForm.username
          this.adminUser.phone = this.registerForm.phone
          this.adminUser.email = this.registerForm.email
          this.adminUser.password = this.registerForm.password
          this.adminUser.status = 1
          this.adminUser.role = 3
          // console.log('adminUser: ', this.adminUser)
          createUser(this.adminUser).then(response => {
            if (response.status === 200) {
              success('新增成功')
              this.registerFormVisible = false
              this.loginFormVisible = true
              this.resetForm('registerForm')
            } else {
              error('新增失败')
            }
          })
        } else {
          warning('填写的注册信息不符合规范，请检查修改')
          // console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    getVerification() {
      success('获取验证码')
    },
    refreshCode() {
      refreshVerifyCode(window.localStorage.getItem('webId')).then(response => {
        console.log('图片编码', response.data)
        this.imageSrc = response.data.data.captcha
        window.localStorage.setItem('webId', response.data.data.webId)
      })
    }
  }

}
</script>

<style>
.ant-back-top {
  right: 30px !important;
  bottom: 100px !important;
}
/*.item:hover {
  -webkit-transform: translateY(-2px);
  transform: translateY(-2px);
  box-shadow: 0 5px 5px rgb(0 0 0 / 10%);
}*/
/* 背景网格 */
.june_main {
  font-family: ChenYu Font;
  background: #f5f5f5;
  background-image: linear-gradient(90deg,rgba(60,10,30,.04) 5%,transparent 0),linear-gradient(1turn,rgba(60,10,30,.04) 5%,transparent 0);
  background-size: 35px 35px;
  background-position: 50%;
  background-repeat: repeat;
}
</style>

