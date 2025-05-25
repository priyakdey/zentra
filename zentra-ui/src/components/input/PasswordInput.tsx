import { Button } from "@/components/ui/button.tsx";
import { Input } from "@/components/ui/input.tsx";
import { EyeIcon, EyeOffIcon } from "lucide-react";
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
      <Button type="button"
              variant="ghost"
              className="PasswordInput__toggle-btn"
              onClick={togglePasswordVisibility}
      >
        {showPassword ? <EyeIcon /> : <EyeOffIcon />}
      </Button>
    </div>
  );
}

export default PasswordInput;