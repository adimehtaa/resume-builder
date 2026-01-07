import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../App.vue')
    }
  ],
  scrollBehavior(to, from, savedPosition) {
    // If there's a hash (e.g., #features), scroll to that element
    if (to.hash) {
      return {
        el: to.hash,
        behavior: 'smooth',
        top: 64 // Offset for fixed navbar (h-16 = 64px)
      }
    }

    // If there's a saved position (browser back), use it
    if (savedPosition) {
      return savedPosition
    }

    // Otherwise scroll to top
    return { top: 0 }
  }
})

export default router
