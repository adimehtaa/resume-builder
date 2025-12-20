<script setup lang="ts">
const props = defineProps<{
  icon: string
  title: string
  desc: string
}>();
</script>

<template>
  <div class="feature-card">
    <img :src="props.icon" :alt="props.title" class="icon" />

    <h3>{{ props.title }}</h3>
    <p>{{ props.desc }}</p>
  </div>
</template>

<style scoped>
.feature-card {
  border-radius: 12px;
  border: 1px solid #eee;
  padding: 18px 20px;
  max-width: 300px;
}

.icon {
  width: 32px;
  height: 32px;
  margin-bottom: 12px;
}

h3 {
  margin: 0 0 6px;
  text-align: start;
}

p {
  color: #555;
  font-size: 14px;
  text-align: start;
}
</style>
