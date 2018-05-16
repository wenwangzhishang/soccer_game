<template>
  <div id="app">
    <main-header></main-header>
    <router-view/>
  </div>
</template>

<script>

import MainHeader from 'components/mainHeader';

export default {
  name: 'App',
  components: {
    MainHeader,
  },
};
</script>

<style>
html, body {
  height: 100%;
}

#app {
  font-family: Monaco, Monospaced Number,Chinese Quote,
  -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,
  PingFang SC,Hiragino Sans GB,Microsoft YaHei,Helvetica Neue,Helvetica,Arial,sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow: hidden;
  height: 100%;
}
</style>
