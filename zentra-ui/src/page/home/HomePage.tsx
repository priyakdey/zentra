import useAccount from "@/hooks/useAccount.ts";

function HomePage() {
  const { name } = useAccount();

  return (
    <div>
      <h1>Welcome to the Home Page, {name}</h1>
      <p>This is the home page of our application.</p>
    </div>
  );
}

export default HomePage;