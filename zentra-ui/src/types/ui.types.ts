export interface Task {
  id: number;
  title: string;
  isCompleted: boolean;
  tentativeCompletionDate: Date | null;
  createdAt: Date;
  completedAt: Date | null;
}

export class ZentraError extends Error {
  public description: string;
  public status: number;

  constructor(message: string, description: string, status: number) {
    super(message);
    this.description = description;
    this.status = status;
  }
}
