<template>
    <!--    <h1>这里是个人中心</h1> 编辑资料暂未实现-->
    <div>
        <el-container>
            <el-aside width="355px">
                <div class="first">
                    <li>这</li>
                    <li>里</li>
                    <li>是</li>
                    <li>个</li>
                    <li>人</li>
                    <li>中</li>
                    <li>心</li>
                </div>
            </el-aside>
            <el-main class="second">
                <el-button class="el-icon-switch-button" type="text" style="color: #efe6f1 ;font-size:5vh;position:absolute;top:25px;right:15px;" @click="out_person">退出个人中心</el-button>
                <div class="card_a">
                    <section>
                        <h2>去上日落私藏<span>管理员</span> </h2>
                        <ul>
                            <li>用户名 / qsr</li>
                            <li>密码 / qsr021203</li>
                            <li>性别 / 女</li>
                            <li>年龄 / 19</li>
                            <li>地址 / 湖南常德</li>
                            <li>电话 / 18827255507</li>
                            <li>邮箱 / 2951757706@qq.com</li>
                        </ul>
                        <el-button type="text" style="color: white;font-size:8vh;position: fixed;bottom: 5rem;right: 2rem;" @click="add1">编辑资料</el-button>
                    </section>
                </div>
            </el-main>
        </el-container>
        <!--      新建和编辑的弹窗-->
        <el-dialog :visible.sync="dialogFormVisible" width="30%" title="提示">
            <!--  新增的弹窗表单 定义一个form的对象--->
            <el-form :model="form" label-width="120px">
                <el-form-item label="用户名">
                    <el-input v-model="form.username" />
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="form.nickName" /> <!--数据库中如下划线nick_name在vue中写成小驼峰形式 不然会报400或500的错--->
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age" />
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio v-model="form.sex" label="男">男</el-radio>
                    <el-radio v-model="form.sex" label="女">女</el-radio>
                    <el-radio v-model="form.sex" label="未知">未知</el-radio>
                </el-form-item>
                <el-form-item label="电话号码">
                    <el-input v-model="form.phonenumber" />
                </el-form-item>
                <el-form-item label="地址">
                    <el-input type="textarea" v-model="form.address" />
                </el-form-item>
            </el-form>
            <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>
    import request from "../../utils/request";

    export default {
        name: "Person",
        data(){
            return{
                form:{},
                dialogFormVisible: false,//控制新增对话框的显示与隐藏 默认关闭
            }
        },
        created() { //加载方法
            this.load()
        },
        methods:{
            load(){
                request.get("/user",{
                    //通过get方法的请求不能直接通过 要使用param把参数传进来 得到后台数据
                    params:{
                        pageNum: this.currentPage,
                        pageSize: this.pageSize,
                        search: this.search
                    }
                }).then(res => {
                    console.log(res)
                    this.tableData = res.data.records //在检查中找数据 把recors赋值给tableDa
                    this.total=res.data.total
                })
            },
            add1(){
                this.dialogFormVisible=true //打开弹窗
                this.form= {} //清空弹窗内容
            },
            out_person(){
                this.$router.push("/admin")
            },
            save() {
            if (this.form.id) { //更新 有该id(主键)
                request.put("/user", this.form).then(res => {
                    console.log(res) //对res中的code进行判断操作是否成功
                    if (res.code === '0') {
                        this.$message({
                            type: "success",
                            message: "更新成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load() //刷新表格数据
                    this.dialogFormVisible = false //关闭弹窗
                    })
                }
            }
        }
    }
</script>

<style scoped>
    .first{
        width:350px;
        height:100vh;
        border:2px dotted white;
        background-color: #545c64;
        font-size:10vh;
        color: #8ed3e1;
        font-weight:bold;
        text-align:center;
    }
    .second{
        width:300px;
        height:100vh;
        border:2px dotted white;
        background-color: #545c64;
        font-size:10vh;
        font-weight:bold;
        text-align:center;
    }
    * {
        margin: 0;
        padding: 0
    }

    .card_a {
        width: 1000px;
        height: 600px;
        position: absolute;
        left: 59.5%;
        top: 45%;
        margin-top: -230px;
        margin-left: -400px;
        background: #efe6f1;
        overflow: hidden;
        border-radius: 10px;
    }

    .card_a:before {
        content: "";
        position: absolute;
        width: 400px;
        height: 400px;
        border-radius: 100%;
        background: linear-gradient(45deg, #8ed3e1 8%, transparent, #efe6f1);
        bottom: -53px;
        left: -87px;
    }

    .card_a:after {
        content: "";
        position: absolute;
        width: 700px;
        height: 700px;
        border-radius: 100%;
        background: radial-gradient(#afdfef, #c8e8f2, transparent 60%, #efe6f1);
        top: -123px;
        right: -352px;
    }

    .card_a section {
        width: 600px;
        position: absolute;
        left: 100px;
        top: 80px;
        z-index: 9;
    }

    .card_a section h2 {
        font-size: 50px;
        letter-spacing: .1em;
        margin-bottom: 66px;
    }

    .card_a section span {
        font-size: 28px;
        font-weight: normal;
        margin-left: 50px;
        letter-spacing: normal;
    }

    .card_a img {
        float: right;
        width: 140px
    }

    .card_a section ul {
        border-left: #222 3px solid;
        padding-left: 28px;
    }

    .card_a section ul li {
        list-style: none;
        line-height: 38px;
        font-size: 18px;
    }

</style>