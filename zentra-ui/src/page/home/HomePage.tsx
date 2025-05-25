import useAccount from "@/hooks/useAccount.ts";
import AppLayout from "../layout/AppLayout";
import "./HomePage.css";

function HomePage() {
  const { name } = useAccount();

  return (
    <AppLayout className="HomePage__layout">
      <div className="HomePage__container">

      </div>
    </AppLayout>

  );
}

export default HomePage;