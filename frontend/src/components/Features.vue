<script setup lang="ts">
import FeatureCard from './FeatureCard.vue'

const featureData = [
  {
    icon: "public/eye.png",
    title: "Live Preview",
    desc: "See your changes instantly with our advanced live preview technology."
  },
  {
    icon: "public/layout.png",
    title: "Professional Templates",
    desc: "Choose from expertly designed templates built to impress recruiters."
  },
  {
    icon: "public/email-notification.png",
    title: "ATS Friendly",
    desc: "Optimized formatting ensures your resume passes Applicant Tracking Systems."
  },
  {
    icon: "public/customization.png",
    title: "Easy Customization",
    desc: "Edit layouts, colors, and sections with simple intuitive controls."
  },
  {
    icon: "public/export.png",
    title: "One-Click Export",
    desc: "Download your resume instantly in PDF without losing formatting."
  },
  {
    icon: "public/cyber-security.png",
    title: "Secure & Private",
    desc: "Your data is safely stored and never shared without your permission."
  }
]


</script>

<template>
  <div class="mt-18 mb-18 flex flex-col items-center text-center px-4">

  <h1 class="text-3xl font-extrabold leading-tight">
    Everything You Need to <span class="text-blue-600">Succeed</span>
  </h1>

  <p class="mt-2 text-gray-600 max-w-2xl">
    Our powerful features help you create professional resumes that get noticed
    by employers and ATS systems.
  </p>

  <div class="mt-14 flex flex-wrap gap-6 justify-center">
    <FeatureCard
      v-for="(value, i) in featureData"
      :key="i"
      :icon="value.icon"
      :title="value.title"
      :desc="value.desc"
    />
  </div>

</div>

</template>
