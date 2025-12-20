<template>
  <footer class="bg-slate-900 text-gray-300 pt-14 pb-8 px-6">

    <div class="max-w-6xl mx-auto grid grid-cols-1 md:grid-cols-4 gap-10">

      <!-- Brand -->
      <div>
        <h2 class="text-2xl font-extrabold text-white">
          Resume<span class="text-indigo-400">Craft</span>
        </h2>
        <p class="mt-3 text-gray-400">
          Build beautiful, ATS-friendly resumes in minutes.
        </p>

        <!-- Social Icons -->
        <div class="flex gap-4 mt-5">
          <a class="hover:text-indigo-400 transition" href="#">Twitter</a>
          <a class="hover:text-indigo-400 transition" href="#">LinkedIn</a>
          <a class="hover:text-indigo-400 transition" href="#">GitHub</a>
        </div>
      </div>

      <!-- Links -->
      <div>
        <h3 class="text-lg font-semibold text-white mb-3">Product</h3>
        <ul class="space-y-2 text-gray-400">
          <li><a href="#" class="hover:text-indigo-400">Templates</a></li>
          <li><a href="#" class="hover:text-indigo-400">Features</a></li>
          <li><a href="#" class="hover:text-indigo-400">Pricing</a></li>
        </ul>
      </div>

      <!-- Support -->
      <div>
        <h3 class="text-lg font-semibold text-white mb-3">Support</h3>
        <ul class="space-y-2 text-gray-400">
          <li><a href="#" class="hover:text-indigo-400">Help Center</a></li>
          <li><a href="#" class="hover:text-indigo-400">Contact</a></li>
          <li><a href="#" class="hover:text-indigo-400">FAQs</a></li>
        </ul>
      </div>

      <!-- Newsletter -->
      <div>
        <h3 class="text-lg font-semibold text-white mb-3">
          Stay Updated
        </h3>
        <p class="text-gray-400 mb-3">
          Get resume tips and career resources.
        </p>

        <div class="flex bg-slate-800 rounded overflow-hidden">
          <input type="email" placeholder="Enter your email"
            class="w-full px-3 py-2 bg-transparent outline-none text-white" />
          <button class="bg-indigo-500 px-4 hover:bg-indigo-600 transition">
            Subscribe
          </button>
        </div>
      </div>

    </div>

    <!-- Bottom Bar -->
    <div class="border-t border-gray-700 mt-12 pt-6 text-center text-gray-500">
      © 2025 ResumeCraft. All rights reserved.
    </div>

  </footer>
</template>
