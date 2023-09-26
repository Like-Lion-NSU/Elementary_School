import React from "react";
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

function MegaMenu({
  selectedCategory,
  setSelectedCategory,
  setScore,
  lastScore,
}) {
  const handleCategoryClick = (category) => {
    if (
      category === "커피(Ice)" ||
      category === "스무디&프라페" ||
      category === "티(Tea)"
    ) {
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
