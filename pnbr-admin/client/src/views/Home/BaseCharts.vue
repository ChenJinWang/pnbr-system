<template>
	<div class="base-charts">
		<el-row>
			<!-- <el-col :span="12" class="list-item">
				<div class="grid-content bg-purple">
					<Dotted/>
				</div>
			</el-col>
			<el-col :span="12" class="list-item">
				<div class="grid-content bg-purple-light">
					<Stacked/>
				</div>
			</el-col> -->
			<el-col :span="12" class="list-item">
			    <h1>商品收藏排行榜</h1>
				<div class="grid-content bg-purple-light">
					<petComplaint/>
				</div>
			</el-col>
           <el-col :span="12" class="list-item">
			    <h1>用户消费额排行榜</h1>
				<div class="grid-content bg-purple-light">
					<userConsumption/>
				</div>
			</el-col>
			<el-col :span="12" class="list-item">
				<h1>商品销售额排行榜</h1>
				<div class="grid-content bg-purple-light">
					<petSales/>
				</div>
			</el-col>
			<el-col :span="12" class="list-item">
			    <h1>商品销量排行榜</h1>
				<div class="grid-content bg-purple-light">
					<petRanking/>
				</div>
			</el-col>	
		</el-row>
	</div>
</template>

<script>
	import Stacked from './echarts/Stacked'
	import Dotted from './echarts/Dotted'
	import ageAnalysis from './echarts/ageAnalysis'
	import localDistribution from './echarts/localDistribution'
	import petRanking from './echarts/petRanking'
	import petComplaint from './echarts/petComplaint'
	import petSales from './echarts/petSales'
	import userConsumption from './echarts/userConsumption'
	export default {
	  name: 'Nofind',
		data(){
			return {
				
			}
		},
		components:{
			Stacked,
			Dotted,
			ageAnalysis,
			localDistribution,
			petRanking,
			petComplaint,
			petSales,
			userConsumption
		}
	}
</script>
<style scoped>
.base-charts {
	width: 100%;
	overflow: scroll;
}
.list-item {
	padding-left: 20px;
	padding-right: 20px;
	overflow: scroll;
}
</style>
