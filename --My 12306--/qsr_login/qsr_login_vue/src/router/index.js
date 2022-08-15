import Vue from 'vue'
import VueRouter from 'vue-router'
import AdminView from '../views/AdminView.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Password from '../views/Password'
import UserView from '../views/UserView.vue'
import AdminLayout from "../Layout/AdminLayout";
import UserLayout from "../Layout/UserLayout";
import Shouye from "../views/Shouye";
import CarStyle from "../views/CarStyle";
import User from "../views/User";
import Person from "../views/Person";
import Order from "../views/Order";
import CarStaus from "../views/CarStaus";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path: '/password',
    name: 'password',
    component: Password
  },
  {
    path: '/person',
    name: 'person',
    component: Person
  },

  {
    path: '/adminlayout',
    name: 'adminlayout',
    component: AdminLayout,
    redirect: "/",
    children: [
      {
        path: '/admin',
        name: 'admin',
        component: AdminView
      },
      {
        path: '/shouye',
        name: 'shouye',
        component: Shouye
      },
      {
        path: '/car',
        name: 'car',
        component: CarStyle
      },
      {
        path: '/user',
        name: '/user',
        component: User
      },
      {
        path: '/order',
        name: '/order',
        component: Order
      },
      {
        path: '/staus',
        name: '/staus',
        component: CarStaus
      }
    ]
  },
  {
    path: '/userlayout',
    name: 'userlayout',
    component: UserLayout,
    redirect: "/",
    children: [
      {
        path: '/userview',
        name: 'userview',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: UserView
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
