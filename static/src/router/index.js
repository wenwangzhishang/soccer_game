import Vue from 'vue';
import Router from 'vue-router';

const Index = () => import('layouts/index');
const Manager = () => import('layouts/manager');

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index,
    },
    {
      path: '/manager',
      name: 'manager',
      component: Manager,
    },
  ],
});
