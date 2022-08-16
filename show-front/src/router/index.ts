import { createRouter, createWebHistory} from "vue-router";


const routes = [
    { path: '/', component: () => import('../views/Layout/index.vue'),
        children: [
            {
            // 当 /user/:id/profile 匹配成功
            // UserProfile 将被渲染到 User 的 <router-view> 内部
            path: '/',
            component: () => import('../views/Home/index.vue'),
            children: [
                {
                    path: '/',
                    redirect: '/list/1'
                },
                {
                    path: 'list/:type',
                    component: () => import('../components/NewsArticleList.vue'),
                },
            ]
            },
            {
                path: '/details/:id',
                component: () => import('../views/ArticleDetails/index.vue'),
            },
        ]
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;