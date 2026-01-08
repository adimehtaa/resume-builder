<script setup lang="ts">
import Navbar from '../components/Navbar.vue';
import Hero from '../components/Hero.vue';
import Features from '../components/Features.vue';
import Price from '../components/Price.vue';
import TestimonialsSection from "../components/TestimonialsSection.vue"
import Footer from '../components/Footer.vue';
</script>

<template>
  <div>
    <Navbar logo_text="ResumeCraft" button_text="Get Started Free" />
    <Hero id="home" />
    <Features id="features" />
    <Price id="pricing" />
    <TestimonialsSection id="testimonials" />
    <Footer />
  </div>
</template>

<style scoped></style>
