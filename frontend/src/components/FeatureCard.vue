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
  padding: 24px;
  width: 100%;
  max-width: 100%;
  background: white;
  transition: all 0.2s;
}

.feature-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  border-color: #ddd;
}

.icon {
  width: 40px;
  height: 40px;
  margin-bottom: 16px;
}

h3 {
  margin: 0 0 8px;
  text-align: start;
  font-size: 18px;
  font-weight: 700;
  color: #1e293b;
}

p {
  color: #64748b;
  font-size: 14px;
  line-height: 1.6;
  text-align: start;
  margin: 0;
}
</style>
