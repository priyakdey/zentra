import { Input } from "@/components/ui/input.tsx";
import { Switch } from "@/components/ui/switch.tsx";
import { useState } from "react";
import type { ControllerRenderProps, FieldValues } from "react-hook-form";
import "./PasswordInput.css";

interface PasswordInputPropsType {
  field: ControllerRenderProps<FieldValues, string>;
}

function PasswordInput({ field }: PasswordInputPropsType) {
  const [ showPassword, setShowPassword ] = useState<boolean>(false);

  const togglePasswordVisibility = () => {
    setShowPassword((prev) => !prev);
  };

  return (
    <div className="PasswordInput__container">
      <Input
        type={showPassword ? "text" : "password"}
        placeholder="********"
        className="PasswordInput__field"
        {...field}
      />
      <Switch
        checked={showPassword}
        onCheckedChange={togglePasswordVisibility}
        aria-readonly
        disabled={field.value === ""}
        className="PasswordInput__toggle"
      />
    </div>
  );
}

export default PasswordInput;