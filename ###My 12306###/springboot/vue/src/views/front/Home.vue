<template>
  <div style="
    position: relative;
    left: -90px;
    top: 10px;
">
    <div
            v-for="(img_url, index) in img_src"
            :key="index"
            v-show="index === mark"
    style="padding-top: 10px">
      <img @mouseenter="stop"
           @mouseleave="start"
           :src="img_url.url"
           alt="这是轮播图"
      style="width: 120%"/>

      <el-button
              class="left"
              @click="go_back(index)"
              href="#">
        <i class="el-icon-arrow-left"></i>
      </el-button>

      <el-button
              class="right"
              @click="go(index)"
              href="#">
        <i class="el-icon-arrow-right"></i>
      </el-button>
    </div>

    <div class="bottom" style="color: #505458">
          <span
                  v-for="(img_url, index) in img_src"
                  :key="index"
                  :class="{ active: index === mark }" >
          </span>
    </div>

    <div class="tl4">
      <ul>
        <li>
          <img src="../../../public/12306-Reception/img/ico1.jpg"/>
          <p>
            <a href="">重点客户预约</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico2.jpg"/>
          <p>
            <a href="">遗失物品查找</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico3.jpg"/>
          <p>
            <a href="">约车服务</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico4.jpg"/>
          <p>
            <a href="">便民托运</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico5.jpg"/>
          <p>
            <a href="">车站引导</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico6.jpg"/>
          <p>
            <a href="">战车风采</a>
          </p>
        </li>
        <li>
          <img src="../../../public/12306-Reception/img/ico7.jpg"/>
          <p>
            <a href="">用户反馈</a>
          </p>
        </li>
      </ul>
    </div>
    <!--会员服务-->
    <div class="tl5">
      <div><img src="../../../public/12306-Reception/img/abanner01.jpg" width="100%"/> </div>
      <div class="tl5_fw"><img src="../../../public/12306-Reception/img/abanner02.jpg" width="100%"/></div>
      <div class="tl5_fw2"><img src="../../../public/12306-Reception/img/abanner03.jpg" width="100%"/></div>
      <div class="tl5_fw tl5_fw2"><img src="../../../public/12306-Reception/img/abanner04.jpg" width="100%"/></div>
    </div>
    <!--更多内容-->
    <div class="tl6">
      <span>铁路旅游</span>
      <a>更多&gt;</a>
    </div>
    <!--铁路旅游信息展示-->
    <div class="tl7">
      <div>
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div2">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1 tl7_div2">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1 tl7_div2">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
      <div class="tl7_div1 tl7_div2">
        <div class="tl7_img">
          <img src="../../../public/12306-Reception/img/scope1.jpg" width="100%"/>
          <p class="tl7_p1">“环西部火车游” 高品质旅游版专线列车</p>
          <p class="tl7_p2">&yen; &nbsp;2590</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontHome",
  data() {
    return {
      img_src: [
        {url: require("../../../public/12306-Reception/img/banner09.jpg")},
        {url: require("../../../public/12306-Reception/img/banner11.jpg")},
        {url: require("../../../public/12306-Reception/img/banner12.jpg")},
        {url: require("../../../public/12306-Reception/img/banner13.jpg")},
      ],
      mark: 0,//现在是第几张
      timer: ''//设置定时器
    }
    },
      methods: {
        recycle(){
          if (this.mark === this.img_src.length - 1) {
            this.mark = 0;
          } else {
            this.mark++;
          }
        },
        stop(){
          clearInterval(this.timer);
        },
        start() {
          this.recycle();//先调用一次
          this.timer=setInterval(() =>
                  {this.recycle(),console.log('开始计时');}
                  , 2000);
        },
        go(index) {
          if (index < this.img_src.length - 1) {
            this.mark = index + 1;
          }
        },
        go_back(index) {
          if (index > 0) {
            this.mark = index - 1;
          }
        }
      },
  mounted(){
    console.log('挂载完成');
    this.start();
  },
  beforeDestroy() {
    this.stop();
  },

}
</script>

<style>
@import "../../../public/12306-Reception/css/12306.css";

.cycle {
  width: 100%;
  height: 320px;
  position: relative;
}
.cycle img {
  width: 100%;
  height: 100%;
  border-radius: 20px;
  position: absolute;
  background-color: gainsboro;
  display: block;
}
.cycle button {
  width: 20px;
  height: 55px;
  position: absolute;
  top: 43%;
  right: 0px;
  opacity: 0.5;
  text-align: center;
}
.cycle .left {
  left: 0px;
}
.cycle .left i {
  margin-left: -10px;
}

.bottom {
  position: absolute;
  bottom: 1px;
  left: calc(50% - 27px);
}
.cycle .bottom span {
  width: 14px;
  height: 14px;
  border-radius: 7px;
  background-color: rgba(110, 102, 102, 0.849);
  opacity: 0.4;
  margin: 10px;
  display: inline-block;
  /* span是行内元素 */
}
/* 圆圈圈激活后*/
.cycle .active {
  /*数值分别是：水平偏移，上下偏移，模糊，大小，颜色 */
  box-shadow: 0px 0px 2px 2px #53a8ff;
  background-color: #a0cfff !important;
  opacity: 0.6;
}
  .left{
    position: relative;
    right: 0px;
    top: -150px;
  }
  .right{
    position: relative;
    left: 1100px;
    top: -150px;
  }



</style>
