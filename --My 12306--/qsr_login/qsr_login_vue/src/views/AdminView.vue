<!--后台管理页面中的设置渲染-->
<template>
  <div class="admin" style="padding: 10px">
      <img :src="imgUrl2" class="background">
      <img :src="imgUrl" class="admin3">
      <div class="admin2"><el-button class="admin1-1" @click="qsr_person" type="text">账号与安全</el-button></div>
      <div class="admin2"><el-button class="admin1-2" type="text">消息通知</el-button></div>
      <div class="admin2"><el-button class="admin1-3" type="text">通用</el-button></div>
      <div class="admin2"><el-button class="admin1-4" @click="qsr_outlogin" type="text">退出登录</el-button></div>
      <img :src="imgUrl1" class="admin4">
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'AdminView',
  components: {
  },
    methods: {
        qsr_outlogin(){
            this.$router.push("/");
        },
        qsr_person(){
            this.$router.push("/person")
        }
    },
  data(){
    return {
        imgUrl:require("../assets/12306标识.png"),
        imgUrl1:require("../assets/12306页面2.png"),
        imgUrl2:require("../assets/火车背景图.jpeg"),
    }
  }
}
</script>

<style>
    .background{
        height: 645px;
        width: 1303px;
        position:absolute;
        top:60px;
        left:208px;
    }
    .admin1-1{
        color: #efe6f1 ;
        font-size:5vh;
        position:absolute;
        top:140px;
        left:220px;
    }
    .admin1-2{
        color: #efe6f1 ;
        font-size:5vh;
        position:absolute;
        top:200px;
        left:220px;
    }
    .admin1-3{
        color: #efe6f1 ;
        font-size:5vh;
        position:absolute;
        top:260px;
        left:220px;
    }
    .admin1-4{
        color: #efe6f1 ;
        font-size:5vh;
        position:absolute;
        top:320px;
        left:220px;
    }
    .admin2{
        padding: 30px;
    }
    .admin3{
        position:absolute;
        top:60px;
        left:208px;
    }
    .admin4{
        position:absolute;
        bottom:0px;
        left:208px;
        background-size: 100% 100%;
        width: 1303px;
        height: 300px;
    }
</style>