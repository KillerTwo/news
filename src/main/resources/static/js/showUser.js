//alert("后台管理");
var showUser = {
    template:'#show-user',
    data() {
        return {
            userForm: [],
            total:0,                     //总条数
            pageSize: 5,            //每一页的条数
            isCollapse: false,
            currentPage: 1,          //当前页
            
            formLabelWidth: '120px',
            //dialogFormVisible: false,
            dialogVisible: false, //对话框是否显示
            accountName: '',      //要删数的用户的账号名
        } 
    },
    methods: {
        handleSizeChange(val) {
            //每一页显示的页数改变时触发
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
            console.log(`当前页: ${this.currentPage}`);
            this.handleGetData("/news/user/page");

        },
        handleCurrentChange(val) {
            //当前页改变是触发
            this.currentPage = val;
            console.log(`当前页: ${val}`);
            console.log(`每页: ${this.pageSize} 条`);
            console.log(this.tableData);
            this.handleGetData("/news/user/page");
        },
        handleClick(row) {
            //点击编辑按钮弹出dialog框
            /*console.log(row);*/
            this.accountName = row.accountName;
            this.dialogVisible = true;
            console.log(this.accountName);
        },
        handleClickSub(val) {
            //提交修改的表单内容
            this.dialogVisible = false;
            var self = this;
            console.log("删除用户信息");
            axios.delete('http://localhost:8080/news/user/'+self.accountName/*,{
                    params: {
                      accountName: self.accountName
                    }
                }*/)
                .then(function(response) {
                    if(response.data){
                        console.log("删除数据成功");
                        self.$message.success('删除数据成功');
                        self.handleGetData("/news/user/page");
                    }
                   
            })
        },
        //获取数据库数据
        handleGetData(url) {
          	console.log('加载数据');

          	self = this;
	        axios.get(url+'?currentPage='+self.currentPage+'&pageSize='+self.pageSize)
	            .then(function(response) {
                    // console.log(response);
                    // console.log(self.total);
                    // console.log(self.userForm);
	                self.total = response.data.total;       //设置数据总数
	                self.userForm = response.data.rows;    //设置显示数据
	            })
	            .catch(function(error) {
	                console.log(error);
	            });
        }
    },
    mounted() {
      this.handleGetData("/news/user/page");
    }
}