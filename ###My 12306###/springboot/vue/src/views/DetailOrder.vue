<!--订单管理-->
<template>
    <div>
        <div style="margin: 10px 0">
            <div class="block"><el-date-picker style="width: 200px" v-model="date" type="date" placeholder="请输入下单日期" format="yyyy-MM-dd" value-format="yyyy-MM-dd" suffix-icon="el-icon-message" class="ml-5"></el-date-picker></div>
            <!-- <el-input style="width: 200px" placeholder="请输入下单日期" suffix-icon="el-icon-date" class="ml-5" v-model="date"></el-input> -->
            <el-input style="width: 200px;padding-top: 10px" placeholder="请输入订单编号"  class="ml-5" v-model="oid"></el-input>
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
            <!-- <el-upload action="http://localhost:9090/user/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" style="display: inline-block">
                <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
            </el-upload>
            <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button> -->
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="doid" label="订单状态编号" width="100"></el-table-column>
            <el-table-column prop="oid" label="订单编号" type="date" width="100"></el-table-column>
            <el-table-column prop="date" label="下单日期" width="150"></el-table-column>
            <el-table-column prop="createAt" label="下单时间" width="150"></el-table-column>
            <!--                <template v-slot="scope">-->
            <!--                    <span v-if="scope.row.role">{{ roles.find(v => v.flag === scope.row.role) ? roles.find(v => v.flag === scope.row.role).name : ''  }}</span>-->
            <!--                </template>-->
            <el-table-column prop="status" label="订单状态" ></el-table-column>
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
                            @confirm="del(scope.row.doid)"
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
                <el-form-item label="订单编号">
                    <el-input v-model="form.oid" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="下单日期">
                <el-date-picker v-model="form.date" format="yyyy-MM-dd" value-format="yyyy-MM-dd" autocomplete="off"></el-date-picker>
                </el-form-item>
                <!-- <el-form-item label="下单时间">
                    <el-input v-model="form.createAt" autocomplete="off"></el-input>
                </el-form-item> -->
                <el-form-item label="下单时间">
                    <el-time-picker v-model="form.createAt" format="hh:mm:ss" value-format="hh:mm:ss"> </el-time-picker>
                </el-form-item>
                <el-form-item label="订单状态">
                    <el-input v-model="form.status" autocomplete="off"></el-input>
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
        name: "DetailOrder",
        data() {
            return {
                
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                date: '',
                oid: "",
                form: {},
                dialogFormVisible: false,
                multipleSelection: [],
                roles: []
                
                
            }
        },
        created() {
            this.load()
        },
        pickerOptions: {
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
        },
        methods: {
            load() {
                this.request.get("/detail-order/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        date: this.date,
                        oid: this.oid,
                        
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
                this.request.post("/detail-order", this.form).then(res => {
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
            del(doid) {
                this.request.delete("/detail-order/" + doid).then(res => {
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
                let ids = this.multipleSelection.map(v => v.doid)  // [{}, {}, {}] => [1,2,3]
                this.request.post("/detail-order/del/batch", ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset() {
                this.date = ''
                this.oid = ""
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
                window.open("http://localhost:9090/detail-order/export")
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