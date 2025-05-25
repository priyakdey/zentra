import NewTaskForm from "@/components/forms/task/NewTaskForm.tsx";
import TasksList from "@/components/task/list/TaskList.tsx";
import useAccount from "@/hooks/useAccount.ts";
import AppLayout from "../layout/AppLayout";
import "./HomePage.css";

function HomePage() {
  const { inCompleteTasks, completedTasks,  createNewTask} = useAccount();

  return (
    <AppLayout>
      <div>
        <NewTaskForm createNewTask={createNewTask}/>
      </div>
      <div className="HomePage__task-list-section">
        <TasksList tasks={inCompleteTasks} />
        <TasksList tasks={completedTasks} />
      </div>
    </AppLayout>

  );
}

export default HomePage;