<template>
  <div class="app-container">
    <el-row style="margin-bottom: 10px">
      <el-col :span="18">
        <el-input
          v-model="title"
          type="text"
          placeholder="新闻标题"
          maxlength="100"
          show-word-limit
        />
      </el-col>
      <el-col :offset="1" :span="4">
        <el-button type="primary" @click="handlePublish"><i class="el-icon-edit" />发布文章</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :xs="24" :sm="24" :md="15" :lg="15" :xl="15">
        <div ref="editor" class="text" />
      </el-col>
      <el-col :xs="24" :sm="24" :md="9" :lg="9" :xl="9">
        <div v-html="editorContent" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { upload } from '@/utils/upload'
import E from 'wangeditor'
export default {
  name: 'ArticleEditor',
  data() {
    return {
      editorContent: '',
      title: ''
    }
  },
  computed: {
    ...mapGetters([
      'imagesUploadApi',
      'baseApi'
    ])
  },
  mounted() {
    const _this = this
    var editor = new E(this.$refs.editor)
    // 自定义菜单配置
    editor.config.zIndex = 5
    // 文件上传
    editor.config.customUploadImg = function(files, insert) {
      // files 是 input 中选中的文件列表
      // insert 是获取图片 url 后，插入到编辑器的方法
      files.forEach(image => {
        upload(_this.imagesUploadApi, image).then(res => {
          const data = res.data
          const url = _this.baseApi + '/file/' + data.type + '/' + data.realName
          insert(url)
        })
      })
    }
    editor.config.onchange = (html) => {
      this.editorContent = html
    }
    editor.create()
    // 初始化数据
    editor.txt.html(this.editorContent)
  },
  methods: {
    handlePublish() {
      console.log('click publish')
      if (!this.title || !this.editorContent) {
        this.$message.error('请填写文章标题和文章内容！')
        return
      }
      this.$emit('publish', { title: this.title, content: this.editorContent })
    }
  }
}
</script>

<style scoped>
  .text {
    text-align:left;
  }
 ::v-deep .w-e-text-container {
    height: 420px !important;
  }
</style>
