import TasksList from "@/components/task/list/TaskList.tsx";
import useAccount from "@/hooks/useAccount.ts";
import AppLayout from "../layout/AppLayout";
import "./HomePage.css";

function HomePage() {
  const { inCompleteTasks, completedTasks } = useAccount();

  return (
    <AppLayout>
      <div className="HomePage__task-list-section">
        <TasksList tasks={inCompleteTasks} />
        <TasksList tasks={completedTasks} />
      </div>
    </AppLayout>

  );
}

export default HomePage;