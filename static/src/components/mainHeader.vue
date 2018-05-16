<template>
  <el-header class="header">
    <el-aside class="aside-logo__group">
      <img src="~assets/soccer.jpeg" alt="足球">
      <h2>Soccer System</h2>
    </el-aside>
  </el-header>
</template>

<script>
export default {
  name: 'mainHeader',
};
</script>

<style lang="stylus">
.header {
  height: 90px;
  background-color: #fff;
  box-shadow: 0 -4px 30px 0 #dfe1e6;
  display: flex;
  align-items: center;

  .aside-logo__group {
    float: left;
    display: flex;
    justify-content center;
    align-items center;
    img {
      width: 40px;
      height: 40px;
    }

    h2 {
      display: inline-block;
      margin: 0;
      margin-left: 15px;
      color: gray;
    }
  }
}
</style>

