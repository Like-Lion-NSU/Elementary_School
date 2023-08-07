import React, { useState } from 'react';
import style from '../css/shop.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleLeft, faAngleRight } from '@fortawesome/free-solid-svg-icons';
import shJSweet from './img/새콤달콤.png';
import shJCandy from './img/츄파츕스.png';
import shJIceCream from './img/싱글레귤러.png';
import shJBanana from './img/뚱바.png';
import shJChoco from './img/초코에몽.png';
import shJHaribo from './img/하리보.png';
import shJJelly from './img/마미구미.png';
import shJMegaCoffee from './img/메가아메리카노.png';
import shJMegaChoco from './img/리얼초코프라페.png';
import shJStarCoffee from './img/아메리카노.png';
import shJStarChoco from './img/아이스초코.png';
import shJCU from './img/CU.png';
import shJGS25 from './img/GS25.png';
import shJOlive from './img/올영.png';
import shJCake from './img/아이스박스.png';
import shJBing from './img/인절미 설빙.png';
import shJChicken from './img/황올.png';

function ShJShop() {
  const shJproducts = [
    {
        image: shJCandy,
        name: 'GS25 ) 츄파춥스',
        price: '300'
       },{
        image: shJSweet,
        name: 'GS25 ) 새콤달콤포도29g',
        price: '500',
      },{
        image: shJChoco,
        name: 'CU ) 초콜릿드링크초코에몽250ML',
        price: '1,600'
      },{
        image: shJJelly,
        name: 'CU ) 마이구미포도66g',
        price: '1,000'
      },{
        image: shJBanana,
        name: 'CU ) 빙그레 바나나우유 240ML',
        price: '1,700'
      },{
        image: shJHaribo,
        name: 'CU ) 하리보 골드바렌100g',
        price: '2,000'
      },{
        image: shJStarCoffee,
        name: '스타벅스 ) 아이스 카페 아메리카노 T',
        price: '4,500'
      },{
        image: shJStarChoco,
        name: '스타벅스 ) 아이스 시그니처 초콜릿 T',
        price: '5,700'
      },{
        image: shJMegaCoffee,
        name: '메가커피 ) (ICE) 아메리카노',
        price: '2,000'
      },{
        image: shJMegaChoco,
        name: '메가커피 ) 리얼초코프라페',
        price: '3,900'
      },{
        image: shJCake,
        name: '투썸플레이스 ) 떠먹는 아이스박스',
        price: '6,500'
      },{
        image: shJIceCream,
        name: '배스킨라빈스 ) 싱글레귤러 아이스크림',
        price: '3,900'
      },{
        image: shJCU,
        name: 'CU ) 모바일금액권 3,000원',
        price: '3,000'
      },{
        image: shJGS25,
        name: 'GS25 ) 모바일금액권 3,000원',
        price: '5,000'
      },{
        image: shJChicken,
        name: 'BBQ ) 황금올리브치킨+콜라1.25L',
        price: '22,500'
      },{
        image: shJOlive,
        name: '올리브영 ) 기프트카드 1만원권',
        price: '10,000'
      },{
        image: shJBing,
        name: '설빙 ) 인절미 설빙',
        price: '9,500'
      }
  ];
  const [shJcurrentPage, setshJcurrentPage] = useState(1);
  const shJproductsPerPage = 8; // 페이지당 상품 수

  const shJtotalProducts = shJproducts.length; //전체 상품 개수
  const shJtotalPages = Math.ceil(shJtotalProducts / shJproductsPerPage); // 전체 상품수/페이지 상품수 올림

  const shJindexOfLastProduct = shJcurrentPage * shJproductsPerPage; //현재 페이지 * 페이지당 상품수
  const shJindexOfFirstProduct = shJindexOfLastProduct - shJproductsPerPage;
  const shJcurrentProducts = shJproducts.slice(shJindexOfFirstProduct, shJindexOfLastProduct);
  //입력한 상품들을 시작 인덱스 부터 종료 인덱스까지 복사하여 반환
  //현재 페이지가 1이면 0~7번째 / 2이면 8~15 / 3이면 16~23 일 수 있게 도와줌

  const shJhandlePageChange = (page) => {
    if (page >= 1 && page <= shJtotalPages) {
      setshJcurrentPage(page);
    }
  };

  return (
    <div>
      <div className={style.shJproductWrapper}>
      <h1 className={style.shJTitle}>매점</h1>
        <div className={style.shJcolumns}>
          {shJcurrentProducts.map((product, index) => (
            <div className={style.shJcard} key={index}>
              <img src={product.image} alt="상품" />
              <div className={style.shJname}>
                <div className={style.shJtitle}>{product.name}</div>
                <div className={style.shJpoint}>{product.price} Point</div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div id={style.shJproductFoot}>
        <FontAwesomeIcon
          icon={faAngleLeft}
          onClick={() => shJhandlePageChange(shJcurrentPage - 1)} // <을 클릭하면 현재 페이지 -1
          disabled={shJcurrentPage === 1} //현재 페이지가 1 일때는 동작을 막음
          className={style.shJicon}
        />
        
        {[...Array(shJtotalPages)].map((_, index) => ( //크기가 shJtotalPages인 배열 만들기, _ : 변수 존재X
          <div
            key={index}
            className={`${style.shJbox} ${shJcurrentPage === index + 1 ? style.shJactive : ''}`}
            onClick={() => shJhandlePageChange(index + 1)} 
            //클릭이 발생한 index+1 값 (클릭이 발생한 페이지 숫자)을 넘김 -> 지정한 범위 내라면 그것을 현재 페이지로
          >
            {index + 1 /*배열이 0부터인걸 감안*/}
          </div>
        ))}


        <FontAwesomeIcon
          icon={faAngleRight}
          onClick={() => shJhandlePageChange(shJcurrentPage + 1)} // > 을 클릭하면 현재 페이지 +1
          disabled={shJcurrentPage === shJtotalProducts} //현제 페이지가 마지막 페이지라면 동작 막음
          className={style.shJicon}
        />
      </div>
    </div>
  );
};

export default ShJShop;
