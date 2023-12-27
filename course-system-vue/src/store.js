import Vue from "vue";
import Vuex from "vuex";
import SideItem from "./common/sideItem";

Vue.use(Vuex);
// Vuex模块，它是Vue.js的官方状态管理库，用于集中管理应用程序的状态。
// 通过new Vuex.Store()创建一个新的Vuex存储实例，并将其导出为默认模块
// 使用store.state来访问状态对象，
// 使用store.commit()来触发一个mutation方法 
// 使用store.dispatch()来触发一个action方法。
export default new Vuex.Store({
  state: {
    status: {
      loggedIn: false,
      userId: -1,
      swnumber: "",
      userName: "",
      userType: 0,
      permission: 0
    },
    sideBarItems: [],
    loading: false
  },
  // state：状态对象，用于存储应用程序的数据。响应式对象，当状态发生变化时相关组件更新。
  // 通过this.$store.state或使用mapState辅助函数来访问状态。
  mutations: {
    login(state, res) {
      // res:后端返回的结果response
      state.status.loggedIn = res.loggedIn;
      state.status.userId = res.userId;
      state.status.swnumber = res.swnumber;
      state.status.userType = res.userType;
      state.status.permission = res.permission;
      state.status.userName = res.userName;

      let items = [];
      for (let i = 0; i < SideItem.items.length; i++) {
        let item = SideItem.items[i];
        if (
          item.userType === res.userType 
          // && (item.permission & res.permission) === item.permission
        ) {
          items.push(item);
        }
      }
      state.sideBarItems = items;
    },
    logout(state) {
      state.status = {
        loggedIn: false,
        userId: -1,
        swnumber: "",
        userName: "",
        userType: 0,
        permission: 0
      };
    }
  },
  // 用于修改状态的方法。每个mutation都是一个函数，接收state作为第一个参数，并可以接收额外的参数来进行状态的修改。
  // mutation必须是同步函数，用于追踪状态的变化。可以通过commit方法来触发mutation
  actions: {},
  // 异步操作对象，用于处理异步操作和提交mutation，可以通过dispatch方法来触发action。
  modules: {}
  // 用于将store分割成多个独立的模块。每个模块拥有自己的state、getters、mutations、actions等
});
