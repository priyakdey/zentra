import "./App.css";
import WelcomePage from "@/page/WelcomePage.tsx";
import { Route, Routes } from "react-router";

function App() {

  return (
    <Routes>
      <Route path="/" element={<WelcomePage />} />
    </Routes>
  );
}

export default App;
