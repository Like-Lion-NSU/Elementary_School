import React, { useState } from "react";
import "../css/megaMenu.css";

const categories = [
  "커피(Ice)",
  "커피(Hot)",
  "스무디&프라페",
  "에이드&주스",
  "티(Tea)",
  "음료",
  "디저트",
  "에스프레소",
];

function MegaMenu({ selectedCategory, setSelectedCategory }) {
  const handleCategoryClick = (category) => {
    setSelectedCategory(category);
  };

  return (
    <div className="E-menu">
      {categories.map((category) => (
        <div
          key={category}
          className={`menu-item ${
            selectedCategory === category ? "selected" : ""
          }`}
          onClick={() => handleCategoryClick(category)}
        >
          {category}
        </div>
      ))}
    </div>
  );
}

export default MegaMenu;
