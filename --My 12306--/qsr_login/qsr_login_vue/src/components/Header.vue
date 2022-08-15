<template>
    <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #8e8a8a;display: flex;background-color: #545c64;"> <!--下划线--->
        <div style="width: 200px;padding-left: 30px; font-weight: bold;color: #fff">仿12306后台管理系统</div>
        <div style="flex: 1"></div>
        <div style="width: 100px" >
            <el-dropdown>
                <span class="el-dropdown-link" >
                管理员<i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown" >
                    <el-dropdown-item  @click="qsr_person">个人信息</el-dropdown-item>
                    <el-dropdown-item  disabled>历史足迹</el-dropdown-item>
                    <el-dropdown-item  @click="qsr_outlogin">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Header",
        methods: {
            qsr_outlogin(){
                this.$router.push("/");
            },
            qsr_person(){
                this.$router.push("/person")
            }
        }
    }
</script>

<style scoped>
    .el-dropdown-link {
        cursor: pointer;
        color: #ffd04b;
        width: 200%;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
</style>