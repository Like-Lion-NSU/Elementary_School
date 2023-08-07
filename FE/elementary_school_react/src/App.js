import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./login/login";
import Role from "./role/role";
import Kiosk from "./kiosk/kiosk";
import Main from "./main/main";
import Question from "./question/question";
import Mypage from "./mypage/mypage";
import Community from "./community/community";
import PracticeType from "./kiosk/practiceType";
import MainPage from './main/main';
import Sidebar from './sidebar/sidebar';
import Shop from './shop/shop';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/role" element={<Role />} />
        <Route path="/kiosk" element={<Kiosk />} />
        <Route path="/main" element={<Main />} />
        <Route path="/question" element={<Question />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/community" element={<Community />} />
        <Route path="/practiceType" element={<PracticeType />} />
        <Route path="/main" element={<MainPage />} />
        <Route path="/sidebar" element={<Sidebar />} />
        <Route path="/shop" element={<Shop />} />
      </Routes>
    </>
  );
}

export default App;
