<script lang="ts" setup>
import { defineProps } from 'vue';

const props = defineProps<{
    logo_icon?: string,
    logo_text?: string,
    button_text: string
}>()

</script>
<template>
    <div class="w-screen h-16 bg-amber-50 flex items-center justify-between px-24">
        <h1 class="text-lg font-semibold flex items-center gap-2">
            <img v-if="logo_icon != null" :src="logo_icon" class="h-8 w-8 object-contain" />
            {{ logo_text }}
        </h1>

        <button
            class="px-4 py-2 bg-amber-200 rounded cursor-pointer hover:pointer-hover:bg-amber-300 hover:shadow-md transition">
            {{ button_text }}
        </button>
    </div>
</template>
