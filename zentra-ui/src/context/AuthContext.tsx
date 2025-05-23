import { authenticateUser, createAccount } from "@/service/authService.ts";
import type {
  AuthResponse,
  LoginRequest,
  NewAccountRequest
} from "@/types/api.types.ts";
import { ZentraError } from "@/types/ui.types.ts";
import * as React from "react";
import { createContext, useState } from "react";
import { toast } from "sonner";

interface AuthContextType {
  isLoggedIn: boolean;
  login: (body: LoginRequest) => void;
  signup: (body: NewAccountRequest) => void;
  logout: () => void;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderPropsType {
  children: React.ReactNode;
}

function AuthProvider({ children }: AuthProviderPropsType) {
  const [ accountId, setAccountId ] = useState<number | undefined>(undefined);

  const login = (body: LoginRequest): void => {
    authenticateUser(body)
      .then((response: AuthResponse) => {
        setAccountId(response.accountId);
      })
      .catch((error: ZentraError) => {
        const message = error.message;
        const description = error.description;
        console.error(`ERROR: ${message}: ${description}`);
        toast.error(message, { description: description });
      });
  };

  const signup = (body: NewAccountRequest): void => {
    createAccount(body)
      .then((response: AuthResponse) => {
        setAccountId(response.accountId);
      })
      .catch((error: ZentraError) => {
        const message = error.message;
        const description = error.description;
        console.error(`ERROR: ${message}: ${description}`);
        toast.error(message, { description: description });
      });
  };

  const logout = () => {
    // TODO: call logout API
    setAccountId(undefined);
  };

  const isLoggedIn = !!accountId;

  return (
    <AuthContext.Provider
      value={{ isLoggedIn, login, signup, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;