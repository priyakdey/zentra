export class ZentraError extends Error {
  public description: string;

  constructor(message: string, description: string) {
    super(message);
    this.description = description;
  }
}
