import SingleDatePicker from "@/components/input/date/SingleDatePicker.tsx";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage
} from "@/components/ui/form.tsx";
import { Input } from "@/components/ui/input.tsx";
import type { NewTaskRequest } from "@/types/api.types.ts";
import { titleSchema } from "@/types/schemas";
import { tentativeCompletionDateSchema } from "@/types/schemas.ts";
import type { ZentraError } from "@/types/ui.types.ts";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { toast } from "sonner";
import z from "zod";
import "./NewTaskForm.css";

const newTaskSchema = z.object({
  title: titleSchema,
  tentativeCompletionDate: tentativeCompletionDateSchema
});

interface NewTaskFormPropsType {
  createNewTask: (newTaskRequest: NewTaskRequest) => Promise<void>;
}

function NewTaskForm({ createNewTask }: NewTaskFormPropsType) {
  const form = useForm<z.infer<typeof newTaskSchema>>({
    resolver: zodResolver(newTaskSchema),
    defaultValues: {
      title: "",
      tentativeCompletionDate: null
    }
  });

  function handleCreateTask(values: z.infer<typeof newTaskSchema>) {
    createNewTask({
      title: values.title,
      tentativeCompletionDate: values.tentativeCompletionDate?.toISOString() ?? null
    })
      .then(() => {
        form.reset();
      })
      .catch((error) => {
        const err = error as ZentraError;
        console.error(`Error creating task: ${err.message}`, err.description);
        toast.error(err.message, { description: err.description });
      });
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(handleCreateTask)} noValidate
            className="NewTaskForm__form">
        <FormField
          name="title"
          control={form.control}
          render={({ field }) => (
            <FormItem>
              <FormLabel>Title</FormLabel>
              <FormControl>
                <Input type="text" placeholder="Enter task title"
                       {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        >
        </FormField>
        <FormField
          name="tentativeCompletionDate"
          control={form.control}
          render={({ field }) => (
            <FormItem>
              <FormLabel>Tentative Completion Date</FormLabel>
              <FormControl>
                <SingleDatePicker
                  value={field.value}
                  onChange={field.onChange}
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        >
        </FormField>
        <div className="NewTaskForm__form-button-container">
          <Button variant="destructive" type="button" onClick={() => {
            form.setValue("tentativeCompletionDate", null, {
              shouldDirty: true,
              shouldTouch: true,
              shouldValidate: true
            });
          }}>
            Clear Date
          </Button><Button variant="default" type="submit">
          Create Task
        </Button>
        </div>
      </form>
    </Form>
  );
}

export default NewTaskForm;
