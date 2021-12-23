import Vue from 'vue'
import VueRouter from 'vue-router'
import BookManage from '../views/BookManage'
import AddBook from '../views/AddBook'
import Index from '../views/Index'
import UpdateBook from '../views/UpdateBook'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"图书管理",
    component: Index,
    show:true,
    redirect:"/BookManage",
    children:[
      {
        path:"/BookManage",
        name:"查询图书",
        component: BookManage
      },
      {
        path:"/Add",
        name:"添加图书",
        component: AddBook
      }
    ]
  },
  {
    path:'/Update',
    name: "更新",
    component: UpdateBook,
    show: false,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
