<template>
  <div class="joe_main">
    <div class="joe_archive">
      <div class="joe_archive__title">
        <svg width="20" height="20" class="joe_archive__title-icon" xmlns="http://www.w3.org/2000/svg"
             viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
             stroke-linejoin="round">
          <path d="M20.24 12.24a6 6 0 0 0-8.49-8.49L5 10.5V19h8.5zM16 8L2 22M17.5 15H9"></path>
        </svg>
        <div class="joe_archive__title-title">
          <span>搜索到</span>
          <span class="muted">{{ this.pagination.total }}</span>
          <span>篇与</span>
          <span class="muted ellipsis">{{ articleLable.name }}</span>
          <span>的结果</span>
        </div>
      </div>

      <ul class="joe_archive__list joe_list" data-wow="fadeIn">
        <template v-for="item in listData">
          <li class="joe_list__item wow default" style="visibility: visible;">
            <div class="line"></div>
            <router-link :to="'/articleDetails/'+item.id" class="thumbnail"
               :title="item.title" target="_self" rel="noopener noreferrer">
              <img width="100%" height="100%" class=" ls-is-cached lazyloaded"
                   :src="getImgView(item.cover)"
                   :data-src="getImgView(item.cover)"
                   :alt="item.title">
              <time :datetime="item.createTime">{{ item.createTime }}</time>
              <svg viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="20" height="20">
                <path
                  d="M903.93 107.306H115.787c-51.213 0-93.204 42.505-93.204 93.72V825.29c0 51.724 41.99 93.717 93.717 93.717h788.144c51.72 0 93.717-41.993 93.717-93.717V201.025c-.512-51.214-43.017-93.719-94.23-93.719zm-788.144 64.527h788.657c16.385 0 29.704 13.316 29.704 29.704v390.229L760.54 402.285c-12.805-13.828-30.217-21.508-48.14-19.971-17.924 1.02-34.821 10.754-46.602 26.114l-172.582 239.16-87.06-85.52c-12.29-11.783-27.654-17.924-44.039-17.924-16.39.508-31.755 7.676-43.53 20.48L86.595 821.705V202.05c-1.025-17.416 12.804-30.73 29.191-30.217zm788.145 683.674H141.906l222.255-245.82 87.06 86.037c12.8 12.807 30.212 18.95 47.115 17.417 17.41-1.538 33.797-11.266 45.063-26.118l172.584-238.647 216.111 236.088 2.051-1.54V825.8c.509 16.39-13.315 29.706-30.214 29.706zm0 0"></path>
                <path
                  d="M318.072 509.827c79.89 0 144.417-65.037 144.417-144.416 0-79.378-64.527-144.925-144.417-144.925-79.891 0-144.416 64.527-144.416 144.412 0 79.892 64.525 144.93 144.416 144.93zm0-225.327c44.553 0 80.912 36.362 80.912 80.91 0 44.557-35.847 81.43-80.912 81.43-45.068 0-80.916-36.36-80.916-80.915 0-44.556 36.872-81.425 80.916-81.425zm0 0"></path>
              </svg>
            </router-link>
            <div class="information">
              <router-link :to="'/articleDetails/'+item.id" class="title"
                 :title="item.title" target="_self" rel="noopener noreferrer">
                {{ item.title }}
              </router-link>
              <router-link class="abstract" :to="'/articleDetails/'+item.id" title="文章摘要" target="_self"
                 rel="noopener noreferrer">
                {{ vHtml(item) }}
              </router-link>
              <div class="meta">
                <ul class="items">
                  <li>{{ item.createTime }}</li>
                  <li>{{ item.viewsNum }} 阅读</li>
                  <li>{{ item.collectNum }} 收藏</li>
                  <li>{{ item.likesNum }} 点赞</li>
                  <li>{{ item.publishForm_dictText }}</li>
                </ul>
              </div>
            </div>
          </li>
        </template>

      </ul>
    </div>
    <div class="joe_load" @click="doSeeMore" v-show="this.pagination.total !== listData.length">
      查看更多
    </div>
  </div>
</template>

<script>
import {getAction, getFileAccessHttpUrl} from "@/api/manage";
import {ellipsis, filterObj} from "@/utils/util";

export default {
  name: "AntsContainerLable",
  data() {
    return {
      id: this.$route.params.id,
      url: {
        list: '/articleView/getArticleByLableId',
        getArticleLableById: '/articleView/getArticleLableById',
      },
      listData: [],
      articleLable: {},
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
      loading: false,
      /* 排序参数 */
      sorter: {
        column: 'createTime',
        order: 'desc'
      },
      /* 查询条件-请不要在queryParam中声明非字符串值的属性 */
      queryParam: {}
    }
  },
  created() {
    this.loadData()
    this.getArticleLable()
  },
  methods: {
    getArticleLable() {
      getAction(this.url.getArticleLableById, { id: this.id }).then(res => {
        if (res.success) {
          this.articleLable = res.result
        }
      })
    },
    loadData(arg) {
      if (!this.url.list) {
        this.$message.error('请设置url.list属性!')
        return
      }
      // 加载数据 若传入参数1则加载第一页的内容
      if (arg === 1) {
        this.pagination.current = 1
      }
      var params = this.getQueryParams()// 查询条件
      this.loading = true
      getAction(this.url.list, params).then((res) => {
        if (res.success) {
          res.result.records.forEach(e => {
            this.listData.push(e)
          })
          this.pagination.total = res.result.total
        }
        if (res.code === 510) {
          this.$message.warning(res.message)
        }
        this.loading = false
      })
    },
    changeSelect(value) {
      this.listData = []
      this.sorter.column = value
      this.loadData()
    },
    getQueryParams() {
      // 获取查询条件
      const param = Object.assign(this.queryParam, this.sorter, this.filters)
      param.id = this.id
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
      this.loadData()
    }
  }
}
</script>

<style scoped>

</style>
