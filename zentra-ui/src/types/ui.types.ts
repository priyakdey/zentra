export interface Task {
  id: number;
  title: string;
  isCompleted: boolean;
  tentativeCompletionDate?: Date;
  createdAt: Date;
  completedAt?: Date;
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
