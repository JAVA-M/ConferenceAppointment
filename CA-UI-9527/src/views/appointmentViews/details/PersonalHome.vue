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
                src="https://q1.qlogo.cn/headimg_dl?dst_uin=2519307389&spec=640"
                alt="博主头像"
              ><!--:src="getImgView(personalHomeInfo.userAvatar)"-->

              <span style="font-family: custom;font-size: 30px">
                {{ userInfo.username }}
              </span>
            </div>
            <div class="count" style="border-bottom: none; padding-bottom: 0">
              <div class="item" :title="userInfo.phone" style="border-right: 1px solid #ebeef5;">
                <svg
                  t="1675326139952"
                  class="icon my_icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="3810"
                  width="200"
                  height="200"
                >
                  <path
                    d="M676.2 64H347.8c-41.1 0-74.7 33.6-74.7 74.7v746.7c0 41.1 33.6 74.7 74.7 74.7h328.5c41.1 0 74.7-33.6 74.7-74.7V138.7c-0.1-41.1-33.7-74.7-74.8-74.7m-209 59.7h89.6c8.2 0 14.9 6.7 14.9 14.9s-6.7 14.9-14.9 14.9h-89.6c-8.2 0-14.9-6.7-14.9-14.9s6.7-14.9 14.9-14.9M512 930.1c-24.8 0-44.8-20-44.8-44.8 0-24.8 20-44.8 44.8-44.8 24.8 0 44.8 20 44.8 44.8 0 24.8-20 44.8-44.8 44.8m209.1-119.4H303V213.3h418.1v597.4z"
                    fill="#1296db"
                    p-id="3811"
                  />
                </svg>
                <!--                <span class="num">{{ personalHomeInfo.collectNum }}</span>
                                <span>收藏量</span>-->
              </div>
              <div class="item" :title="userInfo.email" style="border-right: 1px solid #ebeef5;">
                <svg
                  t="1675325504148"
                  class="icon my_icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="2736"
                  width="200"
                  height="200"
                >
                  <path
                    d="M874.666667 375.189333V746.666667a64 64 0 0 1-64 64H213.333333a64 64 0 0 1-64-64V375.189333l64 54.250667V746.666667h597.333334V429.44l64-54.250667zM810.666667 213.333333a64.789333 64.789333 0 0 1 22.826666 4.181334 63.616 63.616 0 0 1 26.794667 19.413333 64.32 64.32 0 0 1 9.344 15.466667c2.773333 6.570667 4.48 13.696 4.906667 21.184L874.666667 277.333333v21.333334L553.536 572.586667a64 64 0 0 1-79.893333 2.538666l-3.178667-2.56L149.333333 298.666667v-21.333334a63.786667 63.786667 0 0 1 35.136-57.130666A63.872 63.872 0 0 1 213.333333 213.333333h597.333334z m-9.6 64h-578.133334L512 523.882667 801.066667 277.333333z"
                    fill="#007FFC"
                    p-id="2737"
                  />
                </svg>

              </div>
              <div class="item" title="状态正常" style="border-right: 1px solid #ebeef5;">
                <svg
                  v-if="userInfo.status"
                  t="1675326363032"
                  class="icon my_icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="7802"
                  width="200"
                  height="200"
                >
                  <path
                    d="M512 0c282.282667 0 511.886222 229.603556 512 512 0 282.339556-229.660444 512-512 512-282.396444 0-512-229.603556-512-512s229.603556-512 512-512z m0 113.777778C292.408889 113.777778 113.777778 292.408889 113.777778 512s178.631111 398.222222 398.222222 398.222222c219.477333 0 398.222222-178.688 398.222222-398.222222-0.113778-219.591111-178.744889-398.222222-398.222222-398.222222z"
                    fill="#0082FF"
                    p-id="7803"
                  />
                  <path
                    d="M495.104 598.698667l206.336-214.926223a44.999111 44.999111 0 0 1 50.403556-10.353777 46.478222 46.478222 0 0 1 14.791111 74.808889l-238.933334 248.888888a45.056 45.056 0 0 1-65.194666 0L326.371556 555.292444a46.705778 46.705778 0 0 1-12.060445-44.259555 45.852444 45.852444 0 0 1 32.312889-33.223111 44.828444 44.828444 0 0 1 44.600889 12.629333l103.879111 108.259556z m0 50.232889l-8.419556 8.817777a11.776 11.776 0 0 1 16.896 0l-8.476444-8.817777z"
                    fill="#0082FF"
                    p-id="7804"
                  />
                </svg>
                <svg
                  v-else
                  t="1675326421815"
                  class="icon my_icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="8737"
                  width="200"
                  height="200"
                >
                  <path
                    d="M 506.56 25.92 C 239.68 25.92 23.36 242.24 23.36 509.12 s 216.32 483.2 483.2 483.2 s 483.2 -216.32 483.2 -483.2 C 989.44 242.24 773.12 25.92 506.56 25.92 Z m 239.04 663.68 l -81.28 81.28 l -157.76 -157.76 l -157.76 157.76 l -81.28 -81.28 l 157.76 -157.76 l -157.76 -157.76 l 81.28 -81.28 l 157.76 157.76 l 157.76 -157.76 l 81.28 81.28 l -157.76 157.76 l 157.76 157.76 Z"
                    fill="#0caaef"
                    p-id="8738"
                  />
                </svg>

              </div>
              <div class="item" :title="userInfo.role.roleDesc">
                <svg
                  t="1677555911844"
                  class="icon my_icon"
                  viewBox="0 0 1024 1024"
                  version="1.1"
                  xmlns="http://www.w3.org/2000/svg"
                  p-id="7990"
                  width="200"
                  height="200"
                >
                  <path
                    d="M509.952 10.24C232.448 10.24 8.192 234.496 8.192 512s224.256 501.76 501.76 501.76 501.76-224.256 501.76-501.76-224.256-501.76-501.76-501.76z m0 152.576c84.992 0 153.6 68.608 153.6 153.6s-68.608 153.6-153.6 153.6-153.6-68.608-153.6-153.6 68.608-153.6 153.6-153.6z m-1.024 698.368c-177.152 0-320.512-80.896-320.512-168.96S331.776 532.48 509.952 532.48c177.152 0 321.536 71.68 321.536 159.744S686.08 861.184 508.928 861.184z"
                    fill="#38ADFF"
                    p-id="7991"
                  />
                </svg>
                <!--                <span class="num">{{ personalHomeInfo.collectNum }}</span>
                                <span>收藏量</span>-->
              </div>
            </div>
          </section>

          <ul class="joe_header__slideout-count">
            <p>
              <svg
                t="1677750989629"
                class="icon"
                style="height: 2em;width:2em;top: 5px; position: relative"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="8504"
                width="200"
                height="200"
              >
                <path
                  d="M512 128c164.949333 0 298.666667 129.941333 298.666667 290.261333 0 3.392-0.064 6.784-0.213334 10.24-3.669333 95.914667-58.24 203.776-147.797333 313.173334a1260.714667 1260.714667 0 0 1-139.818667 144.768l-10.837333 9.322666-10.837333-9.322666-13.013334-11.626667a1260.714667 1260.714667 0 0 1-126.805333-133.162667c-89.557333-109.376-144.128-217.237333-147.818667-313.173333-0.128-3.413333-0.192-6.826667-0.192-10.24 0-158.08 130.069333-286.677333 291.904-290.176L512 128z m0 64c-130.005333 0-234.666667 101.717333-234.666667 226.261333 0 2.56 0.042667 5.141333 0.149334 7.765334 2.944 76.608 48.554667 171.52 133.376 275.093333a1184.682667 1184.682667 0 0 0 86.613333 94.506667l14.528 14.016 14.506667-14.016a1184.682667 1184.682667 0 0 0 86.634666-94.506667c84.821333-103.573333 130.432-198.485333 133.376-275.093333 0.106667-2.624 0.149333-5.205333 0.149334-7.765334C746.666667 293.717333 642.005333 192 512 192z m0 74.666667a149.333333 149.333333 0 1 1 0 298.666666 149.333333 149.333333 0 0 1 0-298.666666z m0 64a85.333333 85.333333 0 1 0 0 170.666666 85.333333 85.333333 0 0 0 0-170.666666z"
                  fill="#007FFC"
                  p-id="8505"
                />
              </svg>
              {{ position }}
              <svg
                t="1677746285254"
                class="icon"
                style="height: 2em;width:2em;top: 5px; position: relative"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="7486"
                width="200"
                height="200"
              >
                <path
                  d="M634.7 457.8m-158.4 0a158.4 158.4 0 1 0 316.8 0 158.4 158.4 0 1 0-316.8 0Z"
                  fill="#F04726"
                  p-id="7487"
                />
                <path
                  d="M634.7 628.7c-94.3 0-171-76.7-171-171s76.7-171 171-171 171 76.7 171 171-76.7 171-171 171z m0-316.8c-80.4 0-145.8 65.4-145.8 145.8s65.4 145.8 145.8 145.8 145.8-65.4 145.8-145.8-65.4-145.8-145.8-145.8z"
                  fill="#592900"
                  p-id="7488"
                />
                <path
                  d="M663.7 220.8c0 16.2-13.1 29.3-29.3 29.3-16.2 0-29.3-13.1-29.3-29.3 0-16.2 13.1-29.3 29.3-29.3 16.2 0 29.3 13.1 29.3 29.3z"
                  fill="#F7BC00"
                  p-id="7489"
                />
                <path
                  d="M634.4 258.5c-20.8 0-37.7-16.9-37.7-37.7 0-20.8 16.9-37.7 37.7-37.7s37.7 16.9 37.7 37.7c0 20.8-17 37.7-37.7 37.7z m0-58.7c-11.6 0-21 9.4-21 21s9.4 21 21 21 21-9.4 21-21c-0.1-11.6-9.5-21-21-21z"
                  fill="#592900"
                  p-id="7490"
                />
                <path
                  d="M541.3 238c8.1 14 3.3 32-10.7 40.1-14 8.1-32 3.3-40.1-10.7-8.1-14-3.3-32 10.7-40.1 14.1-8.1 32-3.3 40.1 10.7z"
                  fill="#F7BC00"
                  p-id="7491"
                />
                <path
                  d="M516 290.4c-13 0-25.7-6.8-32.7-18.8-10.4-18-4.2-41.1 13.8-51.5 18-10.4 41.1-4.2 51.5 13.8 10.4 18 4.2 41.1-13.8 51.5-6 3.4-12.4 5-18.8 5z m-0.1-58.7c-3.6 0-7.2 0.9-10.4 2.8-10 5.8-13.4 18.6-7.7 28.6 5.8 10 18.6 13.5 28.6 7.7s13.4-18.6 7.7-28.6c-3.9-6.7-11-10.5-18.2-10.5z"
                  fill="#592900"
                  p-id="7492"
                />
                <path
                  d="M871.7 457.5m-29.3 0a29.3 29.3 0 1 0 58.6 0 29.3 29.3 0 1 0-58.6 0Z"
                  fill="#F7BC00"
                  p-id="7493"
                />
                <path
                  d="M871.7 495.2c-20.8 0-37.7-16.9-37.7-37.7 0-20.8 16.9-37.7 37.7-37.7s37.7 16.9 37.7 37.7c0 20.7-16.9 37.7-37.7 37.7z m0-58.7c-11.6 0-21 9.4-21 21s9.4 21 21 21 21-9.4 21-21c-0.1-11.6-9.5-21-21-21z"
                  fill="#592900"
                  p-id="7494"
                />
                <path
                  d="M854.4 364.4c-14 8.1-32 3.3-40.1-10.7-8.1-14-3.3-32 10.7-40.1 14-8.1 32-3.3 40.1 10.7 8.2 14.1 3.4 32-10.7 40.1z"
                  fill="#F7BC00"
                  p-id="7495"
                />
                <path
                  d="M839.8 376.7c-3.3 0-6.6-0.4-9.8-1.3-9.7-2.6-17.9-8.8-22.9-17.6-5-8.7-6.4-18.9-3.8-28.6 2.6-9.7 8.8-17.9 17.6-22.9 8.7-5 18.9-6.4 28.6-3.8 9.7 2.6 17.9 8.8 22.9 17.6 5 8.7 6.4 18.9 3.8 28.6-2.6 9.7-8.8 17.9-17.6 22.9-5.8 3.4-12.2 5.1-18.8 5.1z m-0.1-58.7c-3.6 0-7.2 1-10.4 2.8-4.9 2.8-8.3 7.3-9.8 12.7-1.4 5.4-0.7 11.1 2.1 15.9 2.8 4.9 7.3 8.3 12.7 9.8 5.4 1.5 11.1 0.7 15.9-2.1 4.8-2.8 8.3-7.3 9.8-12.7 1.4-5.4 0.7-11.1-2.1-15.9-2.8-4.9-7.3-8.3-12.7-9.8-1.8-0.4-3.7-0.7-5.5-0.7z"
                  fill="#592900"
                  p-id="7496"
                />
                <path
                  d="M778.3 267c-8.1 14-26 18.8-40.1 10.7-14-8.1-18.8-26-10.7-40.1 8.1-14 26-18.8 40.1-10.7 14 8.2 18.8 26.1 10.7 40.1z"
                  fill="#F7BC00"
                  p-id="7497"
                />
                <path
                  d="M752.8 290.1c-6.4 0-12.9-1.6-18.8-5-18-10.4-24.2-33.5-13.8-51.5 10.4-18 33.5-24.2 51.5-13.8 18 10.4 24.2 33.5 13.8 51.5-6.9 12-19.6 18.8-32.7 18.8z m0.1-58.7c-7.3 0-14.3 3.8-18.2 10.5-5.8 10-2.3 22.9 7.7 28.6 10 5.8 22.9 2.3 28.6-7.7 5.8-10 2.3-22.9-7.7-28.6-3.2-1.9-6.8-2.8-10.4-2.8z"
                  fill="#592900"
                  p-id="7498"
                />
                <path
                  d="M806.2 678.3c0-80.9-62.1-146.7-142.3-149.5-25.5-96.8-112.6-168.2-217.4-168.2-120.8 0-225.6 100.6-225.1 214.1-54.3 15.2-93.9 65.1-93.9 124.3 0 71.3 54.6 129.1 125.9 129.1h-2.8l414.6-0.6c78.2-6.6 141-70.8 141-149.2z"
                  fill="#99DCFF"
                  p-id="7499"
                />
                <path
                  d="M256.2 840.9c-78.1 0-141.6-63.5-141.6-141.6s63.5-141.6 141.6-141.6c45.6 0 88.7 22.2 115.3 59.3 4 5.6 2.7 13.5-2.9 17.5s-13.5 2.7-17.5-2.9c-21.9-30.6-57.3-48.8-94.8-48.8-64.2 0-116.5 52.3-116.5 116.5s52.3 116.5 116.5 116.5c6.9 0 12.6 5.6 12.6 12.6-0.1 6.9-5.7 12.5-12.7 12.5z"
                  fill="#592900"
                  p-id="7500"
                />
                <path
                  d="M172.1 708.5c-6.9 0-12.6-5.6-12.6-12.6 0-51 41.5-92.4 92.4-92.4 6.9 0 12.6 5.6 12.6 12.6 0 6.9-5.6 12.6-12.6 12.6-37.1 0-67.3 30.2-67.3 67.3 0.1 6.9-5.6 12.5-12.5 12.5zM294.2 503.5c-2 0-4.1-0.5-6-1.6-6.1-3.3-8.3-11-5-17.1 22.5-40.9 59.4-71 103.8-84.7 6.6-2 13.7 1.7 15.7 8.3 2 6.6-1.7 13.7-8.3 15.7-37.6 11.6-70.2 38.1-89.2 72.8-2.3 4.3-6.6 6.6-11 6.6z"
                  fill="#F5FFFF"
                  p-id="7501"
                />
                <path
                  d="M253.4 840.5h-3.9c-75.8-2-134.6-63.5-134.6-141.6 0-60.1 38.2-113.7 94.1-133.5 2.2-52.9 25.4-104 66.1-144.9 45.8-46.1 108.3-72.5 171.4-72.5 104.6 0 196.7 69 227.1 168.9 82.1 7.5 145.2 76.7 145.2 161.4 0 83.5-67 154.5-152.5 161.5h-1l-411.3 0.6c-0.2 0.1-0.4 0.1-0.6 0.1z m193.1-467.4c-56.4 0-112.4 23.7-153.6 65.1-38.3 38.5-59.2 86.9-58.9 136.4 0 5.7-3.7 10.6-9.2 12.2-49.9 14-84.8 60.1-84.8 112.2 0 64.8 49 115.7 111.8 116.5l412.8-0.6c72.4-6.2 129-66.1 129-136.5 0-74.2-57.2-134.4-130.2-136.9-5.5-0.2-10.3-4-11.7-9.4-24.5-93.6-109-159-205.2-159z"
                  fill="#592900"
                  p-id="7502"
                />
              </svg>
              {{ weather }}
            </p>
            <svg
              t="1677743181179"
              class="icon"
              style="width: 2em;height: 2em;top: 5px; position: relative"
              viewBox="0 0 1024 1024"
              version="1.1"
              xmlns="http://www.w3.org/2000/svg"
              p-id="6397"
              width="200"
              height="200"
            >
              <path
                d="M412.083 331.955c-10.951 0-19.838-8.881-19.838-19.838v-65.982c0-10.95 8.887-19.838 19.838-19.838 10.958 0 19.845 8.888 19.845 19.838v65.982c0.001 10.957-8.887 19.838-19.845 19.838zM516.974 331.955c-10.952 0-19.839-8.881-19.839-19.838v-65.982c0-10.95 8.887-19.838 19.839-19.838 10.958 0 19.845 8.888 19.845 19.838v65.982c-0.001 10.957-8.887 19.838-19.845 19.838zM621.099 331.955c-10.956 0-19.844-8.881-19.844-19.838v-65.982c0-10.95 8.888-19.838 19.844-19.838 10.951 0 19.839 8.888 19.839 19.838v65.982c0 10.957-8.888 19.838-19.839 19.838z"
                fill="#F1CA4E"
                p-id="6398"
              />
              <path
                d="M725.913 521.903c0 22.771-18.462 41.233-41.235 41.233H332.773c-22.773 0-41.242-18.462-41.242-41.233v-147.48c0-22.773 18.468-41.242 41.242-41.242h351.904c22.773 0 41.235 18.469 41.235 41.242v147.48z"
                fill="#F08C3D"
                p-id="6399"
              />
              <path
                d="M684.678 582.975H332.773c-33.683 0-61.079-27.396-61.079-61.071v-147.48c0-33.682 27.396-61.08 61.079-61.08h351.904c33.677 0 61.073 27.398 61.073 61.08v147.48c0.001 33.674-27.396 61.071-61.072 61.071z m-351.905-229.95c-11.802 0-21.397 9.596-21.397 21.397v147.48c0 11.793 9.595 21.396 21.397 21.396h351.904c11.796 0 21.397-9.603 21.397-21.396v-147.48c0-11.802-9.602-21.397-21.397-21.397H332.773z"
                fill="#353533"
                p-id="6400"
              />
              <path
                d="M835.767 765.096c0 20.342-18.469 36.83-41.243 36.83H222.92c-22.779 0-41.235-16.488-41.235-36.83V517.094c0-20.335 18.456-36.828 41.235-36.828h571.604c22.774 0 41.243 16.493 41.243 36.828v248.002z"
                fill="#F1CA4E"
                p-id="6401"
              />
              <path
                d="M794.523 821.763H222.92c-33.676 0-61.08-25.415-61.08-56.667V517.094c0-31.245 27.404-56.667 61.08-56.667h571.604c33.685 0 61.08 25.422 61.08 56.667v248.002c0 31.252-27.396 56.667-61.081 56.667zM222.92 500.11c-11.597 0-21.396 7.776-21.396 16.983v248.002c0 9.215 9.799 16.99 21.396 16.99h571.604c11.605 0 21.398-7.775 21.398-16.99V517.094c0-9.207-9.793-16.983-21.398-16.983H222.92z"
                fill="#353533"
                p-id="6402"
              />
              <path
                d="M412.083 206.289c-18.046 0-30.176-18.666-30.176-46.451s12.129-46.458 30.176-46.458 30.182 18.673 30.182 46.458-12.135 46.451-30.182 46.451zM516.974 206.289c-18.048 0-30.184-18.666-30.184-46.451s12.136-46.458 30.184-46.458c18.046 0 30.168 18.673 30.168 46.458s-12.122 46.451-30.168 46.451z"
                fill="#F1CA4E"
                p-id="6403"
              />
              <path
                d="M621.113 206.289c-18.061 0-30.189-18.666-30.189-46.451s12.129-46.458 30.189-46.458c18.032 0 30.161 18.673 30.161 46.458s-12.128 46.451-30.161 46.451z"
                fill="#F1CA4E"
                p-id="6404"
              />
              <path
                d="M853.029 710.949c-36.781 0-53.37-33.641-66.69-60.664-11.469-23.277-19.831-38.531-31.102-38.531s-19.634 15.254-31.103 38.531c-13.326 27.023-29.909 60.664-66.697 60.664-36.78 0-53.363-33.641-66.684-60.664-11.469-23.277-19.832-38.531-31.095-38.531-11.265 0-19.627 15.254-31.095 38.531-13.321 27.023-29.898 60.664-66.686 60.664-36.775 0-53.363-33.641-66.678-60.664-11.475-23.277-19.837-38.531-31.102-38.531-11.256 0-19.613 15.254-31.087 38.524-13.32 27.03-29.896 60.671-66.677 60.671-36.789 0-53.364-33.641-66.685-60.664-11.468-23.277-19.831-38.531-31.088-38.531-10.958 0-19.838-8.887-19.838-19.845 0-10.95 8.88-19.839 19.838-19.839 36.781 0 53.357 33.636 66.677 60.673 11.475 23.276 19.831 38.53 31.095 38.53 11.257 0 19.612-15.254 31.088-38.53 13.32-27.037 29.896-60.673 66.676-60.673 36.789 0 53.371 33.636 66.691 60.673 11.475 23.276 19.831 38.53 31.088 38.53 11.264 0 19.626-15.254 31.094-38.53 13.321-27.037 29.898-60.673 66.686-60.673 36.787 0 53.37 33.636 66.69 60.673 11.469 23.276 19.832 38.53 31.088 38.53 11.264 0 19.626-15.254 31.108-38.53 13.32-27.037 29.903-60.673 66.691-60.673s53.37 33.636 66.69 60.673c11.476 23.276 19.838 38.53 31.102 38.53 10.958 0 19.838 8.881 19.838 19.831 0.005 10.965-8.875 19.845-19.833 19.845z"
                fill="#DF4F7E"
                p-id="6405"
              />
            </svg>
            <span> 于2023年2月1日</span>
            <span class="user-general-info-key-word"> {{ personalHomeInfo.createTime }}</span>
            加入 <span class="user-general-info-key-word">会议预约系统</span>
            <el-button type="primary" plain style="width: 100%" @click="userInfoFormVisible = true">修改信息</el-button>
            <br>
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
            <!-- 加载动画 -->
            <div class="joe_index__list" data-wow="fadeIn" style="max-width: 800px">
              <div class="align-items header">
                <div class="align-items">
                  <div class="title">
