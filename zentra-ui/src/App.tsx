import "./App.css";
import ProtectedRoute from "@/components/route/ProtectedRoute.tsx";
import { Toaster } from "@/components/ui/sonner.tsx";
import HomePage from "@/page/home/HomePage.tsx";
import WelcomePage from "@/page/welcome/WelcomePage.tsx";
import { Route, Routes } from "react-router";

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<WelcomePage />} />
        <Route path="/home" element={
          <ProtectedRoute>
            <HomePage />
          </ProtectedRoute>
        } />
      </Routes>
      <Toaster
        richColors={true}
        theme="dark"
        dir="ltr"
        toastOptions={{
          duration: 5000,
          closeButton: true,
          closeButtonAriaLabel: "Close"
        }}
      />
    </>
  );
}

export default App;
