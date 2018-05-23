<template>
  <el-tabs type="border-card" :value="currentTab">
    <el-tab-pane>
      <span slot="label" name="0">
        <i class="el-icon-date"></i> 登记中心
      </span>
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

    <el-tab-pane v-if="hasAllRegistered">
      <span slot="label" name="1">
        <i class="el-icon-date"></i> 比赛中心
      </span>
      <section class="match-area">
        <h2 class="unselectable">
          <span class="el-icon-arrow-left expanded" @click="preDay"></span>
          <span class="match-type color-blue">{{currentMatchType}}</span> - Day
          <span class="current-day color-blue">{{currentDay}}</span>
          <span class="el-icon-arrow-right expanded" @click="nextDay"></span>
        </h2>
        <el-table height="600" fixed :data="currentMatchMessage.data" v-if="currentMatchMessage.data">
          <el-table-column prop="team1" label="Team1"></el-table-column>
          <el-table-column prop="team2" label="Team2"></el-table-column>
          <el-table-column prop="points" label="Team1:Team2"></el-table-column>
          <el-table-column prop="duration" label="Time(minutes)"></el-table-column>
          <el-table-column prop="pitch" label="Pitch"></el-table-column>
        </el-table>
      </section>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import Manager from '../services/manager'
import format from '../services/format'

export default {
  name: 'manager',
  data() {
    return {
      active: 1, // 当前活跃的进度点
      actionStep: {
        names: ['数据初始化', '球队登记', '裁判登记', '场地登记'], // 进度点对应的名称
        enNames: ['Teams', 'Judges', 'Pitches'] // 英文名称，用于调整接口名一致
      },
      actionButton: {
        // 登记按钮当前状况
        disable: false, // 是否不可用
        type: 'primary',
        active: '球队登记' // 当前显示在按钮上的文字
      },
      listGroup: [], // 表名与表的内容
      listGroupNames: ['球队登记表', '裁判登记表', '场地登记表'], // 登记表左侧名称
      sourceData: null, // 登记表的所有信息，包括裁判、球队、场地
      currentTab: '0', // 当前显示的tab的name
      cycleMatches: [], // 单循环赛的赛程
      matchStart: false, // 比赛是否已经开始
      currentDay: 1,
      matchTypes: ['单循环赛', '淘汰赛'],
      currentMatchType: '单循环赛',
      currentMatchMessage: {},
      totalDay: 0
    }
  },
  methods: {
    /**
     * 开始比赛，跳到比赛中心tab
     */
    startMatch() {
      this.nextTab('1')
    },
    /**
     * 注册下一项
     */
    next() {
      this.active += 1
      this.pushListGroup(this.actionStep.enNames[this.active - 2])
    },
    /**
     * 切换到指定tab
     */
    nextTab(tabName) {
      this.currentTab = tabName
    },
    /**
     * 更新比赛时间，往前调整一天
     */
    preDay() {
      if (this.currentDay === 1) {
        return
      }
      this.currentDay--
    },
    nextDay() {
      if (this.currentDay >= this.totalDay) {
        return
      }
      this.currentDay++
    },
    /**
     * 打开成功的弹窗、500ms后消失
     */
    openSuccessToast() {
      this.$message({
        message: 'success',
        type: 'success',
        duration: 500
      })
    },
    /**
     * 初始化数据，从后台抓取各个队伍注册信息
     */
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
    /**
     * ListGroup为name注册表左侧名字以及data为表内容
     */
    pushListGroup(name) {
      this.listGroup.push({
        name: this.listGroupNames[this.active - 2],
        data: format.registerList[name.toLowerCase()](this.sourceData[name.toLowerCase()])
      })
    },
    /**
     * 获取某日比赛赛程
     */
    async fetchMatchesByDay(day) {
      if (this.cycleMatches[day - 1] !== undefined) {
        this.currentMatchMessage = this.cycleMatches[day - 1]
      } else {
        let data = await Manager.fetchMatchesByDay(day)
        let potData = data.data.map(item => {
          return {
            pitch: item.pitch,
            team1: item.team1,
            team2: item.team2,
            points: item.team1Points + ":" + item.team2Points,
            duration: item.duration
          }
        })
        data.data = potData;
        this.currentMatchMessage = data
        this.cycleMatches.push(data)
      }
      this.totalDay = this.currentMatchMessage.totalDay
      console.log(this.currentMatchMessage)
    },
    /**
     * 判断是否全部都注册完毕
     */
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
    /**
     * 默认获取第一天的比赛
     */
    this.fetchMatchesByDay(1)
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
    },
    currentDay(value) {
      this.fetchMatchesByDay(value)
    }
  },
  computed: {
    hasAllRegistered() {
      return this.actionButton.disable
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

  .match-area {
    .expanded {
      padding: 5px;
      cursor: pointer;

      &:hover {
        &::before {
          color: #409Eff;
        }
      }
    }
  }
}
</style>
