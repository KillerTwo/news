const axios = require('axios');

// 查看详情
export async function getDetail(id: number) {
    try {
        return await axios.get(`/detail/{id}`);
    } catch(e) {
        console.log(e)
    }
}

