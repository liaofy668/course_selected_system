<template>
  <div class="student-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-user"></i> 学生管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button @click="create" icon="el-icon-plus">创建</el-button>
          </el-col>
          <el-col :offset="13" :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="学生姓名"
              v-model="queryForm.name"
            />
          </el-col>
          <!-- <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="专业名"
              v-model="queryForm.majorName"
            />
          </el-col> -->
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="班级名"
              v-model="queryForm.className"
            />
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary"
              >搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <el-row justify="center" type="flex">
        <el-pagination
          :current-page.sync="pageIndex"
          :page-size="pageSize"
          :total="pageSize * pageCount"
          @current-change="getPage"
          background
          layout="prev, pager, next"
        >
        </el-pagination>
      </el-row>

      <div class="table">
        <el-table
            :data="tableData"
            border
            height="390px"
            stripe>
          <el-table-column label="学生id" prop="id" width="80px" />
          <el-table-column label="学生账号" prop="username" />
          <el-table-column label="学生姓名" prop="name" />
          <el-table-column label="学生密码" prop="password" />
          <el-table-column label="学生班级" prop="className" />
          <!-- <el-table-column label="专业" min-width="150px" prop="majorName" /> -->
          <!-- <el-table-column label="性别" prop="sex" width="80px" /> -->
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button @click="edit(scope.row.id)" size="mini" type="success"
                >编辑
              </el-button>
              <el-button
                @click="startDelete(scope.row.id)"
                size="mini"
                type="danger"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <el-dialog :visible.sync="editing" title="编辑" width="30%">
        <el-form :model="entityForm" label-width="70px" ref="form">
          <el-form-item label="学生账号">
            <el-input v-model="entityForm.username"></el-input>
          </el-form-item>
          <el-form-item label="学生姓名">
            <el-input v-model="entityForm.name"></el-input>
          </el-form-item>
          <el-form-item label="所属班级">
            <el-input v-model="entityForm.className"></el-input>
            <!-- <el-select placeholder="请选择班级" v-model="entityForm.className">
              <el-option
                :key="index"
                :label="item.name"
                :value="item.id"
                v-for="(item, index) in classes"
              />
            </el-select> -->
          </el-form-item>
          <el-form-item label="登录密码">
            <el-input type="password" v-model="entityForm.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input type="password"  v-model="entityForm.confirmPassword" show-password></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="entityForm.sex">
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <span class="dialog-footer" slot="footer">
          <el-button @click="save" type="primary">确 定</el-button>
          <el-button @click="editing = false">取 消</el-button>
        </span>
      </el-dialog>

      <el-dialog :visible.sync="deleting" title="删除" width="30%">
        <div>确定要删除该学生吗</div>
        <span class="dialog-footer" slot="footer">
          <el-button @click="deleteItem" type="primary">确 定</el-button>
          <el-button @click="deleting = false">取 消</el-button>
        </span> 
      </el-dialog>

    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/student";
// import * as classApi from "../../api/admin/class";
import {exportInnerMessage}  from "../../common/ajax";

export default {
  name: "AdminStudent",
  data() {
    return {
      queryForm: {
        className: "",
        name: ""
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      deleting:false,
      deleteId: -1
    };
  },
  methods: {
    query() {
      api
        .getPageCount(
          this.queryForm.className,
          this.queryForm.name
        )
        .then(res => {
          this.pageCount = res.count;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(
          pageIndex,this.queryForm.className,this.queryForm.name)
        .then(res => {
          this.tableData = [];
          // for (let i = 0; i < res.length; i++) {
          //   res[i].sex = res[i].sex === 1 ? "男" : "女";
          // }
          for (var i = 0; i <= Object.keys(res).length; i++) {
            var obj = {
              id: res[i].id,
              username: res[i].username,
              name: res[i].name,
              password: res[i].password,
              className: res[i].className
            };
            this.tableData.push(obj);
          }
          this.tableData = res;
        });
    },
    create() {
      this.entityForm = {
        id: -1,
        username: "",
        name: "",
        className: "",
        password: "",
        confirmPassword:"",
        sex: 0
      };
      this.editing = true;
    },
    edit(id) {
      api.get(id).then(res => {
        this.entityForm = res;
        this.entityForm.confirmPassword = this.entityForm.password
        this.editing = true;
      });
    },
    save() {
      if (this.entityForm.password !=this.entityForm.confirmPassword){
        exportInnerMessage("error","密码不一致");
      }
      else {
        if(this.entityForm.password === ""){
          this.entityForm.password="A12345678"
        }
        if (this.entityForm.id === -1) {
          api.create(this.entityForm).then(() => {
            this.finishSave();
          });
        } else {
          api.update(this.entityForm).then(() => {
            this.finishSave();
          });
        }
      }
    },
    finishSave() {
      this.$message.success("成功");
      this.getPage(this.pageIndex);
      this.editing = false;
    },
    startDelete(id){
      this.deleting = true;
      this.deleteId = id;
      // this.deleteItem(id);
    },
    deleteItem() {
      this.deleting = true;
      // alert(this.deleteId)
      api.deleteItem(this.deleteId).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
        this.deleting=false;
      });
    },
    // getClasses() {
    //   classApi.listName().then(res => {
    //     this.classes = res;
    //   });
    // }
  },
  created() {
    this.query();
    // this.getClasses();
  }
};
</script>

<style scoped></style>
