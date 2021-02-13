<template>
  <div class="article-reply">
    <div class="box-total">
      <a href="#">댓글 <span>0</span></a>
    </div>
    <div>
      <!-- area-reply -->
      <div class="area-reply">
        <ul class="list-reply">
          <!-- 반복 -->
          <Reply></Reply>
        </ul>
      </div>
      <form method="post">
        <!-- reply_write -->
        <div class="area-write">
          <div class="box-account">
            <input type="text" title="이름" placeholder="이름" />
            <input type="password" title="비밀번호" maxlength="12" placeholder="비밀번호" />
          </div>
          <div class="box-textarea">
            <textarea placeholder="내용을 입력해주세요."></textarea>
          </div>
          <div class="box-write">
            <label class="xe-label">
              <input type="checkbox" id="secret">
              <span class="xe-input-helper"></span>
              <span class="xe-label-text">비밀글</span>
            </label>
            <button type="button" class="btn_register">등록</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import Reply from '@/components/Reply';
export default {
  name: 'ArticleReply',
  components: { Reply },
};
</script>

<style scoped>
</style>