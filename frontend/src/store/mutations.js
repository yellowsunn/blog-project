export default {
  GET_HEADER_DATA(state, data) {
    state.coverHeaderData = data;
  },
  GET_COVER_ARTICLE_DATA(state, data) {
    state.coverArticle = data;
  },
  GET_COVER_CATEGORY_DATA(state, data) {
    state.coverCategoryData = data;
  },
  GET_CATEGORY_DATA(state, data) {
    state.categoryData = data;
  },
  UPDATE_CATEGORY_DATA(state, data) {
    const articles = [];
    articles.push(...state.categoryData.articles);
    articles.push(...data.articles);
    state.categoryData = data;
    state.categoryData.articles = articles;
  },
  GET_ARTICLE_DATA(state, data) {
    state.articleData = data;
  },
  GET_COMMENT_DATA(state, data) {
    state.commentData = data;
    state.commentData.content = state.commentData.content.filter((comment) => {
      return !comment.parentCommentId
    });
  },
  UPDATE_COMMENT_DATA(state, data) {
    // 답글이 아닌 경우
    const commentData = state.commentData.content;

    if (!data.parentCommentId) {
      commentData.push(data);
    }
    // 답글인 경우
    else {
      let i = 0;
      for (i = 0; i < commentData.length; i++) {
        if (commentData[i].commentId === data.parentCommentId) {
          if (!commentData[i].subComment) commentData[i].subComment = [];
          commentData[i].subComment.push(data);
          break;
        }
      }
    }
    state.commentData.totalElements += 1;
  },
  DELETE_COMMENT_DATA(state, commentId) {
    const commentData = state.commentData.content;

    for (let i = 0; i < commentData.length; i++) {
      if (commentData[i].commentId === commentId) {
        state.commentData.totalElements -= 1;
        if (commentData[i].subComment) {
          state.commentData.totalElements -= commentData[i].subComment.length;
        }
        commentData.splice(i, 1); // 삭제
        return;
      }

      let subCommentData = commentData[i].subComment;
      if (!subCommentData) continue;
      for (let j = 0; j < subCommentData.length; j++) {
        if (subCommentData[j].commentId === commentId) {
          state.commentData.totalElements -= 1;
          subCommentData.splice(j, 1); // 삭제
          return;
        }
      }
    }
  },
  // 더보기로 불러오는 댓글
  LOAD_COMMENT_DATA(state, data) {
    // 기존 콘텐츠
    const content = state.commentData.content;
    let newContent = data.content.filter((comment) => {
      return !comment.parentCommentId;
    });
    newContent.push(...content);

    // 중복 제거
    let set = new Set(newContent.map(JSON.stringify));
    newContent = Array.from(set).map(JSON.parse);

    state.commentData = data;
    state.commentData.content = newContent;
  },
  LOADING_COMMENT_STATE(state) {
    state.loadComments = true;
  },
  INIT_COMMENT_STATE(state) {
    state.loadComments = false;
  },
  GET_ASIDE_PROFILE_DATA(state, data) {
    state.asideProfileData = data;
  },
  GET_ASIDE_CATEGORY_LIST(state, data) {
    state.asideCategoryList = data;
  },
  GET_ASIDE_ARTICLES(state, data) {
    state.asideArticles = data;
  },
  GET_COVER_CATEGORY_ID(state, data) {
    state.coverCategoryId = data;
  },
  GET_COMMENT_HISTORY(state, data) {
    state.commentHistory = data;
  }
}