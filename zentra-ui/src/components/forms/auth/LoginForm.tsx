import { Button } from "@/components/ui/button.tsx";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage
} from "@/components/ui/form.tsx";
import { Input } from "@/components/ui/input.tsx";
import useAccount from "@/hooks/useAccount.ts";
import useAuth from "@/hooks/useAuth.ts";
import { emailSchema, passwordSchema } from "@/types/schemas.ts";
import type { ZentraError } from "@/types/ui.types.ts";
import { zodResolver } from "@hookform/resolvers/zod";
import { LogInIcon } from "lucide-react";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import { toast } from "sonner";
import { z } from "zod";
import "./AuthForm.css";

const loginSchema = z.object({
  email: emailSchema,
  password: passwordSchema
});


function LoginForm() {
  const { login } = useAuth();
  const { refresh } = useAccount();

  const navigate = useNavigate();

  const form = useForm<z.infer<typeof loginSchema>>({
    resolver: zodResolver(loginSchema),
    defaultValues: {
      email: "",
      password: ""
    }
  });

  const handleLogin = async (values: z.infer<typeof loginSchema>) => {
    try {
      await login(values);
      await refresh();
      navigate("/home", { replace: true });
    } catch(error) {
      const err = error as ZentraError;
      if (err.status === 401 || err.status === 403) {
        toast.error("Session expired", {
          description: "Please log in again to continue."
        });
        navigate("/", { replace: true });
      } else {
        toast.error(err.message, { description: err.description });
      }
    }
  };

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(handleLogin)} className="AuthForm-form"
            noValidate>
        <FormField
          name="email"
          control={form.control}
          render={({ field }) => (
            <FormItem>
              <FormLabel>Email</FormLabel>
              <FormControl>
                <Input type="email"
                       placeholder="johndoe@example.com" {...field} />
              </FormControl>
              <FormMessage className="AuthForm_error_message" />
            </FormItem>
          )}
        >
        </FormField>
        <FormField
          name="password"
          control={form.control}
          render={({ field }) => (
            <FormItem>
              <FormLabel>Password</FormLabel>
              <FormControl>
                <Input type="password"
                       placeholder="*******" {...field} />
              </FormControl>
              <FormMessage className="AuthForm_error_message" />
            </FormItem>
          )}
        >
        </FormField>
        <Button className="AuthForm-form_btn" type="submit" variant="default">
          Login
          <LogInIcon />
        </Button>
      </form>
    </Form>
  );
}

export default LoginForm;