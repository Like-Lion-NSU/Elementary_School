import React from "react";
import "../css/korailMenu.css";

const categories = ["편도", "왕복"];

function KorailMenu({
  selectedCategory,
  setSelectedCategory,
  setScore,
  lastScore,
}) {
  const handleCategoryClick = (category) => {
    if (category === "편도") {
      setSelectedCategory(category);
    } else {
      alert("잘못 고르셨습니다. 감점 처리됩니다. 다른 버튼을 선택해주세요");
      if (lastScore > 0) {
        setScore(lastScore - 10);
        console.log(lastScore);
      } else {
        return;
      }
    }
  };

  return (
    <div className='korail-menu'>
      {categories.map((category) => (
        <div
          key={category}
          className={`korail-menu-item ${
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

export default KorailMenu;
