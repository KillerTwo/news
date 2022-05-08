<template>
  <div>
    <article-editor @publish="handlePublish" />
    <el-dialog
      title="发布文章"
      :visible.sync="dialogVisible"
      width="60%"
      :before-close="handleClose"
    >

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="封面">
          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
          >
            <i slot="default" class="el-icon-plus" />
            <div slot="file" slot-scope="{file}">
              <img
                class="el-upload-list__item-thumbnail"
                :src="file.url"
              >
              <span class="el-upload-list__item-actions">
                <span
                  class="el-upload-list__item-preview"
                  @click="handlePictureCardPreview(file)"
                >
                  <i class="el-icon-zoom-in" />
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleDownload(file)"
                >
                  <i class="el-icon-download" />
                </span>
                <span
                  v-if="!disabled"
                  class="el-upload-list__item-delete"
                  @click="handleRemove(file)"
                >
                  <i class="el-icon-delete" />
                </span>
              </span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="imgDialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="摘要" prop="excerpt">
          <el-input v-model="form.excerpt" type="textarea" maxlength="256" show-word-limit />
        </el-form-item>
        <el-form-item label="文章标签" prop="tags">
          <el-select v-model="form.tags" filterable multiple placeholder="请选择">
            <el-option
              v-for="item in tagOptions"
              :key="item.id"
              :label="item.tagName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="新闻分类" prop="category">
          <el-select v-model="form.category" filterable placeholder="请选择">
            <el-option
              v-for="item in categoryOptions"
              :key="item.id"
              :label="item.categoryName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">发布新闻</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { publish } from '@/api/news/newsArticle'
import { listTagAll } from '@/api/news/newsTag'
import { listCategoryAll } from '@/api/news/newsCategory'

import ArticleEditor from '@/views/components/ArticleEditor.vue'
export default {
  name: 'NewsPublisher',
  components: {
    ArticleEditor
  },
  data() {
    return {
      dialogVisible: false,
      form: {},
      dialogImageUrl: '',
      imgDialogVisible: false,
      disabled: false,
      tagOptions: [
      ],
      categoryOptions: [
      ],
      rules: {
        tags: [
          { required: true, message: '请选择新闻标签', trigger: 'blur' }
        ],
        category: [
          { required: true, message: '请选择新闻类别', trigger: 'change' }
        ],
        excerpt: [
          { required: true, message: '请填写新闻摘要', trigger: 'change' }
        ]
      },
      contentForm: {}
    }
  },
  mounted() {
    listTagAll().then(res => {
      console.log(res.data)
      this.tagOptions = res
    })
    listCategoryAll().then(res => {
      this.categoryOptions = res
    })
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    handlePublish(data) {
      console.log('handle publish', data)
      this.contentForm = data
      this.dialogVisible = true
    },
    handleRemove(file) {
      console.log(file)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url
      this.dialogVisible = true
    },
    handleDownload(file) {
      console.log(file)
    },
    handleSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log('this.form: ', this.form)
          publish({ ...this.form, ...this.contentForm }).then(res => {
            this.$message.success('发布成功！')
            this.dialogVisible = false
            this.$store.dispatch('tagsView/delView', this.$route)
            this.$router.push('/news/articles')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
