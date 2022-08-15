<!--后台管理页面中的账号管理渲染-->
<template>
    <div style="padding: 10px"> <!--给表格和其他空一点间隙--->
        <!---  功能展示区-->
        <div style="margin: 10px 0"> <!--上下10 左右0间隔--->
            <el-button
                    class="filter-item"
                    style="margin-left: 10px;"
                    type="primary"
                    icon="el-icon-edit"
                    @click="add"
            >新增</el-button>
            <el-button type="success">导入</el-button>
            <el-button type="warning" disabled>导出</el-button>
<!--            <el-button type="text" @click="open">点击打开 Message Box</el-button>-->
        </div>
        <!---   搜索区-->
        <div style="margin: 10px 0">
            <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
            <el-button type="primary" round style="margin-left: 5px" @click="load">查询</el-button>
        </div>
        <!---   数据展示区-->
        <el-table :data="tableData"
                  border
                  stripe
                  style="width: 100%;background-color: #8e8a8a">
            <el-table-column prop="id" label="ID"  sortable />
            <el-table-column prop="username" label="用户名"  />
            <el-table-column prop="nickName" label="昵称" />
            <el-table-column prop="age" label="年龄" />
            <el-table-column prop="sex" label="性别" />
            <el-table-column prop="address" label="地址" />
            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button type="text" style="color: lightskyblue" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
                        <template #reference>
                            <el-button type="text" style="color: #dc143c" >删除</el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :currentPage="currentPage"
                    :page-sizes="[5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
            />
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
    </div>
</template>

<script>


    import request from "../../utils/request";

    export default {
        name: 'User',
        components: {

        },
        data(){
            return{
                form:{},
                dialogFormVisible: false,//控制新增对话框的显示与隐藏 默认关闭
                search:' ', //UserController中模糊查询
                currentPage:1,
                pageSize:10,
                total:0,
                tableData:[]
            }
        },
        created() { //加载方法
            this.load()
        },
        methods:{
            //把数据库中的数据传到后台表格中
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
            add(){
                this.dialogFormVisible=true //打开弹窗
                this.form= {} //清空弹窗内容
            },
            //需要一个api进行数据交互 将request对象引入
            save(){
                if(this.form.id) { //更新 有该id(主键)
                    request.put("/user", this.form).then(res => {
                        console.log(res) //对res中的code进行判断操作是否成功
                        if(res.code ==='0'){
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
                } else {  //新增
                    /**
                     *  //实现数据交互 存在跨域问题
                     *
                     * //链式操作 把结果放在res 通过=>取到 不能单独写/user 因为端口不一样必须加上所属端口
                     * request.post("http://localhost:9090/user",this.form).then(res =>{
                     * 但存在跨域问题 通过api申请
                     * */
                    //请求后台接口 请求参数
                    request.post("/user",this.form).then(res =>{
                        console.log(res)//打印res
                        if(res.code ==='0'){
                            this.$message({
                                type: "success",
                                message: "新增成功"
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
                    //form在弹窗中和属性绑定了 最后变成一个json 作为请求参数
                }
            },
            //对form对象赋值 避免取消等操作对数据造成影响 进行深拷贝 即form对象独立 与之前行对象隔离开
            handleEdit(row){
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogFormVisible =true
            },
            handleDelete(id){
                console.log(id)
                request.delete("/user/" + id).then(res => {
                    if(res.code ==='0'){
                        this.$message({
                            type: "success",
                            message: "删除成功"
                        })
                    } else {
                        this.$message({
                            type: "error",
                            message: res.msg
                        })
                    }
                    this.load() //删除之后刷新表格数据
                })
            },
            handleSizeChange(pageSize){ //改变当前每页个数触发
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum){ //改变当前页码触发
                this.currentPage = pageNum
                this.load()
            },
        }
    }
</script>
