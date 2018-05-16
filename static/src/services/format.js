export default {
  judgeTypeName: ['辅助裁判', '主裁判'],
  groupTypeName: ['男子甲组', '男子乙组', '女子组', '成人组'],

  teams(data) {
    return data.map(item => {
      return {
        id: item.id,
        teamName: item.name,
        Group: this.groupTypeName[item.type - 1],
      }
    })
  },

  judges(data) {
    return data.map(item => {
      return {
        id: item.id,
        name: item.name,
        type: this.judgeTypeName[item.type]
      }
    })
  },

  pitches(data) {
    return data.map(item => {
      return {
        id: item.id,
        name: item.name,
        judge: item.judge.name,
        assistJudge1: item.assistJudge1.name,
        assistJudge2: item.assistJudge2.name
      }
    })
  }
}