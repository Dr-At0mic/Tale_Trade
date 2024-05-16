import { Route, Routes } from "react-router-dom"
import SetTitle from "../../components/common/SetTitle"
import AccountVerification from "./pages/AccountVerification"
import Auth from "./pages/Auth"
import NotFound from "../../components/common/NotFound"

function Authentication() {
  return (
    <Routes>
        <Route path="/" element={<>
          <Auth/>
        </>}/>
        <Route path="/AccountVerification/:token" element={
          <>
          <SetTitle title="Account Verification"/>
          <AccountVerification/>
          </>
        }/>
         <Route
          path="*"
          element={
            <>
              <SetTitle title="You are Lost (404)" />
              <NotFound />
            </>
          }
        />
    </Routes>
  )
}

export default Authentication