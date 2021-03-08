import axios from 'axios';

const config = {
  baseURL: `http://localhost:8080`
};
const securityConfig = {
  ...config,
  withCredentials: true
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

const getArticleData = async (articleId) => {
  return await axios.get(`/article/${articleId}`, securityConfig);
};

const getArticleId = async (categoryId, page) => {
  return await axios.get(`/article/find?categoryId=${categoryId}&page=${page}`, securityConfig);
}

const updateArticleLike = async (articleId) => {
  return await axios.put(`/article/like/${articleId}`, null, securityConfig);
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

const submitCommentData = async (commentData, articleId, parentCommentId) => {
  console.log(commentData, articleId, parentCommentId);
  return await axios.post('/comment/upload', {
    ...commentData,
    articleId, parentCommentId
  }, config);
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

export {
  getHeaderData,
  getMainPageData,
  getCategoryData,
  getSearchData,
  getArticleData,
  getArticleId,
  updateArticleLike,
  getCommentData,
  getCommentCount,
  submitCommentData,
  getAsideProfileData,
  getAsideCategoryList,
  getAsideArticles
};