<!--                    预约日历-->
                    <el-button
                      type="primary"
                      plain
                      icon="el-icon-download"
                      size="mini"
                      @click="handleDownload">导出预约信息</el-button>
<!--                    <el-button style="width: 100%" @click="handleDownload">导出预约信息</el-button>-->
                  </div>
                </div>
                <el-date-picker
                  v-model="calendarValue"
                  size="small"
                  type="month"
                  change="onChangeDate"
                  format="yyyy年MM月"
                  value-format="yyyy年MM月"
                  placeholder="选择日期"
                />
              </div>
              <el-calendar v-model="calendarValue">
                <template slot="dateCell" slot-scope="{date, data}"><!--data.day.split('-')[2] == 2-->
                  <el-popover
                    v-if="isView(date, data)"
                    popper-class="popover-bgcolor"
                    placement="top"
                    trigger="click"
                  >
                    <div class="popover-bgc">
                      <div style="color:#fff;font-size:15px;">{{ data.day }}</div>
                      <div v-for="(item, index) in parseContent2(date, 1)" class="one" style="font-size: 15px">
                        <!--v-html="parseContent(date, 1)"-->
                        <!--点击可以看到的内容-->
                        <!--                        {{ parseContent(date) }}-->
                        <li v-if="item.status !== 5">
                          {{ index + 1 }}、{{ item.subject }} ({{ item.time }}, {{ item.room }})
                          <span v-if="item.startTime >= curDate.getTime()" title="修改预约信息">
                            <svg
                              t="1675656028592"
                              class="icon"
                              style="width: 1.5em;height: 1.5em;cursor: pointer"
                              viewBox="0 0 1024 1024"
                              version="1.1"
                              xmlns="http://www.w3.org/2000/svg"
                              p-id="3010"
                              width="200"
                              height="200"
                              @click="modify(item.id)"
                            ><path
                              d="M846.8 255L744.5 152.8c-33.1-33.1-87.9-33.1-122.5 0L149.6 625.3c-10.1 10.1-15.8 24.5-15.8 38.9L128 814v4.3c1.4 15.8 7.2 30.2 18.8 40.3 10.1 10.1 24.5 14.4 38.9 14.4h2.9l146.9-5.8c14.4 0 27.3-7.2 37.4-17.3l472.5-472.4c36-34.6 36-89.3 1.4-122.5z m-675 574.3l4.4-124.3 118.3 118.3-122.7 6z m181.1-34.5L204.7 646.7 597 254.4l148.2 148.2-392.3 392.2z m465.8-465.5l-31.5 31.4-148.4-148.2 31.6-31.4c6-6 13.5-9 21-9s15 3 21 9l106.3 106.3c11.9 11.9 11.9 29.9 0 41.9zM907.9 819.8H457.4c-16.6 0-30 13.4-30 30s13.4 30 30 30h450.5c16.6 0 30-13.4 30-30s-13.4-30-30-30zM497.9 769.6c0 16.6 13.4 30 30 30h380c16.6 0 30-13.4 30-30s-13.4-30-30-30h-380c-16.5 0-30 13.4-30 30z"
                              fill="#1296db"
                              p-id="3011"
                            /></svg>
                          </span>
                          <span v-if="item.startTime >= curDate.getTime()" title="取消预约">
                            <svg
                              t="1675656164436"
                              class="icon"
                              style="width: 1.5em;height: 1.5em;margin-left: 5px;cursor: pointer"
                              viewBox="0 0 1024 1024"
                              version="1.1"
                              xmlns="http://www.w3.org/2000/svg"
                              p-id="4023"
                              width="200"
                              height="200"
                              @click="cancel(item.id)"
                            ><path
                              d="M746.666667 981.333333H277.333333c-23.466667 0-42.666667-19.2-42.666666-42.666666V170.666667h554.666666v768c0 23.466667-19.2 42.666667-42.666666 42.666666z"
                              fill="#FF6060"
                              p-id="4024"
                            /><path
                              d="M832 256H192c-12.8 0-21.333333-8.533333-21.333333-21.333333V192c0-12.8 8.533333-21.333333 21.333333-21.333333h640c12.8 0 21.333333 8.533333 21.333333 21.333333v42.666667c0 12.8-8.533333 21.333333-21.333333 21.333333z"
                              fill="#E03232"
                              p-id="4025"
                            /><path
                              d="M341.333333 789.333333c-12.8 0-21.333333-8.533333-21.333333-21.333333V384c0-12.8 8.533333-21.333333 21.333333-21.333333s21.333333 8.533333 21.333334 21.333333v384c0 12.8-8.533333 21.333333-21.333334 21.333333zM512 789.333333c-12.8 0-21.333333-8.533333-21.333333-21.333333V384c0-12.8 8.533333-21.333333 21.333333-21.333333s21.333333 8.533333 21.333333 21.333333v384c0 12.8-8.533333 21.333333-21.333333 21.333333zM682.666667 789.333333c-12.8 0-21.333333-8.533333-21.333334-21.333333V384c0-12.8 8.533333-21.333333 21.333334-21.333333s21.333333 8.533333 21.333333 21.333333v384c0 12.8-8.533333 21.333333-21.333333 21.333333z"
                              fill="#FFFFFF"
                              p-id="4026"
                            /><path
                              d="M512 42.666667c-70.4 0-128 57.6-128 128h42.666667c0-46.933333 38.4-85.333333 85.333333-85.333334s85.333333 38.4 85.333333 85.333334h42.666667c0-70.4-57.6-128-128-128z"
                              fill="#FF6060"
                              p-id="4027"
                            /></svg>
                          </span>
                        </li>

                        <li v-else>
                          <s>{{ index + 1 }}、{{ item.subject }} ({{ item.time }}, {{ item.room }})</s>
                          <span style="margin-left: 15px;color: red">[已取消]</span>
                        </li>
                      </div>
                    </div>
                    <div slot="reference">
                      <div>
                        {{ data.day.split('-')[2] }}
                        <svg
                          t="1675413943981"
                          class="icon"
                          style="width: 1.5em;height: 1.5em;"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="13686"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M512 955.008A443.52 443.52 0 0 1 68.992 512 443.52 443.52 0 0 1 512 68.992 443.52 443.52 0 0 1 955.008 512c0 244.224-198.784 443.008-443.008 443.008zM512 116.992C294.208 116.992 116.992 294.208 116.992 512S294.208 907.008 512 907.008 907.008 729.792 907.008 512 729.792 116.992 512 116.992z"
                            fill="#1296db"
                            p-id="13687"
                          />
                          <path
                            d="M535.232 514.88a30.72 30.72 0 0 1-61.312 0V278.848a30.592 30.592 0 1 1 61.312 0v236.032z"
                            fill="#1296db"
                            p-id="13688"
                          />
                          <path
                            d="M494.528 547.2a30.72 30.72 0 0 1 20.096-57.984l222.976 77.312a30.72 30.72 0 0 1-20.096 57.984L494.528 547.2z"
                            fill="#1296db"
                            p-id="13689"
                          />
                        </svg>
                      </div>
                      <div
                        v-for="(item, index) in parseContent2(date, 0)"
                        class="one"
                        style="border-left: 3px solid #3797FF;"
                      ><!--v-html="parseContent(date, 0)"-->
                        <!-- 日历界面看到的信息 -->
                        <!--                        {{ parseContent(date) }}-->
                        <li v-if="item.status !== 5">{{ index + 1 }}、{{ item.subject }}</li>
                        <li v-else><s>{{ index + 1 }}、{{ item.subject }}</s></li>
                      </div>
                      <!--                  <div class="two">岳麓区20...</div>
                      <div class="three">岳麓区20...</div>-->
                    </div>
                  </el-popover>
                  <div v-else>
                    <div>
                      {{ data.day.split('-')[2] }}
                    </div>
                  </div>
                </template>
              </el-calendar>

              <el-dialog title="修改预约信息" :visible.sync="modifyFormVisiable" @closed="curDialogClosed()">
                <el-form
                  ref="form"
                  :rules="rules"
                  class="ruleForm"
                  :model="modifyAppointment"
                  label-position="left"
                  label-width="100px"
                  style="width: 400px; margin-left:50px;"
                >
                  <el-form-item label="会议主题" label-width="auto" prop="conferenceSubject" required>
                    <el-input v-model="modifyAppointment.conferenceSubject" style="width: 70%" />
                  </el-form-item>
                  <el-form-item label="预约用户" label-width="auto" prop="username" required>
                    <el-input v-model="modifyAppointment.appointmentUser.username" style="width: 70%" disabled />
                  </el-form-item>
                  <el-form-item label="参会人数" label-width="auto" prop="participationNums" required>
                    <!--        <el-input v-model="form.participationNums" style="width: 40%" />-->
                    <el-input-number
                      v-model="modifyAppointment.participationNums"
                      :min="2"
                      :max="modifyAppointment.appointmentRoom.roomType.roomTypeCapacity"
                      label="参会人数"
                    />
                  </el-form-item>
                  <el-form-item label="预约会议室" prop="roomName" required>
                    <el-input v-model="modifyAppointment.appointmentRoom.roomName" disabled />
                  </el-form-item>
                  <el-form-item label="会议室类型" prop="roomType" required>
                    <el-input v-model="modifyAppointment.appointmentRoom.roomType.roomTypeName" disabled />
                  </el-form-item>
                  <!--                  <el-form-item v-if="!customize" label="预约时段" label-width="auto" prop="appointTime" required>
                                      <el-input v-model="form.appointTime" style="width: 70%" disabled />
                                    </el-form-item>-->
                  <el-form-item label="开始时间" prop="appointmentStartTime" required>
                    <el-date-picker
                      v-model="modifyAppointment.appointmentStartTime"
                      type="datetime"
                      format="yyyy年MM月dd日 HH:mm:ss"
                      placeholder="选择会议开始日期时间"
                      disabled
                    />
                  </el-form-item>
                  <el-form-item label="结束时间" prop="appointmentEndTime" required>
                    <el-date-picker
                      v-model="modifyAppointment.appointmentEndTime"
                      type="datetime"
                      format="yyyy年MM月dd日 HH:mm:ss"
                      placeholder="选择会议结束日期时间"
                      disabled
                    />
                  </el-form-item>
                  <el-form-item label="状态" prop="status" required>
                    <el-select disabled placeholder="待审核" value="待审核" />
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="modifyFormVisiable = false">
                    取消
                  </el-button>
                  <el-button type="primary" @click="modifyAppoint('form')">
                    确认
                  </el-button>
                </div>
              </el-dialog>

              <el-dialog title="修改个人信息" width="40%" :center="true" :visible.sync="userInfoFormVisible">
                <hr size="1">
                <el-form label-position="top" :model="userInfo">
                  <el-form-item label="用户名" required>
                    <el-input v-model="userInfo.username" style="width: 80%;border-radius: 5px;" disabled />
                  </el-form-item>
                  <el-form-item label="手机号" required>
                    <el-input v-model="userInfo.phone" style="width: 80%;border-radius: 5px;" />
                  </el-form-item>
                  <el-form-item label="邮箱" required>
                    <el-input v-model="userInfo.email" style="width: 80%;border-radius: 5px;" />
                  </el-form-item>
                  <el-form-item label="状态" required>
                    <el-input v-model="userInfo.status" style="width: 80%;border-radius: 5px;" disabled />
                  </el-form-item>
                  <el-form-item label="新密码" required>
                    <el-input
                      v-model="userInfo.password"
                      placeholder="请输入密码"
                      style="width: 80%;border-radius: 5px;"
                      type="password"
                      show-password
                    />
                  </el-form-item>
                  <el-form-item label="确认密码" required>
                    <el-input
                      v-model="userInfo.password"
                      placeholder="请确认密码"
                      style="width: 80%;border-radius: 5px;"
                      type="password"
                      show-password
                    />
                  </el-form-item>
                  <!--                  <el-form-item label="验证码" required>
                                      <el-input v-model="form.verifyCode" placeholder="请输入验证码" style="width: 80%;border-radius: 5px;" />
                                      <img :src="imageSrc" style="width: 20%" @click="refreshCode">
                                    </el-form-item>-->
                </el-form>
                <a-button
                  type="primary"
                  ghost
                  style="width: 30%;margin-left: 20%"
                  :loading="loading"
                  @click="handleModify"
                >
                  修改
                </a-button>
                <a-button
                  type="primary"
                  ghost
                  style="width: 30%;margin-left: 10px"
                  :loading="loading"
                  @click="handleCancel"
                >
                  取消
                </a-button>
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
  deleteAppointmentsByID, exportInfoExcel,
  queryAppointDataById,
  queryAppointmentsByUser,
  updateAppointment
} from '@/api/appointment'
import moment from 'moment'
import { success, warning, error } from '@/utils/message'
import { queryVisitorInfo } from '@/api/commonApi'

