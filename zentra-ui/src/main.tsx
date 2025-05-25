import AccountProvider from "@/context/AccountContext.tsx";
import AuthProvider from "@/context/AuthContext.tsx";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router";
import App from "./App.tsx";
import "./index.css";

createRoot(document.getElementById("root")!).render(
  <BrowserRouter>
    <AuthProvider>
      <AccountProvider>
        <StrictMode>
          <App />
        </StrictMode>
      </AccountProvider>
    </AuthProvider>
  </BrowserRouter>
);
