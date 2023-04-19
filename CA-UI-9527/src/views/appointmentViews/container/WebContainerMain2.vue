<template>
  <div class="joe_main">
    <div class="joe_index">
      <div class="joe_index__banner"><!-- style="text-align: center"-->
        <!-- 顶部轮播图 -->
        <!--        <div class="swiper-container swiper-container-initialized swiper-container-vertical">
          <div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(0px, 0px, 0px);">
            <el-carousel :interval="5000" arrow="always">
              <template v-for="(item,index) in extensionData">
                <el-carousel-item :key="index">
                  <div
                    class="swiper-slide swiper-slide-duplicate swiper-slide-active"
                    data-swiper-slide-index="2"
                    style="height: 335px;"
                  >
                    <a
                      class="item"
                      :href="item.url"
                      target="_blank"
                      rel="noopener noreferrer nofollow"
                    >
                      <img
                        width="100%"
                        height="100%"
                        class="thumbnail lazyloaded"
                        style="border-radius: 4px"
                        :src="item.imgUrl"
                        :data-src="item.imgUrl"
                        :alt="item.title"
                      >
                      <div class="title" style="border-radius: 4px"> {{ item.title }}</div>
                      <svg
                        class="icon"
                        viewBox="0 0 1026 1024"
                        xmlns="http://www.w3.org/2000/svg"
                        width="18"
                        height="18"
                      >
                        <path
                          d="M784.3 1007.961a33.2 33.2 0 0 1-27.106-9.062L540.669 854.55 431.766 962.813c-9.062 9.062-36.168 18.044-45.23 9.062a49.72 49.72 0 0 1-27.106-45.23V727.763a33.2 33.2 0 0 1 9.463-27.106l343.071-370.578a44.748 44.748 0 0 1 63.274 63.274l-334.17 361.515v72.175l63.273-54.211a42.583 42.583 0 0 1 54.212-9.062l198.64 126.386L910.847 140.34 151.647 510.837 323.343 619.34c18.044 9.062 27.106 45.23 9.062 63.273-9.062 18.044-45.23 27.106-63.273 18.044L34.082 547.005c-8.981-8.982-18.043-17.723-18.043-36.168s9.062-27.105 27.105-36.167l903.79-451.815c18.043-9.062 36.167-9.062 45.229 0 18.284 9.223 18.284 27.106 18.284 45.15L829.69 971.794c0 18.043-9.062 27.105-27.105 36.167z"
                        />
                      </svg>
                    </a>
                  </div>
                </el-carousel-item>
              </template>

            </el-carousel>
          </div>
        </div>-->
        <div class="filter-container" style="text-align: center; width: 100%"> <!---->
          <el-row>
            <el-col :span="24">
              <el-input
                v-model="listQuery.roomName"
                placeholder="会议室名称"
                style="width: 400px;border-radius: 3px"
                class="search"
                prefix-icon="el-icon-search"
              /><!--@keyup.enter.native="handleFilter"-->

              <!--              <el-select v-model="listQuery.type" placeholder="会议室类型" clearable class="filter-item" style="width: 130px">
                &lt;!&ndash;            <el-option v-for="item in roomTypes" :key="item.id" :label="item.roomTypeName" :value="item.id" />&ndash;&gt;
              </el-select>-->
              <el-button plain :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-search" @click="conditionSearch"><!-- @click="handleFilter"-->
                搜索
              </el-button>
              <el-button plain :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-refresh" @click="clearFilter">
                清除过滤条件
              </el-button>
            </el-col>
          </el-row>
          <el-form style="text-align: left;margin-left: 17%">
            <el-form-item label="会议室类型">
              <!--              <el-checkbox-group v-model="checkedType">-->
              <el-select v-model="listQuery.type" placeholder="选择类型" clearable class="filter-item" style="width: 130px" @change="conditionSearch">
                <el-option v-for="item in types" :key="item.id" :label="item.roomTypeName" :value="item.id" :title="formatArea(item.roomArea, item.roomTypeCapacity)" />
              </el-select>
