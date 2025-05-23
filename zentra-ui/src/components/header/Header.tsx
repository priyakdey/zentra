import zentraLogo from "@/assets/zentra-logo.svg";
import { Button } from "@/components/ui/button.tsx";
import { Switch } from "@/components/ui/switch.tsx";
import useAccount from "@/hooks/useAccount.ts";
import useAuth from "@/hooks/useAuth.ts";
import { LogOutIcon } from "lucide-react";
import "./Header.css";
import { useState } from "react";

export function Header() {
  const [ isDark, setIsDark ] = useState<boolean>(() => document.documentElement.classList.contains("dark"));

  const { isLoggedIn, logout } = useAuth();
  const { name } = useAccount();

  const handleLogout = async () => {
    try {
      await logout();
    } catch(error) {
      console.error("Logout failed", error);
    }
  };

  const toggleColorMode = (checked: boolean) => {
    const html = document.documentElement;
    html.classList.toggle("dark");
    html.classList.toggle("light");
    setIsDark(checked);
  };

  return (
    <header className="Header">
      <img src={zentraLogo} alt="zentra" className="Header__logo" />
      <div className="Header__controls">
        <Switch
          checked={true}
          onCheckedChange={() => toggleColorMode(isDark)}
          aria-readonly
          className="Header__colormode-toggle"
        >
        </Switch>
        {
          isLoggedIn && (
            <div className="Header__controls-container">
            <span className="Header__controls-name">
              Welcome, {name}
            </span>
              <Button type="button" variant="destructive" onClick={handleLogout}>
                Logout
                <LogOutIcon />
              </Button>
            </div>
          )
        }
      </div>
    </header>
  );
}

export default Header;