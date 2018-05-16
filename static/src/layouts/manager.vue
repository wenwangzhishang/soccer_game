<template>
  <el-tabs type="border-card">
    <el-tab-pane>
      <span slot="label"><i class="el-icon-date"></i> 登记中心</span>
      <section class="step__group">
        <el-steps
          :active="active"
          finish-status="success"
          :align-center="true"
          process-status="finish">
          <el-step title="数据初始化"></el-step>
          <el-step title="球队登记"></el-step>
          <el-step title="裁判登记"></el-step>
          <el-step title="场地登记"></el-step>
        </el-steps>
      </section>
    </el-tab-pane>
    <el-tab-pane label="比赛中心">比赛中心</el-tab-pane>
  </el-tabs>
</template>

<script>
export default {
  name: 'manager',
  data() {
    return {
      active: 1,
    };
  },
};
</script>

<style lang="stylus" scoped>
  .el-tabs {
    margin: 50px;
    .step__group {
      margin: 30px;
    }
  }
</style>
