<template>
  <div class="info-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-id-badge"></i> 信息维护
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <el-form :model="entityForm" class="info-form" label-width="80px">
        <el-form-item label="账号">
          <el-input disabled v-model="entityForm.username"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input disabled v-model="entityForm.name"></el-input>
        </el-form-item>
        <!-- <el-form-item label="学院">
          <el-input disabled v-model="entityForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="专业">
          <el-input disabled v-model="entityForm.majorName"></el-input>
        </el-form-item> -->
        <el-form-item label="班级">
          <el-input disabled v-model="entityForm.className"></el-input>
        </el-form-item>
        <!-- <el-form-item label="Email">
          <el-input v-model="entityForm.email"></el-input>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker
            format="yyyy-MM-dd"
            placeholder="选择生日"
            type="date"
            v-model="entityForm.birthday"
          >
          </el-date-picker> -->
        <!-- </el-form-item> -->
        <el-form-item label="性别">
          <el-radio-group v-model="entityForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="entityForm.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="操作">
          <el-button @click="updating = true" type="primary">确认更新</el-button>
        </el-form-item>
      </el-form>
      <el-dialog :visible.sync="updating" title="更新" width="30%">
        <div>确定要修改信息吗</div>
        <span class="dialog-footer" slot="footer">
          <el-button @click=update type="primary">确 定</el-button>
          <el-button @click="updating = false">取 消</el-button>
        </span> 
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/info";

export default {
  name: "StudentInfo",
  data() {
    return {
      entityForm: {
        username: "",
        name: "",
        // departmentName: "",
        // majorName: "",
        className: "",
        // email: "",
        // birthday: "",
        sex: "",
        password: ""
      },
      updating:false
    };
  },
  methods: {
    get() {
      var id = this.$store.state.status.userId
      api.get(id).then(res => {
        this.entityForm = res;
      });
    },
    update() {
      api.update(this.entityForm).then(() => {
        this.$message.success("更新信息成功!");
        this.updating = false
      });
    }
  },
  created() {
    this.get();
  }
};
</script>

<style scoped>
.info-form {
  min-width: 400px;
  width: 35%;
  margin: auto;
}
</style>