export default {
  name: 'PersonalHome',
  inject: ['reload'],
  components: {},
  data() {
    return {
      rules: {
        conferenceSubject: [{ required: true, message: '请输入会议主题', trigger: 'blur' },
          { max: 10, message: '主题长度不能超过10', trigger: 'blur' }
        ]
      },
      modifyFormVisiable: false,
      username: this.$route.params.username,
      personalHomeInfo: {},
      showSelect: true,
      isOpen: true,
      sortData: [],
      lableData: [],
      userInfo: {
        email: '',
        authorities: [{ authority: '' }]
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
      visitor: '',
      position: '',
      weather: '',
      userInfoFormVisible: false,
      loading: false
    }
  },
  created() {
    this.getPersonalHomeInfo()
    this.init()
    // this.$el.querySelector('div.el-calendar__title').remove()
  },
  methods: {
    init() {
      queryAppointmentsByUser().then(response => {
        // console.log(response.data.data)
        this.appointInfo = response.data.data
      })
      queryVisitorInfo().then(response => {
        this.position = response.data.location
        this.weather = response.data.tq + '(' + response.data.low.split('°')[0] + '~' + response.data.high + ')'
        console.log(this.position, this.weather)
      })
    },
    getPersonalHomeInfo() {
      getInfo().then(response => {
        console.log(response.data.data)
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
    isView(date, data) {
      for (let i = 0; i < this.appointInfo.length; i++) {
        if (moment(this.appointInfo[i].appointmentStartTime).dayOfYear() === moment(date).dayOfYear()) {
          return true
        }
      }
      return false
    },
    parseContent(date, type) {
      let msg = '<ul style="margin-bottom: 0px">'
      // moment().format('HH:mm:ss')
      let index = 1
      for (let i = 0; i < this.appointInfo.length; i++) {
        if (moment(this.appointInfo[i].appointmentStartTime).dayOfYear() === moment(date).dayOfYear()) {
          if (type === 1) {
            msg += '<li @click="hi()">' + (index++) + '. ' + this.appointInfo[i].conferenceSubject + '('
            msg += moment(this.appointInfo[i].appointmentStartTime).format('HH:mm:ss') + '~' + moment(this.appointInfo[i].appointmentEndTime).format('HH:mm:ss')
            msg += ', ' + this.appointInfo[i].appointmentRoom.roomName + '-' + this.appointInfo[i].appointmentRoom.roomType.roomTypeName
            msg += ') <a><svg t="1675654418193" class="icon" style="height: 1.5em;width: 1.5em" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2708" width="200" height="200"><path d="M684.202667 117.248c15.893333-15.872 42.154667-15.36 58.922666 1.408l90.517334 90.517333c16.661333 16.661333 17.344 42.986667 1.429333 58.922667l-445.653333 445.653333c-7.936 7.914667-23.104 16.746667-34.218667 19.776l-143.701333 39.253334c-21.909333 5.994667-35.114667-7.104-29.568-28.949334l37.248-146.773333c2.773333-10.944 11.562667-26.346667 19.392-34.176l445.653333-445.653333zM268.736 593.066667c-2.901333 2.901333-8.106667 12.074667-9.130667 16.021333l-29.12 114.773333 111.957334-30.570666c4.437333-1.216 13.632-6.549333 16.810666-9.728l445.653334-445.653334-90.517334-90.496-445.653333 445.653334zM682.794667 178.986667l90.517333 90.517333-30.186667 30.186667-90.496-90.517334 30.165334-30.165333z m-362.026667 362.048l90.496 90.517333-30.165333 30.165333-90.517334-90.496 30.165334-30.186666zM170.666667 874.666667c0-11.776 9.429333-21.333333 21.461333-21.333334h661.077333a21.333333 21.333333 0 1 1 0 42.666667H192.128A21.333333 21.333333 0 0 1 170.666667 874.666667z" fill="#3D3D3D" p-id="2709"></path></svg></a></li>'
          } else {
            msg += '<li>' + (index++) + '. ' + this.appointInfo[i].conferenceSubject + '</li>'
          }
        }
      }
      msg += '</ul>'
      // console.log(msg)
      return msg
    },
    parseContent2(date, type) {
      const data = []
      // let msg = '<ul style="margin-bottom: 0px">'
      // moment().format('HH:mm:ss')
      // let index = 1
      for (let i = 0; i < this.appointInfo.length; i++) {
        if (moment(this.appointInfo[i].appointmentStartTime).dayOfYear() === moment(date).dayOfYear()) {
          const item = {
            'id': undefined,
            'subject': '',
            'time': '',
            'room': '',
            'status': undefined,
            startTime: undefined
          }
          item.id = this.appointInfo[i].id
          item.status = this.appointInfo[i].appointmentStatus
          item.startTime = moment(this.appointInfo[i].appointmentStartTime).toDate().getTime()
          // console.log(item.startTime)
          if (type === 1) {
            item.subject = this.appointInfo[i].conferenceSubject
            item.time = moment(this.appointInfo[i].appointmentStartTime).format('HH:mm:ss') + '~' + moment(this.appointInfo[i].appointmentEndTime).format('HH:mm:ss')
            item.room = this.appointInfo[i].appointmentRoom.roomName + '-' + this.appointInfo[i].appointmentRoom.roomType.roomTypeName
            // msg += ') <a><svg t="1675654418193" class="icon" style="height: 1.5em;width: 1.5em" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2708" width="200" height="200"><path d="M684.202667 117.248c15.893333-15.872 42.154667-15.36 58.922666 1.408l90.517334 90.517333c16.661333 16.661333 17.344 42.986667 1.429333 58.922667l-445.653333 445.653333c-7.936 7.914667-23.104 16.746667-34.218667 19.776l-143.701333 39.253334c-21.909333 5.994667-35.114667-7.104-29.568-28.949334l37.248-146.773333c2.773333-10.944 11.562667-26.346667 19.392-34.176l445.653333-445.653333zM268.736 593.066667c-2.901333 2.901333-8.106667 12.074667-9.130667 16.021333l-29.12 114.773333 111.957334-30.570666c4.437333-1.216 13.632-6.549333 16.810666-9.728l445.653334-445.653334-90.517334-90.496-445.653333 445.653334zM682.794667 178.986667l90.517333 90.517333-30.186667 30.186667-90.496-90.517334 30.165334-30.165333z m-362.026667 362.048l90.496 90.517333-30.165333 30.165333-90.517334-90.496 30.165334-30.186666zM170.666667 874.666667c0-11.776 9.429333-21.333333 21.461333-21.333334h661.077333a21.333333 21.333333 0 1 1 0 42.666667H192.128A21.333333 21.333333 0 0 1 170.666667 874.666667z" fill="#3D3D3D" p-id="2709"></path></svg></a></li>'
          } else {
            item.subject = this.appointInfo[i].conferenceSubject
            // msg += '<li>' + (index++) + '. ' + this.appointInfo[i].conferenceSubject + '</li>'
          }
          data[data.length] = item
        }
      }
      // console.log(msg)
      return data
    },
    modify(id) {
      // success('修改: ' + id)
      queryAppointDataById(id).then(response => {
        this.modifyAppointment = response.data.data
        this.modifyFormVisiable = true
        console.log(this.modifyAppointment)
      })
    },
    modifyAppoint(formName) {
      //
      updateAppointment(this.modifyAppointment).then(response => {
        if (response.data.data) {
          success('修改成功')
          this.reload()
        } else {
          warning('修改失败, 请重试!')
        }
      })
    },
    cancel(id) {
      this.$confirm('确认要取消本次预约？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAppointmentsByID(id).then(response => {
          console.log(response.data.data)
        }).catch(e => {
          console.log(e)
          error('取消预约失败')
        })
        this.reload()
        success('预约取消成功')
      }).catch(() => {
        warning('取消本次操作')
      })
      // warning('取消[' + id + ']的预约')
    },
    curDialogClosed() {
      this.modifyFormVisiable = false
      this.modifyAppointment = undefined
    },
    handleModify() {
      //
    },
    handleCancel() {
      //
    },
    handleDownload() {
      exportInfoExcel(null).then(response => {
        // success(response.data)
        // console.log(response.data)
        const _res = response.data
        const blob = new Blob([_res], { type: 'application/vnd.ms-excel;charset=utf-8' })
        const downloadElement = document.createElement('a')
        const href = window.URL.createObjectURL(blob) // 创建下载的链接
        downloadElement.href = href
        // downloadElement.download = fileName; //下载后文件名x
        downloadElement.download = '预约信息汇总.xlsx' // 下载后文件名
        document.body.appendChild(downloadElement)
        downloadElement.click() // 点击下载
        document.body.removeChild(downloadElement) // 下载完成移除元素
      })
    },
    parseRoleName(role) {
      console.log('role', role)
      switch(role) {
        case 1 : return '超级管理员'
        case 2 : return '普通管理员'
        case 3 : return '普通用户'
        default: return '未知'
      }
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

.align-items {
  display: flex;
  align-items: center;
}

.header {
  padding: 20px 20px 0;
  background-color: #fff;
  justify-content: space-between;
}

.icon-a {
  width: 20px;
  height: 20px;
}

.title {
  color: #101010;
  font-size: 16px;
}

::v-deep .el-calendar-table__row .prev {
  width: 120px;
}

::v-deep .el-calendar-table .el-calendar-day {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 8px;
  height: 150px;
  /*width: 120px;*/
}

.one {
  /*height: 50px;*/
  padding: 0 4px;
  line-height: 20px;
  /*border-left: 3px solid #3797FF;*/
  background: #DBECFF;
  font-size: 12px;
  margin-top: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.two {
  height: 20px;
  line-height: 20px;
  padding: 0 4px;
  border-left: 3px solid #FF6161;
  background: #FFE1E1;
  font-size: 12px;
  margin-top: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.three {
  height: 20px;
  line-height: 20px;
  font-size: 12px;
  padding: 0 4px;
  border-left: 3px solid #4EC95B;
  background: #E4FFE6;
  margin-top: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.popover-bgc {
  /*background-color: #000 !important;*/
  background-color: #bae7b8 !important;
  padding: 12px;
  border-radius: 6px;
}

.popover-bgc .one,
.popover-bgc .two,
.popover-bgc .three {
  /* background-color: #000 !important;*/
  background-color: #bae7b8 !important;
  color: #fff;
}
</style>

<style>
.popover-bgcolor {
  padding: 0 !important;
}

.calendar_class .el-calendar-table:not(.is-range) td.next, .calendar_class .el-calendar-table:not(.is-range) td.prev {
  pointer-events: none;
}

.calendar_class .el-calendar__header {
  display: none !important;
}

.calendar_class .el-calendar__body {
  padding: 0 20px 35px
}

.calendar_class .el-calendar-day {
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
  left: 55%;
  transform: translate(-50%);
  font-size: 20px;
  color: #4e8cda;
}

.el-calendar-table thead th:before {
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

.el-button-group > .el-button:not(:first-child):not(:last-child) {
  display: none;
}

.el-button-group > .el-button:first-child:before {
  content: '<';
}

.el-button-group > .el-button:last-child:before {
  content: '>';
}

.el-button-group > .el-button:first-child span, .el-button-group > .el-button:last-child span {
  display: none;
}
</style>
