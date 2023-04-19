<template>
  <div class="app-container">
    <div class="filter-container" style="text-align: center">
      <svg t="1675847060519" class="icon" style="width: 2em;height: 2em" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8565" width="200" height="200"><path d="M878 127.3V256H64V127.3C64 92.4 92.7 64 127.9 64H814c35.3 0 64 28.4 64 63.3z" fill="#5280C1" p-id="8566"></path><path d="M878 256v284.5c-7.5-39.1-31.3-72.5-64-92.7V256h64z" fill="#727272" p-id="8567"></path><path d="M256 351.1h64v64h-64z" fill="#E6C37C" p-id="8568"></path><path d="M384 351.9h320v64H384z" fill="#B2B2B2" p-id="8569"></path><path d="M256 479.6h64v64h-64z" fill="#E6C37C" p-id="8570"></path><path d="M384 480.4h191v64H384z" fill="#B2B2B2" p-id="8571"></path><path d="M256 607.6h64v64h-64z" fill="#E6C37C" p-id="8572"></path><path d="M384 608.4h128v64H384z" fill="#B2B2B2" p-id="8573"></path><path d="M896.8 768l-59.1-100.2c-15.4 14.8-34.1 26.1-55 32.7l39.8 67.5H663.9l37.9-66.4c-21.1-6.1-40.1-16.9-55.8-31.3L590.2 768H521v32H128V256H64v544.7c0 34.9 28.7 63.3 63.9 63.3H521v96h439V768h-63.2z m-0.8 128H585v-64h311v64z" fill="#727272" p-id="8574"></path><path d="M880.5 567c0 39.6-16.4 75.4-42.8 100.8-15.4 14.8-34.1 26.1-55 32.7-13.3 4.2-27.5 6.5-42.2 6.5-13.5 0-26.5-1.9-38.8-5.4-21.1-6.1-40.1-16.9-55.8-31.3-27.9-25.6-45.4-62.3-45.4-103.2 0-77.4 62.7-140 140-140 27 0 52.1 7.6 73.5 20.8 32.7 20.2 56.5 53.5 64 92.7 1.6 8.5 2.5 17.3 2.5 26.4z" fill="#5280C1" p-id="8575"></path></svg>
      <span style="font-family: custom;font-size: 30px;text-align: center">审 核</span>
<!--
      <svg t="1675847060519" class="icon" style="width: 2em;height: 2em;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="8565" width="200" height="200"><path d="M878 127.3V256H64V127.3C64 92.4 92.7 64 127.9 64H814c35.3 0 64 28.4 64 63.3z" fill="#5280C1" p-id="8566"></path><path d="M878 256v284.5c-7.5-39.1-31.3-72.5-64-92.7V256h64z" fill="#727272" p-id="8567"></path><path d="M256 351.1h64v64h-64z" fill="#E6C37C" p-id="8568"></path><path d="M384 351.9h320v64H384z" fill="#B2B2B2" p-id="8569"></path><path d="M256 479.6h64v64h-64z" fill="#E6C37C" p-id="8570"></path><path d="M384 480.4h191v64H384z" fill="#B2B2B2" p-id="8571"></path><path d="M256 607.6h64v64h-64z" fill="#E6C37C" p-id="8572"></path><path d="M384 608.4h128v64H384z" fill="#B2B2B2" p-id="8573"></path><path d="M896.8 768l-59.1-100.2c-15.4 14.8-34.1 26.1-55 32.7l39.8 67.5H663.9l37.9-66.4c-21.1-6.1-40.1-16.9-55.8-31.3L590.2 768H521v32H128V256H64v544.7c0 34.9 28.7 63.3 63.9 63.3H521v96h439V768h-63.2z m-0.8 128H585v-64h311v64z" fill="#727272" p-id="8574"></path><path d="M880.5 567c0 39.6-16.4 75.4-42.8 100.8-15.4 14.8-34.1 26.1-55 32.7-13.3 4.2-27.5 6.5-42.2 6.5-13.5 0-26.5-1.9-38.8-5.4-21.1-6.1-40.1-16.9-55.8-31.3-27.9-25.6-45.4-62.3-45.4-103.2 0-77.4 62.7-140 140-140 27 0 52.1 7.6 73.5 20.8 32.7 20.2 56.5 53.5 64 92.7 1.6 8.5 2.5 17.3 2.5 26.4z" fill="#5280C1" p-id="8575"></path></svg>
