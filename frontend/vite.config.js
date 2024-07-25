import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  // Configured server to 3000 from default server port:5173
  server:{
    port:3000
  }
}
)
