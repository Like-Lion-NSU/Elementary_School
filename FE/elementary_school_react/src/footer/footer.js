import React from "react";
import likelion from "./whitelogo.png"
import "../css/footer.css"

const Footer=()=>{
    return(
        <div className="Footer">
            <div className="JTable"><img src={likelion} className="Jlogo"/>&nbsp;&nbsp;<b>소속</b> 남서울대학교&nbsp;&nbsp;<b>팀명</b> 이게우리야&nbsp;&nbsp;<b>Back-End</b> 권영태 서송현&nbsp;&nbsp;<b>Front-End</b> 장창현 황은혜 정재은</div>
      </div>
    )
}
export default Footer;
