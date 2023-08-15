import Lo_J_KakaoLogin from './img/kakao_login.png'
import Lo_J_GoogleLogin from './img/google_login.png'
import style from '../css/login.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import Lo_J_Logo from './img/logo.png'


function LoJLogin() {
  const [showInfo, setShowInfo] = useState(false);

  const onMouseEnter = () => {
    setShowInfo(true);
  };

  const onMouseLeave = () => {
    setShowInfo(false);
  };

  return (
    <div className={style.loJCenter}>
      <img src={Lo_J_Logo} className={style.loJLogo} alt='로고'/>
        {/*좌표 메인으로 잡아야됨*/} 
      <div className={style.loJLoginTitle}>
        <h1 className={style.loJLogin}>로그인</h1>
        <h2 style={{ color: '#4682A9' }} className={style.loJIcon}><FontAwesomeIcon
          icon={faInfoCircle}
          onMouseEnter={onMouseEnter}
          onMouseLeave={onMouseLeave} /></h2>
          {showInfo && <div className={style.loJInfo}><b>로그인 안내</b> <hr /><b>국민학교</b>는 서비스 간편화를 위해 <br />카톡/구글의 간편로그인만 제공합니다</div>}
      </div>
      
      <div className={style.loJFlex}>
        <a className={style.loJLogin_Btn} href="#">
          <img src={Lo_J_KakaoLogin} className={style.loJImgWidth} alt="카카오 로그인 버튼" />
        </a>
        <a className={style.loJLogin_Btn} href="#">
          <img src={Lo_J_GoogleLogin} className={style.loJImgWidth} alt="구글 로그인 버튼" />
        </a>
      </div>
    </div>
  )
}
export default LoJLogin