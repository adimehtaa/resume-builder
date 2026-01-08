<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '../services/authService'
import type { RegisterRequest, LoginRequest } from '../services/authService'

const router = useRouter()
const isLogin = ref(true)
const loading = ref(false)
const error = ref('')
const successMessage = ref('')

// Modal state
const showVerificationModal = ref(false)
const resendLoading = ref(false)
const resendMessage = ref('')
const userEmail = ref('')

// Login form
const loginForm = ref<LoginRequest>({
  email: '',
  password: ''
})

// Register form
const registerForm = ref<RegisterRequest>({
  email: '',
  name: '',
  password: '',
  profileImageUrl: ''
})

const toggleMode = () => {
  isLogin.value = !isLogin.value
  error.value = ''
  successMessage.value = ''
}

const closeVerificationModal = () => {
  showVerificationModal.value = false
  resendMessage.value = ''
}

const resendVerificationEmail = async () => {
  try {
    resendLoading.value = true
    resendMessage.value = ''

    // Call your resend verification endpoint
    // Adjust this according to your authService implementation
    await authService.resendVerification(userEmail.value)

    resendMessage.value = 'Verification email sent successfully! Please check your inbox.'
  } catch (err: any) {
    resendMessage.value = err.message || 'Failed to resend verification email. Please try again.'
  } finally {
    resendLoading.value = false
  }
}

const handleLogin = async () => {
  try {
    loading.value = true
    error.value = ''

    const response = await authService.login(loginForm.value)

    if (!response.emailVerify) {
      userEmail.value = loginForm.value.email
      showVerificationModal.value = true
      return
    }

    successMessage.value = 'Login successful! Redirecting...'
    setTimeout(() => {
      router.push('/dashboard')
    }, 1000)
  } catch (err: any) {

    const errorMessage = err.message || ''

    if (errorMessage.includes('Email is not verify') ||
      errorMessage.includes('Verify Your email') ||
      err.statusCode === 400) {

      userEmail.value = loginForm.value.email
      showVerificationModal.value = true
    } else {
      error.value = errorMessage || 'Login failed. Please try again.'
    }
  } finally {
    loading.value = false
  }
}

const handleRegister = async () => {
  try {
    loading.value = true
    error.value = ''


    if (registerForm.value.password.length < 8) {
      error.value = 'Password must be at least 8 characters long'
      return
    }

    if (registerForm.value.name.length < 2) {
      error.value = 'Name must be at least 2 characters long'
      return
    }

    const response = await authService.register(registerForm.value)

    successMessage.value = 'Registration successful! Please check your email to verify your account.'


    setTimeout(() => {
      isLogin.value = true
      loginForm.value.email = registerForm.value.email
    }, 3000)
  } catch (err: any) {
    error.value = err.message || 'Registration failed. Please try again.'
  } finally {
    loading.value = false
  }
}

const goHome = () => {
  router.push('/')
}
</script>

