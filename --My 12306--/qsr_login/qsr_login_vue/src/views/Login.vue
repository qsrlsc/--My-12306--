<!--登录界面-->
<template>
    <body id="poster">
        <el-form class="login-container" label-position="left" label-width="50px" ref="form" :model="form" :rules="rules">
        <h3 class="login_title">
            登录
            |
            <el-button type="text" style="color:#505458;" @click="toRegister">注册</el-button>
        </h3>
            <el-form-item label="账号">
                <el-input type="text" v-model="form.username" placeholder="请输入你的账号" id="uname" name="uname"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input type="password" v-model="form.password" show-password placeholder="请输入你的密码" id="upwd" name="upwd"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" style="width:100px;background-color: darkgrey;border: none " @click="qsr_login">登录</el-button>
                <el-button type="text" style="width:100px;color: #cac6c6" @click="topassword">？忘记密码</el-button>
            </el-form-item>
        </el-form>
    </body>
</template>

<script>
    import request from "../../utils/request";

    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: '',
                    password:'',
                },
                rules: {
                    username: [
                        {required: true, message:'请输入用户名',trigger: 'blur'},
                    ],
                    password: [
                        {required: true, message:'请输入密码',trigger: 'blur'},
                    ],
                }
            }
        },
        methods: {
            qsr_login() { //尚未与后端联系 先举个例子 管理者qsr和用户lsc
                // var username=document.getElementById("uname");
                // var username=document.getElementById("uname");
                // if(uname.value=="qsr" && upwd.value==123){ //模拟管理者登录成功
                //     this.loginform={};
                //     this.$message({
                //         message: '登录成功，欢迎来到秦淑润的世界',
                //         type: 'success'
                //     });
                //     this.$router.push("/shouye")
                // }else if(uname.value=="qsr" && upwd.value!=123){ //模拟用户存在但密码输入有误
                //     this.$message({
                //         showClose: true,
                //         message: '登录失败，您输入的密码有误',
                //         type: 'error'
                //     });
                // }else if(uname.value!="qsr"&&uname.value!="lsc") { //模拟用户不存在
                //     this.$message({
                //         showClose: true,
                //         message: '登录失败，该用户不存在',
                //         type: 'error'
                //     });
                // }else if(uname.value=="lsc" && upwd.value==908){ //模拟用户登录成功
                //     this.loginform={};
                //     this.$message({
                //         message: '登录成功，欢迎来到洛诗初的世界',
                //         type: 'success'
                //     });
                //     this.$router.push("/user")
                // }else if(uname.value=="lsc" && upwd.value!=908){ //模拟用户存在但密码输入有误
                //     this.$message({
                //         showClose: true,
                //         message: '登录失败，您输入的密码有误',
                //         type: 'error'
                //     });
                // }
                // // console.log('submit!',this.loginform); //打印loginform输入属性
                this.$refs['form'].validate((valid) =>{
                    if (valid){
                        request.post("/user/login",this.form).then(res =>{
                            if (res.code ==='0'){
                                this.$message({
                                    message: '登录成功，欢迎来到秦淑润的世界',
                                    type: 'success'
                                })
                                this.$router.push("/shouye")
                            }else {
                                this.$message({
                                    showClose: true,
                                    message: res.msg,
                                    type: 'error'
                                });
                            }
                        })
                    }
                })
            },
            toRegister (){
                this.$router.push("/register")
            },
            topassword (){
                this.$router.push("/password")
            }
        },
    }
</script>

<style scoped>
    #poster{
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    body{
        margin: 0px;
        padding: 0px;

    }
    .login-container{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #cac6c6;
        box-shadow: 0 0 25px #cac6c6;
    }
    .login_title{
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
</style>