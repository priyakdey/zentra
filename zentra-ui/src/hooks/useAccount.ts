import { AccountContext } from "@/context/AccountContext.tsx";
import { useContext } from "react";

const useAccount = () => useContext(AccountContext)!;

export default useAccount;