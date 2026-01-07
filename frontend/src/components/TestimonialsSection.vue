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
  <div>
    <section class="w-full bg-indigo-50 py-20">
      <div class="max-w-7xl mx-auto px-6 md:px-10">

        <!-- Heading -->
        <div class="text-center mb-12">
          <h2 class="text-4xl font-extrabold text-slate-900">
            Loved by Professionals
          </h2>
          <p class="text-gray-600 text-lg mt-4">
            See what our users have to say
          </p>
        </div>

        <!-- Cards Grid -->
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          <TestimonialCard v-for="(t, i) in testimonials" :key="i" v-bind="t" />
        </div>

      </div>
    </section>

    <!-- CTA Section -->
    <section class="w-full bg-indigo-600 py-20">
      <div class="max-w-7xl mx-auto px-6 md:px-10 text-center text-white">
        <h2 class="text-4xl font-extrabold mb-4">
          Ready to Build Your Future?
        </h2>
        <p class="text-lg opacity-90 mb-8">
          Join thousands of professionals who've landed their dream jobs
        </p>
        <button
          class="bg-white text-indigo-600 font-semibold px-8 py-3 rounded-lg hover:bg-gray-100 transition-all duration-200 shadow-lg hover:shadow-xl">
          Get Started Now
        </button>
      </div>
    </section>
  </div>
</template>
