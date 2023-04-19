<template>
  <!--  <header class="joe_header current active">-->
  <header class="joe_header active">
    <div class="joe_header__above">
      <div class="joe_container">
        <svg
          class="joe_header__above-slideicon"
          viewBox="0 0 1152 1024"
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          @click="joeHeaderSlideout('joe_header__slideout active','joe_header__mask active slideout')"
        >
          <path
            d="M76.032 872a59.968 59.968 0 1 0 0 120h999.936a59.968 59.968 0 1 0 0-120H76.032zm16-420.032a59.968 59.968 0 1 0 0 120h599.936a59.968 59.968 0 1 0 0-119.936H92.032zM76.032 32a59.968 59.968 0 1 0 0 120h999.936a60.032 60.032 0 0 0 0-120H76.032z"
          />
        </svg>
        <router-link title="M-Blog" class="joe_header__above-logo" to="/index">
          <img class="ls-is-cached lazyloaded" src="@/assets/M_Logo.png" alt="M-Blog">
        </router-link>
        <nav class="joe_header__above-nav">
          <router-link
            to="/webHome"
            :class="key() === '/webHome' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="articleHome" />
            首 页
          </router-link>
          <router-link
            to="/articleSort"
            :class="key() === '/articleSort' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="articleSort" />
            分 类
          </router-link>
          <!--          <router-link
            to="/articleArchive"
            :class="key() === '/articleArchive' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="articleArchive"/>
            归 档
          </router-link>-->
          <!--          <router-link
            to="/navigation"
            :class="key() === '/navigation' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="friendLinks"/>
            导 航
          </router-link>-->
<!--          <router-link
            to="/meiTu"
            :class="key() === '/meiTu' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="meiTu" />
            壁 纸
          </router-link>-->
          <router-link
            to="/articleAboutMe"
            :class="key() === '/articleAboutMe' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="articleAboutMe" />
            关 于
          </router-link>
          <router-link
            to="/leavingMessage"
            :class="key() === '/leavingMessage' ? 'item active' : 'item'"
          >
            <svg-icon icon-class="articleAboutMe" />
            留 言
          </router-link>
        </nav>
        <div class="joe_header__above-search">
          <input
            v-model="globalSearchValue"
            maxlength="16"
            autocomplete="off"
            placeholder="请输入关键字..."
            name="s"
            value=""
            class="input"
            type="text"
            @blur="initSearchRecommend('result')"
            @focus="initSearchRecommend('result active')"
          >
          <button v-if="!globalSearchValue" class="submit" @click="searchTips">
            Search
          </button>
          <router-link v-else :to="'/globalSearch/'+globalSearchValue">
            <button class="submit">
              Search
            </button>
          </router-link>
          <span class="icon" />
