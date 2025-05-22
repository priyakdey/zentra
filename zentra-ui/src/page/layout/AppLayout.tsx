import Footer from "@/components/footer/Footer.tsx";
import Header from "@/components/header/Header.tsx";
import * as React from "react";
import "./AppLayout.css";

interface AppLayoutPropsType {
  children: React.ReactNode;
  className?: string;
}

function AppLayout({ children, className }: AppLayoutPropsType) {
  return (
    <div className="AppLayout">
      <Header />
      <div className={`AppLayout__main-container ${className}`}>
        <main>
          {children}
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default AppLayout;