<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" status-icon size="small" label-position="top" class="ruleForm">
      <el-form-item label="会议主题" label-width="auto" prop="conferenceSubject" required>
        <el-input v-model="form.conferenceSubject" style="width: 40%" />
      </el-form-item>
      <el-form-item label="参会人数" label-width="auto" prop="participationNums" required>
        <!--        <el-input v-model="form.participationNums" style="width: 40%" />-->
        <el-input-number v-model="form.participationNums" :min="2" :max="50" label="参会人数" @change="handleChange" />
      </el-form-item>
      <el-form-item label="预约会议室 (注: 预约大型会议室需要人工审核)" prop="appointmentRoomId" required>
        <el-select v-model="form.appointmentRoomId" placeholder="请选择要预约的会议室">
          <el-option v-for="room in form.rooms" :label="room.roomName+'-'+room.roomType.roomTypeName" :value="room.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="appointmentStartTime" required>
        <el-date-picker
          v-model="form.appointmentStartTime"
          type="datetime"
          placeholder="选择会议开始日期时间"
        />
      </el-form-item>
      <el-form-item label="结束时间" prop="appointmentEndTime" required>
        <el-date-picker
          v-model="form.appointmentEndTime"
          type="datetime"
          placeholder="选择会议结束日期时间"
        />
      </el-form-item>
      <el-form-item label="状态" required>
        <el-select disabled placeholder="待审核" />
        <!--        <el-steps :active="1" align-center>
          <el-step title="填写预约"></el-step>
          <el-step title="审核"></el-step>
          <el-step title="使用"></el-step>
          <el-step title="结束"></el-step>
        </el-steps>-->
        <!--        <el-switch
          v-model="adminUser.status"
          active-color="#13ce66"
          inactive-color="#ff4949"
          width="50"
        /> {{ adminUser.status ? '启用' : '禁用' }}-->
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
        <el-button @click="resetForm('form')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import { querySuitableRoom } from '@/api/conference_room'
import { success, error } from '@/utils/message'
import { createAppointment } from '@/api/appointment'

export default {
  data() {
    /*
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
    }*/
    return {
      form: {
        rooms: [],
        conferenceSubject: undefined,
        participationNums: 1,
        appointmentUserId: undefined,
        appointmentRoomId: undefined,
        appointmentStartTime: undefined,
        appointmentEndTime: undefined,
        appointmentCreateTime: undefined,
        appointmentStatus: undefined
      }, /*,      rules: {
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
      }*/
      appointmentInfo: {
        id: undefined,
        conferenceSubject: undefined,
        participationNums: 0,
        appointmentUserId: undefined,
        appointmentRoomId: undefined,
        appointmentStartTime: undefined,
        appointmentEndTime: undefined,
        appointmentCreateTime: undefined,
        appointmentStatus: 0 /* 预约状态{0: 待审核, 1: 预约成功, 2: 预约失败, 3:会议进行, 4:预约完成} */
      },
      dataOfSmall: undefined,
      dataOfMedium: undefined,
      dataOfLarge: undefined,
      oldNums: undefined
    }
  },
  created() {
    this.init()
  },
  methods: {
    onSubmit() {
      this.appointmentInfo.conferenceSubject = this.form.conferenceSubject
      this.appointmentInfo.participationNums = this.form.participationNums
      this.appointmentInfo.appointmentRoomId = this.form.appointmentRoomId
      this.appointmentInfo.appointmentStartTime = this.form.appointmentStartTime
      this.appointmentInfo.appointmentEndTime = this.form.appointmentEndTime
      console.log('数据：', this.appointmentInfo)
      createAppointment(this.appointmentInfo).then(response => {
        // console.log(response)
        if (response.status === 200) {
          success('预约申请成功')
          this.resetForm('form')
        } else {
          error('预约申请失败')
        }
      })
    },
    init() {
      querySuitableRoom(1).then(response => {
        console.log(response.data.data)
        this.form.rooms = response.data.data
        this.dataOfSmall = response.data.data
        this.oldNums = response.data.data.length
      })
      querySuitableRoom(23).then(response => {
        this.dataOfMedium = response.data.data
      })
      querySuitableRoom(37).then(response => {
        this.dataOfLarge = response.data.data
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
    },
    handleChange() {
      const num = this.form.participationNums
      if (num >= 1 && num <= 22) {
        this.form.rooms = this.dataOfSmall
      } else if (num >= 23 && num <= 36) {
        this.form.rooms = this.dataOfMedium
      } else {
        this.form.rooms = this.dataOfLarge
      }
      if (this.form.rooms.length !== this.oldNums) {
        this.appointmentInfo.appointmentRoomId = undefined
        this.oldNums = this.form.rooms.length
      }
      // console.log('参会人数: ' + this.form.participationNums)
      /* if ((this.form.participationNums === 23) || (this.form.participationNums === 37)) {
        this.init()
      } */
    }
  }
}
</script>

<style lang="scss" scoped>

.ruleForm {
  margin: 100px 100px;
}
</style>
