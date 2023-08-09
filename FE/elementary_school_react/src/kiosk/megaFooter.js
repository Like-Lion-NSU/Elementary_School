import React, { useState } from 'react';
import '../css/megaFooter.css';
import { Link } from 'react-router-dom';

    function MegaFooter() {
    const [selectedMenu, setSelectedMenu] = useState('');
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
        {selectedMenu ? `선택한 메뉴: ${selectedMenu}` : ''}
        </div>
        <div className="E-right-box">
        <div className="E-delete-button">
            전체삭제
        </div>
        <div className="E-cart-info">
            선택한 상품 {selectedProductsCount}개
        </div>
        <Link to="#" className='E-payment-box-btn'>
        <div className="E-payment-box">
            <div className="E-amount">{totalAmount}원</div>
            <div className="E-payment-button">결제하기</div>
        </div>
        </Link>
        </div>
    </div>
    
    );
    }

export default MegaFooter;