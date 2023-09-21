import "./App.css";
import { Route, Routes } from "react-router-dom";
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
import Chatpage from "./chat/stomp";
import Korailproblem from "./korail/korailproblem";
import Korail from "./korail/korail";
import KorailHeader from "./korail/korailHeader";
import KorailFirstBody from "./korail/korailFirstBody";
import KorailSecondBody from "./korail/korailSecondBody";
import KorailLastBody from "./korail/korailLastBody";
import KorailFooter from "./korail/korailFooter";
import KorailMenu from "./korail/korailMenu";
import KorailSeat from "./korail/korailSeat";
import TrainSchedule from "./korail/korailTrainSchedule";
import KorailSeatNum from "./korail/korailSeatNum";
import KorailSeatNumBody from "./korail/korailSeatNumBody";
import KorailPay from "./korail/korailPay";
import KorailPayHeader from "./korail/korailPayHeader";
import KorailPayMenu from "./korail/korailPayMenu";
import KorailPayBody from "./korail/korailPayBody";
import { ScoreProvider } from "./korail/context";

function App() {
  return (
    <ScoreProvider>
      <div className="wrapper">
        <div className="contentWrapper">
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/role" element={<Role />} />
            <Route path="/kiosk" element={<Kiosk />} />
            <Route path="/home" element={<Main />} />
            <Route path="/mypage" element={<Mypage />} />
            <Route path="/practiceType" element={<PracticeType />} />
            <Route path="/sidebar" element={<Sidebar />} />
            <Route path="/posting" element={<Posting />} />
            <Route path="/comments" element={<Comments />} />
            <Route path="/writePost" element={<Write />} />
            <Route path="/shop" element={<Shop />} />
            <Route path="/menu" element={<MegaMenu />} />
            <Route path="/megabody" element={<Megabody />} />
            <Route path="/footer" element={<MegaFooter />} />
            <Route path="/megapay" element={<MegaPay />} />
            <Route path="/MegaIceTea" element={<MegaIceTea />} />
            <Route path="/MegaAmericano" element={<MegaAmericano />} />
            <Route path="/MegaChoco" element={<MegaChoco />} />
            <Route path="/MegaBasket" element={<MegaBasket />} />
            <Route path="/boardHeader" element={<BoardHeader />} />
            <Route path="/boardTable" element={<BoardTable />} />
            <Route path="/소통해요/posts" element={<Community />} />
            <Route path="/policy" element={<Policy />} />
            <Route path="/질문해요/posts" element={<Question />} />
            <Route path="/postHeader" element={<PostHeader />} />
            <Route path="/postMain" element={<PostMain />} />
            <Route path="/postFooter" element={<PostFooter />} />
            <Route path="/post/:category/:postId" element={<PostPage />} />
            <Route path="/postLike" element={<PostLike />} />
            <Route path="/kioskproblem" element={<Kioskproblem />} />
            <Route path="/elementary404" element={<ErrorPage />} />
            <Route path="/chat/:roomId" element={<Chatpage />} />
            <Route path="/korail" element={<Korail />} />
            <Route path="/korailHeader" element={<KorailHeader />} />
            <Route path="/korailFirstBody" element={<KorailFirstBody />} />
            <Route path="/korailSecondBody" element={<KorailSecondBody />} />
            <Route path="/korailLastBody" element={<KorailLastBody />} />
            <Route path="/korailFooter" element={<KorailFooter />} />
            <Route path="/korailMenu" element={<KorailMenu />} />
            <Route path="/korailSeat" element={<KorailSeat />} />
            <Route path="/korailproblem" element={<Korailproblem />} />
            <Route path="/trainSchedule" element={<TrainSchedule />} />
            <Route path="/korailSeatNum" element={<KorailSeatNum />} />
            <Route path="/korailPay" element={<KorailPay />} />
            <Route path="/korailPayHeader" element={<KorailPayHeader />} />
            <Route path="/korailPayMenu" element={<KorailPayMenu />} />
            <Route path="/korailPayBody" element={<KorailPayBody />} />
            <Route path="/korailSeatNumBody" element={<KorailSeatNumBody />} />
          </Routes>
        </div>
        <Footer />
      </div>
    </ScoreProvider>
  );
}

export default App;
