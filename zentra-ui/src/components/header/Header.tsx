import zentraLogo from "@/assets/zentra-logo.svg";
import { Button } from "@/components/ui/button.tsx";
import useAccount from "@/hooks/useAccount.ts";
import useAuth from "@/hooks/useAuth.ts";
import "./Header.css";
import { LogOutIcon } from "lucide-react";

export function Header() {
  const { isLoggedIn, logout } = useAuth();
  const { name } = useAccount();

  const handleLogout = async () => {
    try {
      await logout();
      // Optionally, you can add a redirect or a toast notification here
    } catch(error) {
      console.error("Logout failed", error);
    }
  };

  return (
    <header className="Header">
      <img src={zentraLogo} alt="zentra" className="Header__logo" />
      <div className="Header__controls">
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