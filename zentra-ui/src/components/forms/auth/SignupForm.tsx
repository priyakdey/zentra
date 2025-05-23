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
import useAuth from "@/hooks/useAuth.ts";
import { emailSchema, nameSchema, passwordSchema } from "@/types/schemas.ts";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import "./AuthForm.css";

const signupSchema = z.object({
  name: nameSchema,
  email: emailSchema,
  password: passwordSchema
});


function SignupForm() {
  const { signup } = useAuth();

  const form = useForm<z.infer<typeof signupSchema>>({
    resolver: zodResolver(signupSchema),
    defaultValues: {
      name: "",
      email: "",
      password: ""
    }
  });

  const handleLogin = (values: z.infer<typeof signupSchema>) => {
    signup(values);
  };

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(handleLogin)} className="AuthForm-form"
            noValidate>
        <FormField
          name="name"
          control={form.control}
          render={({ field }) => (
            <FormItem>
              <FormLabel>Name</FormLabel>
              <FormControl>
                <Input type="text"
                       placeholder="John Doe" {...field} />
              </FormControl>
              <FormMessage className="AuthForm_error_message" />
            </FormItem>
          )}
        >
        </FormField>
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
          Signup
        </Button>
      </form>
    </Form>
  )
    ;
}

export default SignupForm;