<!--          <nav :class="searchRecommendCss">
            <template v-for="(top5, index) in articleTop5Data">
              <router-link :to="'/articleDetails/'+top5.id" :title="top5.title" class="item">
                <span class="sort">{{ index + 1 }}</span>
                <span class="text">{{ top5.title }}</span>
                <span class="views">{{ top5.viewsNum }} 阅读</span>
              </router-link>
            </template>

          </nav>-->
        </div>
        <svg
          class="joe_header__above-searchicon"
          viewBox="0 0 1024 1024"
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          @click="showHeaderSearchout"
        >
          <path
            d="M1008.19 932.031L771.72 695.56a431.153 431.153 0 1 0-76.158 76.158l236.408 236.472a53.758 53.758 0 0 0 76.158 0 53.758 53.758 0 0 0 0-76.158zM107.807 431.185a323.637 323.637 0 0 1 323.316-323.381 323.7 323.7 0 0 1 323.381 323.38 323.637 323.637 0 0 1-323.38 323.317 323.637 323.637 0 0 1-323.317-323.316z"
          />
        </svg>
      </div>
    </div>

    <div class="joe_header__below" style="border-top: 1px solid #ebeef5">
      <div class="joe_container">
        <!--        <nav class="joe_header__below-class">
          <template v-for="item in sortTop5Data">
            <a class="item" :href="'/articleSortList/'+item.id" :title="item.name">{{ item.name }}</a>
          </template>
        </nav>-->
        <div class="joe_header__below-sign">
          <div class="item">
            <svg
              v-if="!showLoginBtn()"
              class="icon"
              viewBox="0 0 1024 1024"
              xmlns="http://www.w3.org/2000/svg"
              width="15"
              height="15"
            >
              <path
                d="M710.698 299a213.572 213.572 0 1 0-213.572 213.954A213.572 213.572 0 0 0 710.698 299zm85.429 0a299.382 299.382 0 1 1-299-299 299 299 0 0 1 299 299z"
              />
              <path
                d="M114.223 1024a46.91 46.91 0 0 1-46.91-46.91 465.281 465.281 0 0 1 468.332-460.704 475.197 475.197 0 0 1 228.827 58.35 46.91 46.91 0 1 1-45.384 82.378 381.378 381.378 0 0 0-183.443-46.909 371.08 371.08 0 0 0-374.131 366.886A47.29 47.29 0 0 1 114.223 1024zM944.483 755.129a38.138 38.138 0 0 0-58.733 0l-146.449 152.55-92.675-91.53a38.138 38.138 0 0 0-58.732 0 43.858 43.858 0 0 0 0 61.402l117.083 122.422a14.492 14.492 0 0 0 8.39 4.577c4.196 0 4.196 4.195 8.39 4.195h32.037c4.195 0 4.195-4.195 8.39-4.195s4.195-4.577 8.39-4.577L946.39 816.15a48.054 48.054 0 0 0-1.906-61.02z"
              />
              <path d="M763.328 776.104L730.53 744.45a79.708 79.708 0 0 0 32.798 31.654" />
            </svg>
            <a v-if="!showLoginBtn()" rel="noopener noreferrer nofollow" @click="login">登录</a>
            <router-link v-if="showLoginBtn()" to="/userHome" title="个人中心">
              <!--              个人中心-->
              <svg t="1675849046300" class="icon" style="height: 2em;width: 2em" viewBox="0 0 1025 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11961" width="200" height="200"><path d="M690.366075 350.568358c0-98.876614-79.937349-179.048571-178.558027-179.048571-98.59935 0-178.515371 80.150629-178.515371 179.048571 0 98.833958 79.916021 178.963259 178.515371 178.963259C610.428726 529.531617 690.366075 449.380988 690.366075 350.568358M376.140632 350.568358c0-75.159877 60.72082-136.072649 135.667416-136.072649 74.989253 0 135.667416 60.912772 135.667416 136.072649 0 75.117221-60.678164 136.029993-135.667416 136.029993C436.861451 486.577022 376.140632 425.664251 376.140632 350.568358M197.284012 762.923936 197.284012 778.472049l15.526785 0 291.255186 0.127968L819.784387 778.472049l15.569441 0 0-15.548113c0-139.783721-136.413897-285.581938-311.026243-273.275681-10.002833 0.703824-24.740482 9.128385-34.658002 9.938849-8.573857 0.74648 13.692577 8.232609 14.396401 16.827793 9.021745-0.789136 6.313088 13.095393 15.505457 13.095393 150.597017 0 263.14488 103.07823 263.14488 224.62651l15.441473-15.590769-285.816546-0.042656-278.991585 1.81288 15.526785 15.612097c0-82.752645 75.095893-152.70849 136.861785-191.824044 7.25152-4.58552 8.659169-17.659585 4.862784-22.906273-6.846288-9.426977-19.877697-8.701825-28.046322-6.014496C285.262018 560.521203 197.284012 667.758394 197.284012 762.923936" fill="#1296db" p-id="11962" /><path d="M512.31992 1.535616c-282.766642 0-512.021328 228.89211-512.021328 511.210864 0 282.46805 229.254686 511.25352 512.021328 511.25352 117.431975 0 228.828126-39.606098 318.810964-111.204199 10.791969-8.488545 12.540865-24.22861 3.988336-34.99925-8.616513-10.770641-24.356578-12.540865-35.127218-3.94568-81.174373 64.538532-181.586603 100.241606-287.650754 100.241606-255.210864 0-462.028493-206.561693-462.028493-461.367325 0-254.762976 206.817629-461.303341 462.028493-461.303341 255.210864 0 462.092477 206.561693 462.092477 461.303341 0 87.380821-24.33525 171.093227-69.614596 243.651087-7.272848 11.645089-3.668416 27.086562 8.040657 34.35941 11.709073 7.272848 27.10789 3.62576 34.402066-7.976672 50.184787-80.406565 77.143381-173.247355 77.143381-270.055153C1024.383904 230.427726 795.10789 1.535616 512.31992 1.535616z" fill="#1296db" p-id="11963" /></svg>
            </router-link>
            <router-link  v-if="showManagement()" to="/dashboard" title="管理后台" style="margin-left: 10px">
              <svg t="1675908331624" class="icon" style="height: 2em;width: 2em" viewBox="0 0 1122 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4024" width="200" height="200"><path d="M1081.663368 571.919244a95.842577 95.842577 0 0 1 32.230784-71.684664 26.03956 26.03956 0 0 0 5.341449-32.473577l-44.249043-76.297733a25.614672 25.614672 0 0 0-29.92425-11.59337 99.302379 99.302379 0 0 1-30.349138 4.673767 97.784922 97.784922 0 0 1-95.903275-76.297733 25.675371 25.675371 0 0 0-25.068388-20.333922H806.153895a25.675371 25.675371 0 0 0-25.068387 20.333922 97.784922 97.784922 0 0 1-95.903276 76.297733 99.302379 99.302379 0 0 1-30.349138-4.673767 25.553974 25.553974 0 0 0-29.56006 11.59337L580.902595 467.457511a26.100258 26.100258 0 0 0 5.341448 32.534276 95.781879 95.781879 0 0 1 0 143.369327 26.03956 26.03956 0 0 0-5.341448 32.473577l44.370439 76.540526a25.553974 25.553974 0 0 0 29.92425 11.59337 99.302379 99.302379 0 0 1 30.349138-4.673767 97.784922 97.784922 0 0 1 95.903275 76.297733 25.675371 25.675371 0 0 0 24.704198 20.333922h87.769707a25.675371 25.675371 0 0 0 25.068388-20.333922 97.784922 97.784922 0 0 1 95.903275-76.297733 99.302379 99.302379 0 0 1 30.349138 4.673767 25.614672 25.614672 0 0 0 29.92425-11.59337l44.249043-76.237034a26.100258 26.100258 0 0 0-5.341449-32.534276 95.781879 95.781879 0 0 1-32.412879-71.684663z m-17.723896 86.434344l-30.713328 52.200517a132.079448 132.079448 0 0 0-78.907758 6.616112 128.983836 128.983836 0 0 0-41.032034 26.889336 126.00962 126.00962 0 0 0-32.412879 52.44331H819.507516a126.00962 126.00962 0 0 0-32.412879-52.382612 128.923137 128.923137 0 0 0-41.032034-26.950034 132.018749 132.018749 0 0 0-78.907759-6.616112l-30.713327-52.139819a126.980792 126.980792 0 0 0 16.631327-21.669284A124.552861 124.552861 0 0 0 636.198724 485.666994l30.713327-52.200517a132.079448 132.079448 0 0 0 78.907758-6.616112 128.983836 128.983836 0 0 0 41.032035-26.889336 126.00962 126.00962 0 0 0 32.655672-52.564707h61.305258a126.00962 126.00962 0 0 0 32.412879 52.382612 128.741042 128.741042 0 0 0 41.032035 26.950034 132.079448 132.079448 0 0 0 78.907758 6.616112l30.713327 52.321914a127.466379 127.466379 0 0 0-16.631327 21.669284 124.552861 124.552861 0 0 0 16.631327 151.01731z" fill="#666666" p-id="4025"></path><path d="M849.856654 464.361899a113.627172 113.627172 0 1 0 113.566473 113.566474A113.627172 113.627172 0 0 0 849.856654 464.361899z m47.769543 160.911129a67.314388 67.314388 0 1 1 19.726939-47.587448 66.768103 66.768103 0 0 1-19.726939 47.587448z" fill="#666666" p-id="4026"></path><path d="M66.424011 66.424005V757.595269h466.709041c27.253526 0 45.463008 6.069828 45.463008 33.141258s-12.139655 33.141258-39.332482 33.141258H45.543804A42.913681 42.913681 0 0 1 0.080796 778.536174V45.543798A42.913681 42.913681 0 0 1 45.543804 0.080789h993.630771c27.253526 0 39.332483 18.209483 39.332483 45.463009v233.324171c0 27.253526-6.069828 45.463008-33.141259 45.463009s-33.141258-27.253526-33.141258-45.463009v-212.443964zM339.323458 1024c-27.253526 0-45.463008-12.139655-45.463008-39.393181s18.209483-39.393181 45.463008-39.393181h408.984981c27.253526 0 45.463008 12.139655 45.463008 39.393181s-18.209483 39.393181-45.463008 39.393181z" fill="#696969" p-id="4027"></path><path d="M180.961657 362.874382l-30.349138-38.786198 172.018913-108.225025 141.973267 91.836491 146.647033-94.871405-0.849775 53.839371L464.665397 363.056477 322.752829 271.159288 180.961657 362.874382zM180.961657 600.811623l-30.349138-38.846897 172.018913-108.225025 141.973267 91.897189 2.063741 1.213966-1.274664 54.567749-142.640947-92.322077L180.961657 600.811623z" fill="#666666" p-id="4028"></path></svg>
            </router-link>
            <a v-if="showLoginBtn()" style="margin-left: 10px" title="退出登录" @click="logout">
              <!--              退 出-->
              <svg t="1675849226967" class="icon" style="height: 2em;width: 2em" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="13392" width="200" height="200"><path d="M948.7 492.6L811.5 321.2c-5.7-5.7-17.1-11.4-28.6-11.4h-11.4c-11.4 5.7-22.9 17.1-22.9 34.3V447H474.2c-40 0-68.6 28.6-68.6 68.6s28.6 68.6 68.6 68.6h274.4V687c0 17.1 11.4 28.6 22.9 34.3h11.4c11.4 0 22.9-5.7 28.6-11.4l137.2-171.5c5.7-11.5 5.7-28.6 0-45.8zM611.4 789.9H337c-40 0-68.6-28.6-68.6-68.6V304c0-40 28.6-68.6 68.6-68.6h274.4c40 0 68.6-28.6 68.6-68.6s-28.6-68.6-68.6-68.6h-343c-74.3 0-137.2 62.9-137.2 137.2v554.5c0 74.3 62.9 137.2 137.2 137.2h343c40 0 68.6-28.6 68.6-68.6 0-34.3-34.3-68.6-68.6-68.6z" fill="#00AA88" p-id="13393" /></svg>
              <!--
              <svg t="1675849200350" class="icon" style="height: 2em;width: 2em" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="13236" width="200" height="200"><path d="M898.5 541H369.6c-17.7 0-32-14.3-32-32s14.3-32 32-32h528.9c17.7 0 32 14.3 32 32s-14.3 32-32 32z" p-id="13237"></path><path d="M927.3 533.6c-8.2 0-16.4-3.1-22.6-9.4L712.4 331.8c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L949.9 479c12.5 12.5 12.5 32.8 0 45.3-6.2 6.2-14.4 9.3-22.6 9.3z" p-id="13238"></path><path d="M734.7 740.9c-8.2 0-16.4-3.1-22.6-9.4-12.5-12.5-12.5-32.8 0-45.3L904.5 494c12.5-12.5 32.8-12.5 45.3 0s12.5 32.8 0 45.3L757.3 731.5c-6.2 6.3-14.4 9.4-22.6 9.4z" p-id="13239"></path><path d="M614.1 955.9H246.7c-48.2 0-93.7-18.9-128-53.2-34.3-34.3-53.2-79.8-53.2-128V246c0-48.2 18.9-93.7 53.2-128 34.3-34.3 79.8-53.2 128-53.2h367.4c17.7 0 32 14.3 32 32s-14.3 32-32 32H246.7c-64.6 0-117.2 52.6-117.2 117.2v528.7c0 64.6 52.6 117.2 117.2 117.2h367.4c17.7 0 32 14.3 32 32s-14.3 32-32 32z" p-id="13240"></path></svg>            </a>
