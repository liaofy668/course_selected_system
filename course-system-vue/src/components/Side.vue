<!-- 用户点击<el-menu-item>组件时，会触发路由功能并进行导航。
  在代码中，<el-menu>组件具有router属性，这意味着该菜单是一个Vue路由的一部分。
  在<el-menu>组件上设置了:default-active="routePath"属性，routePath是通过计算属性获取的当前路由路径（$route.path）。
  这意味着该菜单项的index值与当前路由路径匹配时，该菜单项将被标记为活动状态
  因此，当用户点击菜单项时，如果该菜单项的index值与当前路由路径匹配，Vue路由系统将导航到对应的路径 -->
<template>
  <div class="sidebar">
    <el-menu
      :default-active="routePath"
      active-text-color="#4e5ac3"
      background-color="#FFFFFF"
      class="sidebar-el-menu"
      router
      text-color="#4EACC3"
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu
                :index="subItem.index"
                :key="subItem.index"
                v-if="subItem.subs"
              >
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item
                  :index="threeItem.index"
                  :key="i"
                  v-for="(threeItem, i) in subItem.subs"
                  >{{ threeItem.title }}
                </el-menu-item>
              </el-submenu>
              <!-- <el-menu-item :index="subItem.index" :key="subItem.index" v-else
                >{{ subItem.title }}
              </el-menu-item> -->
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "SideBar",
  computed: {
    items() {
      return this.$store.state.sideBarItems;
    },
    routePath() {
      return this.$route.path;
    }
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 200px;
}

.sidebar > ul {
  height: 100%;
}
</style>
