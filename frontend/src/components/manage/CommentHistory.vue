<template>
  <section>
    <b-table-simple hover small>
      <b-thead>
        <b-tr>
          <b-th>게시글 ID</b-th>
          <b-th>날짜</b-th>
          <b-th>유저명</b-th>
          <b-th>아이피</b-th>
          <b-th>댓글내용</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="(item, idx) in historyData.list" @click="goToArticle(item.articleId)" :key="idx">
          <b-td class="simple_content">{{ item.articleId }}</b-td>
          <b-td>{{ item.date }}</b-td>
          <b-td>{{ item.name }}</b-td>
          <b-td>{{ item.isManager ? "관리자" : item.ipAddr }}</b-td>
          <b-td class="simple_content">{{ item.content.length > 50 ? `${item.content.substring(0, 50)}...` : item.content }}</b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <AreaPage :pageData="historyData"></AreaPage>

    <div class="btn_container">
      <b-button class="button" @click="cancel">돌아가기</b-button>
    </div>
  </section>
</template>

<script>
import AreaPage from '@/components/layout/container/area-main/area-view/AreaPage';

export default {
  components: { AreaPage },
  created() {
    this.$store.dispatch('GET_COMMENT_HISTORY', this.$route.query.page);
  },
  computed: {
    historyData() {
      return this.$store.state.commentHistory;
    }
  },
  methods: {
    cancel() {
      this.$store.state.commentHistory = {};
      this.$router.push("/manage");
    },
    goToArticle(articleId) {
      const url = `${window.location.protocol}//${window.location.host}`;
      window.location.href = `${url}/${articleId}`;
    }
  }
};
</script>

<style lang="scss" scoped>
section {
  font-size: 12px;
  padding: 0 10px;
}

.manager {
  background-color: #f5c6cb;
}

.btn_container {
  display: flex;
  justify-content: center;
  .button {
    width: 6.250em;
    margin: 1.5em 0.625em 0.625em;
  }
}
</style>