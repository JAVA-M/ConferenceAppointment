<template>
  <div class="joe_container">

    <div class="joe_main">
      <!-- 个人主页 -->
      <!-- 基础统计 -->
      <div class="joe_census__basic_copy">
        <!-- 左侧 -->
        <div class="joe_census__basic-item" style="width: 100%">
          <section class="joe_aside__item author" style="width: 100%;">
            <img
              width="100%"
              height="120"
              class="image ls-is-cached lazyloaded"
              src="@/assets/user-background-img.jpg"
              alt="博主栏壁纸"
            >
            <div class="user" style="padding-bottom: 0">
              <img
                width="75"
                height="75"
                class="avatar ls-is-cached lazyloaded"
                :src="getImgView(personalHomeInfo.userAvatar)"
                alt="博主头像"
              >

              <span style="font-family: custom;font-size: 30px">
                {{ userInfo.username }}
              </span>
            </div>
            <div class="count" style="border-bottom: none; padding-bottom: 0">
              <div class="item" :title="userInfo.phone" style="border-right: 1px solid #ebeef5;">
                <svg t="1675326139952" class="icon my_icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3810" width="200" height="200"><path d="M676.2 64H347.8c-41.1 0-74.7 33.6-74.7 74.7v746.7c0 41.1 33.6 74.7 74.7 74.7h328.5c41.1 0 74.7-33.6 74.7-74.7V138.7c-0.1-41.1-33.7-74.7-74.8-74.7m-209 59.7h89.6c8.2 0 14.9 6.7 14.9 14.9s-6.7 14.9-14.9 14.9h-89.6c-8.2 0-14.9-6.7-14.9-14.9s6.7-14.9 14.9-14.9M512 930.1c-24.8 0-44.8-20-44.8-44.8 0-24.8 20-44.8 44.8-44.8 24.8 0 44.8 20 44.8 44.8 0 24.8-20 44.8-44.8 44.8m209.1-119.4H303V213.3h418.1v597.4z" fill="#1296db" p-id="3811" /></svg>
                <!--                <span class="num">{{ personalHomeInfo.collectNum }}</span>
                                <span>收藏量</span>-->
              </div>
              <div class="item" :title="userInfo.email" style="border-right: 1px solid #ebeef5;">
                <svg t="1675325504148" class="icon my_icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2736" width="200" height="200"><path d="M874.666667 375.189333V746.666667a64 64 0 0 1-64 64H213.333333a64 64 0 0 1-64-64V375.189333l64 54.250667V746.666667h597.333334V429.44l64-54.250667zM810.666667 213.333333a64.789333 64.789333 0 0 1 22.826666 4.181334 63.616 63.616 0 0 1 26.794667 19.413333 64.32 64.32 0 0 1 9.344 15.466667c2.773333 6.570667 4.48 13.696 4.906667 21.184L874.666667 277.333333v21.333334L553.536 572.586667a64 64 0 0 1-79.893333 2.538666l-3.178667-2.56L149.333333 298.666667v-21.333334a63.786667 63.786667 0 0 1 35.136-57.130666A63.872 63.872 0 0 1 213.333333 213.333333h597.333334z m-9.6 64h-578.133334L512 523.882667 801.066667 277.333333z" fill="#007FFC" p-id="2737" /></svg>
                <!--                <span class="num">{{ personalHomeInfo.articleNum }}</span>
                <span>文章数</span>-->
              </div>
              <div class="item" title="用户状态" style="border-right: 1px solid #ebeef5;">
                <svg v-if="userInfo.enabled" t="1675326363032" class="icon my_icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="7802" width="200" height="200"><path d="M512 0c282.282667 0 511.886222 229.603556 512 512 0 282.339556-229.660444 512-512 512-282.396444 0-512-229.603556-512-512s229.603556-512 512-512z m0 113.777778C292.408889 113.777778 113.777778 292.408889 113.777778 512s178.631111 398.222222 398.222222 398.222222c219.477333 0 398.222222-178.688 398.222222-398.222222-0.113778-219.591111-178.744889-398.222222-398.222222-398.222222z" fill="#0082FF" p-id="7803" /><path d="M495.104 598.698667l206.336-214.926223a44.999111 44.999111 0 0 1 50.403556-10.353777 46.478222 46.478222 0 0 1 14.791111 74.808889l-238.933334 248.888888a45.056 45.056 0 0 1-65.194666 0L326.371556 555.292444a46.705778 46.705778 0 0 1-12.060445-44.259555 45.852444 45.852444 0 0 1 32.312889-33.223111 44.828444 44.828444 0 0 1 44.600889 12.629333l103.879111 108.259556z m0 50.232889l-8.419556 8.817777a11.776 11.776 0 0 1 16.896 0l-8.476444-8.817777z" fill="#0082FF" p-id="7804" /></svg>
                <svg v-else t="1675326421815" class="icon my_icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8737" width="200" height="200"><path d="M 506.56 25.92 C 239.68 25.92 23.36 242.24 23.36 509.12 s 216.32 483.2 483.2 483.2 s 483.2 -216.32 483.2 -483.2 C 989.44 242.24 773.12 25.92 506.56 25.92 Z m 239.04 663.68 l -81.28 81.28 l -157.76 -157.76 l -157.76 157.76 l -81.28 -81.28 l 157.76 -157.76 l -157.76 -157.76 l 81.28 -81.28 l 157.76 157.76 l 157.76 -157.76 l 81.28 81.28 l -157.76 157.76 l 157.76 157.76 Z" fill="#0caaef" p-id="8738" /></svg>
                <!--                <span class="num">{{ personalHomeInfo.articleNum }}</span>
                <span>文章数</span>-->
              </div>
              <div class="item" title="累计浏览量">
                <span class="num">{{ personalHomeInfo.collectNum }}</span>
                <span>收藏量</span>
              </div>
            </div>
          </section>

          <ul class="joe_header__slideout-count">
            <svg-icon icon-class="cake" />
            <span> 于</span>
            <span class="user-general-info-key-word"> {{ personalHomeInfo.createTime }}</span>
            加入 <span class="user-general-info-key-word">M-Blog</span>
          </ul>
          <!--  左侧  -->
          <aside class="joe_aside" style="padding: 0;margin-left: 0;">
            <section class="joe_aside__item newreply" style="width: 100%;">
              <div class="joe_aside__item-title">
                <svg class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="18" height="18">
                  <path
                    d="M898.048 556.544L547.84 916.992c-43.008 44.032-112.64 44.032-155.648 0L119.808 636.416c-43.008-44.032-43.008-116.224 0-160.256L470.016 115.2c26.624-28.672 31.744-41.472 59.904-41.472h355.84c28.16 0 50.688 23.552 50.688 52.224v366.592c0 28.672-15.872 40.448-38.4 64zM158.72 596.48l272.384 280.576c21.504 22.016 56.32 22.016 77.824 0l38.4-39.936-349.696-361.472-39.424 40.448c-20.992 22.528-20.992 58.368.512 80.384zm727.04-444.416c0-14.336-11.264-26.112-25.6-26.112H555.008c-13.824 0-33.792 16.384-46.592 29.184l-271.36 280.576 349.696 360.96 272.384-280.576c13.824-14.336 26.624-35.328 26.624-49.664V152.064zM610.304 422.4c-42.496-43.52-42.496-114.688 0-158.208 42.496-44.032 111.104-44.032 153.6 0 42.496 43.52 42.496 114.688 0 158.208s-111.616 43.52-153.6 0zm115.2-118.784c-20.992-22.016-55.808-22.016-76.8 0s-20.992 57.344 0 79.36 55.808 22.016 76.8 0 20.992-57.344 0-79.36z"
                  />
                </svg>
                <span class="text">标签云</span>
                <span class="line" />
              </div>
              <ul class="joe_header__slideout-count" style="padding: 12px 15px;">
                <div class="aside-common-box-bottom">
                  <div :class="isOpen ? 'aside-common-box-content aside-box-fold' : ''">
                    <ul>
                      <template v-for="item in lableData">
                        <li>
                          <a
                            :href="'/articleLableList/'+item.id"
                            class="special-column-name"
                          >
                            <span>{{ item.value }}</span>
                          </a>
                          <div class="special-column-num"> {{ item.number }} 篇</div>
                        </li>
                      </template>
                    </ul>
                  </div>
                  <button v-if="isOpen" class="aside-common-box-bottom-btn" @click="showOpen(false)">
                    展开
                    <i class="el-icon-arrow-down" />
                  </button>
                  <button v-else class="aside-common-box-bottom-btn" @click="showOpen(true)">
                    收起
                    <i class="el-icon-arrow-up" />
                  </button>
                </div>
              </ul>
            </section>
          </aside>
        </div>
        <!--  右侧  -->
        <div class="joe_main" style="width: 100%;padding: 0;">
          <div class="joe_index">
            <div class="joe_index__list" data-wow="fadeIn" style="max-width: 800px">
              <div class="align-items header">
                <!--                <span style="text-align: center">待评分</span>-->
                <span style="width: 100%;font-family: custom;font-size: 30px;text-align: center">
                  <svg t="1676362637375" style="margin-right: 0px" class="icon" viewBox="0 0 1350 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6014" width="200" height="200"><path d="M900.629576 627.257941c88.517209-28.404776 170.560772-137.796193 105.163729-264.09836-71.870689-138.853115-143.873494-111.637376-200.815162-93.405473-8.719606 2.77442 138.192539-59.451857-64.34012 25.23401 2.906535-10.701334 17.439211-7.266338 0 0 5.416725-11.758256-20.742092 4.624033-18.364018-8.58749C737.335142 199.865147 668.503103 77.130091 577.607819 61.804724c-121.942364-20.477862-186.282485 77.551645-201.475737 164.087125-5.020379 29.197468 14.929022 66.321849 25.498241 92.084321-4.359803 2.906535-8.455375 5.945186-12.683063 9.115951-3.963457-3.170766-8.05903-6.209416-12.418832-8.983836-76.098377-49.675329-180.073069-31.179196-232.390704 41.219954-52.053404 72.39915-32.632464 171.221348 43.465914 220.896677 34.878423 22.855936 25.101895 36.463806 64.07589 31.839773 1.585383 4.359803 10.437104 26.423048 12.286717 30.650735-42.012646 17.571327-43.465913 21.402669-58.923396 66.057619-28.801122 83.364715 29.197468 179.808839 116.921985 207.156693 87.724518 27.347854 182.186913-17.967672 210.988035-101.332388 4.491918-13.079409-0.924807 13.079409 0 0 3.699227 0.132115 13.607869 57.338013 9.908643 57.338013-13.475754 0 22.45959 67.378771 36.06746 67.378772 59.716087 47.561486 70.021076 88.649325 147.30849 77.551644 98.690083-14.13633 153.253676-73.852418 175.581151-158.934631 19.68517-74.90934-31.839772-191.434979-102.521425-223.142636 1.585383-3.170766 10.172873 0.660576 11.494026-2.51019 27.612085 5.152494 61.30147 4.095572 89.838362-5.020379z" fill="#96C85C" p-id="6015" /><path d="M718.971124 1024c-16.382289 0-32.896694-1.981729-49.146869-6.077301-78.872797-19.68517-136.871386-84.157406-149.818679-162.237512-1.321152 5.54884-2.77442 11.09768-4.624034 16.514405-34.746308 105.69219-148.893873 163.294434-254.586063 128.548126-51.128597-16.778635-92.744897-52.581865-117.0541-100.671811-24.309204-48.089947-28.404776-102.65354-11.494026-153.914252 16.64652-50.468021 52.846095-92.480666 100.275465-116.52564-45.579757 3.302881-90.631053-8.851721-128.283895-34.614192C12.683063 532.13497-10.833449 406.361264 52.053404 314.673289 114.940257 222.985314 240.581848 199.600917 332.269823 262.48777c2.906535 1.981729 5.680955 3.963457 8.455375 6.209416 0.660576-0.528461 1.453268-1.056922 2.113844-1.585383-10.965565-31.707657-13.8721-65.661273-8.455375-98.557967 18.231903-109.655647 122.33871-184.036526 231.994357-165.804624 53.110326 8.851721 99.614889 37.784958 130.926201 81.515102 31.311311 43.730144 43.730144 97.1047 34.878422 150.347141-2.245959 13.343639-5.945186 26.819393-11.229795 40.163032 1.321152 1.056922 2.51019 2.113844 3.567112 3.170766l0.132115-0.264231 1.849613 1.453268c14.929022-10.040758 31.575542-18.231903 49.807445-24.309204 51.524943-17.439211 106.881227-13.739985 155.763865 10.569219 48.750523 24.177089 85.214328 65.925504 102.65354 117.582562 17.439211 51.657058 13.607869 106.881227-10.569219 155.763865-24.177089 48.750523-65.925504 85.214328-117.582562 102.65354-26.290932 8.851721-54.167247 12.286717-81.779332 10.040758 73.456072 45.579757 110.580454 134.625427 89.309901 220.103986-13.079409 52.185519-45.579757 96.312008-91.82009 124.056208-31.575542 18.496133-67.246656 28.272661-103.314116 28.404776z m-214.026685-213.101878l14.796906 0.792691c2.51019 0.132115 5.020379 0.396346 7.530569 0.396346h10.304988l0.264231 10.304988c0 83.2326 56.281091 155.499635 137.003501 175.713266 46.900909 11.758256 95.651432 4.491918 137.135617-20.477861 41.484185-24.96978 70.813767-64.472236 82.439908-111.373146 20.874208-83.628945-20.477862-170.825002-98.293736-207.553038l-9.380182-4.359803 6.473646-13.343639c1.056922-2.245959 2.245959-4.491918 3.170766-6.737877l3.170766-7.662684 8.191144 1.585383c30.78285 5.81307 62.886853 3.699227 92.480667-6.341532 46.372448-15.589598 84.025291-48.486292 105.824305-92.348551 21.799014-43.862259 25.101895-93.669704 9.512297-140.042152s-48.486292-84.025291-92.348551-105.824305c-43.862259-21.799014-93.669704-25.101895-140.042152-9.512297-18.892479 6.341531-35.935345 15.061137-50.600136 25.894586l-6.209416 4.624034-9.115951-7.002108c-1.717498-1.321152-3.567111-2.77442-5.152495-4.227688l-0.132115 0.132116c-0.396346-0.26423-2.245959-1.717498-9.512297-7.398454l-6.341531-4.888263 3.170765-7.266339c6.077301-14.13633 10.437104-28.404776 12.815178-42.541106 7.926914-47.693601-3.170766-95.651432-31.311311-135.021773-28.140546-39.370341-69.888961-65.397043-117.714677-73.323957-98.557967-16.64652-191.96344 50.20379-208.477845 148.761758-5.28461 31.839772-1.717498 64.868582 10.040758 95.255086l2.906535 7.530569-6.605762 4.624033c-4.756149 3.302881-9.380182 6.869992-14.004215 10.569219l-6.473646 5.152494-6.341532-5.284609c-4.227688-3.567111-8.719606-7.002108-13.343639-10.172874-39.898802-27.347854-88.120864-37.520728-135.550234-28.669006-47.561486 8.851721-88.78144 35.671114-116.129294 75.569916-56.677437 82.175678-35.538999 195.134206 46.768794 251.679528 36.199575 24.837665 80.326065 35.538999 124.320439 30.254389l8.191145-1.056922 2.642305 7.794799c1.849613 5.416725 3.567111 10.304989 5.54884 15.061137l3.831342 9.248067-9.248067 3.963457c-47.297255 20.742092-83.49683 60.508779-99.614889 109.127186-15.061137 45.976103-11.36191 94.990856 10.437103 138.192539 21.799014 43.201683 59.055511 75.305686 105.031614 90.366823 94.858741 31.179196 197.380165-20.609977 228.691477-115.468718 4.624033-14.13633 7.398453-28.801122 8.455375-44.787066l0.792692-9.908642z" fill="#070606" p-id="6016" /><path d="M596.103953 692.258638c-5.81307 0-11.626141-0.660576-17.439212-2.113844-25.762471-6.473647-45.315527-26.290932-51.657058-51.128597-5.945186 17.967672-18.628249 32.500348-35.538999 41.087839-17.042866 8.58749-36.33169 10.040758-54.431478 4.095573-18.099788-5.945186-32.896694-18.628249-41.484184-35.538999-8.58749-17.042866-10.040758-36.33169-4.095573-54.431478 5.152494-15.853829 15.853829-29.329583 29.858044-38.049189-13.8721-0.26423-27.479969-4.491918-38.973995-12.418832-32.368233-22.19536-40.691493-66.718195-18.496133-99.218544 22.19536-32.368233 66.718195-40.823608 99.218543-18.496133 0.132115 0 0.132115 0.132115 0.264231 0.132115-3.03865-10.304989-3.699227-21.138438-1.981729-31.575542 3.170766-18.760364 13.343639-35.274768 28.933237-46.372448 15.457483-11.09768 34.349962-15.457483 53.110326-12.418832 18.760364 3.170766 35.274768 13.343639 46.372448 28.933237 11.09768 15.457483 15.457483 34.349962 12.418833 53.242441-0.660576 4.359803-1.849613 8.719606-3.567112 13.079408l0.132115 0.132115c5.020379-3.302881 10.569219-5.945186 16.64652-8.059029 18.364018-6.209416 37.784958-4.888264 55.092054 3.699226 17.307096 8.58749 30.122274 23.384397 36.331691 41.6163 6.209416 18.231903 4.756149 37.784958-3.699227 55.092055-8.58749 17.307096-23.384397 30.122274-41.6163 36.33169-7.398453 2.51019-15.193252 3.831342-22.988051 3.831342h-0.264231c22.855936 16.91075 33.953616 46.372448 26.951509 74.777224-4.624033 18.496133-16.118059 34.085731-32.500349 43.862259-11.229795 6.473647-23.780743 9.908643-36.59592 9.908643zM520.534037 615.36757l7.002107 0.26423c0.792691 0 1.453268 0.132115 2.245959 0.132115h5.152495l0.132115 5.152495c0 28.140546 19.024594 52.449749 46.240333 59.319741 15.853829 3.963457 32.236118 1.453268 46.240333-6.869992 14.004215-8.455375 23.912858-21.799014 27.876315-37.652843 7.002108-28.272661-6.869992-57.602244-33.160924-70.021076l-4.756149-2.245959 2.378074-4.624033c0.924807-1.717498 1.321152-2.642305 1.717498-3.567111l1.585383-3.831342 4.095573 0.792691c10.437104 1.981729 21.270553 1.189037 31.179196-2.113844 15.721713-5.28461 28.404776-16.382289 35.671114-31.179196 7.398453-14.796907 8.455375-31.575542 3.170766-47.297255-5.28461-15.721713-16.382289-28.404776-31.179196-35.671114-14.796907-7.398453-31.575542-8.455375-47.297256-3.170766-6.473647 2.245959-12.154602 5.152494-17.042865 8.719606l-3.170766 2.245959-12.286717-9.644412 1.585383-3.699227c2.113844-4.756149 3.567111-9.644412 4.359803-14.400561 2.642305-16.118059-1.056922-32.368233-10.569219-45.579757s-23.648628-22.063245-39.766687-24.705549c-33.160925-5.54884-64.736466 17.042866-70.285306 50.20379-1.717498 10.701334-0.660576 21.799014 3.434996 32.104003l1.453267 3.699226-3.30288 2.245959c-1.585383 1.189037-3.302881 2.378074-4.756149 3.567112l-3.302881 2.510189-3.170766-2.642304c-1.453268-1.189037-3.03865-2.378074-4.491918-3.434997-27.876315-19.024594-65.925504-11.890371-84.950098 15.853829-19.024594 27.7442-11.890371 65.925504 15.853829 84.950098 12.286717 8.32326 27.215739 12.022487 42.012646 10.172873l4.095572-0.528461 1.321152 3.963457c0.660576 1.717498 1.189037 3.434996 1.849614 5.152494l1.849613 4.624034-4.624033 1.981728c-15.985944 6.869992-28.140546 20.345747-33.557271 36.860152-5.152494 15.457483-3.831342 32.104003 3.434996 46.636679 7.398453 14.532676 19.949401 25.366126 35.538999 30.51862 15.457483 5.152494 32.104003 3.831342 46.636679-3.567112 14.532676-7.398453 25.366126-19.949401 30.51862-35.406884 1.585383-4.624033 2.51019-9.776528 2.906535-15.061137l0.132116-4.756148z" fill="#070606" p-id="6017" /><path d="M657.669653 557.765326c34.217847-14.796907 52.449749-51.524943 41.6163-83.761061-10.701334-32.236118-46.240333-53.506671-78.21222-42.673221-6.209416 2.113844-16.514405 6.209416-21.534784 9.908642-4.359803-3.434996 2.378074 8.58749-3.434996 3.963458 1.981729-4.491918 3.434996-9.248067 4.227687-14.268446 5.54884-33.160925-16.778635-64.604351-49.675329-70.021076-32.896694-5.54884-63.943775 16.91075-69.3605 50.071675-1.849613 11.229795-0.396346 22.063245 3.302881 31.971887-1.585383 1.056922-3.03865 2.245959-4.624033 3.434997-1.453268-1.189037-2.906535-2.378074-4.491918-3.434997-27.479969-19.024594-65.000697-11.890371-83.893176 15.853829-18.760364 27.7442-11.758256 65.529158 15.589598 84.553752 12.683063 8.719606 27.347854 11.890371 41.484184 10.172873 0.660576 1.585383 1.189037 3.302881 1.849614 5.02038-15.193252 6.605762-27.612085 19.553055-33.160925 36.728036-10.437104 31.839772 6.737877 66.321849 38.445534 76.758953 31.707657 10.437104 50.864367-9.776528 72.795496-40.030917 3.03865-4.227688 3.963457-3.302881 4.227688-8.32326h1.321152l4.624033 21.138438c12.550948 29.858044 20.742092 32.236118 44.522836 41.087839 31.311311 11.494026 66.321849-20.081516 74.380878-52.71398 7.134223-28.669007-7.266338-57.602244-32.764579-69.756846 0.528461-1.189037 1.189037-2.378074 1.717499-3.567111 10.172873 1.981729 21.138438 2.245959 31.04708-2.113844z" fill="#EEBF2F" p-id="6018" /><path d="M961.006239 306.085798c0.924807 10.701334 1.585383 21.402669 1.717498 32.368233 1.849613 109.655647-34.614192 210.988035-96.972584 291.446215 92.612782-4.888264 165.540393-85.346444 163.822895-182.847489-0.792691-57.734359-27.612085-108.598725-68.567809-140.966959zM638.645059 983.969083c31.047081 15.457483 66.718195 22.19536 103.710462 17.439211 98.822198-12.947293 168.579043-103.446231 155.763865-202.400544-2.77442-20.874208-9.115951-40.427263-18.099788-58.26282-46.108218 107.541804-131.851007 195.530552-241.374539 243.224153z" fill="#70A02F" p-id="6019" /></svg>
                  待评分
                </span>
              </div>

              <el-table
                :key="tableKey"
                v-loading="loading"
                :data="evaluateAppointments"
                border
                fit
                highlight-current-row
                style="width: 100%;"
              >
                <el-table-column label="预约编号" prop="id" align="center" width="80" /><!--:class-name="getSortClass('id')"-->
                <el-table-column label="预约用户" prop="appointmentUser.username" align="center" width="100" />
                <el-table-column label="会议主题" prop="conferenceSubject" align="center" width="180" />
                <el-table-column label="会议室名称" prop="appointmentRoom.roomName" align="center" width="100" />
                <el-table-column label="预约时间" width="110px" prop="appointmentStartTime" align="center" :formatter="formatDate" />
                <el-table-column label="action" align="center" width="100" class-name="small-padding fixed-width">
                  <template slot-scope="{row,$index}">
                    <el-button plain type="primary" size="mini" @click="handleCheck(row, $index)">
                      评分
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>

              <el-dialog title="对本次预约评分" :visible.sync="dialogFormVisible" @closed="curDialogClosed()">
                <el-form ref="form" :rules="rules" class="ruleForm" :model="modifyAppointment" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
                  <el-form-item label="会议主题" label-width="auto" prop="conferenceSubject" required>
                    <el-input v-model="modifyAppointment.conferenceSubject" style="width: 70%" disabled/>
                  </el-form-item>
                  <el-form-item label="状态" prop="status" required>
                    <el-select disabled placeholder="待评价" value="待评价" />
                  </el-form-item>
                  <el-form-item label="评分">
                    <el-rate
                      v-model="score"
                      text-color="#ff9900"
                      score-template="{value}"
                      :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                      show-text
                    />
                    <!--<el-rate
                      v-model="score"
                      :icon-classes="iconClasses"
                      void-icon-class="icon-rate-face-off"
                      :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
                    </el-rate>-->
                  </el-form-item>
                  <el-form-item label="评价">
                    <el-input v-model="remark" type="textarea" rows="3" />
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">
                    取消
                  </el-button>
                  <el-button type="primary" @click="modifyAppoint('form')">
                    确认
                  </el-button>
                </div>
              </el-dialog>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>
