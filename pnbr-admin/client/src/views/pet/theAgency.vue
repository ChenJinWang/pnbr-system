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
              <el-input type="text" v-model="search_data.title" placeholder="请输入关键词：标题"></el-input>
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
                label="id"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="反馈人id"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.createId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="分类"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.categoryName }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="标题"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.title }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="图片"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <img style="width:50px;height=50px;" alt="图片" :src="'api/'+scope.row.images"/>
                </template>
            </el-table-column>
            <el-table-column
                label="更新时间"
                align="center"
                width="150">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.updateTime | moment }}</span>
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
            <el-table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)" >详情</el-button>
                </template>
            </el-table-column>
        </el-table>
      </div>
      <theAgencyDialong :dialong="dialong" :form="form" :parameter="parameter"   @petData="loadCurrentPagepetList"></theAgencyDialong>
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
</template>

<script>
// @ is an alias to /src
import theAgencyDialong from "../../components/theAgencyDialong";
export default {
  name: "petList",
  data() {
    return {
      petData: {
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
     
      },
      parameter: {
        id: 0,
        dialogImageUrls: []
      },
      search_data: {
         title: null
      }
    };
  },
  methods: {
    petList(formData) {
      this.$axios
        .post("/api/feedback/backstage/list",formData,{headers: {"token": localStorage.getItem("eleToken")}})
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
        const tHeader = ['id', '反馈人id', '分类', '标题', '图片',  '更新时间', '创建时间'];
        const filterVal = ['id', 'createId','categoryName', 'title','images', 'updateTime', 'createTime'
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
                "title": this.search_data.title,
                "type": 1
        }
        this.petList(formData);
    },
    handleEdit(index, row) {
      this.parameter.dialogImageUrls = [];
      //编辑
      this.dialong = {
        title: "详细信息",
        show: true,
        option:"edit"
      }
      console.log(row);
      this.parameter.id = row.id;
      if(row.imagesList!=null) {
        let urlArr = row.imagesList.split(",");
        urlArr.forEach((item,value) => {
            if(!item) {
              return urlArr.splice(value,1);
            }
            if(typeof(item)=="undefined") {
                  return urlArr.splice(value,1);
            }
            this.parameter.dialogImageUrls.push({name:value,url:'api'+item})
        });
      }
      this.form = row;
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
                "title": this.search_data.title,
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
                "title": this.search_data.title,
                "type": 1
      }
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
                "type": 1
    }
    this.petList(formData);
  },
  components: {
    theAgencyDialong
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
