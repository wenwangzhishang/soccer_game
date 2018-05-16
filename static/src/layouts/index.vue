<template>
  <el-container class="index-container">
    <el-main class="main">
      <el-container class="container-intro__group">
        <h1>Scoring System For Football Matches </h1>
        <p>Author: wingsico</p>
      </el-container>

      <el-container class="container-start__group">
        <el-container class="button__group">
          <router-link name="" to="manager">
            <el-button class="start" type="primary" :round="true" size="medium">开始使用</el-button>
          </router-link>

          <a href="https://github.com/wingsico/soccer_game" target="_blank">
            <el-button class="github" :round="true" size="medium">github</el-button>
          </a>
        </el-container>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
export default {};
</script>

<style lang="stylus" scoped>
.index-container {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 250px;

  .main {
    text-align: center;
    padding: 0;
    overflow: inherit;

    .container-intro__group {
      width: 100%;
      flex-direction: column;
      position: relative;

      h1 {
        font-size: 40px;
        color: #409EFF;
      }

      p {
        margin-top: 56px;
        color: #8994c6;
        font-size: 32px;
      }
    }

    .container-start__group {
      margin-top: 180px;
      position: relative;

      .button__group {
        padding: 20px;
        align-items: center;
        justify-content: center;

        .el-button {
          margin: 0 50px;
          font-size: 18px;
          padding: 18px 45px;
          border-radius: 32.5px;
        }
      }

      &:after {
        content: '';
        display: block;
        position: absolute;
        z-index: -1;
        top: -80px;
        width: 100%;
        height: 420px;
        background: #fff;
        box-shadow: 0 -4px 30px 0 #dfe1e6;
        transform: skewY(-4deg);
      }
    }
  }
}
</style>

