<template>
  <div class="teacher-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-user-plus"></i> 教师管理
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
              placeholder="教师名"
              v-model="queryForm.name"
            />
          </el-col>
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="学院名"
              v-model="queryForm.departmentName"
            />
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary"
              >搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <!-- 分页组件，
        @current-change="getPage"：这是一个监听当前页码变化的事件，当用户切换页码时，会触发getPage方法。
        布局包括前一页按钮（prev）、页码列表（pager）和后一页按钮（next）。
      使用.sync修饰符可以实现双向绑定，即当pageIndex发生变化时，分页组件的当前页码也会更新。 -->
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
          <el-table-column label="教师Id" prop="id" />
          <el-table-column label="教师账号" prop="username" />
          <el-table-column label="教师姓名" prop="name" />
          <el-table-column label="教师密码" prop="password" />
          <el-table-column label="所属学院" prop="departmentName" />
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
          <el-form-item label="教师账号">
            <el-input  v-model="entityForm.username"></el-input>
          </el-form-item>
          <el-form-item label="教师姓名">
            <el-input v-model="entityForm.name"></el-input>
          </el-form-item>
          <el-form-item label="所属学院">
            <el-input v-model="entityForm.department"></el-input>
            <!-- <el-select placeholder="请选择学院" v-model="entityForm.department">
              <el-option
                :key="index"
                :label="item.name"
                :value="item.id"
                v-for="(item, index) in departments"
              />
            </el-select> -->
          </el-form-item>
          <el-form-item label="登录密码">
            <el-input type="password"  v-model="entityForm.password" show-password></el-input>
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
        <div>确定要删除该教师吗</div>
        <span class="dialog-footer" slot="footer">
          <el-button @click="deleteItem" type="primary">确 定</el-button>
          <el-button @click="deleting = false">取 消</el-button>
        </span> 
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/teacher";
import {exportInnerMessage}  from "../../common/ajax";
// import * as departmentApi from "../../api/admin/department";

export default {
  name: "AdminTeacher",
  data() {
    return {
      queryForm: {
        departmentName: "",
        name: ""
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      deleting:false,
      deleteId:-1
      // departments: []
    };
  },
  methods: {
    query() {
      api
        .getPageCount(this.queryForm.departmentName, this.queryForm.name)
        .then(res => {
          this.pageCount = res.count;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(pageIndex, this.queryForm.departmentName, this.queryForm.name)
        .then(res => {
          this.tableData = [];
          // alert(Object.keys(res).length)
          // 遍历res对象的属性
          for (var i = 0; i <= Object.keys(res).length; i++) {
            var obj = {
              // id: res[i][0],
              // username: res[i][1],
              // name: res[i][2],
              // password: res[i][3],
              // departmentName: res[i][4]
              id: res[i].id,
              username: res[i].username,
              name: res[i].name,
              password: res[i].password,
              departmentName: res[i].departmentName
            };
            this.tableData.push(obj);
          }
          
          // alert(res)
          // this.tableData = res;
          
        });
    },
    create() {
      this.entityForm = {
        id: -1,
        username: "",
        name: "",
        department: "",
        sex:0,
        password: "",
        confirmPassword:''
      };
      this.editing = true;
    },
    edit(id) {
      api.get(id).then(res => {
        this.entityForm = res;
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
      api.deleteItem(this.deleteId).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
        this.deleting=false;
      });
    },
    // getDepartments() {
    //   departmentApi.listName().then(res => {
    //     this.departments = res;
    //   });
    // }
  },
  created() {
    this.query();
    // this.getDepartments();
  }
};
</script>

<style scoped></style>
