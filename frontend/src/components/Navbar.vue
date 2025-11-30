<script lang="ts" setup>
import { defineProps } from 'vue';

const props = defineProps<{
    logo_icon?: string;
    logo_text?: string;
    button_text: string;
}>();
</script>

<template>
    <header class="w-full h-16 bg-amber-50 flex items-center justify-between px-24 shadow-sm">
        <div class="flex items-center gap-3">
            <img v-if="logo_icon" :src="logo_icon" alt="Logo" class="h-9 w-9 object-contain" />
            <span class="text-xl font-bold text-slate-800 select-none">
                {{ logo_text }}
            </span>
        </div>

        <button
            class="bg-amber-400 hover:bg-amber-500 text-slate-900 font-medium px-5 py-2.5 rounded-md cursor-pointer shadow-sm hover:shadow-md transition-colors duration-200">
            {{ button_text }}
        </button>
    </header>
</template>
