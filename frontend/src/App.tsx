import { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Authentication from "./mainpages/authentication/Authentication";
import SetTitle from "./components/common/SetTitle";
import PageLoader from "./components/common/loaders/PageLoader";
import NotFound from "./components/common/NotFound";
import DashBoard from "./DashBoard/DashBoard";

function App() {
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 1000);
  }, []); 
  return loading ? (
    <PageLoader />
  ) : (
    <BrowserRouter>
      <Routes>
        <Route path="/dashBoard/*" element={<DashBoard/>} />
        <Route
          path="/auth/*"
          element={
            <>
              <SetTitle title="login"/>
              <Authentication />
            </>
          }
        />
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
    </BrowserRouter>
  );
}

export default App;