<!--              <el-radio-group v-model="listQuery.type">
                <el-radio-button v-for="type in types" :label="type.id" :title="formatArea(type.roomArea)">{{ type.roomTypeName }}</el-radio-button>
              </el-radio-group>-->
            </el-form-item>
            <el-form-item label="排序方式">
              <el-radio-group v-model="listQuery.sortType" @change="conditionSearch">
                <el-radio-button label="1" title="按面积排序">面积</el-radio-button>
                <el-radio-button label="2" title="按累计服务人数排序">人数</el-radio-button>
                <el-radio-button label="3" title="按平均评分排序">评分</el-radio-button>
                <el-radio-button label="4" title="按累计预约次数排序">预约量</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-form>
        </div>
      </div>

      <div class="joe_index__hot">
        <ul class="joe_index__hot-list" style="margin-bottom: 0">
          <li v-for="item in hotArticleData" class="item">
            <router-link class="link" :to="'/articleDetails/'+item.id" :title="item.title">
              <figure class="inner">
                <span class="views"> {{ item.viewsNum }}  <svg-icon icon-class="hot" /> </span>
                <img
                  width="100%"
                  height="120"
                  class="image lazyloaded"
                  :src="item.cover"
                  :alt="item.title"
                >
                <figcaption class="title">{{ item.title }}</figcaption>
              </figure>
            </router-link>
          </li>
        </ul>
      </div>

      <!--   数据展示部分   -->
      <div class="joe_index__title" style="margin-top: -1em" />
      <div class="joe_index__list" data-wow="fadeIn">
        <el-button-group>
          <el-button type="primary" icon="el-icon-arrow-left" style="background-color:rgba(24, 144, 255,0.7);" @click="patition(0)">上一页</el-button>
          <el-button type="primary" style="background-color:rgba(24, 144, 255,0.7);"  @click="patition(1)">下一页<i class="el-icon-arrow-right el-icon--right"/></el-button>
        </el-button-group>
        <ul v-if="!loading" class="joe_list">
          <template v-for="(item,index) in roomDatas">
            <li class="joe_list__item wow default" style="visibility: visible; animation-name: fadeIn;">
              <div class="line" />
              <router-link
                :to="'/roomDetails/' + item.id"
                class="thumbnail"
                target="_self"
                rel="noopener noreferrer"
              >   <!-- :title="item.roomType.roomTypeDesc" -->
                <img
                  width="100%"
                  height="100%"
                  class=" ls-is-cached lazyloaded"
                  :src="item.roomType.roomCover"
                  :alt="item.roomType.roomTypeName"
                >
                <!--                <time :datetime="item.roomType.roomTypeCreateTime">{{ item.roomType.roomTypeCreateTime }}</time>-->
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
                <router-link
                  :to="'/roomDetails/' + item.id"
                  class="title"
                  target="_self"
                  rel="noopener noreferrer"
                >
                  <span class="badge" style="display: none">置顶</span>{{ item.roomName }}
                </router-link>
                <router-link
                  :to="'/roomDetails/' + item.id"
                  target="_self"
                  class="abstractm"
                  rel="noopener noreferrer"
                >
                  <p>累计预约次数: {{ statistic[(listQuery.pageNo - 1) * listQuery.pageSize + index].appointmentCount }}</p>
                  <p>
                    平均评分:
                    <el-rate
                      v-model="statistic[(listQuery.pageNo - 1) * listQuery.pageSize + index].averageScore"
                      disabled
                      show-score
                      text-color="#ff9900"
                      score-template="{value}"
                    />
                  </p>
                </router-link>
                <div class="meta">
                  <ul class="items">
                    <li>{{ item.roomType.roomArea }} 平方</li>
                    <li>{{ item.roomType.roomTypeCapacity }} 人</li>
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
                      :href="'/articleSortList/' + item.roomType.id"
                      :title="item.roomType.roomTypeDesc"
                    >
                      {{ item.roomType.roomTypeName }}
                    </a>
                  </div>
                </div>
              </div>
            </li>
          </template>

        </ul>
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
    <!--    <div v-show="this.roomDatas.total !== listData.length" class="joe_load" @click="doSeeMore">
      {{ !loading ? '查看更多' : 'loading...' }}
    </div>-->
  </div>
