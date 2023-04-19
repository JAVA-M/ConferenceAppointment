<template>
  <div class="joe_main">
    <div class="joe_index">
      <!--   置頂文章展示区间   -->
      <div class="joe_index__banner">
        <div class="joe_index__banner-recommend noswiper">
          <template v-for="item in recommendData">
            <figure class="item">
              <router-link
                class="thumbnail"
                :to="'/articleDetails/'+item.id"
                :title="item.title"
              >
                <img
                  width="100%"
                  height="100%"
                  class=" ls-is-cached lazyloaded"
                  :src="getImgView(item.coverImage)"
                  :data-src="getImgView(item.coverImage)"
                  :alt="item.title"
                >
              </router-link>
              <figcaption class="information">
                <span class="type">置顶</span>
                <div class="text">{{ item.title }}</div>
              </figcaption>
            </figure>
          </template>

        </div>
      </div>

      <!--   简单选择文章类型   -->
      <div class="joe_index__title">
        <ul class="joe_index__title-title" style="margin-bottom: 0">
          <li :class="latestArticleCss" data-type="created" @click="joeIndexTitleTitle('1')">最新文章</li>
          <li :class="popularArticlesCss" data-type="views" @click="joeIndexTitleTitle('2')">热门文章</li>
          <!--              <li class="item" data-type="commentsNum">评论最多</li>-->
          <!--              <li class="item" data-type="agree">点赞最多</li>-->
          <li v-if="latestArticleCss === 'item active'" class="line" style="left: 0px; width: 56px;" />
          <li v-if="popularArticlesCss === 'item active'" class="line" style="left: 76px; width: 56px;" />
          <!--              <li class="line" style="left: 152px; width: 56px;"></li>-->
          <!--              <li class="line" style="left: 228px; width: 56px;"></li>-->
        </ul>
      </div>

      <!--   根据上面做出的选择来展示对应的文章列表   -->
      <div class="joe_index__list" data-wow="fadeIn">
        <ul v-if="!loading" class="joe_list">
          <template v-for="item in listData">
            <!--            <li>{{item.title}}</li>-->
            <li class="joe_list__item wow default" style="visibility: visible; animation-name: fadeIn;">
              <div class="line" />
              <!-- 文章封面展示 -->
              <router-link
                :to="'/articleDetails/'+item.id"
                class="thumbnail"
                :title="item.title"
                target="_self"
                rel="noopener noreferrer"
              >
                <img
                  width="100%"
                  height="100%"
                  class=" ls-is-cached lazyloaded"
                  :src="item.coverImage"
                  :alt="item.title"
                >
                <time :datetime="item.updateTime">{{ myDateFormat(item.updateTime) }}</time>
                <!--                <time :datetime="item.createTime">{{ moment(item.createTime).format('YYYY-MM-DD hh:mm:ss') }}</time>-->
                <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="20" height="20">
                  <path
                    d="M903.93 107.306H115.787c-51.213 0-93.204 42.505-93.204 93.72V825.29c0 51.724 41.99 93.717 93.717 93.717h788.144c51.72 0 93.717-41.993 93.717-93.717V201.025c-.512-51.214-43.017-93.719-94.23-93.719zm-788.144 64.527h788.657c16.385 0 29.704 13.316 29.704 29.704v390.229L760.54 402.285c-12.805-13.828-30.217-21.508-48.14-19.971-17.924 1.02-34.821 10.754-46.602 26.114l-172.582 239.16-87.06-85.52c-12.29-11.783-27.654-17.924-44.039-17.924-16.39.508-31.755 7.676-43.53 20.48L86.595 821.705V202.05c-1.025-17.416 12.804-30.73 29.191-30.217zm788.145 683.674H141.906l222.255-245.82 87.06 86.037c12.8 12.807 30.212 18.95 47.115 17.417 17.41-1.538 33.797-11.266 45.063-26.118l172.584-238.647 216.111 236.088 2.051-1.54V825.8c.509 16.39-13.315 29.706-30.214 29.706zm0 0"
                  />
                  <path
                    d="M318.072 509.827c79.89 0 144.417-65.037 144.417-144.416 0-79.378-64.527-144.925-144.417-144.925-79.891 0-144.416 64.527-144.416 144.412 0 79.892 64.525 144.93 144.416 144.93zm0-225.327c44.553 0 80.912 36.362 80.912 80.91 0 44.557-35.847 81.43-80.912 81.43-45.068 0-80.916-36.36-80.916-80.915 0-44.556 36.872-81.425 80.916-81.425zm0 0"
                  />
                </svg>
              </router-link>
              <div class="information">
                <!-- 文章标题展示 -->
                <router-link
                  :to="'/articleDetails/'+item.id"
                  class="title"
                  :title="item.title"
                  target="_self"
                  rel="noopener noreferrer"
                >
                  <span class="badge" style="display: none">置顶</span>{{ item.title }}
                </router-link>
                <!-- 文章摘要展示 -->
                <router-link
                  class="abstract"
                  :to="'/articleDetails/'+item.id"
                  title="文章摘要"
                  target="_self"
                  rel="noopener noreferrer"
                >
                  <!--                  {{ vHtml(item) }}-->
                  {{ item.content }}
                  <!--                      <div v-html="item.html" />-->
                </router-link>
                <!-- 文章信息展示 -->
                <div class="meta">
                  <ul class="items">
                    <li>{{ myDateFormat(item.updateTime) }}</li>
                    <li>{{ item.read }} 阅读</li>
                    <!--                    <li>{{ item.collectNum }} 收藏</li>-->
                    <li>{{ item.love }} 点赞</li>
                    <li>{{ item.status == 1 ? '公开' : '私密' }}</li>
                  </ul>
                  <div class="last" style="display: flex">
                    <svg
                      class="icon"
                      viewBox="0 0 1024 1024"
                      xmlns="http://www.w3.org/2000/svg"
                      width="15"
                      height="15"
                    >
                      <path
                        d="M512.2 564.743a76.818 76.818 0 0 1-30.973-6.508L108.224 393.877c-26.105-11.508-42.56-35.755-42.927-63.272-.384-27.44 15.356-52.053 41.042-64.232l373.004-176.74c20.591-9.737 45.16-9.755 65.75.017L917.68 266.39c25.668 12.188 41.39 36.792 41.024 64.231-.384 27.5-16.821 51.73-42.908 63.237l-372.57 164.377a77.18 77.18 0 0 1-31.025 6.508zM139.843 329.592l370.213 163.241c1.291.56 3.018.567 4.345-.009l369.758-163.128-369.706-175.464v-.01c-1.326-.628-3.158-.636-4.502 0l-370.108 175.37zm748.015 1.858h.175-.175zM512.376 941.674c-10.348 0-20.8-2.32-30.537-6.997L121.05 778.624c-18.113-7.834-26.454-28.87-18.62-46.983 7.835-18.112 28.862-26.488 46.993-18.61l362.08 156.629 345.26-156.366c17.939-8.166 39.14-.253 47.324 17.746 8.166 17.964.227 39.157-17.729 47.324l-344.51 156.61c-9.196 4.449-19.281 6.7-29.471 6.7z"
                        fill="#444"
                      />
                      <path
                        d="M871.563 515.449L511.81 671.775 152.358 515.787v73.578a34.248 34.248 0 0 0 20.76 31.48l301.518 129.19c11.806 5.703 24.499 8.546 37.175 8.546s25.367-2.843 37.174-8.546L850.82 620.534a34.248 34.248 0 0 0 20.744-31.474V515.45z"
                        fill="#ff6a18"
                      />
                    </svg>
                    <a
                      class="link"
                      target="_self"
                      rel="noopener noreferrer"
                      :href="'/articleSortList/'+item.type.id"
                    >
                      {{ item.type.name }}
                    </a>
                  </div>
                </div>
              </div>
            </li>
          </template>
        </ul>
        <!-- 文章还未加载出来时通过空骨架进行预展示 -->
        <ul v-else class="joe_list__loading">
          <li class="item">
            <div class="thumbnail" />
            <div class="information">
              <div class="title" />
              <div class="abstract">
                <p />
                <p />
              </div>
            </div>
          </li>
          <li class="item">
            <div class="thumbnail" />
            <div class="information">
              <div class="title" />
              <div class="abstract">
                <p />
                <p />
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <div v-show="this.pagination.total !== listData.length" class="joe_load" @click="doSeeMore">
      {{ !loading ? '查看更多' : 'loading...' }}
    </div>
  </div>
