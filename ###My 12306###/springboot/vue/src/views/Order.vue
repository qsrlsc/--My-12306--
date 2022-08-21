<!--列车管理-->
<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入订单编号" suffix-icon="el-icon-search" v-model="oid" clearable></el-input>
            <el-input style="width: 200px" placeholder="请输入乘客编号" suffix-icon="el-icon-message" class="ml-5" v-model="pid" clearable></el-input>
            <el-input style="width: 200px" placeholder="请输入列车编号" suffix-icon="el-icon-position" class="ml-5" v-model="tid" clearable></el-input>
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
            <el-table-column prop="oid" label="订单编号" width="80"></el-table-column>
            <el-table-column prop="pid" label="乘客编号" width="80"></el-table-column>
            <el-table-column prop="tid" label="列车编号" width="120"></el-table-column>
            <el-table-column prop="uid" label="用户编号" width="120">
            </el-table-column>
            <el-table-column prop="doid" label="订单详情编号" ></el-table-column>
            <el-table-column prop="extraProduct" label="有无额外产品"></el-table-column>
            <el-table-column prop="insurance" label="有无保险" ></el-table-column>
            <el-table-column prop="discount" label="折扣" ></el-table-column>
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
                            @confirm="del(scope.row.oid)"
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
<!-- 表单验证：列车编号存在，乘客编号存在 -->
        <el-dialog title="订单信息" :visible.sync="dialogFormVisible" width="30%" v-dialogDrag>
            <el-form label-width="80px" size="small" >
                <el-form-item label="乘客编号">
                    <el-input v-model="form.pid" autocomplete="off" placeholder="请输入已存在的乘客"></el-input>
                </el-form-item>

                <el-form-item label="列车编号">
                    <el-input v-model="form.tid" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="用户编号">
                    <el-input v-model="form.uid" autocomplete="off" placeholder="请输入已存在的用户"></el-input>
                </el-form-item>
                <el-form-item label="订单详情编号">
                    <el-input v-model="form.doid" autocomplete="off"></el-input>
                </el-form-item>
                    <el-form-item label="有无额外产品" :label-width="formLabelWidth">
                        <el-select v-model="form.extraProduct" placeholder="请选择">
                        <el-option label="有" value="有"></el-option>
                        <el-option label="无" value="无"></el-option>
                        </el-select>
                    </el-form-item>
                <!-- <el-form-item label="有无额外产品">
                    <el-input v-model="form.extra_product" autocomplete="off"></el-input>
                </el-form-item> -->
                <!-- <el-form-item label="有无保险">
                    <el-input v-model="form.insurance" autocomplete="off"></el-input>
                </el-form-item> -->
                    <el-form-item label="有无保险" :label-width="formLabelWidth">
                        <el-select v-model="form.insurance" placeholder="请选择">
                        <el-option label="有" value="有"></el-option>
                        <el-option label="无" value="无"></el-option>
                        </el-select>
                    </el-form-item>
                <el-form-item label="折扣">
                    <el-input v-model="form.discount" autocomplete="off"></el-input>
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
        name: "Orders",
        data() {
            return {
                tableData: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                oid: "",
                pid: "",
                tid: "",
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
                this.request.get("/orders/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        oid: this.oid,
                        pid: this.pid,
                        tid: this.tid,
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
                this.request.post("/orders", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")                        
                        this.dialogFormVisible = false
                        this.load()
                    } else{
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
            del(oid) {
                this.request.delete("/orders/" + oid).then(res => {
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
                let ids = this.multipleSelection.map(v => v.oid)  // [{}, {}, {}] => [1,2,3]
                this.request.post("/orders/del/batch", ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            }, 


            reset() {
                this.oid = ""
                this.pid = ""
                this.tid = ""
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
                window.open("http://localhost:9090/orders/export")
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