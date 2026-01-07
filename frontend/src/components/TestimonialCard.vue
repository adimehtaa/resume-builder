<script setup lang="ts">
const props = defineProps<{
  message: string
  name: string
  role: string
}>()
</script>

<template>
  <div class="bg-white rounded-2xl shadow-md p-6 hover:shadow-xl transition-all duration-200 h-full flex flex-col">
    <!-- Stars -->
    <div class="text-amber-400 text-xl mb-3">
      ⭐⭐⭐⭐⭐
    </div>

    <!-- Message -->
    <p class="text-gray-700 italic flex-grow">
      "{{ message }}"
    </p>

    <!-- User -->
    <div class="mt-6 pt-4 border-t border-gray-100">
      <p class="font-bold text-slate-800">
        {{ name }}
      </p>
      <p class="text-gray-600 text-sm mt-1">
        {{ role }}
      </p>
    </div>
  </div>
</template>
