import axios from 'axios';

const config = {
  baseURL: `http://localhost:8080`,
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

const getArticleData = async (articleId) => {
  return await axios.get(`/article/${articleId}`, config);
};

const getArticleId = async (categoryId, page) => {
  return await axios.get(`/article/find?categoryId=${categoryId}&page=${page}`, config);
}

const getCommentData = async (articleId, page) => {
  return await axios.get(`/comment/${articleId}?size=30`, {
    ...config,
    params: { page }
  });
};

const getCommentCount = async (articleId) => {
  return await axios.get(`/comment/count/${articleId}`, config);
};

const getAsideProfileData = async () => {
  return await axios.get("/profile", config);
}

const getAsideCategoryList = async () => {
  return await axios.get('/categoryList', config);
};

export {
  getHeaderData,
  getMainPageData,
  getCategoryData,
  getArticleData,
  getArticleId,
  getCommentData,
  getCommentCount,
  getAsideProfileData,
  getAsideCategoryList
};