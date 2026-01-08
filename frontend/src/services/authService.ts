const API_BASE_URL = `${import.meta.env.VITE_API_BASE_URL}/api/auth`;

export interface RegisterRequest {
  email: string
  name: string
  password: string
  profileImageUrl?: string
}

export interface LoginRequest {
  email: string
  password: string
}

export interface AuthResponse {
  id: string
  name: string
  email: string
  profileImageUrl?: string
  subscriptionPlan: string
  emailVerify: boolean
  token: string
  createdAt: string
  updatedAt: string
}

export interface ApiError {
  message: string
  errors?: Record<string, string>
}

class AuthService {
  async register(data: RegisterRequest): Promise<AuthResponse> {
    const response = await fetch(`${API_BASE_URL}/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })

    if (!response.ok) {
      const error = await response.json()
      throw new Error(error.message || 'Registration failed')
    }

    const result = await response.json()
    if (result.token) {
      this.setToken(result.token)
      this.setUser(result)
    }
    return result
  }

  async login(data: LoginRequest): Promise<AuthResponse> {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })

    if (!response.ok) {
      const error = await response.json()
      throw new Error(error.message || 'Login failed')
    }

    const result = await response.json()
    if (result.token) {
      this.setToken(result.token)
      this.setUser(result)
    }
    return result
  }

  async verifyEmail(token: string): Promise<void> {
    const response = await fetch(`${API_BASE_URL}/verify-email?token=${token}`, {
      method: 'GET',
    })

    if (!response.ok) {
      const error = await response.json()
      throw new Error(error.message || 'Email verification failed')
    }
  }

  async resendVerification(email: string): Promise<string> {
    const response = await fetch(`${API_BASE_URL}/resend-email`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email }),
    })

    if (!response.ok) {
      const error = await response.json()
      throw new Error(error.message || 'Failed to resend verification email')
    }

    const result = await response.json()
    return result.message
  }

  async getProfile(): Promise<AuthResponse> {
    const token = this.getToken()
    if (!token) {
      throw new Error('No authentication token found')
    }

    const response = await fetch(`${API_BASE_URL}/profile`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
      },
    })

    if (!response.ok) {
      if (response.status === 401) {
        this.logout()
        throw new Error('Session expired. Please login again.')
      }
      const error = await response.json()
      throw new Error(error.message || 'Failed to fetch profile')
    }

    const result = await response.json()
    this.setUser(result)
    return result
  }

  async uploadImage(file: File): Promise<string> {
    const formData = new FormData()
    formData.append('images', file)

    const response = await fetch(`${API_BASE_URL}/upload-image`, {
      method: 'POST',
      body: formData,
    })

    if (!response.ok) {
      throw new Error('Image upload failed')
    }

    const result = await response.json()
    return result.url || result.imageUrl
  }

  setToken(token: string): void {
    localStorage.setItem('authToken', token)
  }

  getToken(): string | null {
    return localStorage.getItem('authToken')
  }

  setUser(user: AuthResponse): void {
    localStorage.setItem('user', JSON.stringify(user))
  }

  getUser(): AuthResponse | null {
    const userStr = localStorage.getItem('user')
    return userStr ? JSON.parse(userStr) : null
  }

  logout(): void {
    localStorage.removeItem('authToken')
    localStorage.removeItem('user')
  }

  isAuthenticated(): boolean {
    return !!this.getToken()
  }
}

export const authService = new AuthService()
