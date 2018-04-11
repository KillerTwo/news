//定义路由
const routes = [
  { path: '/register', component: register },
  { path: '/login', component: login }
]
//创建router实例
const router = new VueRouter({
  routes // （缩写）相当于 routes: routes
})
new Vue({
    router,
    data(){
        return {
            name:'Alice',
        };
    },
    
    components:{
        'register':register,
        'login': login
    },
    method:{

    },
    mounted(){

    }
}).$mount('#app');

