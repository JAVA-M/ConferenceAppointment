<template>
  <aside class="joe_aside" :style="{marginTop: key()?'35px':'0'}">
    <section class="joe_aside__item author">
      <img
        width="100%"
        height="120"
        class="image ls-is-cached lazyloaded"
        src="@/assets/user-background-img.jpg"
        alt="博主栏壁纸"
      >
      <div class="user" style="padding-bottom: 0;font-family: custom;">
        <img
          width="75"
          height="75"
          class="avatar ls-is-cached lazyloaded"
          src="@/assets/M_Logo.png"
          alt="博主头像"
        >
        <span style="margin-bottom: 10px;font-size: 20px;font-weight: 500">
          {{ userInfo.realname }}
        </span>
        <span style="font-size: 15px;margin-bottom: 3px">{{ userInfo.personDescribe }}</span>
        <p>Made By 满兴富</p>
      </div>
      <div class="count">
        <div class="item">
          <span class="num"></span>
          <span>{{ curDate }}</span>
        </div>
        <div class="item">
          <span class="num"></span>
          <span>
            {{ curWeather }}
          </span>
        </div>
      </div>
      <ul class="list" style="margin-bottom: 0">
        <template v-for="item in limit3Data">
          <li class="item">
            <router-link class="link" :to="'/articleDetails/'+item.id" :title="item.title">
              {{ item.title }}
            </router-link>
            <svg class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="16" height="16">
              <path
                d="M448.12 320.331a30.118 30.118 0 0 1-42.616-42.586L552.568 130.68a213.685 213.685 0 0 1 302.2 0l38.552 38.551a213.685 213.685 0 0 1 0 302.2L746.255 618.497a30.118 30.118 0 0 1-42.586-42.616l147.034-147.035a153.45 153.45 0 0 0 0-217.028l-38.55-38.55a153.45 153.45 0 0 0-216.998 0L448.12 320.33zM575.88 703.67a30.118 30.118 0 0 1 42.616 42.586L471.432 893.32a213.685 213.685 0 0 1-302.2 0l-38.552-38.551a213.685 213.685 0 0 1 0-302.2l147.065-147.065a30.118 30.118 0 0 1 42.586 42.616L173.297 595.125a153.45 153.45 0 0 0 0 217.027l38.55 38.551a153.45 153.45 0 0 0 216.998 0L575.88 703.64zm-234.256-63.88L639.79 341.624a30.118 30.118 0 0 1 42.587 42.587L384.21 682.376a30.118 30.118 0 0 1-42.587-42.587z"
              />
            </svg>
          </li>
        </template>

      </ul>
    </section>

    <!-- 纪念日 -->
    <ul class="joe_header__slideout-count" style="padding: 12px 15px;">
      <div id="lovexhj" style="width: 100%; height: 100%; text-align: center; font-size: 1rem;">
        <div id="lovexhjImage" style="width: 220px; margin: 0 auto;">
          <img
            src="@/assets/running.png"
            alt="系统正常波动"
            style="width: 100px;"
          >
        </div>
        <p id="loveSitetime" style="color: #909399;font-size: 13.5px;margin-bottom: 0">系统正常运行时间<br>{{
          loveTime
        }}<br></p>
      </div>
    </ul>

    <section class="joe_aside__item hot">
      <div class="joe_aside__item-title">
        <svg class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="18" height="18">
          <path
            d="M512 938.667A426.667 426.667 0 0 1 85.333 512a421.12 421.12 0 0 1 131.2-306.133 58.88 58.88 0 0 1 42.667-16.64c33.28 1.066 58.027 28.16 84.267 56.96 7.893 8.533 19.626 21.333 28.373 29.013a542.933 542.933 0 0 0 24.533-61.867c18.134-52.266 35.414-101.76 75.307-121.6 55.04-27.733 111.573 37.974 183.253 121.6 16.214 18.774 38.614 44.8 53.547 59.52 1.707-4.48 3.2-8.96 4.48-12.373 8.533-24.32 18.987-54.613 51.2-61.653a57.813 57.813 0 0 1 55.68 20.053A426.667 426.667 0 0 1 512 938.667zM260.693 282.453A336.64 336.64 0 0 0 170.667 512a341.333 341.333 0 1 0 614.826-203.733 90.24 90.24 0 0 1-42.666 50.56 68.267 68.267 0 0 1-53.547 1.706c-25.6-9.173-51.627-38.4-99.2-93.226a826.667 826.667 0 0 0-87.253-91.734 507.733 507.733 0 0 0-26.24 64c-18.134 52.267-35.414 101.76-75.947 119.254-48.853 21.333-88.32-21.334-120.107-56.96-5.76-4.694-13.226-13.014-19.84-19.414z"
          />
          <path
            d="M512 810.667A298.667 298.667 0 0 1 213.333 512a42.667 42.667 0 0 1 85.334 0A213.333 213.333 0 0 0 512 725.333a42.667 42.667 0 0 1 0 85.334z"
          />
        </svg>
        <span class="text">热门会议室</span>
        <span class="line" />
      </div>
      <ol class="joe_aside__item-contain">
        <template v-for="(item,index) in hotCA">
          <li class="item">
            <router-link class="link" :to="'/articleDetails/'+item.id" :title="item.roomName">
              <i class="sort">{{ index + 1 }}</i>
              <img
                width="100%"
                height="130"
                class="image ls-is-cached lazyloaded"
                :src="getImgView(item.roomType.roomCover)"
                data-src="getImgView(item.cover)"
                :alt="item.title"
              >
              <div class="describe">
                <h6>{{ item.roomName }}</h6>
