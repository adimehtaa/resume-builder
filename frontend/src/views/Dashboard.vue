<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'
import type { AuthResponse } from '../services/authService'

const router = useRouter()
const user = ref<AuthResponse | null>(null)
const loading = ref(true)
const showProfileMenu = ref(false)

onMounted(async () => {
  try {
    user.value = await authService.getProfile()
  } catch (error) {
    console.error('Failed to load profile:', error)
    router.push('/auth')
  } finally {
    loading.value = false
  }
})

const handleLogout = () => {
  authService.logout()
  router.push('/')
}

const goHome = () => {
  router.push('/')
}

const getInitials = (name: string) => {
  return name
    .split(' ')
    .map(n => n[0])
    .join('')
    .toUpperCase()
    .slice(0, 2)
}

const formatDate = (dateString: string) => {
  return new Date(dateString).toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric'
  })
}
</script>

<template>
  <div class="min-h-screen bg-gray-50">

    <!-- Header -->
    <header class="bg-white shadow-sm sticky top-0 z-50">
      <div class="max-w-7xl mx-auto px-6 md:px-10 h-16 flex items-center justify-between">

        <!-- Logo -->
        <div class="flex items-center gap-8">
          <h1 class="text-xl font-bold text-slate-900 cursor-pointer" @click="goHome">
            Resume<span class="text-amber-600">Craft</span>
          </h1>
          <nav class="hidden md:flex gap-6">
            <button class="text-amber-600 font-medium">Dashboard</button>
            <button class="text-gray-600 hover:text-gray-900">My Resumes</button>
            <button class="text-gray-600 hover:text-gray-900">Templates</button>
          </nav>
        </div>

        <!-- User Menu -->
        <div class="relative">
          <button @click="showProfileMenu = !showProfileMenu"
            class="flex items-center gap-3 hover:bg-gray-50 rounded-lg px-3 py-2 transition">
            <div v-if="user?.profileImageUrl" class="w-8 h-8 rounded-full overflow-hidden">
              <img :src="user.profileImageUrl" :alt="user.name" class="w-full h-full object-cover" />
            </div>
            <div v-else
              class="w-8 h-8 rounded-full bg-amber-500 flex items-center justify-center text-white font-semibold text-sm">
              {{ user ? getInitials(user.name) : 'U' }}
            </div>
            <span class="hidden md:block font-medium text-gray-700">{{ user?.name }}</span>
            <svg class="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
            </svg>
          </button>

          <!-- Dropdown Menu -->
          <div v-if="showProfileMenu"
            class="absolute right-0 mt-2 w-64 bg-white rounded-xl shadow-lg border border-gray-200 py-2">
            <div class="px-4 py-3 border-b border-gray-100">
              <p class="font-semibold text-gray-900">{{ user?.name }}</p>
              <p class="text-sm text-gray-500">{{ user?.email }}</p>
            </div>
            <button class="w-full text-left px-4 py-2 hover:bg-gray-50 text-gray-700">Profile Settings</button>
            <button class="w-full text-left px-4 py-2 hover:bg-gray-50 text-gray-700">Subscription</button>
            <hr class="my-2" />
            <button @click="handleLogout" class="w-full text-left px-4 py-2 hover:bg-gray-50 text-red-600">
              Sign Out
            </button>
          </div>
        </div>

      </div>
    </header>

    <!-- Loading State -->
    <div v-if="loading" class="max-w-7xl mx-auto px-6 md:px-10 py-8">
      <div class="animate-pulse space-y-4">
        <div class="h-8 bg-gray-200 rounded w-1/4"></div>
        <div class="h-4 bg-gray-200 rounded w-1/2"></div>
      </div>
    </div>

    <!-- Dashboard Content -->
    <main v-else class="max-w-7xl mx-auto px-6 md:px-10 py-8">

      <!-- Welcome Section -->
      <div class="mb-8">
        <h2 class="text-3xl font-bold text-slate-900 mb-2">
          Welcome back, {{ user?.name?.split(' ')[0] }}! 👋
        </h2>
        <p class="text-gray-600">Here's what's happening with your resumes today.</p>
      </div>

      <!-- Stats Cards -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-100">
          <div class="flex items-center justify-between mb-2">
            <p class="text-gray-600 text-sm font-medium">Total Resumes</p>
            <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
              </svg>
            </div>
          </div>
          <h3 class="text-3xl font-bold text-slate-900">0</h3>
        </div>

        <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-100">
          <div class="flex items-center justify-between mb-2">
            <p class="text-gray-600 text-sm font-medium">Downloads</p>
            <div class="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4" />
              </svg>
            </div>
          </div>
          <h3 class="text-3xl font-bold text-slate-900">0</h3>
        </div>

        <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-100">
          <div class="flex items-center justify-between mb-2">
            <p class="text-gray-600 text-sm font-medium">Subscription</p>
            <div class="w-10 h-10 bg-purple-100 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-purple-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
              </svg>
            </div>
          </div>
          <h3 class="text-lg font-bold text-slate-900 capitalize">{{ user?.subscriptionPlan || 'Free' }}</h3>
        </div>

        <div class="bg-white rounded-xl p-6 shadow-sm border border-gray-100">
          <div class="flex items-center justify-between mb-2">
            <p class="text-gray-600 text-sm font-medium">Member Since</p>
            <div class="w-10 h-10 bg-amber-100 rounded-lg flex items-center justify-center">
              <svg class="w-5 h-5 text-amber-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
          </div>
          <h3 class="text-sm font-bold text-slate-900">{{ user?.createdAt ? formatDate(user.createdAt) : 'Today' }}</h3>
        </div>
      </div>

      <!-- Quick Actions -->
      <div class="bg-white rounded-xl p-8 shadow-sm border border-gray-100 mb-8">
        <h3 class="text-xl font-bold text-slate-900 mb-4">Quick Actions</h3>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
          <button
            class="flex items-center gap-3 p-4 border-2 border-dashed border-gray-300 rounded-lg hover:border-amber-500 hover:bg-amber-50 transition group">
            <div
              class="w-12 h-12 bg-amber-100 group-hover:bg-amber-200 rounded-lg flex items-center justify-center transition">
              <svg class="w-6 h-6 text-amber-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
              </svg>
            </div>
            <div class="text-left">
              <p class="font-semibold text-gray-900">Create New Resume</p>
              <p class="text-sm text-gray-500">Start from scratch</p>
            </div>
          </button>

          <button
            class="flex items-center gap-3 p-4 border-2 border-gray-200 rounded-lg hover:border-amber-500 hover:bg-amber-50 transition group">
            <div
              class="w-12 h-12 bg-blue-100 group-hover:bg-blue-200 rounded-lg flex items-center justify-center transition">
              <svg class="w-6 h-6 text-blue-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z" />
              </svg>
            </div>
            <div class="text-left">
              <p class="font-semibold text-gray-900">Browse Templates</p>
              <p class="text-sm text-gray-500">Pick a design</p>
            </div>
          </button>

          <button
            class="flex items-center gap-3 p-4 border-2 border-gray-200 rounded-lg hover:border-amber-500 hover:bg-amber-50 transition group">
            <div
              class="w-12 h-12 bg-green-100 group-hover:bg-green-200 rounded-lg flex items-center justify-center transition">
              <svg class="w-6 h-6 text-green-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                  d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12" />
              </svg>
            </div>
            <div class="text-left">
              <p class="font-semibold text-gray-900">Import Resume</p>
              <p class="text-sm text-gray-500">Upload existing file</p>
            </div>
          </button>
        </div>
      </div>

      <!-- Recent Activity / Empty State -->
      <div class="bg-white rounded-xl p-8 shadow-sm border border-gray-100">
        <h3 class="text-xl font-bold text-slate-900 mb-4">Recent Resumes</h3>
        <div class="text-center py-12">
          <div class="w-20 h-20 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <svg class="w-10 h-10 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
          </div>
          <h4 class="text-lg font-semibold text-gray-900 mb-2">No resumes yet</h4>
          <p class="text-gray-600 mb-6">Create your first resume to get started!</p>
          <button class="bg-amber-500 hover:bg-amber-600 text-white font-semibold px-6 py-3 rounded-lg transition">
            Create Your First Resume
          </button>
        </div>
      </div>

    </main>

  </div>
</template>