</template>

<script>

import { queryCaCount, queryConferenceRoomByCondition, queryConferenceRoomType } from '@/api/conference_room'
import { queryAllAppointInfo } from '@/api/appointment'
import { warning } from '@/utils/message'

export default {
  name: 'AntsContainer',
  data() {
    return {
      roomDatas: {},
      statistic: [{ 'appointmentCount': '' , 'averageScore': 0}],
      score: 4.4,
      appointmentCount: 10,
      latestArticleCss: 'item active',
      popularArticlesCss: 'item',
      listData: [],
      listQuery: {
        roomName: undefined,
        type: undefined,
        pageNo: 1,
        pageSize: 10,
        sortType: undefined // {1: 按面积, 2: 按人数, 3: 按评分, 4: 按预约次数}
      },
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
      recommendData: [],
      hotArticleData: [],
      extensionData: [],
      downloadLoading: false,
      checkedType: [],
      types: ['课堂式', '宴会式', 'U形'],
      maxPageSize: undefined
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      /* allRoom().then(response => {
        this.roomDatas = response.data.data
        // console.log('roomDatas: ', this.roomDatas)
      }) */
      queryConferenceRoomByCondition(this.listQuery).then(response => {
        this.roomDatas = response.data.data
      }).catch(e => {
        warning('初始化异常，请重试或稍后访问')
      })
      queryAllAppointInfo().then(response => {
        this.statistic = response.data.data
      })
      queryConferenceRoomType().then(response => {
        this.types = response.data.data
      })
      queryCaCount().then(response => {
        // console.log(response.data.data)
        // Math.ceil(num) 如果num带有小数，则结果+1
        this.maxPageSize = Math.ceil(response.data.data / this.listQuery.pageSize)
        /* if (response.data.data % this.listQuery.pageSize > 0) {
          this.maxPageSize += 1
        } */
        // console.log('一共有: ', this.maxPageSize)
      })
    },
    conditionSearch() {
      this.downloadLoading = true
      // console.log('查询条件', this.listQuery)
      queryConferenceRoomByCondition(this.listQuery).then(response => {
        this.roomDatas = response.data.data
      }).catch(e => {
        warning('查询条件出错，请重试')
      })
      this.downloadLoading = false
    },
    clearFilter() {
      this.listQuery = {
        roomName: undefined,
        type: undefined,
        pageNo: 1,
        pageSize: 10,
        sortType: undefined // {1: 按面积, 2: 按人数, 3: 按评分, 4: 按预约次数}
      }
      this.conditionSearch()
    },
    formatArea(name, people) {
      return '面积约为: ' + name + '平方 能容纳: ' + people + '人'
    },
    patition(opretor) { // 0: 前一页, 1: 后一页
      if (opretor === 0 && this.listQuery.pageNo <= 1) {
        warning('当前是第一页')
      } else if (opretor === 1 && this.listQuery.pageNo >= this.maxPageSize) {
        warning('已经到最后一页')
      } else {
        if (opretor === 0) {
          this.listQuery.pageNo -= 1
        } else {
          this.listQuery.pageNo += 1
        }
        this.conditionSearch()
      }
    }
  }

}
</script>
<style scoped>
.el-carousel__container {
  height: 335px;
}
.search {
  border-radius: 3px;
  display: inline-block;
  vertical-align: middle;
  margin-bottom: 10px;
}
/deep/ .el-input__inner{   /*或者 .s2>>>.el-input__inner  */
  border-radius: 10px;    /*输入框圆角值*/
}
</style>

<style lang="less">

</style>
