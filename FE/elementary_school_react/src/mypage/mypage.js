import style from '../css/mypage.module.css'
import My_J_userImg from './img/userImg.png'
function MyJMypage(){

return(
    <div>
    <div className={style.myJCenter}>
    <h1 style={{marginBottom:'3vh'}}>마이페이지</h1>
        <div className={style.myJFlex} style={{marginBottom:'3vh'}}>
            <img src={My_J_userImg} className={style.myJUser} alt='유저'/>
            <div className={`${style.myJFlexColumn} ${style.myJ15Font}`}>
                <div className={style.myJFlex}>
                    <div>아이디 : </div>&nbsp;<div>milkDiet@gmail.com</div>
                </div>
                <div className={style.myJFlex}>
                    <div>역할 : </div>&nbsp;<div>선생님</div>
                </div>
                <div className={style.myJFlex}>
                    <div>포인트 : </div>&nbsp;<div>1004 Point</div>
                </div>
            </div>
        </div>
        <div className={`${style.myJFlex} ${style.myJ15Font}`}>
            <div className={style.myJButton}>내가 쓴 게시글</div>
            <div className={style.myJButton}>내가 쓴 댓글</div>
        </div>
        </div>
        <div className={style.myJBottom}>회원 탈퇴</div>
        </div>
)
}
export default MyJMypage;
