<template>
  <div class="course-select-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 进入选课
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
              v-model="queryForm.courseName"
            />
          </el-col>
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="教师名"
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
            height="390px"
            stripe>
          <el-table-column label="课程Id" prop="courseId" />
          <el-table-column label="课程名" prop="courseName" />
          <el-table-column label="教师" prop="teacherName" />
          <el-table-column label="上课时间" prop="time" />
          <el-table-column label="上课地点" prop="place" />
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <div v-if="scope.row.haveSelected"> 
                <el-button @click="deleteSelect(scope.row.courseName)" 
                  size="mini" type="danger" 
                  >退课
                </el-button>
              </div>
              <div v-else>
                <el-button @click="select(scope.row.courseId)" 
                  size="mini" type="success" 
                  >选课
                </el-button>
              </div>

            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/courseSelect";

export default {
  name: "StudentCourseSelect",
  data() {
    return {
      queryForm: {
        courseName: "",
        teacherName: ""
      },
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1
    };
  },
  methods: {
    query() {
      api
        .getPageCount(this.queryForm.courseName, this.queryForm.teacherName)
        .then(res => {
          this.pageCount = res.count;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(pageIndex,this.queryForm.courseName,this.queryForm.teacherName,this.$store.state.status.userName)
        .then(res => {
          // this.tableData = res;
          this.tableData = [];
          // alert(Object.keys(res).length)
          // 遍历res对象的属性
          for (var i = 0; i <= Object.keys(res).length; i++) {
            var obj = {
              courseId: res[i].courseId,
              courseName: res[i].courseName,
              teacherName: res[i].courseTeacherName,
              time: '周'+res[i].courseDay+'第'+res[i].courseSession+'节',
              place: res[i].coursePlace,
              haveSelected : res[i].haveSelected
            };
            this.tableData.push(obj);
          }
        });
    },
    select(id) {
      var userName = this.$store.state.status.userName
      api.select(id,userName).then(() => {
        this.$message.success("选课成功!");
        this.getPage(this.pageIndex);
      });
    },
    deleteSelect(courseName) {
      var userName = this.$store.state.status.userName
      api.deleteSelect(courseName,userName).then(() =>{
        this.$message.success("退课成功!");
        this.getPage(this.pageIndex);
      });
    }
  },
  created() {
    this.query();
  }
};
</script>

<style scoped></style>
