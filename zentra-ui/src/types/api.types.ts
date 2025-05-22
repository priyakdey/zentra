export interface AuthRequest {
  email: string;
  password: string;
}

export interface AuthResponse {
  accountId: number;
}

export interface ErrorResponse {
  message: string;
  description: string;
}