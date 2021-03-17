<template>
  <footer id="footer">
    <div class="inner-footer">
      <div class="box-policy">
      </div>
      <div>
        <p class="text-info">DESIGNED BY TISTORY (Copyright (c) 2020 TISTORY)</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
};
</script>

<style scoped>

.text-info {
  color: #999 !important;
}

@media screen and (max-width: 1060px) {
  #footer {
    padding: 0 0 26px 0;
  }
}
</style>