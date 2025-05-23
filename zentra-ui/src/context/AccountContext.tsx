import { getAccountDetails } from "@/service/accountService.ts";
import type { AccountDetailsResponse } from "@/types/api.types.ts";
import type { ZentraError } from "@/types/ui.types.ts";
import * as React from "react";
import { createContext, useEffect, useState } from "react";

interface AccountContextType {
  accountId?: number;
  name?: string;
  isLoading: boolean;
  getProfileDetails: () => void;
}

// eslint-disable-next-line react-refresh/only-export-components
export const AccountContext = createContext<AccountContextType | null>(null);

interface AccountProviderPropTypes {
  children: React.ReactNode;
}

function AccountProvider({ children }: AccountProviderPropTypes) {
  const [ accountId, setAccountId ] = useState<number | undefined>(undefined);
  const [ name, setName ] = useState<string | undefined>(undefined);

  const [ isLoading, setIsLoading ] = useState<boolean>(true);

  useEffect(() => {
    getProfileDetails();
  }, []);

  const getProfileDetails = () => {
    setIsLoading(true);
    getAccountDetails()
      .then((response: AccountDetailsResponse) => {
        setAccountId(response.accountId);
        setName(response.name);
      })
      .catch((error: ZentraError) => {
        const message = error.message;
        const description = error.description;
        console.error(`ERROR: ${message}: ${description}`);
      })
      .finally(() => {
        setIsLoading(false);
      });
  };

  return (
    <AccountContext.Provider
      value={{ accountId, name, isLoading, getProfileDetails }}>
      {children}
    </AccountContext.Provider>
  );
}

export default AccountProvider;