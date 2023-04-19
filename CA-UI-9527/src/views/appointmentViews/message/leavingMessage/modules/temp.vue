<template>
  <div class="joe_main">
    <div class="joe_comment">

      <!-- 评论区 -->
      <h3 class="joe_comment__title">留言 ({{this.pagination.total}})</h3>

      <div id="respond-page-1176" class="joe_comment__respond">
        <div class="joe_comment__respond-form">
          <div class="head">
            <div class="list">
              <a-input v-model="leavingMessage.name" :max-length=16 placeholder="请输入昵称..."/>
            </div>
            <div class="list">
              <a-input v-model="leavingMessage.mail" placeholder="请输入真实邮箱，用于接收回信..."/>
            </div>
            <div class="list">
              <a-input v-model="leavingMessage.url" placeholder="请输入网址（非必填）..."/>
            </div>
          </div>
          <div class="head">
            <div class="list">
              <a-input v-model="leavingMessage.avatar" :max-length=16 placeholder="自定义头像链接（非必填）..."/>
            </div>
          </div>
          <div class="body">
            <a-textarea class="text joe_owo__target" v-model="leavingMessage.message" :max-length=500
                        placeholder="来都来了，说点什么吧？"/>
          </div>
          <div class="foot">
            <div class="owo joe_owo__contain">
            </div>
            <div class="submit">
              <span class="cancle joe_comment__cancle">取消</span>
              <a-button @click="submitLeavingMessage()">发送评论</a-button>
            </div>
          </div>
        </div>
      </div>
      <ol class="comment-list">
        <li class="comment-list__item" v-for="item in data">
          <div class="comment-list__item-contain" id="comment-548">
            <div class="term">
              <img width="48" height="48" class="avatar ls-is-cached lazyloaded"
                   :src="item.avatar"
                   :data-src="item.avatar" alt="头像">
              <div class="content">
                <div class="user">
                  <span class="author" v-if="item.url">
                    <a :href="item.url" rel="external nofollow" target="_blank">
                      {{ item.name }}
                    </a>
                  </span>
                  <span class="author commentReview" v-else>
                      {{ item.name }}
                  </span>
                  <div class="agent">{{ item.ip }} · {{ item.ipAddr }} · {{ item.osName }} · {{ item.browser }}</div>
                </div>
                <div class="substance">
                  <p>
                    {{ item.message }}
                  </p>
                </div>
                <div class="handle">
                  <time class="date commentReview" :datetime="item.createTime">{{item.status === '0' ? "评论审核中" : "评论审核通过"}} —— {{ item.createTime }}</time>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ol>
      <div class="joe_pagination">
        <a-pagination
          :pageSizeOptions="pagination.pageSizeOptions"
          show-size-changer
          :default-current="1"
          :page-size.sync="pagination.pageSize"
          :total="pagination.total"
          @change="onChange"
          :show-total="(total, range) => `${range[0]}-${range[1]} 共 ${total} 条`"
          @showSizeChange="onShowSizeChange"
        />
      </div>
    </div>
  </div>
</template>
<!--  <div style="margin: 0 auto" class="joe_container">
    <div class="joe_main">
      <el-input type="textarea"></el-input>
    </div>
  </div>-->

<script>
import {getAction, postAction} from '@/api/manage'
import {filterObj} from "@/utils/util";

export default {
  name: 'leavingMessage',
  components: {},
  data() {
    return {
      url: {
        list: '/leavingMessage/list',
        add: '/leavingMessage/add',
      },
      leavingMessage: {
        mail: '',
        message: '',
        name: '',
        url: '',
        avatar: ''
      },
      data: [],
      /* 分页参数 */
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '20', '30'],
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      },
      /* 排序参数 */
      sorter: {
        column: 'createTime',
        order: 'desc'
      },
    }
  },
  created() {
    // this.loadData()
  },
  methods: {
    loadData() {
      var params = this.getQueryParams()// 查询条件
      getAction(this.url.list, params).then(res => {
        if (res.success) {
          this.data = res.result.records
          this.pagination.total = res.result.total
        }
      })
    },
    getQueryParams() {
      const param = Object.assign( this.sorter)
      // 获取查询条件
      param.pageNo = this.pagination.current
      param.pageSize = this.pagination.pageSize
      param.status = '0,2'
      return filterObj(param)
    },
    submitLeavingMessage() {
      if (this.leavingMessage.name === null || this.leavingMessage.name.length === 0 || this.leavingMessage.name.trim().length === 0) {
        this.$message.error("请输入昵称！")
      } else if (!new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/).test(this.leavingMessage.mail)) {
        this.$message.error("请输入有效的邮箱！")
      } else if (this.leavingMessage.message === null || this.leavingMessage.message.length === 0 || this.leavingMessage.message.trim().length === 0) {
        this.$message.error("请输入留言内容！")
      } else {
        console.log(this.leavingMessage)
        postAction(this.url.add, this.leavingMessage).then(res => {
          if (res.success) {
            this.leavingMessage = {}
            this.loadData()
            this.$message.success(res.message)
          }
        })
      }
    },
    onShowSizeChange(current, pageSize) {
      this.pagination.current = current
      this.pagination.pageSize = pageSize
      this.loadData()
    },
    onChange(pageNumber) {
      this.pagination.current = pageNumber
      this.loadData()
    },
  }

}
</script>

<style>
.commentReview {
  color: #999;
  display: inline;
  font-weight: 400!important;
}
</style>

