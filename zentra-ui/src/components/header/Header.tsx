import zentraLogo from '@/assets/zentra-logo.svg';
import "./Header.css";

export function Header() {
  return (
    <header className="Header">
      <img src={zentraLogo} alt="zentra" className="Header__logo" />
      <div className="Header__controls">

      </div>
    </header>
  );
}

export default Header;