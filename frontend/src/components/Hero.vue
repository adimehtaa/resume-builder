<script lang="ts" setup>
</script>

<template>
  <section class="w-full bg-gradient-to-r from-amber-50 via-orange-50 to-amber-100 min-h-[calc(100vh-4rem)]">
    <div class="max-w-7xl mx-auto flex flex-col lg:flex-row items-center justify-between gap-8 py-16 px-6 md:px-10">

      <!-- Left Content -->
      <div class="w-full lg:w-1/2 space-y-6">
        <h3
          class="inline-flex items-center gap-2 bg-white px-4 py-2 rounded-2xl shadow-sm text-sm font-medium text-slate-700">
          <span>⭐️</span>
          <span>Trusted by 100+ Professionals</span>
        </h3>

        <div class="space-y-2">
          <h1 class="text-4xl md:text-5xl font-extrabold text-slate-900 leading-tight">
            Build Your<br />Dream Resume<br />
            <span class="text-amber-600">in Minutes</span>
          </h1>
        </div>

        <p class="text-slate-600 text-lg max-w-lg">
          Create stunning, professional resumes with our AI-powered builder. Stand out from the crowd
          and land your dream job faster.
        </p>

        <div class="flex flex-wrap items-center gap-4 pt-2">
          <button
            class="bg-amber-400 hover:bg-amber-500 text-slate-900 font-semibold px-6 py-3 rounded-lg cursor-pointer shadow-sm hover:shadow-md transition-all duration-200">
            Start Building Now
          </button>
          <button
            class="bg-white hover:bg-amber-100 text-slate-800 font-medium px-6 py-3 rounded-lg cursor-pointer border border-amber-300 hover:border-amber-400 hover:shadow-sm transition-all duration-200">
            View Templates
          </button>
        </div>

        <!-- Stats -->
        <div class="grid grid-cols-2 md:grid-cols-4 gap-6 pt-4">
          <div>
            <h2 class="font-bold text-2xl text-slate-800">10K+</h2>
            <p class="text-slate-600 text-sm">Resumes Created</p>
          </div>
          <div>
            <h2 class="font-bold text-2xl text-slate-800">95%</h2>
            <p class="text-slate-600 text-sm">Success Rate</p>
          </div>
          <div>
            <h2 class="font-bold text-2xl text-slate-800">4.5/5</h2>
            <p class="text-slate-600 text-sm">User Rating</p>
          </div>
          <div>
            <h2 class="font-bold text-2xl text-slate-800">24/7</h2>
            <p class="text-slate-600 text-sm">Support</p>
          </div>
        </div>
      </div>

      <!-- Right Image -->
      <div class="w-full lg:w-1/2 flex justify-center lg:justify-end">
        <img src="/resume-hero.webp" alt="Resume Preview"
          class="w-full max-w-md object-contain opacity-90 drop-shadow-2xl" />
      </div>

    </div>
  </section>
</template>
