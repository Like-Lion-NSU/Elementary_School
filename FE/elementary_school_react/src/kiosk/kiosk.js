import React, { useState } from "react";
import MegaHeader from "../kiosk/megaHeader";
import KioskLayout from "./kioskLayout";
import MegaMenu from "./megaMenu";
import Sidebar from "../sidebar/sidebar";
import Megabody_ice from "./megabody_ice";
import Megabody_choco from "./megabody_choco";
import Megabody_tea from "./megabody_tea";
import MegaFooter from "./megaFooter";

const Kiosk = () => {
  const [selectedCategory, setSelectedCategory] = useState("커피(Ice)");
  const [selectedMenu, setSelectedMenu] = useState("");
  return (
    <KioskLayout>
      <Sidebar/>
      <div className="left-panel"></div>
      <div className="center-panel">
        <MegaHeader />
        <MegaMenu
          setSelectedCategory={setSelectedCategory}
          selectedCategory={selectedCategory}
        />
        {selectedCategory === "커피(Ice)" && <Megabody_ice />}
        {selectedCategory === "스무디&프라페" && <Megabody_choco />}
        {selectedCategory === "티(Tea)" && <Megabody_tea />}
        <MegaFooter />
      </div>
      <div className="right-panel"></div>
    </KioskLayout>
  );
};

export default Kiosk;
