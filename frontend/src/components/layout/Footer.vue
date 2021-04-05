<template>
  <footer id="footer">
    <div class="inner-footer">
      <div class="box-policy">
      </div>
      <div>
        <p class="text-info">Copyright 2021. yellowsunn. All rights reserved.</p>
        <p class="text-info">Theme by <a href="https://tistory1.daumcdn.net/tistory/0/Odyssey/LICENSE">TISTORY</a> (© 2020. TISTORY. All rights reserved.)</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
};
</script>

<style lang="scss" scoped>
a {
  color: #999 !important;
  cursor: pointer;
  &:hover {
    text-decoration: underline !important;
    color: #999 !important;
  }
}

.text-info {
  color: #999 !important;
}

@media screen and (max-width: 1060px) {
  #footer {
    padding: 0 0 26px 0;
  }
}
</style>