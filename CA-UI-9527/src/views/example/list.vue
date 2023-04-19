<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.roomName" placeholder="用户名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.type" placeholder="角色类型" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in roles" :key="item.id" :label="item.roleDesc" :value="item.id" />
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter" plain>
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate" plain>
        新增
      </el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload" plain>
        导出
      </el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-refresh" @click="handleRemoveFilters" plain>
        清除过滤条件
      </el-button>
      <!--      <el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">
        reviewer
      </el-checkbox>-->
    </div>
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column label="ID" prop="id" align="center" width="80"/>
      <el-table-column label="用户名" prop="username" align="center" width="180"/>
      <el-table-column label="手机号码" prop="phone" align="center" width="180"/>
      <el-table-column label="电子邮箱" prop="email" align="center" width="200"/>
      <el-table-column label="角色" prop="role.roleDesc" align="center" width="200"/>
      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.status == 1 ? '正常' : '异常' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)" plain>
            修改
          </el-button>
          &emsp;&emsp;
          <el-popconfirm
            title="确定删除位用户？"
            @onConfirm="handleDelete(row,$index)"
          >
            <el-button v-if="row.status!='deleted'" slot="reference" size="mini" type="danger" @click="handleDelete(row,$index)" plain>
              删除
            </el-button>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { createAdminUser, fetchList } from '@/api/conference_room'
import Pagination from '@/components/Pagination'
import { getRoles } from '@/api/role' // Secondary package based on el-pagination

export default {
  name: 'ArticleList',
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      adminUser: {
        id: '',
        username: 'test',
        password: 'test',
        role: '2',
        phone: '18569687990',
        email: 'manxingfu2022@163.com',
        status: '1',
        authorities: []
      },
      roles: []
    }
  },
  created() {
    // this.createUser()
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        console.log(response.data.data)
        this.list = response.data.data
        this.total = response.data.data.length
        this.listLoading = false
      })
      getRoles().then(response => {
        this.roles = response.data.data
      })
    },
    createUser() {
      createAdminUser(this.adminUser).then(response => {
        console.log('添加结果', response.data.msg)
      })
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
