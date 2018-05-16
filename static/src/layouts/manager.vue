<template>
  <el-tabs type="border-card">
    <el-tab-pane>
      <span slot="label">
        <i class="el-icon-date"></i> 登记中心</span>
      <section class="step__group">
        <el-steps :active="active" finish-status="success" :align-center="true" process-status="finish">
          <el-step v-for="name in actionStep.names" :key="name" :title="name"></el-step>
        </el-steps>

        <el-button style="margin-top: 50px;" :disabled="actionButton.disable" @click="next" :type="actionButton.type">
          {{actionButton.active}}
        </el-button>
      </section>

      <section class="list__group">
        <el-tabs tab-position="left" style="height: 400px;" v-if="listGroup.length">
          <el-tab-pane v-for="list in listGroup" :key="list.name" :label="list.name">
            <el-table :data="list.data" height="400" fixed border>
              <el-table-column v-for="title in Object.keys(list.data[0])" :key="title" :prop="title" :label="title.toUpperCase()"></el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </section>
    </el-tab-pane>
    <el-tab-pane label="比赛中心">比赛中心</el-tab-pane>
  </el-tabs>
</template>

<script>
import Manager from '../services/manager'
import format from '../services/format'

export default {
  name: 'manager',
  data() {
    return {
      active: 1,
      actionStep: {
        names: ['数据初始化', '球队登记', '裁判登记', '场地登记'],
        enNames: ['Teams', 'Judges', 'Pitches']
      },
      actionButton: {
        disable: false,
        type: 'primary',
        active: '球队登记'
      },
      listGroup: [],
      listGroupNames: ['球队登记表', '裁判登记表', '场地登记表'],
      sourceData: null
    }
  },
  methods: {
    next() {
      this.active += 1
      this.pushListGroup(this.actionStep.enNames[this.active - 2])
    },
    openSuccessToast() {
      this.$message({
        message: 'success',
        type: 'success',
        duration: 500
      })
    },
    async initialData() {
      let teams = await Manager.fetchTeams()
      let judges = await Manager.fetchJudges()
      let pitches = await Manager.fetchPitches()

      this.sourceData = {
        teams: teams.data,
        judges: judges.data,
        pitches: pitches.data
      }

      this.isAllRegistered()
    },
    pushListGroup(name) {
      this.listGroup.push({
        name: this.listGroupNames[this.active - 2],
        data: format[name.toLowerCase()](this.sourceData[name.toLowerCase()])
      })
    },
    isAllRegistered() {
      Object.keys(this.sourceData).forEach((key, index) => {
        let newArr = this.sourceData[key].filter(item => {
          return !item.register
        })
        if (!newArr.length) {
          this.active = index + 2
          this.pushListGroup(key)
        }
      })
    }
  },
  created() {
    this.initialData()
  },
  watch: {
    active() {
      let postfix = this.actionStep.enNames[this.active - 2]
      Manager[`register${postfix}`]().then(res => {
        this.openSuccessToast()
        if (this.active === this.actionStep.names.length) {
          this.actionButton = {
            disable: true,
            type: 'success',
            active: '已完成登记'
          }
        } else {
          this.actionButton.active = this.actionStep.names[this.active]
        }
      })
    }
  }
}
</script>

<style lang="stylus" scoped>
.el-tabs {
  margin: 50px;

  .step__group {
    margin: 30px;
  }

  .list__group {
    border-top: 1px solid #dcdfe6;
    margin: 30px;
  }
}
</style>
