import Footer from "@/components/footer/Footer.tsx";
import Header from "@/components/header/Header";
import * as React from "react";
import "./AppLayout.css";

interface AppLayoutPropsType {
  children: React.ReactNode;
}

function AppLayout({ children }: AppLayoutPropsType) {
  return (
    <div className="AppLayout">
      <Header />
      <div className="AppLayout__main-container">
        <main>
          {children}
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default AppLayout;