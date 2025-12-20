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
  <section class="flex flex-col items-center text-center px-6 py-16">

    <h2 class="text-3xl font-extrabold">
      Simple, Transparent Pricing
    </h2>

    <p class="text-gray-600 mt-2 mb-10">
      Choose the plan that works best for you
    </p>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl w-full">
      <PriceCard
        v-for="(plan, i) in priceData"
        :key="i"
        v-bind="plan"
      />
    </div>

  </section>
</template>