-->
    </div>
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="uncheckAppointments"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="预约编号" prop="id" sortable="custom" align="center" width="120" /><!--:class-name="getSortClass('id')"-->
      <el-table-column label="预约用户" prop="appointmentUser.username" align="center" width="180" />
      <el-table-column label="会议主题" prop="conferenceSubject" align="center" width="180" />
      <el-table-column label="参会人数" prop="participationNums" align="center" width="180" />
      <el-table-column label="会议室名称" prop="appointmentRoom.roomName" align="center" width="180" />
      <el-table-column label="会议室类型" prop="appointmentRoom.roomType.roomTypeName" align="center" width="180" />
      <el-table-column label="起始时间" width="230px" prop="appointmentStartTime" align="center" :formatter="formatDate" />
      <el-table-column label="结束时间" width="180px" align="center" prop="appointmentEndTime" :formatter="formatDate" />
      <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleCheck(row, $index)">
            审核
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.size" @pagination="listenPagination" />
    <!--  更新/新增区域  -->
    <el-dialog title="审核预约" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="会议主题" prop="id">
          <el-input v-model="temp.conferenceSubject" disabled />
        </el-form-item>
        <el-form-item label="预约人" prop="id">
          <el-input v-model="temp.appointmentUser.username" disabled />
        </el-form-item>
        <el-form-item label="人数匹配度" prop="roomName">
          <div v-if="temp.participationNums >= temp.appointmentRoom.roomType.roomTypeCapacity * 0.5">
            <span style="color:green">参与人数与会议室容纳人数适配</span>
          </div>
          <div v-else>
            <span style="color:red">参与人数与会议室容纳人数不适配，过少!</span>
          </div>
        </el-form-item>
        <el-form-item label="会议开始时间" prop="id">
          <el-date-picker
            v-model="temp.appointmentStartTime"
            type="datetime"
            format="yyyy年MM月dd日 HH:mm:ss"
            disabled
          />
          <!--          <el-input v-model="temp.appointmentCreateTime" disabled />-->
        </el-form-item>
        <el-form-item label="会议结束时间" prop="id">
          <el-date-picker
            v-model="temp.appointmentEndTime"
            type="datetime"
            format="yyyy年MM月dd日 HH:mm:ss"
            disabled
          />
<!--          <el-input v-model="temp.appointmentEndTime" disabled />-->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="toSubmit('审核不通过')">
          拒绝
        </el-button>
        <el-button type="primary" @click="toSubmit('审核通过')">
          通过
        </el-button>
      </div>
    </el-dialog>
    <el-dialog :title="submitTitle" :visible.sync="submitForm">
      <div>
        <span>审核意见: </span>
        <el-input v-model="remark" />
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="check()">
          提交
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
import { error, success } from '@/utils/message'
import { insertCheckInfo, queryAllWaitCheckInfo } from '@/api/appointment'
import { parseTime } from '@/utils' // secondary package based on el-pagination

export default {
  name: 'AppointCheck',
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
        appointmentUser: {
          username: ''
        },
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
        status: '',
        appointmentRoom: {
          roomType: {
            roomTypeCapacity: ''
          }
        }
      },
      dialogFormVisible: false,
      dialogStatus: '',
      dialogPvVisible: false,
      submitForm: false,
      pvData: [],
      rules: {
        roomName: [{ required: true, message: '请输入会议室名称', trigger: 'blur' }]
      },
      downloadLoading: false,
      checkIndex: undefined,
      uncheckAppointments: [],
      remark: '',
      submitTitle: '',
      checkInfo: {
        id: undefined,
        checkAppointId: undefined,
        checkUserId: undefined,
        checkResult: undefined,
        remark: undefined,
        createTime: undefined
      }
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
    init() {
      this.listLoading = true
      queryAllWaitCheckInfo().then(response => {
        this.uncheckAppointments = response.data.data
        this.listLoading = false
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
    toSubmit(title) {
      this.dialogFormVisible = false
      this.submitTitle = title
      this.submitForm = true
    },
    check() {
      this.checkInfo.checkUserId = this.temp.appointmentUser.id
      this.checkInfo.checkAppointId = this.temp.id
      this.checkInfo.checkResult = this.submitTitle === '审核通过' ? 1 : 0
      this.checkInfo.remark = this.remark
      this.checkInfo.createTime = new Date()

      insertCheckInfo(this.checkInfo).then(response => {
        console.log(response.data.data)
        this.uncheckAppointments.splice(this.checkIndex, 1)
        success('审核完成')
      }).catch(e => {
        error('审核失败')
      })
      this.submitForm = false
      this.remark = ''
      // console.log('check', this.checkInfo)
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
    formatDate(row, column) {
      // 获取单元格数据
      const data = row[column.property]
      if (data == null) {
        return '未知'
      }
      const dt = new Date(data)
      return parseTime(dt, undefined)
      // return dt.getFullYear() + '年' + (dt.getMonth() + 1) + '月' + dt.getDate() + '日 ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
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
    handleCheck(row, index) {
      // console.log('row: ', row)
      this.checkIndex = index
      this.temp = Object.assign({}, row) // copy obj
      // console.log('temp: ', this.temp) // 当前行的数据
      this.dialogFormVisible = true
    },
    handleDelete(row, index) {
      this.list.splice(index, 1)
      deleteRoomById(row.id).then(response => {
        success('删除成功')
      }).catch(msg => {
        error('删除失败')
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
