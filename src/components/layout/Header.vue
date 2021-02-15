<template>
  <header class="header">
    <div class="line-bottom display-none"></div>

    <!-- inner-header -->
    <div class="inner-header topnavmenu" :class="{ 'slogun-use' : hasSlogun}">

      <div class="box-header">
        <h1 class="title-logo">
          <a href="/" title="title" class="link_logo">{{ data.title }}</a>
        </h1>

        <!-- search-bar for PC -->
        <div class="util use-top">
          <div class="search">
            <input class="searchInput" type="text" name="search" value="" placeholder="Search..." onkeypress="if (event.keyCode == 13) { requestSearch('.util.use-top .searchInput') }"/>
          </div>
        </div>
      </div>

      <!-- area-align -->
      <div class="area-align">

        <template v-if="hasSlogun">
          <!-- area-slogan -->
          <div class="area-slogun topnavmenu slogunmobileoff">
            <strong>{{ data.slogunTitle }}</strong>
            <p v-html="data.slogunText"></p>
          </div>
        </template>

        <!-- area-gnb -->
        <div class="area-gnb" v-if="hasCategory">
          <nav class="topnavmenu">
            <ul>
              <li class="t_menu_home" :class="{ first: index === 0, last: index === data.categories.length - 1 }" v-for="(category, index) in data.categories" :key="index">
                <a :href="category.link">{{ category.name }}</a>
              </li>
            </ul>
          </nav>
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
    },
    hasCategory() {
      return !(this.data.categories === undefined || this.data.categories.length === 0);
    }
  },
  methods: {
    asideOnEvent(event) {
      this.$store.state.asideOn = true;
      document.body.classList.add('bg-dimmed');
      document.body.style.overflow = 'hidden';
      event.stopImmediatePropagation();
    }
  }
};
</script>

<style lang="scss" scoped>
</style>