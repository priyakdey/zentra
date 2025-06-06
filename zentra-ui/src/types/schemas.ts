import { z } from "zod";

export const nameSchema = z.string()
  .min(1, "Name is required");

export const emailSchema = z.string()
  .min(1, "Email is required")
  .max(254, "Email address must not exceed 254 characters")
  .email("Please enter a valid email address");

export const passwordSchema = z.string()
  .min(8, "Password must be between 8 and 25 characters")
  .max(25, "Password must be between 8 and 25 characters");

export const titleSchema = z.string()
  .min(1, "Title is required");

export const tentativeCompletionDateSchema = z.date()
  .min(new Date(), "Tentative completion date cannot be in the past")
  .nullable();