import type {
  ErrorResponse,
  NewTaskRequest,
  TaskResponse,
  TasksResponse
} from "@/types/api.types";
import { ZentraError } from "@/types/ui.types";

export async function createTask(newTaskRequest: NewTaskRequest): Promise<TaskResponse> {
  const response = await fetch("http://localhost:8080/v1/me/tasks", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    credentials: "include",
    body: JSON.stringify(newTaskRequest)
  });

  const status = response.status;

  if (status !== 201) {
    const errResponse: ErrorResponse = await response.json();
    throw new ZentraError(errResponse.message, errResponse.description, status);
  }

  return await response.json() as TaskResponse;
}

export async function fetchAllTasks(): Promise<TasksResponse> {
  const response = await fetch("http://localhost:8080/v1/me/tasks", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    credentials: "include"
  });

  const status = response.status;

  if (status !== 200) {
    const errResponse: ErrorResponse = await response.json();
    throw new ZentraError(errResponse.message, errResponse.description, status);
  }

  return await response.json() as TasksResponse;
}

export async function markTaskComplete(taskId: number): Promise<void> {
  const response = await fetch(`http://localhost:8080/v1/me/tasks/${taskId}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    credentials: "include"
  });

  const status = response.status;

  if (status !== 204) {
    const errResponse: ErrorResponse = await response.json();
    throw new ZentraError(errResponse.message, errResponse.description, status);
  }
}