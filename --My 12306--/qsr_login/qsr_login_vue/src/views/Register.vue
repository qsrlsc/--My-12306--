<!--注册界面-->
<template>
    <body id="poster">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="register-container">
<!--            prop用于后面的判断中的值赋-->
            <h3 class="register_title">
                注册界面
            </h3>
            <el-form-item label="用户名" prop="username">
                <el-input v-model.number="ruleForm.username" prefix-icon="el-icon-user-solid" ></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickName">
                <el-input v-model.number="ruleForm.nickName" prefix-icon="el-icon-place" ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" autocomplete="off" prefix-icon="el-icon-lock" ></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" prefix-icon="el-icon-lock" ></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phonenumber">
                <el-input v-model.number="ruleForm.phonenumber" prefix-icon="el-icon-phone-outline"></el-input>
            </el-form-item>
            <el-form-item label="年龄" prop="age">
                <el-input v-model.number="ruleForm.age" prefix-icon="el-icon-star-on" ></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="sex" >
                <el-select v-model="ruleForm.sex" placeholder="请选择你的性别">
                    <el-option label="男" value="Man" ></el-option>
                    <el-option label="女" value="Woman"></el-option>
                    <el-option label="未知" value="Unknown"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" style="background-color: darkgrey;border: none " @click="submitForm('ruleForm')">提交</el-button>
                <el-button type="primary" style="background-color: #4f6763; border: none " @click="resetForm('ruleForm')">重置</el-button>
                <el-button type="success" style="background-color: #575767; border: none " @click="out_register">取消注册</el-button>
            </el-form-item>
        </el-form>
    </body>
</template>

<script>
    import request from "../../utils/request";

    export default {
        name: "Register",
        data() {
            var validatePhonenumber = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入你的电话号码'));
                }else {
                    callback();
                }
            };
            var validateusername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('用户名将唯一作为身份识别，不能为空'));
                }else {
                    callback();
                }
            };
            var checkAge = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('年龄不能为空'));
                }
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 18) {
                            callback(new Error('必须年满18岁'));
                        } else {
                            callback();
                        }
                    }
                }, 1000);
            };
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validateRegion = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请选择你的性别'));
                }else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    username:'',
                    nickName:'',
                    password: '',
                    checkPass: '',
                    phonenumber: '',
                    age: '',
                    sex:'',
                },
                rules: {
                    username: [
                        { validator: validateusername, trigger: 'blur' },
                        { min:2,max:9,message:"以字母开头，长度为2~9个字符", trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    phonenumber:[
                        { validator: validatePhonenumber, trigger: 'blur' },
                        { pattern: /^1[34578]\d{9}$/,message:"请确认手机号是否正确", trigger: 'blur' }
                    ],
                    age: [
                        { validator: checkAge, trigger: 'blur' }
                    ],
                    sex: [
                        { validator: validateRegion, trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            submitForm() {
                // this.$refs[formName].validate((valid) => {
                //     if (valid) {
                //         this.$message({
                //             showClose: true,
                //             message: '恭喜你，注册成功',
                //             type: 'success'
                //         });
                //         this.$router.push("/")
                //     } else {
                //         console.log('error submit!!');
                //         return false;
                //     }
                // });
                this.$refs['ruleForm'].validate((valid) => {
                        if (valid) {
                            request.post("/user/register",this.ruleForm).then(res =>{
                                if (res.code ==='0'){
                                    this.$message({
                                        message: '注册成功，请登录',
                                        type: 'success'
                                    })
                                    this.$router.push("/")
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
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            out_register(){
                this.$router.push("/")
            }
        }
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
    .register-container{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #cac6c6;
        box-shadow: 0 0 25px #cac6c6;
    }
    .register_title{
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
</style>