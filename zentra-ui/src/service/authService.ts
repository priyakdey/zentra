import type {
  AuthResponse,
  ErrorResponse,
  LoginRequest,
  NewAccountRequest
} from "@/types/api.types.ts";
import { ZentraError } from "@/types/ui.types";

export async function createAccount(accountRequest: NewAccountRequest): Promise<AuthResponse> {
  // TODO: needs to be env driven
  const response = await fetch("http://localhost:8080/v1/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify(accountRequest),
    credentials: "include"
  });

  const status = response.status;
  if (status !== 201) {
    const errorResponse: ErrorResponse = await response.json();
    throw new ZentraError(errorResponse.message, errorResponse.description, status);
  }

  return await response.json() as AuthResponse;
}

export async function authenticateUser(authRequest: LoginRequest): Promise<AuthResponse> {
  // TODO: needs to be env driven
  const response = await fetch("http://localhost:8080/v1/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify(authRequest),
    credentials: "include"
  });

  const status = response.status;
  if (status !== 200) {
    const errorResponse: ErrorResponse = await response.json();
    throw new ZentraError(errorResponse.message, errorResponse.description, status);
  }

  return await response.json() as AuthResponse;
}

export async function logoutUser(): Promise<void> {
  const response = await fetch("http://localhost:8080/v1/me/logout", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    credentials: "include"
  });

  const status = response.status;
  if (status !== 200) {
    const errorResponse: ErrorResponse = await response.json();
    throw new ZentraError(errorResponse.message, errorResponse.description, status);
  }

  return Promise.resolve();
}