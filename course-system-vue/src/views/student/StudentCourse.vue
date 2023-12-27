<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-edit"></i> 已选课程
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="table">
        <el-table
            :data="tableData"
            border
            height="450px"
            stripe>
          <el-table-column label="选课Id" prop="studentCourseId" />
          <el-table-column label="课程名" prop="courseName" />
          <el-table-column label="教师" prop="teacherName" />
          <el-table-column label="上课地点" prop="place" />
          <el-table-column label="上课地点" prop="time" />
          <!-- <el-table-column label="日常成绩" prop="dailyScore" />
          <el-table-column label="考试成绩" prop="examScore" /> -->
          <el-table-column label="成绩" prop="score" />
          <!-- <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button
                @click="deleteItem(scope.row.studentCourseId)"
                size="mini"
                type="danger"
                >退选
              </el-button>
            </template>
          </el-table-column> -->
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/course";

export default {
  name: "StudentCourse",
  data() {
    return {
      tableData: []
    };
  },
  methods: {
    getList() {
      var studentName = this.$store.state.status.userName
      // alert(name)
      api.list(studentName).then(res => {
        // this.tableData = res;
        this.tableData = [];
        for (var i = 0; i <= Object.keys(res).length; i++) {
          var obj = {
            studentCourseId : res[i].scId,
            courseName      : res[i].name,
            teacherName     : res[i].teacherName,
            time            : '周'+res[i].day+'第'+res[i].session+'节',
            place           : res[i].location,
            score           : res[i].scScore
          };
          this.tableData.push(obj);
        }
      });
    },
    deleteItem(studentCourseId) {
      api.deleteItem(studentCourseId).then(() => {
        this.$message.success("退选成功!");
        this.getList();
      });
    }
  },
  created() {
    this.getList();
  }
};
</script>

<style scoped></style>
