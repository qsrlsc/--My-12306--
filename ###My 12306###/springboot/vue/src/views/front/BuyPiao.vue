<template>
    <div style="margin: 10px 0">
        <el-input style="width: 200px" placeholder="请输入起点" suffix-icon="el-icon-location-outline" v-model="origin"></el-input>
        <el-input style="width: 200px" placeholder="请输入终点" suffix-icon="el-icon-location-information" class="ml-5" v-model="destination"></el-input>
            <el-date-picker
                    style="padding: 5px"
                    v-model="sdate"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker>
        <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
        <el-button type="danger" slot="reference">批量购买 <i class="el-icon-circle-check"></i></el-button>
        <div style="color: #b8110a">当前余票：191</div>
        <div style="padding-top:10px;padding-bottom:5px;color: #6aa4b8">
            <span style="padding: 15px">二等座：123 张</span>
            <span style="padding: 15px">一等座：53 张</span>
            <span style="padding: 15px">商务座：15 张</span>
        </div>
        <div style="color: #b886b8">
            <span style="padding: 15px">站票：0 张</span>
            <span style="padding: 50px">硬座：0 张</span>
            <span style="padding: 18px">硬卧：0 张</span>
            <span style="padding: 15px">软卧：0 张</span>
            <span style="padding: 15px">高级软卧：0 张</span>
            <span style="padding: 15px; color: #686ab8">
                选购数量
                <input type="button" name="minus" value="-" class="shuliang"  @click="editCount(-1)" :disabled="buyCount==1">
                <input type="text" name="amount" v-model="buyCount" class="shuzhi">
                <input type="button" name="plus" value="+" class="shuliang"  @click="editCount(1)" :disabled="buyCount==productInfo">

            </span>
            <span style="color: #b83d42">
                总金额：￥
<!--                {{sum | toDecimal2}}-->
            </span>
        </div>
        <div style="padding-top: 10px;padding-bottom: 10px">
        <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="sdate" label="日期" ></el-table-column>
            <el-table-column prop="tid" label="列车编号" ></el-table-column>
            <el-table-column prop="origin" label="出发站" ></el-table-column>
            <el-table-column prop="destination" label="终点站" ></el-table-column>
            <el-table-column prop="starttime" label="出发时间" ></el-table-column>
            <el-table-column prop="mileage" label="里程" ></el-table-column>
            <el-table-column prop="arrivetime" label="到达时间"></el-table-column>
            <el-table-column prop="seattype" label="座位类型"></el-table-column>
            <el-table-column prop="money" label="票价"></el-table-column>
            <el-table-column label="操作"  width="200" align="center" fixed="right">
                <template slot-scope="scope">
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定购买吗？"
                            @confirm="buypiao"
                    >
                        <el-button type="danger" slot="reference">购买 <i class="el-icon-circle-check"></i></el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        </div>
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

        <el-dialog title="订单信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small" >
                <el-form-item label="日期">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="列车编号">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="起点站">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="终点站">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="出发时间">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="到达时间">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="座位类别">
                    <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="票价">
                <el-input  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="乘客" :label-width="formLabelWidth">
                    <el-select v-model="passager" placeholder="请选择乘客">
                        <el-option label="秦淑润" value="passager1"></el-option>
                        <el-option label="洛诗初" value="passager2"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="$router.push('/front/buysuccess')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "BuyPiao",
        data(){
            return{
                //购买数量
                buyCount:1 , //默认购买1个
                productInfo:999, //最大库存
                origin:'',
                destination:'',
                sdate:'',
                dialogFormVisible: false,
                passager:'',
                formLabelWidth: '120px',
              tableData: [{
                  sdate: '2022-08-20',
                  tid: 'G2578',
                  origin:'南京南站',
                  destination:'北京南站',
                  starttime: '07:16',
                  mileage: '857km',
                  arrivetime: '12:08',
                  seattype: '二等座',
                  money: '￥445',

              },{
                  sdate: '2022-08-20',
                  tid: 'G2578',
                  origin:'南京南站',
                  destination:'北京南站',
                  starttime: '07:16',
                  mileage: '857km',
                  arrivetime: '12:08',
                  seattype: '一等座',
                  money: '￥747'
              }, {
                  sdate: '2022-08-20',
                  tid: 'G2578',
                  origin:'南京南站',
                  destination:'北京南站',
                  starttime: '07:16',
                  mileage: '857km',
                  arrivetime: '12:08',
                  seattype: '商务座',
                  money: '￥1405'
              }, {
                  sdate: '2022-08-20',
                  tid: 'G2',
                  origin:'南京南站',
                  destination:'北京南站',
                  starttime: '08:13',
                  mileage: '857km',
                  arrivetime: '11:36',
                  seattype: '二等座',
                  money: '￥504'
              }]
          }
        },
        methods:{
            //购买数量+  -
            editCount(count){
                this.buyCount=this.buyCount+count;
            },
            // sum() {
            //     var sum = 0;
            //     this.tableData.forEach(item => {
            //         if (this.tableData.selection.checked)
            //             sum += item.money * this.buyCount;
            //     })
            //     return sum;
            // }
            buypiao() {
                this.dialogFormVisible = true
            },

        },
        //购物数量监听
        watch:{
            buyCount:function (newValue,oldValue) {
                //this.productInfo.stock  数据库现有库存
                if(parseInt(newValue)> this.productInfo){
                    this.buyCount = this.productInfo;
                }else if(parseInt(newValue)<1){
                    this.buyCount = 1;
                }else if(isNaN(newValue)){
                    this.buyCount = 1;
                } else {
                    this.buyCount=parseInt(newValue);
                }
            }
        },
        filters: {
            toDecimal2: function(x) {
                var f = parseFloat(x);
                if (isNaN(f)) {
                    return false;
                }
                var f = Math.round(x * 100) / 100;
                var s = f.toString();
                var rs = s.indexOf('.');
                if (rs < 0) {
                    rs = s.length;
                    s += '.';
                }
                while (s.length <= rs + 2) {
                    s += '0';
                }
                return s;
            }
        }

    }
</script>

<style scoped>
    .shuzhi{
        width: 45px;
        text-align: center;
        border: 1px solid #999999;
        border-left: none;
        border-right: none;
        height: 25px;
    }

    .shuliang{
        height: 25px;
        border: 1px #999999 solid;
        width: 25px;
        text-align: center;
    }
</style>