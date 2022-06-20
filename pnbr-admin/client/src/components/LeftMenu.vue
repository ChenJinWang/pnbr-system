<template>
	<div class="sidebar">
		
			<el-row class="menu_page">
				<el-col>
					<el-menu 
						mode="vertical" 
						class="el-menu-vertical-demo" 
						:collapse="collapse" 
						:default-active="$route.path"
						background-color="#324057" 
						text-color="#fff">
						<div class="wrapper" ref="wrapper">
							<Menu :items='items'/>
						</div>
					</el-menu>
				</el-col>	
			</el-row>
		
	</div>
</template>

<script>
import bus from "../common/bus";
import Menu from "./Menu";
import Bscroll from 'better-scroll'
// @ is an alias to /src
export default {
  name: "LeftMenu",
  data() {
    return {
      collapse: false,
      items: [
        {
          icon: "el-icon-menu",
          name: "首页",
          path: "/home"
        },
        {
          icon: "el-icon-service",
          name: "验证管理",
          path: "/staff"
        },
        {
          icon: "el-icon-service",
          name: "用户管理",
          path: "/userList"
        },
        {
          icon: "el-icon-setting",
          name: "主题管理",
          path: "/categoryList"
        },
		{
          icon: "el-icon-setting",
          name: "反馈管理",
          path: "/theAgency"
        },
        {
          icon: "el-icon-bell",
          name: "商品管理",
          path: "/list",
          children: [
            {
              path: "/petList",
              name: "商品审核列表"
            },
            {
              path: "/orderList",
              name: "订单列表"
            },
          ]
        },
		{
          icon: "el-icon-setting",
          name: "社区中心",
          path: "/community"
        },
        {
          icon: "el-icon-setting",
          name: "个人信息",
          path: "/infoshow",
          
        },
      ]
    };
  },
  components: {
    Menu
  },
  methods:{
		initScroll() {
			this.scroll = new Bscroll(this.$refs.wrapper, {
			})
		}
	},
	mounted() {
		this.$nextTick(() => {
			this.initScroll()
		})
	},
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
  }
};
</script>
<style scoped>
	.menu_page {
		position: fixed;
		top: 71px;
		/* left: 1340px; */
		left: 0;
		min-height: 100%;
		background-color: #324057;
		z-index: 99;	
	}
	.sidebar {
		position: relative;
		overflow: hidden;
	}
	.wrapper {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		width: 100%;
		height: 100%;
		min-height: 500px;
		overflow: hidden;
	}
	.sidebar::-webkit-scrollbar {
		width: 0;
	}

	.sidebar-el-menu:not(.el-menu--collapse) {
		width: 250px;
	}

	.el-menu {
		border: none;
		z-index: 9999;
	}

	.fa-margin {
		margin-right: 5px;
	}

	.el-menu-vertical-demo:not(.el-menu--collapse) {
		width: 180px;
		min-height: 400px;
	}

	.el-menu-vertical-demo {
		width: 49px;
	}

	.el-submenu .el-menu-item {
		min-width: 180px;
	}

	.hiddenDropdown,
	.hiddenDropname {
		display: none;
	}

	a {
		text-decoration: none;
	}
</style>

