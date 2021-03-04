<template>
  <div class="box-category box-category-2depth">
    <nav>
      <ul class="tt_category">
        <li>
          <a class="link_tit" href="/category/">분류 전체보기 <span class="c_cnt">({{ data.total }})</span></a>
          <!-- 카테고리 리스트 -->
            <ul class="category_list">
              <li v-for="(item, idx) in data.list" :key="idx">
                <a class="link_item" :href="`/category/${item.id}`" :class="{ 'selected' : item.category === currentCategory }">
                  {{ item.category }} <span class="c_cnt">({{ item.totalArticles }})</span>
                </a>
                <!-- 서브 카테고리 리스트 -->
                <template v-if="item.children">
                  <ul class="sub_category_list" v-if="item.children.length > 0">
                    <li v-for="(subItem, idx) in item.children" :key="idx">
                      <a class="link_sub_item" :href="`/category/${subItem.id}`" :class="{ 'selected' : subItem.category === currentCategory }">
                        {{ subItem.category }} <span class="c_cnt">({{ subItem.totalArticles }})</span>
                      </a>
                    </li>
                  </ul>
                </template>
              </li>
            </ul>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.dispatch('GET_ASIDE_CATEGORY_LIST');
  },
  computed: {
    data() {
      return this.$store.state.asideCategoryList;
    },
    currentCategory() {
      return this.$store.state.categoryData.category;
    }
  },
  methods: {

  }
};
</script>

<style scoped>

</style>