import axios from 'axios';
import getEnv from '@/utils/env'

const server = getEnv('VUE_APP_API_DOMAIN')

const config = {
  baseURL: `${server}`,
  withCredentials: true
};

const fetchLogin = async (account) => {
  return await axios.post('/api/login', account, config);
};

const getAuthority = async () => {
  return await axios.get("/api/authority", config);
};

const updateHeaderData = async (header) => {
  return await axios.put('/admin/update/header', header, config);
};

const getHeaderData = async () => {
  return await axios.get('/header', config);
};

const getMainPageData = async () => {
  return await axios.get('/', config);
};

const getCategoryData = async (categoryId, page) => {
  if (!categoryId) categoryId = '';

  return await axios.get(`/category/${categoryId}`, {
    ...config,
    params: { page, size: 10 }
  })
};

const getSearchData = async (search, page) => {
  if (!search) search = '';
  return await axios.get(`/search/${search}`, {
    ...config,
    params: { page, size: 10 }
  })
};

const uploadArticleData = async (formData) => {
  return await axios.post('/article/create', formData, {
    ...config,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
};

const updateArticleData = async (formData) => {
  return await axios.put('/article/update', formData, {
    ...config,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
};

const getArticleData = async (articleId) => {
  return await axios.get(`/article/${articleId}`, config);
};

const deleteArticleData = async (articleId) => {
  return await axios.delete(`/article/delete/${articleId}`, config);
};

const getArticleId = async (categoryId, page) => {
  return await axios.get(`/article/find?categoryId=${categoryId}&page=${page}`, config);
}

const updateArticleLike = async (articleId) => {
  return await axios.put(`/article/like/${articleId}`, null, config);
}

const getCommentData = async (articleId, page) => {
  return await axios.get(`/comment/${articleId}`, {
    ...config,
    params: { page, size: 30 }
  });
};

const getCommentCount = async (articleId) => {
  return await axios.get(`/comment/count/${articleId}`, config);
};

const submitCommentData = async (commentData, articleId, parentCommentId) => {
  return await axios.post('/comment/upload', {
    ...commentData,
    articleId, parentCommentId
  }, config);
};

const deleteCommentData = async (commentId, password) => {
  return await axios.delete('/comment/delete', {
    ...config,
    data: { commentId, password }
  });
};

const updateAsideProfileData = async (formData) => {
  return await axios.put("/admin/update/profile", formData, {
    ...config,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};

const getAsideProfileData = async () => {
  return await axios.get("/profile", config);
}

const getAsideCategoryList = async () => {
  return await axios.get('/categoryList', config);
};

const getAsideArticles = async () => {
  return await axios.get('/asideArticles', config);
}

const getCoverCategoryId = async () => {
  return await axios.get('/coverCategoryId', config);
}

const updateCoverCategoryId = async (articleCategoryId, categoryId) => {
  return await axios.put('/admin/update/coverCategoryId', {
    articleCategoryId, categoryId
  }, config);
}

const createCategory = async (data) => {
  return await axios.post('/category/create', data, config);
}

const getCategoryInfo = async (categoryId) => {
  return await axios.get(`/category/info/${categoryId}`, config);
}

const updateCategory = async (data) => {
  return await axios.put('/category/update', data, config);
}

const deleteCategory = async (categoryId) => {
  return await axios.delete(`/category/delete/${categoryId}`, config);
}

const getCommentHistory = async (page) => {
  return axios.post(`/admin/comment/history`, null, {
    ...config,
    params: {
      size: 20, page,
    }
  });
}

const getAllMainViewData = async () => {
  return axios.all([
    getHeaderData(),
    getMainPageData(),
    getAsideProfileData(), getAsideCategoryList(), getAsideArticles(),
  ]);
}

const getAllArticleViewData = async (articleId, page) => {
  return axios.all([
    getHeaderData(),
    getArticleData(articleId),
    getCommentData(articleId, page),
    getAsideProfileData(), getAsideCategoryList(), getAsideArticles(),
  ]);
}

const getAllCategoryViewData = async (categoryId, page) => {
  return axios.all([
    getHeaderData(),
    getCategoryData(categoryId, page),
    getAsideProfileData(), getAsideCategoryList(), getAsideArticles(),
  ]);
}

const getAllSearchViewData = async (search, page) => {
  return axios.all([
    getHeaderData(),
    getSearchData(search, page),
    getAsideProfileData(), getAsideCategoryList(), getAsideArticles(),
  ]);
}

export {
  fetchLogin, getAuthority,
  updateHeaderData, getHeaderData,
  getMainPageData,
  getCategoryData,
  getSearchData,
  uploadArticleData, updateArticleData, getArticleData, deleteArticleData,
  getArticleId,
  updateArticleLike,
  getCommentData,
  getCommentCount,
  submitCommentData,
  deleteCommentData,
  updateAsideProfileData, getAsideProfileData,
  getAsideCategoryList,
  getAsideArticles,
  getCoverCategoryId, updateCoverCategoryId,
  createCategory, getCategoryInfo, updateCategory, deleteCategory,
  getCommentHistory,
  getAllMainViewData, getAllArticleViewData, getAllCategoryViewData,getAllSearchViewData,
};