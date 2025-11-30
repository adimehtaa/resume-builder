<script lang="ts" setup>
import { defineProps } from 'vue'

const props = defineProps<{}>();

</script>

<template>
    <div class="flex bg-amber-100 h-[calc(100vh-4rem)] items-center justify-between px-24">
        <div class="w-1/2">
            <h3>⭐️ Trusted By 100+ Professionals</h3>
            <h1 class="text-4xl font-bold">Build Your Dream Resume in Minutes</h1>
            <p>Create stunning, professional resumes with our AI-powered builder. Stand out from the crowd and land your
                dream job faster. </p>
            <button>Start Building Now</button>
            <button>View Template</button>
        </div>
        <div class="">
            <img src="../../public/resume-hero.webp" class="w-80 object-contain">
        </div>
    </div>
</template>
