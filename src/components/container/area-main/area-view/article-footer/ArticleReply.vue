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

      <!-- AreaWrite -->
      <AreaWrite></AreaWrite>
    </div>
  </div>
</template>

<script>
import Reply from '@/components/Reply';
import AreaWrite from '@/components/AreaWrite';

export default {
  name: 'ArticleReply',
  components: { Reply, AreaWrite },
};
</script>

<style scoped>
</style>