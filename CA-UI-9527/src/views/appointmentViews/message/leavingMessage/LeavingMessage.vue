<template>
  <div class="joe_main">
    <div class="joe_comment">

      <!-- 评论区 -->
      <h3 class="joe_comment__title">留言 ({{ this.messageTotal }})</h3>

      <div id="respond-page-1176" class="joe_comment__respond">
        <div class="joe_comment__respond-form">
          <div class="head">
            <div class="list">
              <el-input v-model="qq" max-length="16" placeholder="输入QQ自动填充相关信息" @blur="parseQQInfo"/>
            </div>
            <div class="list">
              <el-input v-model="leavingMessage.nickname" placeholder="请输入昵称..." />
            </div>
            <div class="list">
              <el-input v-model="leavingMessage.email" placeholder="请输入真实邮箱，用于接收回信..." />
            </div>
          </div>
          <div class="head">
            <div class="list">
              <a-input v-model="leavingMessage.avatar" style="width: 60%" placeholder="自定义头像链接（非必填）..." />
              <el-avatar :src="leavingMessage.avatar" shape="square" alt="评论头像" style="top: 7px; position: relative"" />
            </div>
          </div>
          <div class="body">
            <a-textarea
              v-model="leavingMessage.message"
              class="text joe_owo__target"
              :max-length="500"
              placeholder="来都来了，说点什么吧？"
            />
          </div>
          <div class="foot">
            <div class="owo joe_owo__contain" />
            <el-button type="primary" plain @click="submitLeavingMessage()">发送评论</el-button>
<!--            <div class="submit">
              <span class="cancle joe_comment__cancle">取消</span>

            </div>-->
          </div>
        </div>
      </div>
      <ol class="comment-list">
        <li v-for="item in data" class="comment-list__item">
          <div id="comment-548" class="comment-list__item-contain">
            <div class="term">
              <img
                width="48"
                height="48"
                class="avatar ls-is-cached lazyloaded"
                :src="item.avatar"
                :data-src="item.avatar"
                alt="头像"
              >
              <div class="content">
                <div class="user">
                  <span class="author commentReview">
                    {{ item.nickname }}
                  </span>
                  <div class="agent">{{ item.ip }} · {{ item.location }} · {{ item.osName }} · {{ item.browser }}</div>
                </div>
                <div class="substance">
                  <p>
                    {{ item.message }}
                  </p>
                </div>
                <div class="handle">
                  <time class="date commentReview" :datetime="item.createTime">
                    {{ item.isHandle === 0 ? '评论审核中' : '评论审核通过' }} —— {{ parseTimeCur(item.createTime) }}
                  </time>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ol>
      <div class="joe_pagination">
        <a-pagination
          :page-size-options="pageSizeOptions"
          show-size-changer
          :default-current="1"
          :page-size.sync="pagination.pageSize"
          :total="total"
          :show-total="(total, range) => `${range[0]}-${range[1]} 共 ${total} 条`"
          @change="onChange"
          @showSizeChange="onShowSizeChange"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { filterObj } from '@/utils'
import { insertLeaveMessage, queryAllLeaveMessage, queryLeaveMessageSize } from '@/api/message'
import { queryQQInfo, queryVisitorInfo } from '@/api/commonApi'
import moment from 'moment/moment'
import { success, warning } from '@/utils/message'

export default {
  name: 'LeavingMessage',
  components: {},
  data() {
    return {
      leavingMessage: {
        id: undefined,
        nickname: '',
        message: '',
        email: '',
        avatar: '',
        ip: '',
        location: '',
        browser: '',
        osName: '',
        isHandle: 0,
        createTime: undefined
      },
      data: [],
      /* 分页参数 */
      pagination: {
        current: 1,
        pageSize: 10,
        timeOrder: true
      },
      pageSizeOptions: ['10', '20', '30'],
      /* 排序参数 */
      sorter: {
        column: 'createTime',
        order: 'desc'
      },
      qq: '',
      preTimeStamp: undefined,
      total: undefined,
      messageTotal: undefined
    }
  },
  created() {
    this.loadData()
  },
  methods: {
    loadData() {
      this.preTimeStamp = moment().toDate().getTime()
      this.refreshLeaveMessage()
      queryLeaveMessageSize().then(response => {
        console.log(response.data)
        this.messageTotal = response.data.data
      })
      queryVisitorInfo().then(response => {
        const { browser, system, ip, location } = response.data
        this.leavingMessage.browser = browser
        this.leavingMessage.osName = system
        this.leavingMessage.ip = ip
        this.leavingMessage.location = location
      })
    },
    getQueryParams() {
      const param = Object.assign(this.sorter)
      // 获取查询条件
      param.pageNo = this.pagination.current
      param.pageSize = this.pagination.pageSize
      param.status = '0,2'
      return filterObj(param)
    },
    submitLeavingMessage() {
      if (this.leavingMessage.nickname === null || this.leavingMessage.nickname.length === 0 || this.leavingMessage.nickname.trim().length === 0) {
        this.$message.error('请输入昵称！')
      } else if (!new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(this.leavingMessage.email)) {
        this.$message.error('请输入有效的邮箱！')
      } else if (this.leavingMessage.message === null || this.leavingMessage.message.length === 0 || this.leavingMessage.message.trim().length === 0) {
        this.$message.error('请输入留言内容！')
      }else if (moment().toDate().getTime() - this.preTimeStamp <= 30 * 1000) {
        warning('评论过于频繁，两次评论要间隔30S，请稍后在评论')
      } else {
        success('留言成功!')
        // console.log(this.leavingMessage)
        this.leavingMessage.createTime = new Date()
        insertLeaveMessage(this.leavingMessage)
        this.preTimeStamp = moment().toDate().getTime()
        this.refreshLeaveMessage()
        this.leavingMessage.message = ''
      }
    },
    refreshLeaveMessage() {
      queryAllLeaveMessage(this.pagination).then(response => {
        this.data = response.data.data
        this.total = response.data.data.length
      })
    },
    onShowSizeChange(current, pageSize) {
      this.pagination.current = current
      this.pagination.pageSize = pageSize
      this.refreshLeaveMessage()
    },
    onChange(pageNumber) {
      this.pagination.current = pageNumber
      this.refreshLeaveMessage()
    },
    parseQQInfo() {
      queryQQInfo(this.qq).then(response => {
        // console.log(response.data.imgUrl)
        const { imgurl, name, qemail } = response.data
        this.leavingMessage.avatar = imgurl
        this.leavingMessage.nickname = name
        this.leavingMessage.email = qemail
      })
    },
    parseTimeCur(time) {
      return moment(time).format('yyyy-MM-DD HH:mm:ss')
    }
  }

}
</script>

<style>
.commentReview {
  color: #999;
  display: inline;
  font-weight: 400 !important;
}
</style>

