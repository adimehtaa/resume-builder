<script setup lang="ts">
const props = defineProps<{
  message: string
  name: string
  role: string
}>()
</script>

<template>
  <div class="bg-white rounded-2xl shadow-md p-6">
    <!-- Stars -->
    <div class="text-amber-400 text-xl mb-2">
      ⭐⭐⭐⭐⭐
    </div>

    <!-- Message -->
    <p class="text-gray-700 italic">
      "{{ message }}"
    </p>

    <!-- User -->
    <div class="mt-4">
      <p class="font-bold text-slate-800">
        {{ name }}
      </p>
      <p class="text-gray-500 text-sm">
        {{ role }}
      </p>
    </div>
  </div>
</template>
