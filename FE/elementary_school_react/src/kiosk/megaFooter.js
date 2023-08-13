import React, { useState } from "react";
import BasketModal from "./modalbasket";
import MegaPay from "./kioskpay";
import "../css/megaFooter.css";
import { Link } from "react-router-dom";

function MegaFooter() {
  const [payModalIsOpen, setPayModalIsOpen] = useState(false);
  const [bascketModalIsOpen, setBascketModalIsOpen] = useState(false);
  const [selectedMenu, setSelectedMenu] = useState("");
  const [selectedProductsCount, setSelectedProductsCount] = useState(0);
  const [totalAmount, setTotalAmount] = useState(0);

  const handleProductSelection = (menu, price) => {
    setSelectedMenu(menu);
    setSelectedProductsCount(selectedProductsCount + 1);
    setTotalAmount(totalAmount + price);
  };

  return (
    <div className="E-footer">
      <div className="E-left-box">
        <div>뭘봐</div>
        {selectedMenu ? `선택한 메뉴: ${selectedMenu}` : ""}
      </div>
      <div className="E-right-box">
        <div className="E-delete-button">전체삭제</div>
        <div className="E-cart-info">선택한 상품 {selectedProductsCount}개</div>
        <Link to="#" className="E-payment-box-btn">
          <div
            className="E-payment-box"
            onClick={() => {
              setBascketModalIsOpen(true);
            }}
          >
            <div className="E-amount">{totalAmount}원</div>
            <div className="E-payment-button">결제하기</div>
          </div>
        </Link>
      </div>
      <BasketModal
        bascketModalIsOpen={bascketModalIsOpen}
        setBascketModalIsOpen={setBascketModalIsOpen}
        payModalIsOpen={payModalIsOpen}
        setPayModalIsOpen={setPayModalIsOpen}
      />
      <MegaPay
        payModalIsOpen={payModalIsOpen}
        setPayModalIsOpen={setPayModalIsOpen}
      />
    </div>
  );
}

export default MegaFooter;
