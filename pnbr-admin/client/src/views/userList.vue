<template>
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
              <el-input type="text" v-model="search_data.title" placeholder="请输入关键词"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="search" @click='onScreeoutMoney("search_data")'>筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <el-form :inline="true">
            <el-form-item class="btnRight">
                <el-button type="primary" size ="small" icon="el-icon-edit-outline" @click='onAddMoney()'>添加</el-button>
            </el-form-item>
        </el-form>
    </div>
       <div class="tables">
          <div class="excel_container">
            <el-button type="primary" icon='document' @click='exportToExcel()'>导出excel</el-button>
          </div>
          <el-table
            :data="userData.list"
            border
            style="width: 100%">
            <el-table-column
                label="头像"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <img alt="头像" style="width:100px;height=80px;" :src="'api/'+scope.row.avatarImage"/>
                </template>
            </el-table-column>
            <el-table-column
                label="用户名"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.username }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="手机号码"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.phone }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="角色"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.role }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="身份"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.grade }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="创建时间"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.createTime | moment }}</span>
                </template>
            </el-table-column>
            <el-table-column align="center" label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    <!-- <el-button
                    size="mini"
                    @click="handleLiaotian(scope.$index, scope.row)">聊天</el-button> -->
                    <el-button
                    size="mini"
                    @click="handleBan(scope.$index, scope.row)">封禁</el-button>
                </template>
            </el-table-column>
         </el-table>
       </div>
      <!-- <UserDialong :dialong="dialong" :form="form" :id="id" @userData="loadCurrentPageUserList"></UserDialong> -->
      <cduanUserDialong :dialong="dialong" :form="form" :id="id" @UserData="loadCurrentPageUserList"></cduanUserDialong>
      <liaotian :dialong="dialong2" :toUser="toUser"></liaotian>
      <div class="page">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="userData.pageNum"
          :page-sizes="userData.page_sizes"
          :page-size="userData.pageSize"
          :layout="userData.layout"
          :total="userData.total">
        </el-pagination>
	 	  </div>
  </div>
</template>

<script>
// @ is an alias to /src
import cduanUserDialong from "../components/cduanUserDialong";
import liaotian from "../components/liaotian";
import config from '../../../config/config'
export default {
  name: "Staff",
  data() {
    return {

      userData: {
        list: [],
        pageNum: 1,
        pageSize: 3,
        pages: 0,
        total: 0,
        page_sizes:[3,6,9,12], //每页显示多少条
				layout:'total, sizes, prev, pager, next, jumper'
      }, //数据存储
      dialong: {
        //弹出框
        show: false,
        title: "",
        option: "edit"
      },
      form: {   //添加和删除需要传递的字段名
        role: "",
        grade: "",
        password: "",
        phone: "",
        username: "",
        id: 0
      },
      search_data: {
         title: null
      },
      id: 0
    };
  },
  methods: {
    userInfo(formData) {
      this.$axios
        .post(config.url+"/user/list",formData,{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
          this.userData = res.data.data;
        }).catch(err => console.log(err));
    },


    exportToExcel() {
      //excel数据导出
      require.ensure([], () => {
        const {
          export_json_to_excel
        } = require('../assets/js/Export2Excel');
        const tHeader = ['头像', '用户名', '手机号码', '角色', '身份', '创建时间'];
        const filterVal = ['avatarImage', 'username', 'phone', 'role', 'grade', 'createTime'
        ];
        const list = this.userData.list;
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, '列表excel');
      })
    },

    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },

    loadCurrentPageUserList() {
	      let pageSize = this.userData.pageSize;
        let page = this.userData.pageNum;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize,
                "title": this.search_data.phone,
                "type": 0
        }
        this.userInfo(formData);
    },
    handleEdit(index, row) {
      //编辑
      this.dialong = {
        title: "操作区域",
        show: true,
        option:"edit"
      }
      this.id = row.id;
      this.form = {
          password: row.password,
          phone: row.phone,
          username: row.username,
          role: row.role,
          grade: row.grade,
          id: row.id,
      }
    },
    handleLiaotian(index, row){
      this.dialong2 = {
        title: row.username,
        show: true,
        option:"edit"
      }
      var toUser = {
        toUserId: row.id,
        toUserName: row.username,
        toAvatarImage: row.avatarImage
      }
      console.log(toUser);
      this.toUser = toUser;
    },
    handleDelete(index, row) {
      //删除数据
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios.delete(config.url+`/background/user/${row.id}`,{headers: {"token": localStorage.getItem("eleToken")}})
          .then(res => {
            this.$message({
              type: "success",
              message: "删除成功!"
            });
            this.loadCurrentPageUserList();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    handleBan(index, row) {
      //封禁
      this.dialong = {
        title: "操作区域",
        show: true,
        option:"edit"
      }
      this.id = row.id;
      this.form = {
          password: row.password,
          phone: row.phone + "*",
          username: row.username,
          id: row.id,
          role: row.role,
          grade: row.grade
      }
    },
    onAddMoney() {
      //添加内容
      this.dialong = {
        title: "添加信息",
        show: true,
        option:"add"
      }
      this.form = {
        role: "",
        grade: "",
        password: "",
        phone: "",
        username: "",
      }
    },
		handleSizeChange(page_size) {
				this.userData.pageNum = 1; //第一页
				this.userData.pageSize = page_size; //每页先显示多少数据
		},
		handleCurrentChange(page){
				// 跳转页数
				//获取每行数
				let pageSize = this.userData.pageSize;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize,
                "title": this.search_data.phone,
                "type": 0
        }
        this.userInfo(formData);
		}
  },
  onScreeoutMoney(){
    const formData = {
              "isAsc": null,
              "orderBy": null,
              "orderByColumn": null,
              "pageNum": 1,
              "pageSize": this.userData.pageSize,
              "title": this.search_data.phone,
              "type": 0
    }
    this.userInfo(formData);
  },
  created() {
     const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": this.userData.pageSize,
                "type": 0
    }
    this.userInfo(formData);
  },
  components: {
    cduanUserDialong,
    liaotian
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
</style>
