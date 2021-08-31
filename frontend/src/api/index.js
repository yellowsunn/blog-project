import axios from 'axios';
import getEnv from '@/utils/env'

const server = getEnv('VUE_APP_API_DOMAIN')

const config = {
  baseURL: `${server}/api`,
  withCredentials: true
};

const fetchLogin = async (account) => {
  return await axios.post('/login', account, config);
};

const getAuthority = async () => {
  return await axios.get("/authority", config);
};

const updateHeaderData = async (header) => {
  return await axios.put('/header', header, config);
};

const getHeaderData = async () => {
  return await axios.get('/header', config);
};

const getMainPageData = async () => {
  return await axios.get('/info', config);
};

const getCategoryData = async (categoryId, page) => {
  if (!categoryId) categoryId = '';

  return await axios.get(`/categories/${categoryId}`, {
    ...config,
    params: { page, size: 10 }
  })
};

const getSearchData = async (keyword, page) => {
  if (!keyword) keyword = '';
  return await axios.get('/categories/search', {
    ...config,
    params: { 
      keyword,
      page, size: 10 
    }
  })
};

const uploadArticleData = async (formData) => {
  return await axios.post('/article', formData, {
    ...config,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
};

const updateArticleData = async (formData) => {
  return await axios.put('/article', formData, {
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
  return await axios.delete(`/article/${articleId}`, config);
};

const getArticleId = async (categoryId, page) => {
  return await axios.get(`/article/find?categoryId=${categoryId}&page=${page}`, config);
}

const updateArticleLike = async (articleId) => {
  return await axios.put(`/article/${articleId}/like`, null, config);
}

const getCommentData = async (articleId, page) => {
  return await axios.get(`/comments/${articleId}`, {
    ...config,
    params: { page, size: 30 }
  });
};

const getCommentCount = async (articleId) => {
  return await axios.get(`/comments/${articleId}/count`, config);
};

const submitCommentData = async (commentData, articleId, parentCommentId) => {
  return await axios.post('/comments', {
    ...commentData,
    articleId, parentCommentId
  }, config);
};

const deleteCommentData = async (commentId, password) => {
  return await axios.delete('/comments', {
    ...config,
    data: { commentId, password }
  });
};

const updateAsideProfileData = async (formData) => {
  return await axios.put("/profile", formData, {
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
  return await axios.get('/categories', config);
};

const getAsideArticles = async () => {
  return await axios.get('/article/aside', config);
}

const getCoverCategoryId = async () => {
  return await axios.get('/coverCategoryId', config);
}

const updateCoverCategoryId = async (articleCategoryId, categoryId) => {
  return await axios.put('/coverCategoryId', {
    articleCategoryId, categoryId
  }, config);
}

const createCategory = async (data) => {
  return await axios.post('/categories', data, config);
}

const getCategoryInfo = async (categoryId) => {
  return await axios.get(`/categories/${categoryId}/info`, config);
}

const updateCategory = async (data) => {
  return await axios.put('/categories', data, config);
}

const deleteCategory = async (categoryId) => {
  return await axios.delete(`/categories/${categoryId}`, config);
}

const getCommentHistory = async (page) => {
  return axios.post(`/comments/history`, null, {
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