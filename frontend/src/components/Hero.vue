<script lang="ts" setup>
import { defineProps } from 'vue'

const props = defineProps<{}>();

</script>

<template>
    <div
        class="flex bg-linear-to-r from-amber-50 via-orange-50 to-amber-100 h-[calc(100vh-4rem)] items-center justify-between px-24">
        <div class="w-1/2 space-y-4">
            <h3
                class="inline-flex items-center gap-2 bg-white px-4 py-2 rounded-2xl shadow-sm text-sm font-medium text-slate-700">
                <span>⭐️</span>
                <span>Trusted by 100+ Professionals</span>
            </h3>

            <div class="space-y-1">
                <p class="text-4xl font-extrabold text-slate-900 leading-tight">Build Your</p>
                <p class="text-4xl font-extrabold text-slate-900 leading-tight">Dream Resume</p>
                <p class="text-4xl font-extrabold text-amber-600 leading-tight">in Minutes</p>
            </div>

            <p class="text-slate-600 max-w-lg">
                Create stunning, professional resumes with our AI-powered builder. Stand out from the crowd
                and land your dream job faster.
            </p>

            <div class="flex items-center gap-4 pt-2">
                <button
                    class="bg-amber-400 hover:bg-amber-500 text-slate-900 font-semibold px-5 py-3 rounded-lg cursor-pointer shadow-sm hover:shadow-md transition-colors duration-200">
                    Start Building Now
                </button>
                <button
                    class="bg-white hover:bg-amber-100 text-slate-800 font-medium px-5 py-3 rounded-lg cursor-pointer border border-amber-300 hover:border-amber-400 hover:shadow-sm transition-colors duration-200">
                    View Templates
                </button>
            </div>

            <div class="flex gap-8 mt-8 text-slate-800">
                <div>
                    <h1 class="font-bold text-2xl">10K+</h1>
                    <small class="text-slate-500">Resumes Created</small>
                </div>
                <div>
                    <h1 class="font-bold text-2xl">95%</h1>
                    <small class="text-slate-500">Success Rate</small>
                </div>
                <div>
                    <h1 class="font-bold text-2xl">4.5/5</h1>
                    <small class="text-slate-500">User Rating</small>
                </div>
                <div>
                    <h1 class="font-bold text-2xl">24/7</h1>
                    <small class="text-slate-500">Support</small>
                </div>
            </div>
        </div>

        <div>
            <img src="../../public/resume-hero.webp" class="w-80 object-contain opacity-90 drop-shadow-lg">
        </div>
    </div>

</template>
