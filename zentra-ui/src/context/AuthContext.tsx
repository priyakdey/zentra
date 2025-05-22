import { authenticateUser } from "@/service/authService.ts";
import type { AuthRequest, AuthResponse } from "@/types/api.types.ts";
import { ZentraError } from "@/types/ui.types.ts";
import * as React from "react";
import { createContext, useState } from "react";
import { toast } from "sonner";

interface AuthContextType {
  login: (body: AuthRequest) => void;
  accountId?: number;
  isLoggedIn: boolean;
  logout: () => void;
  setAccountId: (accountId: number) => void;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AuthContext = createContext<AuthContextType | null>(null);

interface AuthProviderPropsType {
  children: React.ReactNode;
}

function AuthProvider({ children }: AuthProviderPropsType) {
  const [ accountId, setAccountId ] = useState<number | undefined>(undefined);

  const login = (body: AuthRequest): void => {
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

  const logout = () => {
    setAccountId(undefined);
    throw new ZentraError("Not implemented", "Logout not implemented");
  };

  const isLoggedIn = !!accountId;

  return (
    <AuthContext.Provider
      value={{ login, accountId, isLoggedIn, logout, setAccountId }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;