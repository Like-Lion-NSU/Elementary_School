import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./css/common.css";
import Login from "./login/login";
import Role from "./role/role";
import Kiosk from "./kiosk/kiosk";
import Main from "./main/main";
import Mypage from "./mypage/mypage";
import PracticeType from "./kiosk/practiceType";
import Posting from "./board/myposting";
import Comments from "./board/mycomment";
import Sidebar from "./sidebar/sidebar";
import Write from "./write/write";
import Shop from "./shop/shop";
import Megabody from "./kiosk/megabody_ice";
import MegaMenu from "./kiosk/megaMenu";
import MegaFooter from "./kiosk/megaFooter";
import MegaPay from "./kiosk/kioskpay";
import MegaIceTea from "./kiosk/modalIceTea";
import MegaAmericano from "./kiosk/modalAmericano";
import MegaChoco from "./kiosk/modalChoco";
import MegaBasket from "./kiosk/modalbasket";
import Footer from "./footer/footer";
import BoardHeader from "./board/boardHeader";
import BoardTable from "./board/boardTable";
import Community from "./board/boardCommunity";
import Policy from "./board/boardPolicy";
import Question from "./board/boardQuestion";
import PostHeader from "./postpage/postHeader";
import PostMain from "./postpage/postMain";
import PostFooter from "./postpage/postFooter";
import PostPage from "./postpage/postPage";
import PostLike from "./postpage/postLike";
import Kioskproblem from "./kiosk/kioskproblem";
import ErrorPage from "./error/404";

function App() {
  return (
    <div className="wrapper">
      <div className="contentWrapper">
        <Routes>
          <Route path="/v1" element={<Login />} />
          <Route path="/v1/role" element={<Role />} />
          <Route path="/v1/kiosk" element={<Kiosk />} />
          <Route path="/v1/home" element={<Main />} />
          <Route path="/v1/mypage" element={<Mypage />} />
          <Route path="/v1/practiceType" element={<PracticeType />} />
          <Route path="/v1/sidebar" element={<Sidebar />} />
          <Route path="/v1/posting" element={<Posting />} />
          <Route path="/v1/comments" element={<Comments />} />
          <Route path="/v1/writePost" element={<Write />} />
          <Route path="/v1/shop" element={<Shop />} />
          <Route path="/v1/menu" element={<MegaMenu />} />
          <Route path="/v1/megabody" element={<Megabody />} />
          <Route path="/v1/footer" element={<MegaFooter />} />
          <Route path="/v1/megapay" element={<MegaPay />} />
          <Route path="/v1/MegaIceTea" element={<MegaIceTea />} />
          <Route path="/v1/MegaAmericano" element={<MegaAmericano />} />
          <Route path="/v1/MegaChoco" element={<MegaChoco />} />
          <Route path="/v1/MegaBasket" element={<MegaBasket />} />
          <Route path="/v1/boardHeader" element={<BoardHeader />} />
          <Route path="/v1/boardTable" element={<BoardTable />} />
          <Route path="/v1/소통하기/posts" element={<Community />} />
          <Route path="/v1/policy" element={<Policy />} />
          <Route path="/v1/질문하기/posts" element={<Question />} />
          <Route path="/v1/postHeader" element={<PostHeader />} />
          <Route path="/v1/postMain" element={<PostMain />} />
          <Route path="/v1/postFooter" element={<PostFooter />} />
          <Route path="/v1/post/:category/:postId" element={<PostPage />} />
          <Route path="/v1/postLike" element={<PostLike />} />
          <Route path="/v1/kioskproblem" element={<Kioskproblem />} />
          <Route path="/v1/elementary404" element={<ErrorPage />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