-->
            </a></div>

        </div>
      </div>
    </div>

  </header>
</template>

<script>
import { getToken, hasToken, removeToken } from '@/utils/auth'
import { success, warning } from '@/utils/message'
import jwt from 'jsonwebtoken'
export default {
  name: 'WebHeader',
  inject: ['reload'],
  data() {
    return {
      joeHeaderSlideoutCss: 'joe_header__slideout',
      joeHeaderMaskCss: 'joe_header__mask',
      searchRecommendCss: 'result',
      headerSearchoutCss: 'joe_header__searchout',
      sortTop5Data: [],
      statisticsTopNumData: {},
      linkPageFlag: true,
      linkSortFlag: true,
      sortStatisticsData: [],
      articleLableList: [],
      globalSearchValue: '',
      articleTop5Data: [],
      userInfo: {
        id: 1,
        username: ''
      }

    }
  },
  mounted() {
  },
  methods: {
    // 获取随机色
    getColor() {
      const r = parseInt(Math.random() * 256)
      const g = parseInt(Math.random() * 256)
      const b = parseInt(Math.random() * 256)
      const color = `rgba(${r},${g},${b},0.9)`
      return color
    },
    // 显示菜单栏
    joeHeaderSlideout(joeHeaderSlideoutCss, joeHeaderMaskCss) {
      this.joeHeaderSlideoutCss = joeHeaderSlideoutCss
      this.joeHeaderMaskCss = joeHeaderMaskCss
      this.initStatisticsTopNum()
    },
    // 隐藏遮罩层
    joeHeaderMask(joeHeaderSlideoutCss, joeHeaderMaskCss, headerSearchoutCss) {
      this.joeHeaderSlideoutCss = joeHeaderSlideoutCss
      this.joeHeaderMaskCss = joeHeaderMaskCss
      this.headerSearchoutCss = headerSearchoutCss
    },
    initSearchRecommend(css) {
      this.searchRecommendCss = css
    },
    key() {
      return this.$route.path
    },
    // 显示或隐藏手机端顶部搜索
    showHeaderSearchout() {
      if (this.headerSearchoutCss === 'joe_header__searchout') {
        this.initArticleLable()
        this.joeHeaderMaskCss = 'joe_header__mask active'
        this.headerSearchoutCss = 'joe_header__searchout active'
      } else {
        this.joeHeaderMaskCss = 'joe_header__mask'
        this.headerSearchoutCss = 'joe_header__searchout'
      }
    },
    searchTips() {
      this.$message.warning('请输入要搜索的内容！')
    },
    login() {
      this.$emit('toLogin')
    },
    showLoginBtn() {
      // console.log('是否登录: ', hasToken())
      // console.log(jwt.decode(getToken()))
      return hasToken()
    },
    showManagement() {
      if (!hasToken()) return false
      const { role } = jwt.decode(getToken())
      console.log('角色ID', role)
      return role !== '3'
    },
    logout() {
      this.$confirm('确认要退出登录吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 删除Token
        removeToken()
        this.reload()
        success('成功退出登录')
      }).catch(() => {
        warning('取消退出')
      })
    }
  }
}
</script>

<style scoped>

</style>
