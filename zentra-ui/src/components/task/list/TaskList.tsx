import TaskItem from "@/components/task/item/TaskItem.tsx";
import type { Task } from "@/types/ui.types.ts";
import "./TaskList.css";

interface TaskListContainerPropsType {
  tasks: Task[];
}

function TaskList({ tasks }: TaskListContainerPropsType) {
  return (
    <div className="HomePage__task-list">
      <ul>
        {
          tasks.map((task) => (
            <TaskItem key={task.id} task={task} />
          ))
        }
      </ul>
    </div>
  );
}

export default TaskList;