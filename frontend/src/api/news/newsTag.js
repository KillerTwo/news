import request from '@/utils/request'

export function listTagAll(params) {
  return request({
    url: 'api/newsTag/listAll',
    method: 'get',
    params: params
  })
}

export function add(data) {
  return request({
    url: 'api/newsTag',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/newsTag/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/newsTag',
    method: 'put',
    data
  })
}

export default { add, edit, del }
