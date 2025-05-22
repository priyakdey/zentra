import "./Footer.css";
import { CopyrightIcon } from "lucide-react";
import { Link } from "react-router";

function Footer() {
  return (
    <footer className="Footer">
      <CopyrightIcon height="16" width="16"></CopyrightIcon>
      &nbsp;2025&nbsp;
      <Link to="https://priyakdey.com" target="_blank"
            rel="noopener noreferrer">
        Priyak Dey
      </Link>
    </footer>
  );
}

export default Footer;