<!--                <span>{{ item.read }} 阅读 - {{ formatTime(item.updateTime) }}</span>-->
              </div>
            </router-link>
          </li>
        </template>
      </ol>
    </section>
  </aside>
</template>

<script>

import { getAction, getFileAccessHttpUrl } from '@/api/manage'
import { differenceDate2 } from '@/utils/index'
import moment from 'moment'
import { queryVisitorInfo, queryWeather } from '@/api/commonApi'
import { queryConferenceRoomByCondition } from '@/api/conference_room'

export default {
  name: 'AntsContainer',
  components: {},
  data() {
    return {
      limit3Data: [],
      loveTime: '',
      curDate: '',
      curWeather: '',
      userInfo: {
        realname: '会议预约系统', /* ConferenceRoom_Appointment */
        avatar: 'http://localhost:8085/upload/ks-d9fb8b2990e041869e87b18fd8f3f0e0.jpg',
        personDescribe: '一个小而全的预约系统' /* 即使再小的帆也能远航。 */
      },
      hotCA: []
    }
  },
  watch: {
    $route(to, from) {
      // console.log(from.path);//从哪来
      // console.log(to.path);//到哪去
      this.reloadAside(to.path)
    }
  },
  created() {
    setInterval(this.differenceDate, 1000)
    this.calCurDate()
    this.queryHotCA()
  },
  methods: {
    key() {
      if (this.$route.path.indexOf('/articleDetails/') !== -1) {
        // console.log("this.$route.path",this.$route.path)
        return true
      }
      return false
    },
    // 格式化时间
    formatTime(value) {
      return moment(value).format('MM/DD')
    },
    differenceDate() {
      this.loveTime = differenceDate2('2023-02-03')
    },
    calCurDate() {
      this.curDate = moment().format('yyyy年MM月DD日') //  HH:mm:ss
      /* queryWeather().then(response => {
        console.log('天气', response.data)
        this.curWeather = response.data.city + '(' + response.data.info.low.substr(2, 3) +
          '~' + response.data.info.high.substr(2, 5) + ')'
      }) */
      queryVisitorInfo().then(response => {
        // console.log('天气', response.data)
        this.curWeather = response.data.location.split('-')[1] + '(' + response.data.low.split('°')[0] +
          '~' + response.data.high + ')'
      })
    },
    getArticleLimit3ByView(username) {
      getAction(this.url.getArticleLimit3ByView, { username: username }).then(res => {
        if (res.success) {
          this.limit3Data = res.result
        }
      })
    },
    // 初始化文章标签
    initArticleLable() {
      getAction(this.url.initArticleLable, {}).then(res => {
        if (res.success) {
          this.lableData = res.result
          this.$refs.articleLableSub.data = res.result
        }
      })
    },
    /* 图片预览 */
    getImgView(text) {
      if (text && text.indexOf(',') > 0) {
        text = text.substring(0, text.indexOf(','))
      }
      return getFileAccessHttpUrl(text)
    },
    reloadAside(path) {
      if (path.indexOf('/articleDetails/') !== -1) {
        this.islove = false
        const id = path.replace('/articleDetails/', '')
        console.log(id)
        // this.getUserInfoByArticleId(id); // http://localhost:8085/vue/articleView/getUserInfoByArticleId?id=30
      } else {
        this.islove = true
        this.userInfo = {
          realname: 'Mr.Man',
          avatar: 'http://localhost:8085/upload/ks-d9fb8b2990e041869e87b18fd8f3f0e0.jpg',
          personDescribe: '即使再小的帆也能远航。'
        }
        this.getArticleLimit3ByView()
        this.initStatisticsTopNum()
      }
    },
    queryHotCA() {
      queryConferenceRoomByCondition({
        'rooName': null, type: null, 'pageNo': 1, 'pageSize': 5, 'sortType': 4
      }).then(response => {
        this.hotCA = response.data.data
      })
    }
  }

}
</script>

