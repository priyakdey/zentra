import { getAccountDetails } from "@/service/accountService.ts";
import * as React from "react";
import { createContext, useState } from "react";

interface AccountContextType {
  accountId?: number;
  name?: string;
  isLoading: boolean;
  refresh: () => Promise<void>;
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


  const refresh = async () => {
    setIsLoading(true);
    const response = await getAccountDetails();
    setAccountId(response.accountId);
    setName(response.name);
    setIsLoading(false);
  };

  return (
    <AccountContext.Provider
      value={{ accountId, name, isLoading, refresh }}>
      {children}
    </AccountContext.Provider>
  );
}

export default AccountProvider;