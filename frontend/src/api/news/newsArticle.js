import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/newsArticle',
    method: 'post',
    data
  })
}

export function publish(data) {
  return request({
    url: 'api/newsArticle/publish',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/newsArticle/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/newsArticle',
    method: 'put',
    data
  })
}

export default {
  add,
  edit,
  del
}
