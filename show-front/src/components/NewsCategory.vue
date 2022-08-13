<template>
    <a-menu v-model:selectedKeys="current" mode="horizontal">
    <a-menu-item key="mail">
      <template #icon>
        <mail-outlined />
      </template>
      军事
    </a-menu-item>
    <a-menu-item key="app">
      <template #icon>
        <appstore-outlined />
      </template>
      科技
    </a-menu-item>
    <a-menu-item key="yl">
      <template #icon>
        <appstore-outlined />
      </template>
      娱乐
    </a-menu-item>
    <a-menu-item key="ny">
      <template #icon>
        <appstore-outlined />
      </template>
      农业
    </a-menu-item>
    <a-menu-item key="ss">
      <template #icon>
        <appstore-outlined />
      </template>
      实时
    </a-menu-item>
    <a-menu-item key="cj">
      <template #icon>
        <appstore-outlined />
      </template>
      财经
    </a-menu-item>
    <a-menu-item key="sx">
      <template #icon>
        <appstore-outlined />
      </template>
      思想
    </a-menu-item>
    <a-menu-item key="sh">
      <template #icon>
        <appstore-outlined />
      </template>
      生活
    </a-menu-item>
  </a-menu>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import { MailOutlined, AppstoreOutlined, SettingOutlined } from '@ant-design/icons-vue';

const activeIndex = ref<string>('军事')
const current = ref<string[]>(['mail']);

function handleSelect(value: string) {
    console.log(value)
}
</script>
