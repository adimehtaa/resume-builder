<script setup lang="ts">
const scrollToTop = () => {
  window.scrollTo({ top: 0, behavior: 'smooth' })
}
</script>

<template>
  <footer class="w-full bg-slate-900 text-gray-300 pt-16 pb-8">
    <div class="max-w-7xl mx-auto px-6 md:px-10">

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-10 mb-12">

        <!-- Brand -->
        <div>
          <h2 class="text-2xl font-extrabold text-white mb-3 cursor-pointer" @click="scrollToTop">
            Resume<span class="text-indigo-400">Craft</span>
          </h2>
          <p class="text-gray-400 mb-5">
            Build beautiful, ATS-friendly resumes in minutes.
          </p>

          <!-- Social Icons -->
          <div class="flex gap-4">
            <a class="hover:text-indigo-400 transition" href="#" aria-label="Twitter">Twitter</a>
            <a class="hover:text-indigo-400 transition" href="#" aria-label="LinkedIn">LinkedIn</a>
            <a class="hover:text-indigo-400 transition" href="#" aria-label="GitHub">GitHub</a>
          </div>
        </div>

        <!-- Product Links -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-4">Product</h3>
          <ul class="space-y-2 text-gray-400">
            <li><a href="#" class="hover:text-indigo-400 transition">Templates</a></li>
            <li><a href="#" class="hover:text-indigo-400 transition">Features</a></li>
            <li><a href="#" class="hover:text-indigo-400 transition">Pricing</a></li>
          </ul>
        </div>

        <!-- Support Links -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-4">Support</h3>
          <ul class="space-y-2 text-gray-400">
            <li><a href="#" class="hover:text-indigo-400 transition">Help Center</a></li>
            <li><a href="#" class="hover:text-indigo-400 transition">Contact</a></li>
            <li><a href="#" class="hover:text-indigo-400 transition">FAQs</a></li>
          </ul>
        </div>

        <!-- Newsletter -->
        <div>
          <h3 class="text-lg font-semibold text-white mb-4">
            Stay Updated
          </h3>
          <p class="text-gray-400 mb-4 text-sm">
            Get resume tips and career resources.
          </p>

          <div class="flex bg-slate-800 rounded-lg overflow-hidden">
            <input type="email" placeholder="Enter your email"
              class="w-full px-4 py-2.5 bg-transparent outline-none text-white placeholder-gray-500 text-sm" />
            <button class="bg-indigo-500 px-5 hover:bg-indigo-600 transition font-medium text-sm whitespace-nowrap">
              Subscribe
            </button>
          </div>
        </div>

      </div>

      <!-- Bottom Bar -->
      <div class="border-t border-gray-700 pt-8 text-center text-gray-500 text-sm">
        © 2025 ResumeCraft. All rights reserved.
      </div>

    </div>
  </footer>
</template>