</template>

<script>

import { getAction, getFileAccessHttpUrl } from '@/api/manage'
import { ellipsis, filterObj } from '@/utils/index'
import moment from 'moment'

export default {
  name: 'AntsContainer',
  data() {
    return {
      url: {
        list: '/articleView/getArticleList',
        recommend: '/articleView/queryIsTopArticle'
      },
      latestArticleCss: 'item active',
      popularArticlesCss: 'item',
      listData: [],
      /* 分页参数 */
      pagination: {
        current: 1,
        pageSize: 6,
        pageSizeOptions: ['10', '20', '30'],
        showTotal: (total, range) => {
          return range[0] + '-' + range[1] + ' 共' + total + '条'
        },
        showQuickJumper: true,
        showSizeChanger: true,
        total: 0
      },
      /* 排序参数 */
      sorter: {
        column: 'publishTime',
        order: 'desc'
      },
      loading: false,
      noTitleKey: '1',
      recommendData: []
    }
  },
  created() {
    /* this.joeIndexTitleTitle('1')
    this.getRecommend() */
  },
  methods: {
    joeIndexTitleTitle(id) {
      this.noTitleKey = id
      this.pagination.current = 1
      if (id === '1') {
        this.sorter.column = 'publishTime'
        this.latestArticleCss = 'item active'
        this.popularArticlesCss = 'item'
      } else if (id === '2') {
        this.sorter.column = 'viewsNum'
        this.latestArticleCss = 'item'
        this.popularArticlesCss = 'item active'
      }
      this.listData = []
      this.loading = true
      this.getArticleList(id)
    },
    getArticleList(type) {
      var params = this.getQueryParams()// 查询条件
      params.type = type
      // params.name="MXF"
      // console.log("初始查询条件：" + params.toString())
      getAction(this.url.list, params).then(res => {
        if (res.success) {
          this.pagination.total = res.result.total
          /* console.log(res)
          console.log(res.result) */
          res.result.forEach(e => {
            // console.log(e)
            this.listData.push(e)
          })
          this.loading = false
        }
      }).finally(() => {
        this.loading = false
      })
    },
    getRecommend() {
      getAction(this.url.recommend, {}).then(res => {
        if (res.success) {
          this.recommendData = res.result
        }
      })
    },
    getQueryParams() {
      // 获取查询条件
      const param = Object.assign(this.sorter)
      param.pageNo = this.pagination.current
      param.pageSize = this.pagination.pageSize
      return filterObj(param)
    },
    /* 图片预览 */
    getImgView(text) {
      if (text && text.indexOf(',') > 0) {
        text = text.substring(0, text.indexOf(','))
      }
      return getFileAccessHttpUrl(text)
    },
    vHtml(item) {
      return ellipsis(item.html.replace(/(<([^>]+)>)/ig, ''), 300)
    },
    doSeeMore() {
      this.pagination.current += 1
      this.getArticleList(this.noTitleKey)
    },
    myDateFormat(date) {
      return moment(date).format('YYYY-MM-DD hh:mm:ss')
    }
  }

}
</script>

