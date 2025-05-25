import zentraLogo from "@/assets/zentra-logo.svg";
import { Button } from "@/components/ui/button.tsx";
import useAccount from "@/hooks/useAccount.ts";
import useAuth from "@/hooks/useAuth.ts";
import { LogOutIcon } from "lucide-react";
import "./Header.css";

export function Header() {
  const { isLoggedIn, logout } = useAuth();
  const { name } = useAccount();

  const handleLogout = async () => {
    try {
      await logout();
    } catch(error) {
      console.error("Logout failed", error);
    }
  };


  return (
    <header className="Header">
      <img src={zentraLogo} alt="zentra" className="Header__logo" />
      <div className="Header__controls">
        {/* <Switch */}
        {/*   checked={true} */}
        {/*   aria-readonly */}
        {/*   className="Header__colormode-toggle" */}
        {/* > */}
        {/* </Switch> */}
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