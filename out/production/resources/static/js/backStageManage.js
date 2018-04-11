//alert("后台管理");
//定义路由
const routes = [
	{path: "/showuser", component: showUser},
]
//实例化Router对象
//创建router实例
const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
});
new Vue({
    router,//注册路由
    //注册组件
    components: {
    	'showUser':showUser
    },
    data() {
        return {
            tableData: [],
            total:0,                     //总条数
            pageSize: 5,            //每一页的条数
            isCollapse: false,
            currentPage: 1,          //当前页
            //点击查看时显示
            form: {
                user_name: '',
                user_age: '',
                user_address: '',
                user_email:'',
                user_phone:'',
            },
            formLabelWidth: '120px',
            dialogFormVisible: false,

        }
    },
    methods: {
        handleSizeChange(val) {
            //每一页显示的页数改变时触发
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
            console.log(`当前页: ${this.currentPage}`);
            this.handleGetData();

        },
        handleCurrentChange(val) {
            //当前页改变是触发
            this.currentPage = val;
            console.log(`当前页: ${val}`);
            console.log(`每页: ${this.pageSize} 条`);
            console.log(this.tableData);
            this.handleGetData();
        },
        handleClick(row) {
            //点击编辑按钮弹出dialog框
            /*console.log(row);*/
            this.form.name = row.name;
            this.form.date = row.date;
            this.form.address = row.address;
            this.dialogFormVisible = true;
        },
        handleClickSub(val) {
            //提交修改的表单内容
            this.dialogFormVisible = false;
            /*console.log(this.form.name);
            console.log(this.form.date);
            console.log(this.form.address);*/
        },
        //获取数据库数据
        handleGetData(url) {
          	console.log('加载数据');

          	self = this;
	        axios.get(url+'?currentPage='+self.currentPage+'&pageSize='+self.pageSize)
	            .then(function(response) {
	                self.total = response.data.count;       //设置数据总数
	                self.tableData = response.data.user;    //设置数据
	                // console.log(self.count);
	                // console.log(response.data);
	                // console.log(response.data.user);
	            })
	            .catch(function(error) {
	                console.log(error);
	            });
        }
    },
    mounted() {
      this.handleGetData();
    }
}).$mount('#app');