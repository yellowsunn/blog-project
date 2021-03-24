import {
  createCategory,
  deleteArticleData, deleteCategory,
  deleteCommentData,
  fetchLogin,
  getArticleData,
  getArticleId,
  getAsideArticles,
  getAsideCategoryList,
  getAsideProfileData,
  getAuthority,
  getCategoryData,
  getCategoryInfo,
  getCommentCount,
  getCommentData, getCommentHistory,
  getCoverCategoryId,
  getHeaderData,
  getMainPageData,
  getSearchData,
  submitCommentData,
  updateArticleData,
  updateArticleLike,
  updateAsideProfileData, updateCategory,
  updateCoverCategoryId,
  updateHeaderData,
  uploadArticleData,
} from '@/api';

export default {
  async FETCH_LOGIN(context, account) {
    return fetchLogin(account);
  },
  async GET_AUTHORITY({ state }) {
    try {
      const response = await getAuthority();
      state.isAuthorized = true;
      return response;
    } catch (error) {
      state.isAuthorized = false;
    }
  },
  async UPDATE_HEADER_DATA(context, headerData) {
    return await updateHeaderData(headerData);
  },
  async GET_HEADER_DATA({ commit }) {
    try {
      const response = await getHeaderData();
      commit('GET_HEADER_DATA', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async GET_MAIN_PAGE_DATA({ commit }) {
    try {
      const response = await getMainPageData();
      commit('GET_COVER_ARTICLE_DATA', response.data.coverArticle);
      commit('GET_COVER_CATEGORY_DATA', response.data.coverCategory);
    } catch (error) {
      console.log(error);
    }
  },
  async GET_CATEGORY_DATA({ commit }, { categoryId, page }) {
    try {
      const response = await getCategoryData(categoryId, page);
      commit('GET_CATEGORY_DATA', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async UPDATE_CATEGORY_DATA({ state, commit }, { categoryId, page }) {
    state.loadPage = true;
    try {
      const response = await getCategoryData(categoryId, page);
      commit('UPDATE_CATEGORY_DATA', response.data);
    } catch (error) {
      console.log(error);
    } finally {
      state.loadPage = false;
    }
  },
  async GET_SEARCH_DATA({ commit }, {search, page}) {
    try {
      const response = await getSearchData(search, page);
      commit('GET_CATEGORY_DATA', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async UPDATE_SEARCH_DATA({ state, commit }, { search, page }) {
    state.loadPage = true;
    try {
      const response = await getSearchData(search, page);
      commit('UPDATE_CATEGORY_DATA', response.data);
    } catch (error) {
      console.log(error);
    } finally {
      state.loadPage = false;
    }
  },

  async UPLOAD_ARTICLE_DATA(context, formData) {
    return await uploadArticleData(formData);
  },
  async UPDATE_ARTICLE_DATA(context, formData) {
    return await updateArticleData(formData);
  },
  async GET_ARTICLE_DATA({ commit }, articleId) {
    try {
      const response = await getArticleData(articleId);
      commit('GET_ARTICLE_DATA', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async DELETE_ARTICLE_DATA(context, articleId) {
    return await deleteArticleData(articleId);
  },
  async GET_ARTICLE_ID(context, { categoryId, page }) {
    return await getArticleId(categoryId, page);
  },
  async UPDATE_ARTICLE_LIKE(context, articleId) {
    try {
      await updateArticleLike(articleId);
    } catch (error) {
      Error("Failed to update like");
    }
  },
  async GET_COMMENT_DATA({ commit }, { articleId, page }) {
    try {
      if (page === undefined) {
        const commentCount = await getCommentCount(articleId);
        page = parseInt((commentCount.data - 1) / 30);
        const response = await getCommentData(articleId, page);
        commit('GET_COMMENT_DATA', response.data);
      } else {
        // 댓글 더보기
        commit('LOADING_COMMENT_STATE');

        const response = await getCommentData(articleId, page);
        commit('LOAD_COMMENT_DATA', response.data);

        commit('INIT_COMMENT_STATE');
      }
    } catch (error) {
      console.log(error);
      commit('INIT_COMMENT_STATE')
    }
  },
  async SUBMIT_COMMENT_DATA({ commit }, { commentData, articleId, parentCommentId}) {
    if (!parentCommentId) parentCommentId = '';

    // eslint-disable-next-line no-useless-catch
    try {
      const response = await submitCommentData(commentData, articleId, parentCommentId);
      commit('UPDATE_COMMENT_DATA', response.data);
      console.log(response.data);
      return response;
    } catch (error) {
      throw error;
    }
  },
  async DELETE_COMMENT_DATA({ commit }, { commentId, password }) {

    // eslint-disable-next-line no-useless-catch
    try {
      await deleteCommentData(commentId, password);
      commit('DELETE_COMMENT_DATA', commentId);
    } catch (error) {
      throw error;
    }
  },
  async UPDATE_ASIDE_PROFILE_DATA(context, formData) {
    return await updateAsideProfileData(formData);
  },
  async GET_ASIDE_PROFILE_DATA({ commit }) {
    try {
      const response = await getAsideProfileData();
      commit('GET_ASIDE_PROFILE_DATA', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async GET_ASIDE_CATEGORY_LIST({ commit }) {
    try {
      const response = await getAsideCategoryList();
      commit('GET_ASIDE_CATEGORY_LIST', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async GET_ASIDE_ARTICLES({ commit }) {
    try {
      const response = await getAsideArticles();
      commit('GET_ASIDE_ARTICLES', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async GET_COVER_CATEGORY_ID({ commit }) {
    try {
      const response = await getCoverCategoryId();
      commit('GET_COVER_CATEGORY_ID', response.data);
    } catch (error) {
      console.log(error);
    }
  },
  async UPDATE_COVER_CATEGORY_ID(context, { articleCategoryId, categoryId }) {
    await updateCoverCategoryId(articleCategoryId, categoryId)
  },
  async CREATE_CATEGORY(context, data) {
    await createCategory(data);
  },
  async GET_CATEGORY_INFO(context, categoryId) {
    // eslint-disable-next-line no-useless-catch
    try {
      const response = await getCategoryInfo(categoryId);
      return response.data;
    } catch (error) {
      throw error;
    }
  },
  async UPDATE_CATEGORY(context, data) {
    await updateCategory(data);
  },
  async DELETE_CATEGORY(context, categoryId) {
    await deleteCategory(categoryId);
  },
  async GET_COMMENT_HISTORY({ commit }, page) {
    try {
      const response = await getCommentHistory(page);
      commit('GET_COMMENT_HISTORY', response.data);
    } catch (error) {
      console.log(error);
    }
  }
}