<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.roomName" placeholder="会议室名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <!--      <el-select v-model="listQuery.importance" placeholder="Imp" clearable style="width: 90px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>-->
      <el-select v-model="listQuery.type" placeholder="会议室类型" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in roomTypes" :key="item.id" :label="item.roomTypeName" :value="item.id" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        新增
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-refresh" @click="handleRemoveFilters">
        清除过滤条件
      </el-button>
      <!--      <el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">
        reviewer
      </el-checkbox>-->
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="ID" prop="id" sortable="custom" align="center" width="80"><!--:class-name="getSortClass('id')"-->
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会议室名称" prop="roomName" align="center" width="180">
        <template slot-scope="{row}">
          <span>{{ row.roomName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会议室类型" prop="roomTypeName" align="center" width="180">
        <template slot-scope="{row}">
          <span>{{ row.roomType.roomTypeName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="会议室面积" sortable="custom" prop="roomArea" align="center" width="120">
        <template slot-scope="{row}">
          <span>{{ row.roomType.roomArea }} 平方</span>
        </template>
      </el-table-column>
      <el-table-column label="容纳人数" sortable="custom" prop="roomCapacity" align="center" width="110">
        <template slot-scope="{row}">
          <span>{{ row.roomType.roomTypeCapacity }} 人</span>
        </template>
      </el-table-column>
      <el-table-column label="清洁状态" prop="cleanStatus" align="center" width="80">
        <template slot-scope="{row}">
          <span :style="classByValue(row.cleanStatus)">{{ row.cleanStatus == 1 ? '整洁' : '待清洁' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用状态" prop="status" align="center" width="80">
        <template slot-scope="{row}">
          <span :style="classByValue(row.status)">{{ row.status == 1 ? '启用' : '禁用' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-popconfirm
            title="确定删除该条会议室信息？"
            @onConfirm="handleDelete(row,$index)"
          >
            <el-button v-if="row.status!='deleted'" slot="reference" size="mini" type="danger"><!-- @click="handleDelete(row,$index)"-->
              删除
            </el-button>
          </el-popconfirm>
          <!--          <el-popconfirm
            title="这是一段内容确定删除吗？"
          >
            <el-button slot="reference">删除</el-button>
          </el-popconfirm>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.size" @pagination="listenPagination" />
    <!--  更新/新增区域  -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item v-if="dialogStatus === 'update'" label="ID" prop="id" required>
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>
        <el-form-item label="会议室名称" prop="roomName" required>
          <el-input v-model="temp.roomName" maxlength="10" show-word-limit />
        </el-form-item>
        <el-form-item label="会议室类型" prop="roomType" required>
          <el-select v-model="temp.roomType.id" class="filter-item" placeholder="请选择会议室类型">
            <el-option v-for="item in roomTypes" :key="item.id" :label="item.roomTypeName" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="卫生状态" required>
          <el-switch
            v-model="temp.cleanStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
            width="50"
          /> {{ temp.cleanStatus ? '整洁' : '待清洁' }}
        </el-form-item>
        <el-form-item label="状态" required>
          <el-switch
            v-model="temp.status"
            active-color="#13ce66"
            inactive-color="#ff4949"
            width="50"
          /> {{ temp.status ? '启用' : '禁用' }}
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确认
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { createRoom, deleteRoomById, fetchPv, fetchRoom, fetchRoomType, updateRoom } from '@/api/conference_room'
import waves from '@/directive/waves' // waves directive
import Pagination from '@/components/Pagination'
import { error, success } from '@/utils/message' // secondary package based on el-pagination

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
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
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 20,
        roomName: undefined,
        type: '',
        idSort: true,
        areaSort: undefined,
        capacitySort: undefined
      },
      importanceOptions: [1, 2, 3],
      roomTypes: [],
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        id: '',
        roomName: '',
        roomType: {
          id: '',
          roomTypeName: '',
          roomArea: 0,
          roomTypeCapacity: '',
          roomTypeDesc: '',
          roomCover: '',
          roomTypeCreateTime: ''
        },
        cleanStatus: '',
        status: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '更新',
        create: '新增'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        roomName: [{ required: true, message: '请输入会议室名称', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  computed: {
    classByValue(value) {
      return (value) => {
        if (value !== 1) {
          return { 'color': 'red' }
        } else {
          return { 'color': '#13ce66' }
        }
      }
    }
  },
  created() {
    this.init()
  },

  methods: {
    init(flag) {
      this.listLoading = true
      // success('欢迎访问')
      fetchRoom(this.listQuery).then(response => {
      // fetchRoom().then(response => {
        this.list = response.data.data
        this.total = response.data.data.length
        // console.log('list ', this.list)
      })
      this.listLoading = false

      fetchRoomType().then(response => {
        this.roomTypes = response.data.data
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.init()
    },
    listenPagination(data) {
      const { page, limit } = data
      /* console.log('page: ', page)
      console.log('limit: ', limit) */
      this.listQuery.page = page
      this.listQuery.size = limit
      // 更新数据
      this.listLoading = true
      fetchRoom(this.listQuery).then(response => {
        this.list = response.data.data
        setTimeout(() => {
          this.listLoading = false
        }, 1000)
      })
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作Success',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      /* console.log('prop: ', prop)
      console.log('order: ', order) */
      if (prop === 'id') {
        this.sortByID(order)
      } else if (prop === 'roomArea') {
        this.sortByArea(order)
      } else {
        this.sortByCapacity(order)
      }
    },
    sortByID(order) {
      this.listQuery.areaSort = ''
      this.listQuery.capacitySort = ''
      if (order === 'ascending') {
        this.listQuery.idSort = true
      } else if (order === 'descending') {
        this.listQuery.idSort = false
      } else {
        this.listQuery.idSort = true
      }
      this.handleFilter()
    },
    sortByArea(order) {
      this.listQuery.idSort = ''
      this.listQuery.capacitySort = ''
      if (order === 'ascending') {
        this.listQuery.areaSort = true
      } else if (order === 'descending') {
        this.listQuery.areaSort = false
      } else {
        this.listQuery.areaSort = ''
        this.listQuery.idSort = true
      }
      this.handleFilter()
    },
    sortByCapacity(order) {
      this.listQuery.areaSort = ''
      this.listQuery.idSort = ''
      if (order === 'ascending') {
        this.listQuery.capacitySort = true
      } else if (order === 'descending') {
        this.listQuery.capacitySort = false
      } else {
        this.listQuery.capacitySort = ''
        this.listQuery.idSort = true
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: '',
        roomName: '',
        roomType: {
          id: '',
          roomTypeName: '',
          roomArea: 0,
          roomTypeCapacity: '',
          roomTypeDesc: '',
          roomCover: '',
          roomTypeCreateTime: ''
        },
        cleanStatus: '',
        status: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.temp.cleanStatus = true
      this.temp.status = true
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.status = this.temp.status ? 1 : 0
          this.temp.cleanStatus = this.temp.cleanStatus ? 1 : 0
          // console.log('新增对象: ', this.temp)
          createRoom(this.temp).then(() => {
            success('创建成功')
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.handleRemoveFilters() // 查询所有结果
            /* this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            }) */
          })
        }
      })
    },
    handleUpdate(row) {
      // console.log('row: ', row)
      this.temp = Object.assign({}, row) // copy obj
      // console.log('temp: ', this.temp) 当前行的数据
      this.temp.cleanStatus = this.temp.cleanStatus === 1
      this.temp.status = this.temp.status === 1
      // this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.status = tempData.status ? 1 : 0
          tempData.cleanStatus = tempData.cleanStatus ? 1 : 0
          console.log(tempData)
          // tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateRoom(tempData).then((response) => {
            console.log('更新结果：', response)
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            success('更新成功')
            /* this.$notify({
              title: 'Success',
              message: '更新成功',
              type: 'success',
              duration: 2000
            }) */
          }).catch(errorMsg => {
            error(errorMsg)
          })
        }
      })
    },
    handleDelete(row, index) {
      /* this.$notify({
        title: 'Success',
        message: 'Delete Successfully',
        type: 'success',
        duration: 2000
      })
      console.log('id: ', row.id) */
      this.list.splice(index, 1)
      deleteRoomById(row.id).then(response => {
        success('删除成功')
      }).catch(msg => {
        error('删除失败')
      })
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() { // 导出Excel
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['ID', '会议室名称', '会议室类型', '会议室面积(平方米)', '容纳人数', '卫生状态', '使用状态']
        const filterVal = ['id', 'roomName', 'roomTypeName', 'roomArea', 'roomTypeCapacity', 'cleanStatus', 'status']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: '会议室信息'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'roomTypeName' || j === 'roomArea' || j === 'roomTypeCapacity') {
          return v['roomType'][j]
        } else if (j === 'cleanStatus') {
          return v[j] === 1 ? '整洁' : '待清洁'
        } else if (j === 'status') {
          return v[j] === 1 ? '启用' : '禁用'
        } else {
          return v[j]
        }
      }))
    },
    getSortClass: function(key) {
      console.log('key: ', key)
      let sort
      if (key === 'idSort') {
        sort = this.listQuery.idSort
      } else if (key === 'areaSort') {
        sort = this.listQuery.areaSort
      } else if (key === 'capacitySort') {
        sort = this.listQuery.capacitySort
      }
      return sort === `+${key}` ? 'ascending' : 'descending'
    },
    handleRemoveFilters() {
      this.listQuery = {
        page: 1,
        size: 20,
        roomName: undefined,
        type: '',
        idSort: true,
        areaSort: undefined,
        capacitySort: undefined
      }
      this.handleFilter()
    }
  }
}
</script>
