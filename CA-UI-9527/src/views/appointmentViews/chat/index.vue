<template>
  <div class="joe_main">
    <div class="joe_comment">

      <!-- 评论区 -->
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
  mounted() {
    this.ws = new WebSocket('ws://192.168.11.242:10005/websocket')
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

    this.userInfo = jwt.decode(getToken())
    /* this.socket = io('http://192.168.11.242:10005/chat')
    this.socket.on('connect', () => {
      console.log('connected')
    })
    this.socket.on('disconnect', () => {
      console.log('disconnected')
    })
    this.socket.on('/app/topic/messages', (data) => {
      console.log('received message:', data)
      this.messages.push(data)
    })
    this.userInfo = jwt.decode(getToken())

    const chatMessage = {
      messageId: 1,
      content: '001',
      username: this.userInfo.username,
      createTime: new Date()
    }
    this.socket.emit('/app/chat', chatMessage)*/
  },

  methods: {
    sendMessage() {
      if (this.message !== '') {
        const chatMessage = {
          messageId: 1,
          content: this.message,
          username: this.userInfo.username,
          createTime: new Date()
        }
        this.messages.push(chatMessage)
        this.ws.send(JSON.stringify(chatMessage))
        this.message = ''
      }
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

