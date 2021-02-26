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
    params: { page }
  })
};

const getArticleData = async (articleId) => {
  return await axios.get(`/article/${articleId}`, config);
}

export {
  getHeaderData,
  getMainPageData,
  getCategoryData,
  getArticleData
};