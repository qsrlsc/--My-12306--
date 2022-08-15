<template>
    <el-row class="tac">
        <el-col :span="12">
            <el-menu
                    default-active="111"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    style="width: 200px;min-height: calc(200vh - 50px)"
            >
                <el-menu-item index="111">
                    <i class="el-icon-view"></i>
                    <span slot="title" @click="qsr_shouye">首页</span>
                </el-menu-item>
                <el-menu-item index="7">
                    <i class="el-icon-user-solid"></i>
                    <span slot="title" @click="qsr_user">账号管理</span>
                </el-menu-item>
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-s-goods"></i>
                        <span>列车维护</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="1-1" @click="qsr_carstyle">列车管理</el-menu-item>
                        <el-menu-item index="1-2" @click="qsr_carstaus">列车状态</el-menu-item>
                        <el-menu-item index="1-3">车次详情</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title">
                        <i class="el-icon-menu"></i>
                        <span>订单维护</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="2-1" @click="qsr_order">订单管理</el-menu-item>
                        <el-submenu index="2-2">
                            <template slot="title">退款维护</template>
                            <el-menu-item index="2-2-1">退款管理</el-menu-item>
                            <el-menu-item index="2-2-2">退款--卖家处理申请</el-menu-item>
                            <el-menu-item index="2-2-3">退款--成功</el-menu-item>
                            <el-menu-item index="2-2-4">退款--关闭</el-menu-item>
                        </el-submenu>
                        <el-menu-item index="2-3">改签订单</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-warning"></i>
                        <span>界面维护</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="3-1">轮播图管理</el-menu-item>
                        <el-menu-item index="3-2">导航分类</el-menu-item>
                        <el-menu-item index="3-3">列车信息</el-menu-item>
                        <el-menu-item index="3-4">系统设置</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="4">
                    <template slot="title">
                        <i class="el-icon-s-cooperation"></i>
                        <span>营销管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="4-1">优惠券管理</el-menu-item>
                        <el-menu-item index="4-2">优惠套餐</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="5">
                    <template slot="title">
                        <i class="el-icon-s-data"></i>
                        <span>数据统计</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="5-1">新增用户</el-menu-item>
                        <el-menu-item index="5-2">活跃用户</el-menu-item>
                        <el-menu-item index="5-3">业务数据</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-submenu index="6">
                    <template slot="title">
                        <i class="el-icon-s-marketing"></i>
                        <span>运营维护</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="6-1">我的资产</el-menu-item>
                        <el-menu-item index="6-2">对账单</el-menu-item>
                        <el-menu-item index="6-3">财务数据</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>

                <!--                <el-menu-item index="3" disabled>-->
                <!--                    <i class="el-icon-document"></i>-->
                <!--                    <span slot="title">导航三</span>-->
                <!--                </el-menu-item>-->
                <el-menu-item index="8">
                    <i class="el-icon-setting"></i>
                    <span slot="title" @click="qsr_setting">设置</span>
                </el-menu-item>
            </el-menu>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        name: "AdminMenu",
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            qsr_shouye(){
                this.$router.push("/shouye")
            },
            qsr_carstyle(){
                this.$router.push("/car")
            },
            qsr_setting(){
                this.$router.push("/admin")
            },
            qsr_user(){
                this.$router.push("/user")
            },
            qsr_carstaus(){
                this.$router.push("/staus")
            },
            qsr_order(){
                this.$router.push("/order")
            }
        }
    }
</script>

<style scoped>

</style>