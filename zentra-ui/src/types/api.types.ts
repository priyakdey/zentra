/* Request Types Start */
export interface NewAccountRequest {
  name: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface NewTaskRequest {
  title: string;
  tentativeCompletionDate: string | null;
}

/* Response Types Start */

export interface AuthResponse {
  accountId: number;
}

export interface AccountDetailsResponse {
  accountId: number;
  name: string;
}

export interface ErrorResponse {
  message: string;
  description: string;
}

export interface TaskResponse {
  id: number;
  title: string;
  isCompleted: boolean;
  tentativeCompletionDate: string | null;
  createdAt: string;
  completedAt: string | null;
}