<!--<template>
  <div class="joe_main">
    <div class="joe_comment">

      &lt;!&ndash; 评论区 &ndash;&gt;
      <h3 class="joe_comment__title">聊天室</h3>
      <div>
        <ul>
          <li v-for="msg in messages" :key="msg.id">
            {{ msg.username }}:&emsp;&emsp;{{ msg.content }} ______({{ msg.createTime }})
          </li>
        </ul>

        <input v-model="message" @keyup.enter="sendMessage" />
        <button @click="sendMessage">Send</button>
      </div>
    </div>
  </div>
</template>-->

<!--<template>
  <div class="joe_main">
    <div class="joe_comment">

      &lt;!&ndash; 评论区 &ndash;&gt;
      <h3 class="joe_comment__title">聊天室</h3>

      <div class="messages-container">
        <ul class="messages-list">
          <li v-for="msg in formattedMessages" :key="msg.id" class="message-item">
            <span class="message-username">{{ msg.username }}:</span>
            <span class="message-content">{{ msg.content }}</span>
            <span class="message-time">{{ msg.createTime }}</span>
          </li>
        </ul>

        <div class="message-input-container">
          <input v-model="message" @keyup.enter="sendMessage" class="message-input" placeholder="请输入聊天内容" />
          <button @click="sendMessage" class="send-message-btn">发送</button>
        </div>
      </div>
    </div>
  </div>
</template>-->
<template>
  <div class="joe_main">
    <div class="joe_comment">
      <h3 class="joe_comment__title">聊天室</h3>
      <div class="chat-content" style="max-height: 50%;height: 578px;overflow-y: auto;">
        <!-- recordContent 聊天记录数组-->
        <div v-for="msg in formattedMessages" :key="msg.id">
          <!-- 对方 -->
          <div v-if="msg.userId !== userInfo.id" class="word">
            <img src="https://q4.qlogo.cn/headimg_dl?dst_uin=3238939293&spec=640">
            <div class="info">
              <p class="time">{{ msg.username }}  {{ msg.createTime }}</p>
              <div class="info-content" style="background-color:#e5e5e5;">{{ msg.content }}</div>
            </div>
          </div>
          <!-- 我的 -->
          <div v-else class="word-my">
            <div class="info">
              <p class="time">{{ msg.username }}  {{ msg.createTime }}</p>
              <div class="info-content" style="background-color:#12b7f5;">{{ msg.content }}</div>
            </div>
            <img src="https://q4.qlogo.cn/headimg_dl?dst_uin=2519307389&spec=640">
          </div>
        </div>
      </div>
      <div class="message-input-container">
        <input v-model="message" @keyup.enter="sendMessage" class="message-input" placeholder="请输入聊天内容" />
        <button @click="sendMessage" class="send-message-btn">发送</button>
      </div>
    </div>
  </div>
</template>

<script>
import jwt from 'jsonwebtoken'
import { getToken } from '@/utils/auth'
import { success, warning, error } from '@/utils/message'

export default {
  name: 'Chat',
  inject: ['reload'],
  components: {},
  data() {
    return {
      ws: null,
      messages: [],
      message: '',
      userInfo: {}
    }
  },
  computed: {
    formattedMessages() {
      return this.messages.map(msg => {
        return {
          ...msg,
          createTime: this.formatDate(msg.createTime)
        }
      })
    }
  },
  created() {
    this.userInfo = jwt.decode(getToken())
    this.ws = new WebSocket('ws://192.168.11.242:10005/websocket?userId=' + this.userInfo.id)
    this.ws.onopen = function() {
      success('websocket连接成功')
    }
    this.ws.onmessage = (e) => {
      // console.log('收到的消息', JSON.parse(e.data))
      this.messages.push(JSON.parse(e.data))
      // console.log('消息队列', this.messages)
    }
    this.ws.onclose = (e) => {
      warning('断开连接')
    }
    this.ws.onerror = (e) => {
      error('发送错误')
    }
  },
  methods: {
    sendMessage() {
      if (this.message !== '') {
        const chatMessage = {
          messageId: 1,
          content: this.message,
          userId: this.userInfo.id,
          username: this.userInfo.username,
          createTime: new Date()
        }
        this.messages.push(chatMessage)
        this.ws.send(JSON.stringify(chatMessage))
        this.message = ''
      }
    },
    formatDate(dateStr) {
      const date = new Date(dateStr)
      const year = date.getFullYear()
      const month = (date.getMonth() + 1).toString().padStart(2, '0')
      const day = date.getDate().toString().padStart(2, '0')
      const hours = date.getHours().toString().padStart(2, '0')
      const minutes = date.getMinutes().toString().padStart(2, '0')
      const seconds = date.getSeconds().toString().padStart(2, '0')
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`
    }
  }

}
</script>

<style>
img{
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.chat-content{
  width: 100%;
  max-height: 50%; /* 指定最大高度 /
  overflow-y: auto; / 添加垂直滚动条 */
  padding: 20px;
  .word{
    display: flex;
    margin-bottom: 20px;
    img{
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
    .info{
      margin-left: 10px;
      .time{
        font-size: 12px;
        color: rgba(51,51,51,0.8);
        margin: 0;
        height: 20px;
        line-height: 20px;
        margin-top: -5px;
      }
      .info-content{
        padding: 10px;
        font-size: 14px;
        background: #fff;
        position: relative;
        margin-top: 8px;
        border-radius: 6px;
      }
    //小三角形
      .info-content::before{
        position: absolute;
        left: -8px;
        top: 8px;
        content: '';
        border-right: 10px solid #FFF;
        border-top: 8px solid transparent;
        border-bottom: 8px solid transparent;
      }
    }
  }

  .word-my{
    display: flex;
    justify-content:flex-end;
    margin-bottom: 20px;
    img{
      width: 40px;
      height: 40px;
      border-radius: 50%;
    }
    .info{
      width: 90%;
      margin-left: 10px;
      text-align: right;
      .time{
        font-size: 12px;
        color: rgba(51,51,51,0.8);
        margin: 0;
        height: 20px;
        line-height: 20px;
        margin-top: -5px;
        margin-right: 10px;
      }
      .info-content{
        max-width: 70%;
        padding: 10px;
        font-size: 14px;
        float: right;
        margin-right: 10px;
        position: relative;
        margin-top: 8px;
        background: #A3C3F6;
        text-align: left;
      }
    //小三角形
      .info-content::after{
        position: absolute;
        right: -8px;
        top: 8px;
        content: '';
        border-left: 10px solid #A3C3F6;
        border-top: 8px solid transparent;
        border-bottom: 8px solid transparent;
      }
    }
  }
}

.message-input-container {
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.message-input {
  height: 40px;
  flex: 1;
  margin-right: 10px;
  padding: 0 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.send-message-btn {
  height: 40px;
  padding: 0 20px;
  border: none;
  background-color: #409EFF;
  color: #fff;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.send-message-btn:hover {
  background-color: #66B1FF;
}
/*.messages-container {
  margin-top: 20px;
  border: 1px solid #ccc;
  padding: 10px;
}

.messages-list {
  list-style: none;
  margin: 0;
  padding: 0;
}

.message-item {
  margin-bottom: 10px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.message-username {
  font-weight: bold;
  margin-right: 10px;
}

.message-content {
  flex: 1;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.message-time {
  color: #999;
  margin-left: 10px;
  font-size: 12px;
}

*/
</style>

