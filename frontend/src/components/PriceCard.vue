<script setup lang="ts">
const props = defineProps<{
  name: string
  price: string
  intro: string
  features: string[]
  button: string
  highlight?: boolean
}>()
</script>

<template>
  <div class="rounded-2xl border shadow-sm p-8 w-full transition-all duration-200 hover:-translate-y-1 hover:shadow-lg"
    :class="highlight
      ? 'bg-gradient-to-br from-indigo-500 to-purple-500 text-white border-transparent shadow-xl'
      : 'bg-white border-gray-200'">

    <!-- Header -->
    <div class="flex justify-between items-center mb-4">
      <h3 class="text-2xl font-bold">
        {{ name }}
      </h3>

      <span v-if="highlight" class="bg-amber-300 text-black text-xs font-semibold px-3 py-1 rounded-full">
        Popular
      </span>
    </div>

    <div class="mb-2">
      <span class="text-4xl font-extrabold">{{ price }}</span>
      <span class="text-sm" :class="highlight ? 'text-white/80' : 'text-gray-500'">/month</span>
    </div>

    <p class="text-sm mb-6" :class="highlight ? 'text-white/80' : 'text-gray-500'">
      {{ intro }}
    </p>

    <!-- Features -->
    <ul class="space-y-3 mb-8">
      <li v-for="(f, i) in features" :key="i" class="flex items-center gap-3">
        <span class="flex-shrink-0 w-5 h-5 rounded-full flex items-center justify-center font-bold text-sm"
          :class="highlight ? 'bg-white/20 text-white' : 'bg-green-100 text-green-600'">✓</span>

        <span class="text-sm">
          {{ f }}
        </span>
      </li>
    </ul>

    <!-- Button -->
    <button class="w-full font-semibold py-3 rounded-xl transition-all duration-200" :class="highlight
      ? 'bg-white text-indigo-600 hover:bg-gray-100 shadow-md hover:shadow-lg'
      : 'bg-indigo-600 hover:bg-indigo-700 text-white shadow-md hover:shadow-lg'">
      {{ button }}
    </button>

  </div>
</template>
