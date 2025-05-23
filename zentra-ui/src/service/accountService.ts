import type {
  AccountDetailsResponse,
  ErrorResponse
} from "@/types/api.types.ts";
import { ZentraError } from "@/types/ui.types.ts";

export async function getAccountDetails(): Promise<AccountDetailsResponse> {
  // TODO: urls should be env driven
  const response = await fetch("http://localhost:8080/v1/me", {
    method: "GET",
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

  return await response.json();
}