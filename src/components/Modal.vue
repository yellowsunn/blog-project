<template>
  <div>
    <modal name="reply" :adaptive="true">
      <div class="reply-modal">
        <AreaWrite v-on:submit="replySubmit"></AreaWrite>
      </div>
    </modal>
    <modal name="delete" :adaptive="true" :width="450">
      <DeleteComment v-on:cancel="deleteCancel" v-on:submit="deleteSubmit"></DeleteComment>
    </modal>
  </div>
</template>

<script>
import AreaWrite from '@/components/AreaWrite';
import DeleteComment from '@/components/DeleteComment';

export default {
  components: {
    AreaWrite, DeleteComment
  },
  methods: {
    // replySubmit(event) {
    replySubmit() {
      this.$modal.hide("reply");
    },
    deleteCancel() {
      this.$modal.hide('delete');
    },
    deleteSubmit(event) {
      console.log(event);
      this.$modal.hide('delete');
    }
  }
};
</script>

<style scoped>
.reply-modal {
  padding: 30px;
}
</style>