<script setup lang="ts">
import PriceCard from './PriceCard.vue'

const priceData = [
  {
    name: "Free",
    price: "₹0",
    intro: "Perfect for getting started",
    features: [
      "1 Professional Template",
      "Basic Editing Tools",
      "PDF Download",
      "Email Support"
    ],
    button: "Get Started Free",
    highlight: false
  },
  {
    name: "Premium",
    price: "₹999",
    intro: "Unlock all features",
    features: [
      "All Premium Templates",
      "Advanced Editing Tools",
      "Multiple Export Formats",
      "Priority Support",
      "Custom Branding"
    ],
    button: "Upgrade to Premium",
    highlight: true
  }
]
</script>

<template>
  <section class="w-full bg-gray-50 py-20">
    <div class="max-w-7xl mx-auto flex flex-col items-center text-center px-6 md:px-10">

      <h2 class="text-4xl font-extrabold text-slate-900">
        Simple, Transparent Pricing
      </h2>

      <p class="text-gray-600 text-lg mt-4 mb-12">
        Choose the plan that works best for you
      </p>

      <div class="grid grid-cols-1 md:grid-cols-2 gap-8 w-full max-w-4xl">
        <PriceCard v-for="(plan, i) in priceData" :key="i" v-bind="plan" />
      </div>

    </div>
  </section>
</template>
