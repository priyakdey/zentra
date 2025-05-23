import { authenticateUser, createAccount } from "@/service/authService.ts";
import type { LoginRequest, NewAccountRequest } from "@/types/api.types.ts";
import * as React from "react";
import { createContext, useState } from "react";

interface AuthContextType {
  isLoggedIn: boolean;
  login: (body: LoginRequest) => Promise<void>;
  signup: (body: NewAccountRequest) => Promise<void>;
  logout: () => Promise<void>;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderPropsType {
  children: React.ReactNode;
}

function AuthProvider({ children }: AuthProviderPropsType) {
  const [ accountId, setAccountId ] = useState<number | undefined>(undefined);

  const login = async (body: LoginRequest): Promise<void> => {
    const response = await authenticateUser(body);
    setAccountId(response.accountId);
  };

  const signup = async (body: NewAccountRequest): Promise<void> => {
    const response = await createAccount(body);
    setAccountId(response.accountId);
  };

  const logout = async () => {
    // TODO properly implement logout
    setAccountId(undefined);
    return new Promise<void>(() => {});
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