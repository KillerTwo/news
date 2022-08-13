

<template>
    <a-carousel :after-change="onChange" arrows>
        <template #prevArrow>
      <div class="custom-slick-arrow" style="left: 10px; z-index: 1">
        <left-circle-outlined />
      </div>
    </template>
    <template #nextArrow>
      <div class="custom-slick-arrow" style="right: 10px">
        <right-circle-outlined />
      </div>
    </template>
    <div><h3>1</h3></div>
    <div><h3>2</h3></div>
    <div><h3>3</h3></div>
    <div><h3>4</h3></div>
  </a-carousel>
    <router-view></router-view>
</template>

<script setup lang="ts">
import { ref } from 'vue'

import { LeftCircleOutlined, RightCircleOutlined } from '@ant-design/icons-vue';


const count = ref(0)

const onChange = (current: number) => {
      console.log(current);
    };

</script>

<style scoped>
.ant-carousel :deep(.slick-slide) {
  text-align: center;
  height: 320px;
  line-height: 320px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel :deep(.slick-arrow.custom-slick-arrow) {
  width: 25px;
  height: 25px;
  font-size: 25px;
  color: #fff;
  background-color: rgba(31, 45, 61, 0.11);
  opacity: 0.3;
  z-index: 1;
}
.ant-carousel :deep(.custom-slick-arrow:before) {
  display: none;
}
.ant-carousel :deep(.custom-slick-arrow:hover) {
  opacity: 0.5;
}

.ant-carousel :deep(.slick-slide h3) {
  color: #fff;
}
</style>