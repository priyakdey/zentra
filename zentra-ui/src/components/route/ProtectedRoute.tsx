import useAuth from "@/hooks/useAuth.ts";
import * as React from "react";
import { Navigate } from "react-router";

interface ProtectedRoutePropsType {
  children: React.ReactNode;
}

function ProtectedRoute({ children }: ProtectedRoutePropsType) {
  const { isLoggedIn } = useAuth();

  if (isLoggedIn) {
    return children;
  } else {
    return <Navigate to="/" replace />;
  }
}

export default ProtectedRoute;