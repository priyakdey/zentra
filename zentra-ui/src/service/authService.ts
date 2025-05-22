import type {
  AuthRequest,
  AuthResponse,
  ErrorResponse
} from "@/types/api.types.ts";
import { ZentraError } from "@/types/ui.types";

export async function authenticateUser(authRequest: AuthRequest): Promise<AuthResponse> {
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

  if (response.status !== 200) {
    const errorResponse: ErrorResponse = await response.json();
    throw new ZentraError(errorResponse.message, errorResponse.description);
  }

  return await response.json();
}