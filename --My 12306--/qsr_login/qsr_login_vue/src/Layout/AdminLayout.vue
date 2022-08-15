<template>
    <div>
        <!--   头部-->
        <Header />
        <!--   主体-->
        <div style="display: flex">
            <!--   侧边栏-->
            <AdminMenu />
            <!--   内容区域-->
            <router-view style="flex: 1;background-color: #8e8a8a"/>
        </div>
    </div>
</template>

<script>

    import Header from '../components/Header'
    import AdminMenu from "../components/AdminMenu";
    export default {
        name: "AdminLayout",
        components:{
            Header,
            AdminMenu
        }
    }
</script>

<style scoped>

</style>