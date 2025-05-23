import LoginForm from "@/components/forms/auth/LoginForm.tsx";
import SignupForm from "@/components/forms/auth/SignupForm.tsx";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader
} from "@/components/ui/card.tsx";
import {
  Tabs,
  TabsContent,
  TabsList,
  TabsTrigger
} from "@/components/ui/tabs.tsx";
import AppLayout from "@/page/layout/AppLayout.tsx";
import "./WelcomePage.css";

function WelcomePage() {
  return (
    <AppLayout className="WelcomePage-container">
      <div className="WelcomePage__auth-container">
        <Tabs defaultValue="login" className="WelcomePage__tabs">
          <TabsList className="WelcomePage__tabs_list">
            <TabsTrigger value="login">Login</TabsTrigger>
            <TabsTrigger value="signup">Signup</TabsTrigger>
          </TabsList>
          <TabsContent value="login">
            <Card className="WelcomePage__card">
              <CardHeader className="WelcomePage__card-header">
                Login to Zentra
              </CardHeader>
              <CardDescription></CardDescription>
              <CardContent>
                <LoginForm />
              </CardContent>
            </Card>
          </TabsContent>
          <TabsContent value="signup">
            <Card className="WelcomePage__card">
              <CardHeader className="WelcomePage__card-header">
                Signup to Zentra
              </CardHeader>
              <CardDescription></CardDescription>
              <CardContent>
                <SignupForm />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </div>
    </AppLayout>
  );
}

export default WelcomePage;