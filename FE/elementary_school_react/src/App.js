import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./css/common.css";
import Login from "./login/login";
import Role from "./role/role";
import Kiosk from "./kiosk/kiosk";
import Main from "./main/main";
import Question from "./question/question";
import Mypage from "./mypage/mypage";
import PracticeType from "./kiosk/practiceType";
import Posting from "./myposting/myposting";
import Comment from "./mycomment/mycomment";
import Policy from "./policy/policy";
import Sidebar from "./sidebar/sidebar";
import Write from "./write/write";
import Community from "./community/community";
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

function App() {
  return (
    <div className="wrapper">
      <div className="contentWrapper">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/role" element={<Role />} />
          <Route path="/kiosk" element={<Kiosk />} />
          <Route path="/main" element={<Main />} />
          <Route path="/question" element={<Question />} />
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/practiceType" element={<PracticeType />} />
          <Route path="/sidebar" element={<Sidebar />} />
          <Route path="/posting" element={<Posting />} />
          <Route path="/comment" element={<Comment />} />
          <Route path="/policy" element={<Policy />} />
          <Route path="/write" element={<Write />} />
          <Route path="/community" element={<Community />} />
          <Route path="/shop" element={<Shop />} />
          <Route path="/menu" element={<MegaMenu />} />
          <Route path="/megabody" element={<Megabody />} />
          <Route path="/footer" element={<MegaFooter />} />
          <Route path="/megapay" element={<MegaPay />} />
          <Route path="/MegaIceTea" element={<MegaIceTea />} />
          <Route path="/MegaAmericano" element={<MegaAmericano />} />
          <Route path="/MegaChoco" element={<MegaChoco />} />
          <Route path="/MegaBasket" element={<MegaBasket />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
