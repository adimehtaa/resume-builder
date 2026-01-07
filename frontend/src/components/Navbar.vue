<script setup lang="ts">
const props = defineProps<{
  logo_icon?: string
  logo_text?: string
  button_text: string
}>()

const scrollToSection = (id: string) => {
  const element = document.getElementById(id)
  if (element) {
    const navbarHeight = 64
    const elementPosition = element.getBoundingClientRect().top + window.scrollY
    const targetPosition = elementPosition - navbarHeight

    window.scrollTo({
      top: targetPosition,
      behavior: 'smooth'
    })
  }
}

const scrollToSectionAlt = (id: string) => {
  const element = document.getElementById(id)
  if (element) {
    element.scrollIntoView({
      behavior: 'smooth',
      block: 'start'
    })
    setTimeout(() => {
      window.scrollBy(0, -64)
    }, 500)
  }
}
</script>

<template>
  <header class="w-full bg-amber-50 sticky top-0 z-50 shadow-sm">
    <nav class="max-w-7xl mx-auto h-16 flex items-center justify-between px-6 md:px-10">

      <!-- Logo -->
      <div class="flex items-center gap-3">
        <img v-if="logo_icon" :src="logo_icon" alt="Logo" class="h-9 w-9 object-contain" />
        <span class="text-xl font-bold text-slate-800 select-none cursor-pointer" @click="scrollToSection('home')">
          {{ logo_text || 'MyBrand' }}
        </span>
      </div>

      <!-- Navigation Links (optional - uncomment if needed) -->
      <div class="hidden md:flex items-center gap-8">
        <button @click="scrollToSection('features')" class="text-slate-700 hover:text-amber-600 font-medium transition">
          Features
        </button>
        <button @click="scrollToSection('pricing')" class="text-slate-700 hover:text-amber-600 font-medium transition">
          Pricing
        </button>
        <button @click="scrollToSection('testimonials')"
          class="text-slate-700 hover:text-amber-600 font-medium transition">
          Testimonials
        </button>
      </div>

      <!-- Button -->
      <button class="bg-amber-400 hover:bg-amber-500 text-slate-900 font-medium
               px-5 py-2.5 rounded-md shadow-sm hover:shadow-md
               transition duration-200">
        {{ button_text }}
      </button>

    </nav>
  </header>
</template>
