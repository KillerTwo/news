var register = {
    template: '#register-template',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                var patrn = new RegExp(/^(\w){6,20}$/);
                if (!patrn.exec(value)) {
                    callback(new Error('请输入正确的密码'));
                } else if (this.registerForm.checkPass !== '') {
                    this.$refs.registerForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));

            } else if (value !== this.registerForm.accountPwd) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        var validateAccountName = (rule, value, callback) => {

            if (value === '') {
                callback(new Error('请输入账号名称'));

            } else {
                var patarn = new RegExp(/([a-zA-Z0-9]|[._]|[\u4E00-\u9FA5]){3,19}/);
                if (!patarn.exec(value))
                    callback("用户名不合法！");
                else {
                    //this.exits();
                    console.log('exits调用后');
                    if (this.isExits == true) {
                        console.log("用户名已经存在");
                        callback(new Error('账号名已经存在!'));
                    } else {
                        callback();
                    }
                    

                }
            }
            
        };


        return {
            isExits: false, //用户名是否存在
            isRegist: false, //是否注册成功
            registerForm: {
                accountName: '',
                accountPwd: '',
                checkPass: '',
                email: '',
            },
            rules: {
                accountName: [
                    { required: true, validator: validateAccountName, trigger: 'change' },

                ],
                accountPwd: [
                    { required: true, validator: validatePass, trigger: 'change' }
                ],
                email: [
                    { type: 'email', required: true, message: '请输入邮箱', trigger: 'change' }
                ],
                checkPass: [
                    { required: true, validator: validatePass2, trigger: 'change' }
                ],
            }
        };
    },
    methods: {

        exits(value) {

            var self = this;
            console.log("change事件处理函数");
            console.log(self.isExits);
            //alert(self.isExits);
            axios.get('http://localhost:8080/news/account/exit?accountName=' + self.registerForm.accountName)
                .then(function(response) {
                    console.log(response);
                    self.isExits = response.data;
                    // if(response.data){
                    // 	self.isExits = "用户名已经存在"
                    // }
                    console.log('判断用户名是否存在');
                    console.log(self.isExits);
                    
                    //self.validateNameExit();
                })
                .catch(function(error) {
                    console.log(error);
                });
                
        },
        regAccount(url) {
            var self = this;
            axios.post(url, {
                    accountName: self.registerForm.accountName,
                    accountPwd: self.registerForm.accountPwd,
                    email: self.registerForm.email
                })
                .then(function(response) {
                    console.log(response);
                    if(response.data.code != ""){
                    	//注册成功请验证邮箱激活账号
                    	self.isRegist = true;
                    }
                });
        },


        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
            	this.validateAccountName;            //再次调用验证函数，判断用户是否存在
                if (valid) { //验证通过
                    alert('submit!');
                    this.regAccount("http://localhost:8080/news/account/register");
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