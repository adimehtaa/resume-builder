<script setup lang="ts">
import TestimonialCard from './TestimonialCard.vue'

const testimonials = [
  {
    message:
      "This resume builder helped me land my dream job! The templates are modern and the interface is incredibly user-friendly.",
    name: "Sarah Johnson",
    role: "Software Engineer at Google"
  },
  {
    message:
      "I've tried many resume builders, but this one stands out. The live preview feature is a game-changer.",
    name: "Michael Chen",
    role: "Marketing Manager at Microsoft"
  },
  {
    message:
      "Beautiful templates and seamless editing experience. Highly recommend to anyone looking for a professional resume.",
    name: "Emily Davis",
    role: "UX Designer at Apple"
  }
]
</script>

<template>
  <section class="bg-indigo-100 py-16 px-6">

    <!-- Heading -->
    <div class="text-center mb-10">
      <h1 class="text-3xl font-extrabold text-slate-800">
        Loved by Professionals
      </h1>
      <p class="text-gray-600 mt-2">
        See what our users have to say
      </p>
    </div>

    <!-- Cards Row -->
    <div class="grid grid-cols-1 md:grid-cols-3 gap-6 max-w-6xl mx-auto">
      <TestimonialCard v-for="(t, i) in testimonials" :key="i" v-bind="t" />
    </div>

  </section>

  <!-- CTA Footer aligned same as content -->
  <div class="bg-indigo-500">
    <div class="max-w-6xl mx-auto h-48 flex flex-col items-center justify-center text-white text-center px-6">
      <h1 class="text-3xl font-bold mb-1">
        Ready to Build Your Future?
      </h1>
      <p class="opacity-90">
        Join thousands of professionals who've landed their dream jobs
      </p>
    </div>
  </div>
</template>
