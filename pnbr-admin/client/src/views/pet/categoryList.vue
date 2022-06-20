<template>
  <div class="staff">
    <div class="staff-top">
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
            :data="categoryData"
            border
            style="width: 100%">
            <el-table-column
                label="id"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="分类名"
                align="center"
                width="180">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column lign="10" label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
      </div>
      <CategoryDialong :dialong="dialong" :form="form" :id="id" @categoryData="categoryList"></CategoryDialong>
  </div>
</template>

<script>
// @ is an alias to /src
import CategoryDialong from "../../components/categoryDialong";
export default {
  name: "categoryList",
  data() {
    return {
      categoryData: [], //数据存储
      dialong: {
        //弹出框
        show: false,
        title: "",
        option: "edit"
      },
      form: {   //添加和删除需要传递的字段名
        name: "",
        id: 0
      },
      id: 0
    };
  },
  methods: {
    categoryList(formData) {
      this.$axios.get("/api/category",formData,{headers: {"token": localStorage.getItem("eleToken")}})
        .then(res => {
          this.categoryData = res.data.data;
        }).catch(err => console.log(err));
    },
    

    

    exportToExcel() {
				//excel数据导出
      require.ensure([], () => {
        const {
          export_json_to_excel
        } = require('../../assets/js/Export2Excel');
        const tHeader = ['id', '分类名'];
        const filterVal = ['id', 'name'
        ];
        const list = this.categoryData;
        const data = this.formatJson(filterVal, list);
        export_json_to_excel(tHeader, data, '列表excel');
      })
    },

    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },

    loadCurrentPageUserList() {
	      let pageSize = this.categoryData.pageSize;
        let page = this.categoryData.pageNum;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize
        }
        this.userInfo(formData);
    },

    handleDelete(index, row) {
      //删除数据
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$axios.delete(`/api/category/${row.id}`,{headers: {"token": localStorage.getItem("eleToken")}})
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
    
    handleEdit(index, row) {
      //编辑
      this.dialong = {
        title: "操作区域",
        show: true,
        option:"edit"
      }
      this.id = row.id;
      this.form = {
          name: row.name,
          id: row.id
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
        name: ""
      }
    }
  },
  handleSizeChange(page_size) {
				this.categoryData.pageNum = 1; //第一页
				this.categoryData.pageSize = page_size; //每页先显示多少数据
		},
		handleCurrentChange(page){
				// 跳转页数
				//获取每行数
				let pageSize = this.categoryData.pageSize;
				const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": page,
                "pageSize": pageSize
        }
        this.userInfo(formData);
		},
  created() {
     const formData = {
                "isAsc": null,
                "orderBy": null,
                "orderByColumn": null,
                "pageNum": 1,
                "pageSize": 5
    }
    this.categoryList(formData);
  },
  components: {
    CategoryDialong
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
