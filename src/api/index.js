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

export {
  getHeaderData,
  getMainPageData,
};