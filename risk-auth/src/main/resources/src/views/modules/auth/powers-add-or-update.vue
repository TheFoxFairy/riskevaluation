<template>
  <el-dialog
    :title="!dataForm.powerId ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="权限名称" prop="powerName">
      <el-input v-model="dataForm.powerName" placeholder="权限名称"></el-input>
    </el-form-item>
    <el-form-item label="权限URL" prop="powerUrl">
      <el-input v-model="dataForm.powerUrl" placeholder="权限URL"></el-input>
    </el-form-item>
    <el-form-item label="权限图标" prop="powerIcon">
      <el-input v-model="dataForm.powerIcon" placeholder="权限图标"></el-input>
    </el-form-item>
    <el-form-item label="父权限ID" prop="powerParentId">
      <el-input v-model="dataForm.powerParentId" placeholder="父权限ID"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          powerId: 0,
          powerName: '',
          powerUrl: '',
          powerIcon: '',
          powerParentId: ''
        },
        dataRule: {
          powerName: [
            { required: true, message: '权限名称不能为空', trigger: 'blur' }
          ],
          powerUrl: [
            { required: true, message: '权限URL不能为空', trigger: 'blur' }
          ],
          powerIcon: [
            { required: true, message: '权限图标不能为空', trigger: 'blur' }
          ],
          powerParentId: [
            { required: true, message: '父权限ID不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.powerId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.powerId) {
            this.$http({
              url: this.$http.adornUrl(`/auth/powers/info/${this.dataForm.powerId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.powerName = data.powers.powerName
                this.dataForm.powerUrl = data.powers.powerUrl
                this.dataForm.powerIcon = data.powers.powerIcon
                this.dataForm.powerParentId = data.powers.powerParentId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/auth/powers/${!this.dataForm.powerId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'powerId': this.dataForm.powerId || undefined,
                'powerName': this.dataForm.powerName,
                'powerUrl': this.dataForm.powerUrl,
                'powerIcon': this.dataForm.powerIcon,
                'powerParentId': this.dataForm.powerParentId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
