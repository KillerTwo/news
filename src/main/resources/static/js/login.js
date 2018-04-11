
var login = {
    template: '#login-template',
    data() {
        return {
            isLogin:false,
            loginForm: {
                accountName: '',
                accountPwd: '',
            },
            rules: {
                accountName: [
                    { required: true, message:'请输入账号名', trigger: 'blur' },
                    
                ],
                accountPwd: [
                    { required: true, message:'请输入账号密码', trigger: 'blur' }
                ],
               
            }
        };
    },
    methods: {

        loginAccount(url) {
        	var self = this;
            axios.post(url, {
                    accountName: self.loginForm.accountName,
                    accountPwd: self.loginForm.accountPwd,
                })
                .then(function(response) {
                    console.log(response);
                    if(!response.data){
                        self.$message.error('用户名或密码错误，请重试！');
                        self.resetForm('loginForm');
                    }else{
                        self.isLogin = true;
                    }
                });
        },


        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) { //验证通过
                    //alert('submit!');
                    this.loginAccount("http://localhost:8080/news/account/login");

                } else { //验证失败
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //重置表单
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    },
    //执行网络请求
    mounted() {

    }
}