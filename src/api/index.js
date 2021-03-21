import axios from 'axios';

const config = {
  baseURL: `http://localhost:8080`,
  withCredentials: true
};
const securityConfig = {
  ...config,
  withCredentials: true
};

const fetchLogin = async (account) => {
  return await axios.post('/api/login', account, securityConfig);
};

const getAuthority = async () => {
  return await axios.get("/api/authority", securityConfig);
};

const updateHeaderData = async (header) => {
  return await axios.put('/admin/update/header', header, securityConfig);
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
    ...securityConfig,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
};

const updateArticleData = async (formData) => {
  return await axios.put('/article/update', formData, {
    ...securityConfig,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
};

const getArticleData = async (articleId) => {
  return await axios.get(`/article/${articleId}`, securityConfig);
};

const deleteArticleData = async (articleId) => {
  return await axios.delete(`/article/delete/${articleId}`, securityConfig);
};

const getArticleId = async (categoryId, page) => {
  return await axios.get(`/article/find?categoryId=${categoryId}&page=${page}`, securityConfig);
}

const updateArticleLike = async (articleId) => {
  return await axios.put(`/article/like/${articleId}`, null, securityConfig);
}

const getCommentData = async (articleId, page) => {
  return await axios.get(`/comment/${articleId}?size=30`, {
    ...securityConfig,
    params: { page }
  });
};

const getCommentCount = async (articleId) => {
  return await axios.get(`/comment/count/${articleId}`, securityConfig);
};

const submitCommentData = async (commentData, articleId, parentCommentId) => {
  return await axios.post('/comment/upload', {
    ...commentData,
    articleId, parentCommentId
  }, securityConfig);
};

const deleteCommentData = async (commentId, password) => {
  return await axios.delete('/comment/delete', {
    ...securityConfig,
    data: { commentId, password }
  });
};

const updateAsideProfileData = async (formData) => {
  return await axios.put("/admin/update/profile", formData, {
    ...securityConfig,
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
  getCoverCategoryId, updateCoverCategoryId
};