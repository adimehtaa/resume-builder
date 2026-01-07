<script setup lang="ts">
import FeatureCard from './FeatureCard.vue'

const featureData = [
  {
    icon: "/eye.png",
    title: "Live Preview",
    desc: "See your changes instantly with our advanced live preview technology."
  },
  {
    icon: "/layout.png",
    title: "Professional Templates",
    desc: "Choose from expertly designed templates built to impress recruiters."
  },
  {
    icon: "/email-notification.png",
    title: "ATS Friendly",
    desc: "Optimized formatting ensures your resume passes Applicant Tracking Systems."
  },
  {
    icon: "/customization.png",
    title: "Easy Customization",
    desc: "Edit layouts, colors, and sections with simple intuitive controls."
  },
  {
    icon: "/export.png",
    title: "One-Click Export",
    desc: "Download your resume instantly in PDF without losing formatting."
  },
  {
    icon: "/cyber-security.png",
    title: "Secure & Private",
    desc: "Your data is safely stored and never shared without your permission."
  }
]
</script>

<template>
  <section class="w-full bg-white py-20">
    <div class="max-w-7xl mx-auto flex flex-col items-center text-center px-6 md:px-10">

      <h2 class="text-4xl font-extrabold leading-tight text-slate-900">
        Everything You Need to <span class="text-blue-600">Succeed</span>
      </h2>

      <p class="mt-4 text-gray-600 text-lg max-w-2xl">
        Our powerful features help you create professional resumes that get noticed
        by employers and ATS systems.
      </p>

      <div class="mt-12 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 w-full">
        <FeatureCard v-for="(value, i) in featureData" :key="i" :icon="value.icon" :title="value.title"
          :desc="value.desc" />
      </div>

    </div>
  </section>
</template>
