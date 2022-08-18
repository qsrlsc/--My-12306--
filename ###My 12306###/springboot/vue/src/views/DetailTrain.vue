<!--列车管理-->
<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入订单详情编号" suffix-icon="el-icon-position" class="ml-5" v-model="dtid"></el-input>
            <el-input style="width: 200px" placeholder="请输入列车编号" suffix-icon="el-icon-message" class="ml-5" v-model="tid"></el-input>
            <el-input style="width: 200px" placeholder="请输入座位类型" suffix-icon="el-icon-search" v-model="seattype"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定批量删除这些数据吗？"
                    @confirm="delBatch"
            >
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
            <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
                <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
            </el-upload>
            <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="dtid" label="订单详情编号" width="100"></el-table-column>
            <el-table-column prop="tid" label="列车编号" width="100"></el-table-column>
            <el-table-column prop="ttype" label="列车类型" width="150"></el-table-column>
            <el-table-column prop="origin" label="始发站" width="150"></el-table-column>
            <!--                <template v-slot="scope">-->
            <!--                    <span v-if="scope.row.role">{{ roles.find(v => v.flag === scope.row.role) ? roles.find(v => v.flag === scope.row.role).name : ''  }}</span>-->
            <!--                </template>-->
            <el-table-column prop="destination" label="终点站" ></el-table-column>
            <el-table-column prop="starttime" label="出发时间" ></el-table-column>
            <el-table-column prop="arrivetime" label="到达时间" ></el-table-column>
            <el-table-column prop="mileage" label="里程" ></el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.id)"
                    >
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

        <el-dialog title="列车信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small" >
                <el-form-item label="列车编号">
                    <el-input v-model="form.tid" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="列车类型">
                    <el-input v-model="form.ttype" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="始发站">
                    <el-input v-model="form.origin" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="终点站">
                    <el-input v-model="form.destination" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出发时间">
                    <el-input v-model="form.starttime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="到达时间">
                    <el-input v-model="form.arrivetime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="里程">
                    <el-input v-model="form.mileage" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "DetailTrain",
        data() {
            return {
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                username: "",
                email: "",
                address: "",
                form: {},
                dialogFormVisible: false,
                multipleSelection: [],
                roles: []
            }
        },
        created() {
            this.load()
        },
        methods: {
            load() {
                this.request.get("/seat/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        username: this.username,
                        email: this.email,
                        address: this.address,
                    }
                }).then(res => {

                    this.tableData = res.data.records
                    this.total = res.data.total

                })

                // this.request.get("/role").then(res => {
                //     this.roles = res.data
                // })
            },
            save() {
                this.request.post("/seat", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            handleAdd() {
                this.dialogFormVisible = true
                this.form = {}
            },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.dialogFormVisible = true
            },
            del(id) {
                this.request.delete("/seat/" + id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })
            },
            handleSelectionChange(val) {
                console.log(val)
                this.multipleSelection = val
            },
            delBatch() {
                let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
                this.request.post("/seat/del/batch", ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset() {
                this.username = ""
                this.email = ""
                this.address = ""
                this.load()
            },
            handleSizeChange(pageSize) {
                console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                console.log(pageNum)
                this.pageNum = pageNum
                this.load()
            },
            exp() {
                window.open("http://localhost:9090/seat/export")
            },
            handleExcelImportSuccess() {
                this.$message.success("导入成功")
                this.load()
            }
        }
    }
</script>

<style scoped>

</style>