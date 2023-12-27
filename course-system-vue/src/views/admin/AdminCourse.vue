<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 课程管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :offset="15" :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="课程名"
              v-model="queryForm.name"
            />
          </el-col>
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="开课老师名"
              v-model="queryForm.teacherName"
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
            height="430px"
            stripe>
          <el-table-column label="课程Id" prop="id" />
          <el-table-column label="课程名" prop="name"/>
          <el-table-column label="开课教师" prop="teacherName" />
          <!-- <el-table-column label="所属学院" prop="departmentName" /> -->
          <!-- <el-table-column label="年级" prop="grade" /> -->
          <el-table-column label="开课地点" prop="place" />
          <!-- <el-table-column
            align="center"
            label="上课时间"
            prop="time"
            width="130px"
          />
          <el-table-column label="已选人数" prop="selectedCount" />
          <el-table-column label="最大容量" prop="maxSize" /> -->
          <el-table-column align="center" label="操作" >
            <template slot-scope="scope">
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

      <el-dialog :visible.sync="deleting" title="删除" width="30%">
        <div>确定要删除该课程吗</div>
        <span class="dialog-footer" slot="footer">
          <el-button @click="deleteItem" type="primary">确 定</el-button>
          <el-button @click="deleting = false">取 消</el-button>
        </span> 
      </el-dialog>

    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/course";


export default {
  name: "AdminCourse",
  data() {
    return {
      queryForm: {
        teacherName: "",
        name: ""
      },
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      // teachers: [],
      // courseDay: "",
      // courseTime: "",
      // courseLength: 0,
      deleting:false,
      deleteId:-1
    };
  },
  methods: {
    query() {
      api
        .getPageCount(this.queryForm.teacherName,this.queryForm.name)
        .then(res => {
          this.pageCount = res.count;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(pageIndex,this.queryForm.teacherName,this.queryForm.name)
        .then(res => {
          this.tableData = [];
          // alert(Object.keys(res).length)
          // 遍历res对象的属性
          for (var i = 0; i <= Object.keys(res).length; i++) {
            var obj = {
              id: res[i].id,
              name: res[i].name,
              teacherName: res[i].teacherName,
              place: res[i].location
            };
            this.tableData.push(obj);
          }
        });
    },
    startDelete(id){
      this.deleting = true;
      this.deleteId = id;
      // this.deleteItem(id);
    },
    deleteItem() {
      api.deleteItem(this.deleteId).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
        this.deleting=false;
      });
    },

  },
  created() {
    this.query();
  }
};
</script>

<style scoped></style>
