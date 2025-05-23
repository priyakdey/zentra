import useAccount from "@/hooks/useAccount.ts";
import AppLayout from "../layout/AppLayout";
import "./HomePage.css";

function HomePage() {
  const { name } = useAccount();

  return (
    <AppLayout className="HomePage__layout">
      <div className="HomePage__container">
        <h1>Welcome to the Home Page, {name}</h1>
        <p>This is the home page of our application.</p>
      </div>
    </AppLayout>

  );
}

export default HomePage;