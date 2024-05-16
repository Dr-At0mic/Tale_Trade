import { Route, Routes } from "react-router-dom";
import DashBoardLayout from "./layout/DashBoardLayout";
import NotFound from "../components/common/NotFound";
import SetTitle from "../components/common/SetTitle";
import UserProfile from "./user/pages/UserProfile";
import Following from "./user/pages/Following";
import Followers from "./user/pages/Followers";
import Likes from "./user/pages/Likes";
import Commnets from "./user/pages/Commnets";
import WhisList from "./user/pages/WhisList";

function DashBoard() {
  return (
    <Routes>
      <Route path="/" element={<DashBoardLayout />}>
        <Route path="profile" element={<UserProfile/>}/>
        <Route path="editProfile" element={<></>} />
        <Route path="notifications" element={<></>} />
        <Route path="following" element={<Following/>} />        
        <Route path="followers" element={<Followers/>} />
        <Route path="likes" element={<Likes/>} />
        <Route path="comments" element={<Commnets/>} />
        <Route path="wishList" element={<WhisList/>} />
      </Route>
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
  );
}

export default DashBoard;
