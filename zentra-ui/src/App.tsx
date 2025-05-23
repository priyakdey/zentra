import "./App.css";
import { Toaster } from "@/components/ui/sonner.tsx";
import WelcomePage from "@/page/welcome/WelcomePage.tsx";
import { Route, Routes } from "react-router";

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<WelcomePage />} />
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
