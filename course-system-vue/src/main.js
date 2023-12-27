import Vue from "vue";
import ElementUI from "element-ui";
import VueClipboard from "vue-clipboard2";
import ECharts from "vue-echarts";
import "element-ui/lib/theme-chalk/index.css";
//改默认颜色
// import "element-ui/lib/theme-chalk/index2.css";
import App from "./App.vue";
// 默认导出导入export default 
import router from "./router";
import store from "./store";
import { vueInstance } from "./common/ajax";
// 变量导入

Vue.config.productionTip = false;
// 用于关闭Vue在启动时的生产模式提示
Vue.use(ElementUI);
// 全局注册Element UI组件库
Vue.use(VueClipboard);
// 全局注册VueClipboard插件。VueClipboard是一个用于复制和粘贴操作的Vue插件
Vue.component("v-chart", ECharts);
// 全局注册，可以在任何地方使用<v-chart>标签来渲染ECharts图表组件。
let instance = new Vue({
  router,
  store,
  render: h => h(App)
  // h是createElement函数的别名。createElement函数是Vue中用于创建虚拟DOM元素的函数
  // render接收一个函数作为参数，该函数用于生成Vue应用程序的根组件。
}).$mount("#app");//.$router.push({ name: "login" });
// 挂载到APP id

vueInstance.instance = instance;
vueInstance.store = instance.$store;


// 属性前加$

// # id
// . class
// *表示选择文档中的所有元素。
// .parent > .child表示选择类名为"parent"的元素的直接子元素中类名为"child"的元素。
// 加号用于选择紧接在指定元素之后的同级元素 
// ~波浪号用于选择在指定元素之后的所有同级元素
// .element ~ .sibling表示选择在类名为"element"的元素后的所有同级元素中的类名为"sibling"的元素。