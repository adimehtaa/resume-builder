<script setup lang="ts">
const props = defineProps<{
  logo_icon?: string
  logo_text?: string
  button_text: string
}>()
</script>

<template>
  <header class="w-full bg-amber-50 sticky top-0 z-50">
    <nav class="max-w-7xl mx-auto h-16 flex items-center justify-between px-6 md:px-10">

      <!-- Logo -->
      <div class="flex items-center gap-3">
        <img
          v-if="logo_icon"
          :src="logo_icon"
          alt="Logo"
          class="h-9 w-9 object-contain"
        />
        <span class="text-xl font-bold text-slate-800 select-none">
          {{ logo_text || 'MyBrand' }}
        </span>
      </div>

      <!-- Button -->
      <button
        class="bg-amber-400 hover:bg-amber-500 text-slate-900 font-medium
               px-5 py-2.5 rounded-md shadow-sm hover:shadow-md
               transition duration-200"
      >
        {{ button_text }}
      </button>

    </nav>
  </header>
</template>