<template>
  <div
    class="min-h-screen bg-gradient-to-br from-amber-50 via-orange-50 to-amber-100 flex items-center justify-center px-4 py-8">

    <!-- Back to Home Button -->
    <button @click="goHome"
      class="absolute top-6 left-6 flex items-center gap-2 text-slate-700 hover:text-amber-600 font-medium transition">
      <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
      </svg>
      Back to Home
    </button>

    <div class="w-full max-w-md">

      <!-- Logo/Brand -->
      <div class="text-center mb-8">
        <h1 class="text-3xl font-extrabold text-slate-900">
          Resume<span class="text-amber-600">Craft</span>
        </h1>
        <p class="text-slate-600 mt-2">
          {{ isLogin ? 'Welcome back! Sign in to continue' : 'Create your account to get started' }}
        </p>
      </div>

      <!-- Auth Card -->
      <div class="bg-white rounded-2xl shadow-xl p-8">

        <!-- Toggle Tabs -->
        <div class="flex gap-2 mb-6 bg-gray-100 p-1 rounded-lg">
          <button @click="isLogin = true" :class="[
            'flex-1 py-2 rounded-md font-medium transition',
            isLogin ? 'bg-white text-amber-600 shadow-sm' : 'text-gray-600 hover:text-gray-900'
          ]">
            Login
          </button>
          <button @click="isLogin = false" :class="[
            'flex-1 py-2 rounded-md font-medium transition',
            !isLogin ? 'bg-white text-amber-600 shadow-sm' : 'text-gray-600 hover:text-gray-900'
          ]">
            Sign Up
          </button>
        </div>

        <!-- Success Message -->
        <div v-if="successMessage"
          class="mb-4 p-3 bg-green-50 border border-green-200 rounded-lg text-green-700 text-sm">
          {{ successMessage }}
        </div>

        <!-- Error Message -->
        <div v-if="error" class="mb-4 p-3 bg-red-50 border border-red-200 rounded-lg text-red-700 text-sm">
          {{ error }}
        </div>

        <!-- Login Form -->
        <form v-if="isLogin" @submit.prevent="handleLogin" class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input v-model="loginForm.email" type="email" required placeholder="you@example.com"
              class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 outline-none transition" />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Password</label>
            <input v-model="loginForm.password" type="password" required placeholder="••••••••"
              class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 outline-none transition" />
          </div>

          <button type="submit" :disabled="loading"
            class="w-full bg-amber-500 hover:bg-amber-600 text-white font-semibold py-3 rounded-lg transition disabled:opacity-50 disabled:cursor-not-allowed">
            {{ loading ? 'Signing in...' : 'Sign In' }}
          </button>
        </form>

        <!-- Register Form -->
        <form v-else @submit.prevent="handleRegister" class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Full Name</label>
            <input v-model="registerForm.name" type="text" required minlength="2" maxlength="20" placeholder="John Doe"
              class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 outline-none transition" />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
            <input v-model="registerForm.email" type="email" required placeholder="you@example.com"
              class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 outline-none transition" />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">Password</label>
            <input v-model="registerForm.password" type="password" required minlength="8" maxlength="20"
              placeholder="At least 8 characters"
              class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:ring-2 focus:ring-amber-500 focus:border-amber-500 outline-none transition" />
            <p class="text-xs text-gray-500 mt-1">Password must be 8-20 characters long</p>
          </div>

          <button type="submit" :disabled="loading"
            class="w-full bg-amber-500 hover:bg-amber-600 text-white font-semibold py-3 rounded-lg transition disabled:opacity-50 disabled:cursor-not-allowed">
            {{ loading ? 'Creating account...' : 'Create Account' }}
          </button>
        </form>

        <!-- Divider -->
        <div class="relative my-6">
          <div class="absolute inset-0 flex items-center">
            <div class="w-full border-t border-gray-300"></div>
          </div>
          <div class="relative flex justify-center text-sm">
            <span class="px-2 bg-white text-gray-500">Or</span>
          </div>
        </div>

        <!-- Social Login (Optional) -->
        <div class="text-center text-sm text-gray-600">
          {{ isLogin ? "Don't have an account?" : "Already have an account?" }}
          <button @click="toggleMode" class="text-amber-600 hover:text-amber-700 font-semibold ml-1">
            {{ isLogin ? 'Sign up' : 'Sign in' }}
          </button>
        </div>

      </div>
    </div>

    <!-- Email Verification Modal -->
    <div v-if="showVerificationModal"
      class="fixed inset-0 bg-opacity-40 backdrop-blur-sm flex items-center justify-center px-4 z-50"
      @click.self="closeVerificationModal">
      <div class="bg-white rounded-2xl shadow-2xl max-w-md w-full p-6 transform transition-all">

        <!-- Icon -->
        <div class="flex justify-center mb-4">
          <div class="w-16 h-16 bg-amber-100 rounded-full flex items-center justify-center">
            <svg class="w-8 h-8 text-amber-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
            </svg>
          </div>
        </div>

        <!-- Content -->
        <h3 class="text-2xl font-bold text-center text-gray-900 mb-2">
          Verify Your Email
        </h3>
        <p class="text-center text-gray-600 mb-6">
          Please verify your email address to access your account. We've sent a verification link to:
        </p>

        <div class="bg-amber-50 border border-amber-200 rounded-lg p-3 mb-6">
          <p class="text-center text-amber-900 font-semibold break-all">
            {{ userEmail }}
          </p>
        </div>

        <!-- Resend Message -->
        <div v-if="resendMessage" :class="[
          'mb-4 p-3 rounded-lg text-sm text-center',
          resendMessage.includes('success')
            ? 'bg-green-50 border border-green-200 text-green-700'
            : 'bg-red-50 border border-red-200 text-red-700'
        ]">
          {{ resendMessage }}
        </div>

        <!-- Actions -->
        <div class="space-y-3">
          <button @click="resendVerificationEmail" :disabled="resendLoading"
            class="w-full bg-amber-500 hover:bg-amber-600 text-white font-semibold py-3 rounded-lg transition disabled:opacity-50 disabled:cursor-not-allowed">
            {{ resendLoading ? 'Sending...' : 'Resend Verification Email' }}
          </button>

          <button @click="closeVerificationModal"
            class="w-full bg-gray-100 hover:bg-gray-200 text-gray-700 font-semibold py-3 rounded-lg transition">
            Close
          </button>
        </div>

        <p class="text-center text-xs text-gray-500 mt-4">
          Check your spam folder if you don't see the email
        </p>

      </div>
    </div>

  </div>
</template>
