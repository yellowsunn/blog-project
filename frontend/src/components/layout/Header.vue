<template>
  <header class="header border-none">
    <div class="line-bottom display-none"></div>

    <!-- inner-header -->
    <div class="inner-header topnavnone" :class="{ 'slogun-use' : hasSlogun}">

      <div class="box-header">
        <h1 class="title-logo">
          <a href="/" title="title" class="link_logo">{{ data.title }}</a>
        </h1>

        <!-- search-bar for PC -->
        <div class="util use-top">
          <div class="search">
            <input class="searchInput" type="text" name="search" :value="search" @input="search = $event.target.value" placeholder="Search..." @keyup.enter="searchData"/>
          </div>
        </div>
      </div>

      <!-- area-align -->
      <div class="area-align">

        <template v-if="hasSlogun">
          <!-- area-slogan -->
          <div class="area-slogun topnavnone slogunmobileoff">
            <strong>{{ data.slogunTitle }}</strong>
            <p v-html="data.slogunText"></p>
          </div>
        </template>

        <!-- area-gnb -->
        <div class="area-gnb">
          <nav class="topnavnone"></nav>
        </div>

        <button type="button" class="button-menu" @click="asideOnEvent">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="14" viewBox="0 0 20 14">
            <path fill="#333" fill-rule="evenodd" d="M0 0h20v2H0V0zm0 6h20v2H0V6zm0 6h20v2H0v-2z"></path>
          </svg>
        </button>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  computed: {
    data() {
      return this.$store.state.coverHeaderData;
    },
    hasSlogun() {
      return !(this.data.slogunTitle === undefined || this.data.slogunTitle === '');
    }
  },
  data() {
    return {
      search: '',
    }
  },
  methods: {
    asideOnEvent(event) {
      this.$store.state.asideOn = true;
      document.body.classList.add('bg-dimmed');
      document.body.style.overflow = 'hidden';
      event.stopImmediatePropagation();
    },
    searchData() {
      if (!this.search) return;

      const url = `${window.location.protocol}//${window.location.host}`;
      window.location.href = `${url}/search/${this.search}`;
    }
  }
};
</script>

<style lang="scss" scoped>
</style>