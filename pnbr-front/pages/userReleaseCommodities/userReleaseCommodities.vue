<template>
	<view class="container">
		<!--input列表 -->
		<view class="input-box">
		  <view class="section">
		    <text>标题</text>
		    <input placeholder="请输入标题" type="text" maxlength="11" :value="pet.title" @input="onTitleInput"></input>
		  </view>
		  <view class="section" @tap="changeTypeVisible">
		    <text>类型</text>
		  	<view class="uni-title">{{typeName}}</view>
		  	<picker-view v-if="typeVisible" :indicator-style="indicatorStyle" :value="typevalue" @change="onTypeChange" class="picker-view">
		  				        <picker-view-column>
		  				            <view class="item" v-for="(item,index) in types" :key="index">{{item.name}}</view>
		  				        </picker-view-column>
		    </picker-view>
		  </view>
		  <view class="section" @tap="changeOldNewLevelVisible">
		    <text>内容</text>
		    <view class="uni-title">{{content}}</view>
		    <picker-view v-if="contentVisible" :indicator-style="indicatorStyle" :value="contentValue" @change="onOldNewLevelChange" class="picker-view">
		    	<picker-view-column>
		    	    <view class="item" v-for="(item,index) in contents" :key="index">{{item}}</view>
		    	</picker-view-column>
		    </picker-view>
		  </view>
		  <view class="section">
               <block v-for="(item, index) in images" :key="index">
				   <image style="width: 130rpx;height: 130rpx;padding: 1%;" @tap="upload" :src="item?serverUrl+item :'../../static/images/icon/upload.png'"></image>
			   </block>
		  </view> 
		  <view class="section" @tap="changeClassifyVisible">
		    <text>分类</text>
			<view class="uni-title">{{classify}}</view>
			<picker-view v-if="classifyVisible" :indicator-style="indicatorStyle" :value="classifyValue" @change="onClassifyChange" class="picker-view">
					           <picker-view-column>
					               <view class="item" v-for="(item,index) in classifys" :key="index">{{item.name}}</view>
					           </picker-view-column>
			</picker-view>
		  </view>
		  <view class="section">
		  	<textarea placeholder="请输入简介" maxlength="600" :value="pet.brief" @input="onBriefInput"></textarea>
		  </view>
		</view>
		<!-- end input列表 -->
		<!-- 功能按钮 -->
		<view class="btn-box">
		  <view class="keep btn" @tap="onSubmiti">
		    <text>发布帖子</text>
		  </view>
		</view>
		<!-- end 功能按钮 -->
	</view>
</template>

