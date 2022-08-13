<template>
    <a-layout id="components-layout-demo-basic">
        <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
            <!-- <router-link to="/">Go to Home</router-link>
            <router-link to="/about">Go to About</router-link> -->
            <h3 :style="{ float: 'left' }"><a>澎湃新闻</a></h3>
            <NewsCategoryVue :style="{ marginLeft: '400px' }" />
        </a-layout-header>
        <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
            <div :style="{ margin: '0 205px', minHeight: '380px' }">
                <router-view></router-view>
            </div>
            
        </a-layout-content>
        <a-layout-footer>Footer</a-layout-footer>
    </a-layout>
</template>

<script setup lang="ts">
import NewsCategoryVue from '../../components/NewsCategory.vue';
</script>
<style scoped>
#components-layout-demo-basic .ant-layout-header,
#components-layout-demo-basic .ant-layout-footer {
  color: #fff;
  background: #fff;
}

#components-layout-demo-basic .ant-layout-header {
    /* padding-left: 400px; */
    margin-bottom: 10px;
}

#components-layout-demo-basic .ant-layout-footer {
  color: #000;
  text-align: center;
}
</style>