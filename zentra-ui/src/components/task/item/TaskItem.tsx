import { Badge } from "@/components/ui/badge.tsx";
import { Button } from "@/components/ui/button";
import useAccount from "@/hooks/useAccount.ts";
import type { Task } from "@/types/ui.types.ts";
import { CheckIcon } from "lucide-react";
import "./TaskItem.css";

interface TaskItemPropsType {
  task: Task;
}

function dateFormat(date: Date | null): string | null {
  if (date === null) return date;

  return date.toLocaleDateString("en-US", {
    dateStyle: "long"
  });
}

// | **Urgency**       | **Condition**                    | **Color**    | **Hex Code** |
// | ----------------- | -------------------------------- | ------------ | ------------ |
// | 🔴 Critical       | Overdue (past today)             | Red          | `#EF4444`    |
// | 🟠 High           | Due in 0–1 days                  | Orange       | `#F97316`    |
// | 🟡 Medium         | Due in 2–7 days                  | Yellow       | `#EAB308`    |
// | 🟢 Low / On Track | Due in >7 days                   | Green        | `#22C55E`    |
// | ⚪ No Deadline     | No `tentativeCompletionDate` set | Neutral Gray | `#6B7280`    |
function urgencyColor(dueDate: Date | null): string {
  if (!dueDate) return "#6B7280";

  const now = new Date();
  const diffDays = Math.ceil((dueDate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));

  if (diffDays < 0) return "#EF4444";
  if (diffDays <= 1) return "#F97316";
  if (diffDays <= 7) return "#EAB308";
  return "#22C55E";
}

function TaskItem({ task }: TaskItemPropsType) {
  const { completeTask } = useAccount();

  const tentativeDate = dateFormat(task.tentativeCompletionDate);
  const completedAt = dateFormat(task.completedAt);

  return (
    <li className="TaskItem">
      <div className="TaskItem__title-container">
        {
          !task.isCompleted && (
            <Badge
              style={{ backgroundColor: urgencyColor(task.tentativeCompletionDate) }} />
          )
        }
        <span
          className={`TaskItem__title ${task.isCompleted && "TaskItem__title-strike"}`}>
          {task.title}
        </span>
      </div>
      <div className="TaskItem__actions-container">
        <span className="TaskItem-tentativeDate">
          Tentative: {tentativeDate ?? "-"}
        </span>
        {
          task.isCompleted && (
            <span className="TaskItem-tentativeDate">
            Completed On: {completedAt}
            </span>
          )
        }
        {
          !task.isCompleted && (
            <Button type="button" variant="destructive"
                    className="TaskItem__complete-button"
                    onClick={() => completeTask(task.id)}
            >
              <CheckIcon height={10} width={10} />
            </Button>
          )
        }
      </div>
    </li>
  );
}

export default TaskItem;