import { getAccountDetails } from "@/service/accountService.ts";
import {
  createTask,
  fetchAllTasks,
  markTaskComplete
} from "@/service/taskService.ts";
import type { NewTaskRequest, TaskDto } from "@/types/api.types.ts";
import type { Task } from "@/types/ui.types.ts";
import * as React from "react";
import { createContext, useState } from "react";

interface AccountContextType {
  accountId?: number;
  name?: string;
  isLoading: boolean;
  isSessionLoading: boolean;
  refresh: () => Promise<void>;
  inCompleteTasks: Task[];
  completedTasks: Task[];
  createNewTask: (newTaskRequest: NewTaskRequest) => Promise<void>;
  completeTask: (taskId: number) => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AccountContext = createContext<AccountContextType | null>(null);

interface AccountProviderPropTypes {
  children: React.ReactNode;
}

function mapToTask(task: TaskDto): Task {
  return {
    id: task.id,
    title: task.title,
    isCompleted: task.isCompleted,
    tentativeCompletionDate: task.tentativeCompletionDate !== null
      ? new Date(task.tentativeCompletionDate) : null,
    createdAt: new Date(task.createdAt),
    completedAt: task.completedAt !== null ? new Date(task.completedAt) : null
  };
}

function AccountProvider({ children }: AccountProviderPropTypes) {
  const [ accountId, setAccountId ] = useState<number | undefined>(undefined);
  const [ name, setName ] = useState<string | undefined>(undefined);
  const [ isLoading, setIsLoading ] = useState<boolean>(true);
  const [ inCompleteTasks, setInCompleteTasks ] = useState<Task[]>([]);
  const [ completedTasks, setCompletedTasks ] = useState<Task[]>([]);

  const refreshTasks = async () => {
    const taskResponse = await fetchAllTasks();
    setInCompleteTasks(taskResponse.inCompleteTasks.map(mapToTask));
    setCompletedTasks(taskResponse.completedTasks.map(mapToTask));
  };

  const refresh = async () => {
    setIsLoading(true);
    const accountDetailsResponse = await getAccountDetails();
    setAccountId(accountDetailsResponse.accountId);
    setName(accountDetailsResponse.name);
    await refreshTasks();
    setIsLoading(false);
  };

  const isSessionLoading = !isLoading && !!accountId;

  const createNewTask = async (newTaskRequest: NewTaskRequest) => {
    await createTask(newTaskRequest);
    setIsLoading(true);
    await refreshTasks();
    setIsLoading(false);
  };

  const completeTask = async (taskId: number) => {
    setIsLoading(true);
    await markTaskComplete(taskId);
    await refreshTasks();
    setIsLoading(false);
  };

  return (
    <AccountContext.Provider
      value={{
        accountId,
        name,
        isLoading,
        refresh,
        isSessionLoading,
        inCompleteTasks,
        completedTasks,
        createNewTask,
        completeTask
      }}>
      {children}
    </AccountContext.Provider>
  );
}

export default AccountProvider;