</template>

<script>

import { getAction, getFileAccessHttpUrl } from '@/api/manage'
import { getInfo } from '@/api/user'
import {
  deleteAppointmentsByID, queryAllWaitEvaluate,
  queryAppointDataById,
  updateAppointment, updateAppointStatus
} from '@/api/appointment'
import moment from 'moment'
import { success, warning, error } from '@/utils/message'
import { parseTime } from '@/utils'

export default {
  name: 'PersonalHome',
  inject: ['reload'],
  components: {},
  data() {
    return {
      rules: {
        conferenceSubject: [{ required: true, message: '请输入会议主题', trigger: 'blur' },
          { max: 20, message: '主题长度不能超过20', trigger: 'blur' }
        ]
      },
      dialogFormVisible: false,
      username: this.$route.params.username,
      personalHomeInfo: {},
      isOpen: true,
      sortData: [],
      lableData: [],
      userInfo: {
        email: ''
      },
      calendarValue: new Date(),
      appointInfo: [],
      abstractMsg: [],
      flag: 2,
      modifyAppointment: {
        'appointmentUser': {
          username: ''
        },
        'appointmentRoom': {
          'roomType': {}
        }
      },
      curDate: new Date(),
      tableKey: 0,
      loading: false,
      evaluateAppointments: [],
      score: undefined,
      remark: '',
      roomId: undefined,
      appointId: undefined,
      checkIndex: undefined,
      iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'] // 等同于 { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
    }
  },
  created() {
    this.getPersonalHomeInfo()
    this.init()
  },
  methods: {
    init() {
      this.loading = true
      queryAllWaitEvaluate().then(response => {
        // console.log(response.data.data)
        this.evaluateAppointments = response.data.data
      })
      this.loading = false
    },
    getPersonalHomeInfo() {
      getInfo().then(response => {
        // console.log(response.data.data)
        this.userInfo = response.data.data
      })
    },
    handleChange(value) {
      this.$refs.personalHomeList.changeSelect(value)
    },
    /* 图片预览 */
    getImgView(text) {
      if (text && text.indexOf(',') > 0) {
        text = text.substring(0, text.indexOf(','))
      }
      return getFileAccessHttpUrl(text)
    },
    showOpen(flag) {
      this.isOpen = flag
    },
    onFilterDate(date) {
      const myDate = date || new Date()
      const y = myDate.getFullYear()
      let m = myDate.getMonth() + 1
      m = m > 9 ? m : `0${m}`
      let d = myDate.getDate()
      d = d > 9 ? d : `0${d}`
      return `${y}-${m}-${d}`
    },
    // 初始化文章分类
    initArticleSort() {
      getAction(this.url.initArticleLable, {
        'username': this.username
      }).then(res => {
        if (res.success) {
          this.lableData = res.result
        }
      })
    },
    formatDate(row, column) {
      // 获取单元格数据
      const data = row[column.property]
      if (data == null) {
        return '未知'
      }
      const dt = new Date(data)
      return parseTime(dt, undefined)
      // return dt.getFullYear() + '年' + (dt.getMonth() + 1) + '月' + dt.getDate() + '日 ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
    },
    modifyAppoint() {
      /* console.log(this.remark)
      console.log(this.score)
      console.log(this.roomId)
      console.log(this.appointId) */
      updateAppointStatus(this.appointId, this.roomId, this.score).then(response => {
        console.log(response)
      })
      this.evaluateAppointments.splice(this.checkIndex, 1)
      this.dialogFormVisible = false
      success('评价成功')
      this.remark = ''
      this.score = undefined
    },
    curDialogClosed() {
      this.modifyFormVisiable = false
      this.modifyAppointment = undefined
    },
    handleCheck(row, index) {
      // console.log('row: ', row)
      this.checkIndex = index
      console.log(this.evaluateAppointments[index])
      this.roomId = this.evaluateAppointments[index].appointmentRoom.id
      this.appointId = this.evaluateAppointments[index].id
      this.modifyAppointment = Object.assign({}, row) // copy obj
      // console.log('temp: ', this.temp) // 当前行的数据
      this.dialogFormVisible = true
    }
  }
}
</script>

