<template>
  <section>
    <div class="main_container">
      <div class="sub_container">
        <b-button class="manage_btn" variant="outline-primary" @click="editHeader">상단 정보 수정</b-button>
        <b-button class="manage_btn" variant="outline-primary" @click="editProfile">프로필 수정</b-button>
      </div>
      <div class="sub_container">
        <b-button class="manage_btn long_btn" variant="outline-info" @click="editCover">커버 아이템 수정</b-button>
      </div>
      <div class="sub_container">
        <b-button class="manage_btn" variant="outline-danger" @click="newArticle">게시글 작성</b-button>
        <b-button class="manage_btn" variant="outline-danger" @click="editCategory">카테고리 편집</b-button>
      </div>
      <div class="sub_container">
        <b-button class="manage_btn long_btn" variant="outline-secondary" @click="showCommentHistory">댓글 히스토리</b-button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    const url = `${window.location.protocol}//${window.location.host}`;
    return {
      url,
    }
  },
  methods: {
    editHeader() {
      window.location.href = `${this.url}/manage/header`
    },
    editProfile() {
      window.location.href = `${this.url}/manage/profile`
    },
    editCover() {
      window.location.href = `${this.url}/manage/cover`;
    },
    newArticle() {
      window.location.href = `${this.url}/newpost`
    },
    editCategory() {
      window.location.href = `${this.url}/manage/category`;
    },
    showCommentHistory() {
      window.location.href = `${this.url}/manage/comment_history`;
    }
  }
};
</script>

<style lang="scss" scoped>
section {
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  .main_container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 1.875em;
    border: 1px solid;
    border-radius: 0.500em;
    .sub_container {
      .manage_btn {
        font-size: 1.875em;
        width: 8.333em;
        margin: 0.5em 0.333em;
      }

      .long_btn {
        width: 17.333em;
      }
    }
  }
}

@media screen and (max-width: 1060px) {
  section {
    font-size: 0.875rem;
  }
}

@media screen and (max-width: 660px) {
  section {
    font-size: 0.813rem;
    .main_container {
      .sub_container {
        display: flex;
        flex-direction: column;
        .long_btn {
          width: 8.333em;
        }
      }
    }
  }
}
</style>