<script>
	var http = require("../../utils/http.js");
	var config = require("../../utils/config.js");
	var index = [18, 0, 0];
	var t = 0;
	var show = false;
	var moveY = 200;
	export default {
		data() {
			var currentTime = '';
			const date = new Date()
			return {
				pet: {
					depositingTime: currentTime,
					price: 0
				},
				images: [
					""
				],
				serverUrl: config.domain,
				classifys: [
			     
				],
				classify: '',
				classifyValue: [0],
				types: [
					{
					name: "发布帖子",
					id: 0	
					},
				],
				typevalue: [0],
				typeName: '发布帖子',
				content: '干膨化食品',
				contents: [
					"干膨化食品",
					"半湿食品",
					"软膨化（软干的）食品"
				],
				contentValue: [0],
				value: [0],
				typeVisible: false,
				contentVisible: false,
				classifyVisible: false,
				depositingTimeVisible: false,
				indicatorStyle: `height: 50px;opacity: 1;`,
				placeholder: '开始输入...',
			}
		},
		/**
		 * 生命周期函数--监听页面显示
		 */
		onShow: function() {
			//加载订单数字
			var ths = this; 
			var params = {
			  url: "/category",
			  method: "GET",
			  needToken: true,
			  callBack: function (res) {
			    uni.hideLoading();
				ths.setData({
				  classifys: res.data,
				  classify: res.data[0].name,
				  "pet.cid": res.data[0].id
				});
			  }
			};
			http.request(params);
		},
		methods: {
			upload: function() {
				uni.chooseImage({
				    success: (chooseImageRes) => {
				        const tempFilePaths = chooseImageRes.tempFilePaths;
				        const uploadTask = uni.uploadFile({
				            url: config.domain+"/common/upload", //仅为示例，非真实的接口地址
				            filePath: tempFilePaths[0],
				            name: 'file',
				            formData: {
				                
				            },
				            success: (uploadFileRes) => {
								var data = JSON.parse(uploadFileRes.data);
								console.log(data.fileName);
								var images = this.images;
								images.push(data.fileName);
								this.setData({
								  images: images
								});
				            }
				        });
				
				        uploadTask.onProgressUpdate((res) => {
				            console.log('上传进度' + res.progress);
				            console.log('已经上传的数据长度' + res.totalBytesSent);
				            console.log('预期需要上传的数据总长度' + res.totalBytesExpectedToSend);
				
				            // 测试条件，取消上传任务。
				            // if (res.progress > 50) {
				            //     uploadTask.abort();
				            // }
				        });
				    }
				});
		    },
			onMobileInput: function (e) {
			  this.setData({
			    'pet.contactWay': e.detail.value
			  });
			},
			onPriceInput: function (e) {
				this.setData({
				  'pet.price': e.detail.value
				});
			},
			onTitleInput: function (e) {
				this.setData({
				  'pet.title': e.detail.value
				});
			},
			onTypeChange: function(e) {
				const val = e.detail.value
				this.setData({
				  typeName: this.types[val[0]].name,
				  'pet.type': this.types[val[0]].id
				});
			},
			onOldNewLevelChange: function(e) {
				const val = e.detail.value
				this.setData({
				  content: this.contents[val[0]]
				});
			},
			onClassifyChange: function(e) {
				const val = e.detail.value
				this.setData({
				  classify: this.classifys[val[0]].name,
				  "pet.cid": this.classifys[val[0]].id
				});
			},
			changeTypeVisible: function() {
				this.setData({
				  typeVisible: !this.typeVisible,
				  contentVisible: false,
				  classifyVisible: false,
				  depositingTimeVisible: false
				});
			},
			changeOldNewLevelVisible: function() {
				this.setData({
				  typeVisible: false,
				  contentVisible: !this.contentVisible,
				  classifyVisible: false,
				  depositingTimeVisible: false
				});
			},
			changeClassifyVisible: function() {
				this.setData({
				  typeVisible: false,
				  contentVisible: false,
				  classifyVisible: !this.classifyVisible,
				  depositingTimeVisible: false
				});
			},
			onBriefInput: function(e) {
				this.setData({
				  'pet.brief': e.detail.value
				});
			},
			onSubmiti: function () {
				var images = this.images;
				var imagesList = images.join(',');
				console.log(imagesList);
				if(!this.pet.title||typeof(this.pet.title) == "undefined") {
					uni.showToast({
						title: "标题不能为空",
						icon: 'none',
					});
					return;
				}
				if(!imagesList||typeof(imagesList) == "undefined") {
					uni.showToast({
						title: "图片不能为空",
						icon: 'none',
					});
					return;
				}
				
				
				var params = {
				  url: "/community",
				  method: "POST",
				  needToken: true,
				  data: {
					    cid: this.pet.cid,
					    title: this.pet.title,
					    images: this.images[1],
						imagesList: imagesList,
					    content: this.content,
					    brief: this.pet.brief,
                        type: this.pet.type? this.pet.type:0,
	                    contactWay: this.pet.contactWay,
					    depositingTime: this.pet.depositingTime
				  },
				  callBack: function (res) {
				    uni.showToast({
				    	title: "发布成功",
				    	icon: 'none',
				    });
					setTimeout(() => {
						uni.navigateBack({
							delta: 2
						});
					},1000)
				  }
				};
				http.request(params);
			}
	    }
	}
</script>

<style>
@import "./userReleaseCommodities.css";
	 .picker-view {
			width: 750rpx;
			height: 600rpx;
			margin-top: 20rpx;
	 }
	.item {
			height: 50px;
			align-items: center;
			justify-content: center;
			text-align: center;
	}
    #editor {
        width: 100%;
        height: 300px;
        background-color: #CCCCCC;
    }
</style>
