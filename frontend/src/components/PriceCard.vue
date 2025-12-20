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
  <div
    class="rounded-2xl border shadow-sm p-6 max-w-[360px] w-full mx-auto transition hover:-translate-y-1"
    :class="highlight
      ? 'bg-gradient-to-br from-indigo-500 to-purple-500 text-white border-transparent'
      : 'bg-white border-gray-200'"
  >

    <!-- Header -->
    <div class="flex justify-between items-center">
      <h2 class="text-xl font-bold">
        {{ name }}
      </h2>

      <span
        v-if="highlight"
        class="bg-amber-300 text-black text-xs font-semibold px-3 py-1 rounded-full"
      >
        Popular
      </span>
    </div>

    <h3 class="text-3xl font-extrabold mt-2">
      {{ price }}
    </h3>

    <p
      class="mt-1 text-sm"
      :class="highlight ? 'text-white/80' : 'text-gray-500'"
    >
      {{ intro }}
    </p>

    <!-- Features -->
    <ul class="mt-4 space-y-2">
      <li
        v-for="(f, i) in features"
        :key="i"
        class="flex items-center gap-2"
      >
        <span
          class="text-green-500 font-bold text-lg leading-none"
          :class="highlight && 'text-white'"
        >✓</span>

        <span class="text-sm">
          {{ f }}
        </span>
      </li>
    </ul>

    <!-- Button -->
    <button
      class="w-full mt-6 font-medium py-2.5 rounded-xl transition text-sm"
      :class="highlight
        ? 'bg-white text-black hover:bg-gray-100'
        : 'bg-gray-100 hover:bg-gray-200 text-black'"
    >
      {{ button }}
    </button>

  </div>
</template>
