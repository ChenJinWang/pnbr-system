<template>
 <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="全部" name="0">
          <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                        <template slot-scope="scope">
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" align="center" label="图片">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" property="title" align="center" label="商品名"></el-table-column>
                                    <el-table-column width="100" property="num" align="center" label="数量"></el-table-column>
                                    <el-table-column width="300" property="price" align="center" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
          </div>
    </el-tab-pane>
    <el-tab-pane label="待支付" name="1">
                  <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                        <template slot-scope="scope">
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" align="center" label="图片">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" align="center" property="title" label="商品名"></el-table-column>
                                    <el-table-column width="100" align="center" property="num" label="数量"></el-table-column>
                                    <el-table-column width="300" align="center" property="price" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
          </div>
    </el-tab-pane>
    <el-tab-pane label="待发货" name="2">
        <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                        <template slot-scope="scope">
                             <el-button
                            slot="reference"
                            @click="shipments(scope.row.orderId)">发货</el-button>
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" align="center" label="图片">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" align="center" property="title" label="商品名"></el-table-column>
                                    <el-table-column width="100" align="center" property="num" label="数量"></el-table-column>
                                    <el-table-column width="300" align="center" property="price" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
        </div>
    </el-tab-pane>
    <el-tab-pane label="待收货" name="3">
         <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                        <template slot-scope="scope">
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" align="center" label="图片">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" align="center" property="title" label="商品名"></el-table-column>
                                    <el-table-column width="100" align="center" property="num" label="数量"></el-table-column>
                                    <el-table-column width="300" align="center" property="price" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
        </div>
    </el-tab-pane>
    <el-tab-pane label="已取消" name="5">
         <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                        <template slot-scope="scope">
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" align="center" label="图片">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" align="center" property="title" label="商品名"></el-table-column>
                                    <el-table-column width="100" align="center" property="num" label="数量"></el-table-column>
                                    <el-table-column width="300" align="center" property="price" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
        </div>
    </el-tab-pane>
    <el-tab-pane label="已完成" name="4">
         <div class="staff">
            <div class="staff-top">
                <div class="search-box">
                <el-form
                    :inline="true"
                    ref="search_data"
                    :rules="rules"
                    :model="search_data"
                >
                    <el-form-item label="关键词:">
                    <el-input type="text" v-model="search_data.orderId" placeholder="请输入关键词：订单id"></el-input>
                    </el-form-item>
                    <el-form-item>
                    <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
                    </el-form-item>
                </el-form>
                </div>
            </div>
            <div class="tables">
                <div class="excel_container">
                    <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
                </div>
                <el-table
                    :data="petData.list"
                    border
                    style="width: 100%">
                    <el-table-column
                        label="订单id"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.orderId }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="实际金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.actualPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="总金额"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.totalPay }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiver }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="收货地址"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverState }}{{ scope.row.receiverCity }}{{ scope.row.receiverDistrict }}{{ scope.row.receiverAddress }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="联系方式"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.receiverMobile }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="备注"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.remark}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="付款人"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.buyerNick}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                        label="创建时间"
                        align="center"
                        width="130">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" fixed="right">
                       <template slot-scope="scope">
                            <el-popover
                                placement="right"
                                width="800"
                                trigger="click">
                                <el-table :data="scope.row.orderDetails">
                                    <el-table-column width="130" label="图片" align="center">
                                        <template slot-scope="setails">
                                            <img style="width:100px;height=80px;" alt="图片" :src="'api'+setails.row.image"/>
                                        </template>
                                    </el-table-column>
                                    <el-table-column width="300" align="center" property="title" label="商品名"></el-table-column>
                                    <el-table-column width="100" align="center" property="num" label="数量"></el-table-column>
                                    <el-table-column width="300" align="center" property="price" label="单价"></el-table-column>
                                </el-table>
                                <el-button slot="reference">查看</el-button>
                            </el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="page">
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="petData.pageNum"
                :page-sizes="petData.page_sizes"
                :page-size="petData.pageSize"
                :layout="petData.layout"
                :total="petData.total">
                </el-pagination>
            </div>
        </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
// @ is an alias to /src
export default {
  name: "petList",
  data() {
    return {
      petData: {
        list: [],
        pageNum: 1,
        pageSize: 5,
        pages: 0,
        total: 0,
        page_sizes:[5,10,15,20], //每页显示多少条
		layout:'total, sizes, prev, pager, next, jumper'
      }, //数据存储
      orderDetails: [
          {
                id: 0,
                image: "",
                num: 0,
                orderId: "",
                price: 0,
                skuId: 0,
                title: "",
          }
      ],
      search_data: {
         orderId: null,
         status: 0
      },
      activeName: '0'
    };
  },
  methods: {
    petList(formData) {
      this.$axios
        .post("/api/order/user/list",formData,{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
          this.petData = res.data.data;
      }).catch(err => console.log(err));
    },

    exportToExcel() {
				//excel数据导出
      require.ensure([], () => {
        const {
          export_json_to_excel
        } = require('../../assets/js/Export2Excel');
        const tHeader = ['订单id', '实际金额', '总金额', '收货人', '省', '市', '/区', '详细地址', '联系方式', '备注', '付款人', '创建时间'];
        const filterVal = ['orderId', 'actualPay','totalPay', 'receiver','receiverState','receiverCity','receiverDistrict','receiverAddress',
         'receiverMobile','remark', 'buyerNick', 'createTime'
        ];
        const list = this.petData.list;
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, '列表excel');
      })
    },

    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },

    loadCurrentPagepetList() {
	    let pageSize = this.petData.pageSize;
        let page = this.petData.pageNum;
		const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize,
                "status": this.search_data.status,
                "orderId": this.search_data.orderId,
                "type": 1
        };
        this.petList(formData);
    },
    viewDetails(index, row) {
      console.log(row);
      this.orderDetails = row.orderDetails;
    },
    shipments(orderId) {
      this.$axios
        .put("/api/order/"+orderId+"/3",null,{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
            this.loadCurrentPagepetList();
           this.$message({
              message: '发货成功',
              type: 'success'
           });
        }).catch(err => console.log(err));
    },
    handleSizeChange(page_size) {
				this.petData.pageNum = 1; //第一页
				this.petData.pageSize = page_size; //每页先显示多少数据
	},
	handleCurrentChange(page){
				// 跳转页数
				//获取每行数
				let pageSize = this.petData.pageSize;
				const formData = {
                    "isAsc": null,
                    "orderBy": null,
                    "orderByColumn": null,
                    "pageNum": page,
                    "pageSize": pageSize,
                    "orderId": this.search_data.orderId,
                    "type": 1
                }
        this.petList(formData);
	},
    onScreeoutMoney(){
      const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": this.petData.pageSize,
                "orderId": this.search_data.orderId
      }
      this.petList(formData);
	},
    handleClick(tab, event) {
        let pageSize = this.petData.pageSize;
        let page = this.petData.pageNum;
		const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize,
                "status": tab.name,
                "orderId": this.search_data.title,
                "type": 1
        };
        this.search_data.status=tab.name;
        this.petList(formData);
    }
  },
  created() {
     const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": this.petData.pageSize,
                "status": this.search_data.status,
                "type": 1
    }
    this.petList(formData);
  },
  components: {

  }
};
</script>
<style scoped>
.staff {
  margin: 10px;
}
.btnRight {
  float: right;
}
.search-box {
	margin: 20px 0 0 20px;
}
</style>
