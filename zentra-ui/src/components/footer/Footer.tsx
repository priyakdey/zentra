import githubLogo from "@/assets/github-mark-white.svg";
import linkedinLogo from "@/assets/linkedin-svgrepo-com.svg";
import { CopyrightIcon } from "lucide-react";
import { Link } from "react-router";
import "./Footer.css";

function Footer() {
  return (
    <footer className="Footer">
      <CopyrightIcon height="12" width="12"></CopyrightIcon>
      <span className="Footer__copyright-year">&nbsp;2025&nbsp;</span>
      <Link to="https://priyakdey.com" target="_blank"
            rel="noopener noreferrer" className="Footer__name">
        Priyak Dey
      </Link>
      <div className="Footer__social-handles">
        <Link to="https://www.linkedin.com/in/priyakdey/" target="_blank"
              rel="noopener noreferrer">
          <img src={linkedinLogo} alt="Link to Linkedin Page"
               className="Footer_thirdparty-brand-logo" />
        </Link>
        <Link to="https://github.com/priyakdey/zentra" target="_blank"
              rel="noopener noreferrer">
          <img src={githubLogo} alt="Link to github repository"
               className="Footer_thirdparty-brand-logo" />
        </Link>
      </div>
    </footer>
  );
}

export default Footer;