<style lang="scss" scoped>
.aside-common-box-content.aside-box-fold {
  max-height: 224px;
  overflow: hidden;
}

.aside-common-box-bottom div ul {
  padding: 0;
  margin: 0;
}

.aside-common-box-bottom div ul li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.special-column-name {
  display: flex;
  align-items: center;
  color: #555666;
  font-size: 14px;
  line-height: 24px;
  flex: 1;
  overflow: hidden;
}

.special-column-name:hover {
  font-weight: 500;
  color: #409EFF;
}

.special-column-num {
  color: #999aaa;
  font-size: 14px;
  line-height: 16px;
  flex-shrink: 0;
  margin-left: 16px;
}

.aside-common-box-bottom-btn {
  width: 100%;
  background: #f8f8f8;
  height: 32px;
  color: #555666;
  font-size: 14px;
  outline: none;
  border: none;
  margin-top: 10px;
}

.user-general-info-key-word {
  color: #222226;
  max-width: calc(100% - 124px);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.my_icon {
  width: 2.5em;
  height: 2.5em;
}

.align-items{
  display: flex;
  align-items: center;
}
.header{
  padding: 20px 20px 0 ;
  background-color: #fff;
  justify-content: space-between;
}
.icon-a{
  width: 20px;
  height: 20px;
}
.title{
  color: #101010;
  font-size: 16px;
}
::v-deep .el-calendar-table__row .prev{
  width: 120px;
}
::v-deep .el-calendar-table .el-calendar-day {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 8px;
  height: 150px;
  /*width: 120px;*/
}

.one{
  /*height: 50px;*/
  padding: 0 4px;
  line-height: 20px;
  /*border-left: 3px solid #3797FF;*/
  background: #DBECFF;
  font-size: 12px;
  margin-top: 6px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}
.two{
  height: 20px;
  line-height: 20px;
  padding: 0 4px;
  border-left: 3px solid #FF6161;
  background: #FFE1E1;
  font-size: 12px;
  margin-top: 6px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}
.three{
  height: 20px;
  line-height: 20px;
  font-size: 12px;
  padding: 0 4px;
  border-left: 3px solid #4EC95B;
  background: #E4FFE6;
  margin-top: 6px;
  overflow:hidden;
  text-overflow:ellipsis;
  white-space:nowrap;
}

.popover-bgc {
  /*background-color: #000 !important;*/
  background-color: #bae7b8 !important;
  padding: 12px;
  border-radius: 6px;
}
.popover-bgc .one,
.popover-bgc .two,
.popover-bgc .three{
 /* background-color: #000 !important;*/
  background-color: #bae7b8 !important;
  color: #fff;
}
</style>

<style>
.popover-bgcolor{
  padding: 0 !important;
}
.calendar_class .el-calendar-table:not(.is-range) td.next, .calendar_class .el-calendar-table:not(.is-range) td.prev{
  pointer-events: none;
}
.calendar_class .el-calendar__header{
  display: none !important;
}

.calendar_class .el-calendar__body{
  padding: 0 20px 35px
}

.calendar_class .el-calendar-day{
  height: 120px !important;
}

.el-popper[x-placement^=top] .popper__arrow::after {
  border-top-color: #000 !important;
}

/* 日历表格选择日期样式 */
/*.el-calendar-table td.is-selected {
  !*background-color: #7000BD;*!
  color: white;
}*/

.el-calendar__header .el-calendar__title {
  position: absolute;
  left: 50%;
  transform: translate(-50%);
  font-size: 20px;
  color: #4e8cda;
}
.el-calendar-table thead th:before{
  content: '周';
}

.el-calendar__button-group {
  width: 100%;
}

.el-button-group {
  display: flex;
  justify-content: space-between;
}

.el-button-group::after, .el-button-group::before {
  content: unset;
}

.el-button-group>.el-button:not(:first-child):not(:last-child) {
  display: none;
}

.el-button-group>.el-button:first-child:before{
  content: '<';
}

.el-button-group>.el-button:last-child:before{
  content: '>';
}

.el-button-group>.el-button:first-child span, .el-button-group>.el-button:last-child span{
  display: none;
}
</style>
