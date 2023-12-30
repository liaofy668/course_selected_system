<template>
  <div class="login-wrap">
    <div class="login-form">
      <div class="form-title">信息管理系统</div>
      <el-form :model="form" :rules="rules" class="form-content" label-width="0px" ref="form">
        <!-- ref表单添加了一个引用名为form，可以在代码中通过该引用名来操作这个表单 -->
        <!-- elemnetUI 的表单项 -->
        <el-form-item prop="swnumber">
          <el-input :placeholder="form.userType == 1 ? '学生账户名' : (form.userType == 2 ? '教师账户名' : '管理员账户名')"
            v-model="form.swnumber">
            <!-- v-model用于绑定,placeholder属性指定了在输入框中显示的提示文本为"学工号" -->
            <span slot="prepend"><i class="el-icon-user"></i></span>
            <!-- prepend插槽被用于在输入框前面添加内容 -->
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input @keyup.enter.native="submit()" placeholder="密码" type="password" v-model="form.password" show-password>
            <!-- 事件监听器，当用户在输入框中按下回车键时，会触发submit()方法，执行相应的逻辑。 -->
            <!-- @keyup是一个Vue中的事件修饰符，用于监听键盘按键事件 -->
            <span slot="prepend"><i class="el-icon-edit"></i></span>
          </el-input>
        </el-form-item>

        <el-form-item>
          <div style="display: flex">
            <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%;"
              placeholder="请输入验证码"></el-input>
            <ValidCode @input="createValidCode" />
          </div>
        </el-form-item>

        <el-form-item prop="userType">
          <el-radio-group v-model="form.userType">
            <el-radio label="1" class="checkbox">学生</el-radio>
            <el-radio label="2" class="checkbox">教师</el-radio>
            <el-radio label="3" class="checkbox">管理员</el-radio>
          </el-radio-group>
        </el-form-item>

        <div class="login-btn" v-loading="this.$store.state.loading">
          <!-- 根据Vue应用的状态管理中的loading状态来控制加载状态的显示与隐藏。 -->
          <el-button @click="submit()" type="primary">登录</el-button>
          <!-- @click是Vue的事件绑定语法，指定了点击按钮时触发的方法submit() -->
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from "@/api/login";
import ValidCode from "@/components/ValidCode";
export default {
  components: {
    ValidCode
  },
  data: function () {

    return {
      form: {
        swnumber: "",
        password: "",
        userType: "1"
      },
      rules: {
        swnumber: [
          { required: true, message: "请输入账户名", trigger: "blur" }
          // 不为空,失去焦点时触发
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        userType: [
          { required: true, message: "请选择用户类型", trigger: "blur" }
        ]
      },
      validCode: ""
    };
  },
  methods: {
    // 接收验证码组件提交的 4位验证码
    createValidCode(data) {
      this.validCode = data;
    },
    submit() {
      this.$refs.form.validate(valid => {
        // vaildate内置函数
        // Vue.js 中对一个表单进行验证的方法。它调用一个表单实例的 validate 方法，
        // 根据预设的规则检查表单中的输入字段是否符合要求。如果所有字段都通过验证，validate 方法会返回 true，否则返回 false
        
        if (valid) {
          if (!this.form.validCode) {
            this.$message.error("请填写验证码");
            return;
          }
          if (this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误");
            return;
          }
          login(
            this.form.swnumber,
            this.form.password,
            this.form.userType
          ).then((res) => {
            if (!res.loggedIn) {
              alert("用户名或密码错误，请重新输入")
            }
            else {
              this.$message.success("登录成功: " + res.swnumber);
              this.$store.commit("login", res);
              this.$router.push({ name: "container" });
              window.console.log(res)
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("../assets/sysuBg.jpg");
  background-size: 100% 100%;
}

.form-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}

.login-form {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: linear-gradient(to bottom right, #3498db, #1abc9c);
  overflow: hidden;
}

.login-form .checkbox {
  color: black;
}

.form-content {
  padding: 30px 30px;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
}
</style>
