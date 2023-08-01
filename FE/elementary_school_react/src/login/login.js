import Lo_J_KakaoLogin from './img/kakao_login.png'
import Lo_J_GoogleLogin from './img/google_login.png'
import style from './login.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInfoCircle } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';


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
      <div className={style.loJLoginTitle}>
        <h2>로그인</h2>
        <h3 style={{ color: '#4682A9' }}><FontAwesomeIcon
          icon={faInfoCircle}
          onMouseEnter={onMouseEnter}
          onMouseLeave={onMouseLeave} /></h3>
      </div>
      {showInfo && <div className={style.loJInfo}>로그인 안내 <hr /><b>국민학교</b>는 서비스 간편화를 위해 <br />카톡/구글의 간편로그인만 제공합니다</div>}
      <div className={style.loJFlex}>
        <a className={style.loJLogin_Btn} href="#">
          <img src={Lo_J_KakaoLogin} width="300" height="45" alt="카카오 로그인 버튼" />
        </a>
        <a className={style.loJLogin_Btn} href="#">
          <img src={Lo_J_GoogleLogin} width="300" height="45" alt="구글 로그인 버튼" />
        </a>
      </div>
    </div>
  )
}
export default LoJLogin