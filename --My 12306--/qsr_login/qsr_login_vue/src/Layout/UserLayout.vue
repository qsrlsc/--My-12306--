<template>
    <div>
            <!--   侧边栏-->
            <UserMenu />
            <!--   内容区域-->
    </div>
</template>

<script>
    import Header from '../components/Header'
    import UserMenu from "../components/UserMenu";
    export default {
        name: "UserLayout",
        components:{
            UserMenu
        }
    }
</script>

<style scoped>

</style>