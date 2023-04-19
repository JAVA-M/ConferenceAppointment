<template>
  <div class="components-container">
    <el-upload
      list-type="picture-card"
      :action="uploadImgServer"
      :headers="headers"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :limit="imgLimit"
      :on-exceed="handleExceed"
      :file-list="fileListFront"
    >
      <i class="el-icon-plus" />
    </el-upload>
  </div>
</template>

<script>
import { error, success } from '@/utils/message'
import { getToken } from '@/utils/auth'

export default {
  name: 'MyUpload',
  data() {
    return {
      uploadImgServer: 'http://localhost:10010/api/img/upload',
      imgLimit: 1, // 上传照片数
      fileListFront: [] // 照片列表
    }
  },
  computed: {
    headers() {
      return {
        'token': getToken()
      }
    }
  },
  methods: {
    /** 删除图片 */
    handleRemove(file, fileList) {
      console.log(file, fileList)
      this.fileListFront.some((item, i) => {
        if (item.name === file.name) {
          this.fileListFront.splice(i, 1) // 在数组的some方法中，如果return true，就会立即终止这个数组的后续循环
          return true
        }
      })
    },

    /** 上传文件之前的钩子函数 */
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpg' || file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isJPG) {
        error('仅支持jpg，png格式的图片！')
      }
      if (!isLt2M) {
        error('上传图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },

    /** 上传成功后的钩子函数 */
    handleAvatarSuccess(res, file) {
      console.log('file')
      console.log(file)
      success('文件上传成功')
      console.log(URL.createObjectURL(file.raw))
      this.fileListFront.push(file)
    },

    /** 查看图片 */
    handlePictureCardPreview(file) {
      console.log(file)
      // this.dialogImageUrl = file.url
      // this.dialogVisible = true
    },

    /** 文件超出个数限制时的钩子 */
    handleExceed() {
      this.$message.error(`只能选择${this.imgLimit}个文件`)
    }
  }
}
</script>
