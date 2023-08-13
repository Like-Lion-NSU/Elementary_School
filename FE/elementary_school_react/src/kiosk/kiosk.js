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
  const [lastScore, setScore] = useState(100);
  const [selectedCategory, setSelectedCategory] = useState("커피(Ice)");
  const [selectedIceMenu, setSelectedIceMenu] = useState(false);
  const [selectedBokMenu, setSelectedBokMenu] = useState(false);
  const [selectedChoMenu, setSelectedChoMenu] = useState(false);
  const [Icecount, setIce] = useState(0);
  const [Chocount, setCho] = useState(0);
  const [Bokcount, setBok] = useState(0);

  return (
    <KioskLayout>
      <Sidebar />
      <div className="left-panel"></div>
      <div className="center-panel">
        <MegaHeader />
        <MegaMenu
          setSelectedCategory={setSelectedCategory}
          selectedCategory={selectedCategory}
          setScore={setScore}
          lastScore={lastScore}
        />
        {selectedCategory === "커피(Ice)" && (
          <Megabody_ice
            setSelectedIceMenu={setSelectedIceMenu}
            setIce={setIce}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        {selectedCategory === "스무디&프라페" && (
          <Megabody_choco
            setSelectedChoMenu={setSelectedChoMenu}
            setCho={setCho}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        {selectedCategory === "티(Tea)" && (
          <Megabody_tea
            setSelectedBokMenu={setSelectedBokMenu}
            setBok={setBok}
            setScore={setScore}
            lastScore={lastScore}
          />
        )}
        <MegaFooter
          selectedIceMenu={selectedIceMenu}
          selectedBokMenu={selectedBokMenu}
          selectedChoMenu={selectedChoMenu}
          setSelectedIceMenu={setSelectedIceMenu}
          setSelectedBokMenu={setSelectedBokMenu}
          setSelectedChoMenu={setSelectedChoMenu}
          Icecount={Icecount}
          setIce={setIce}
          Chocount={Chocount}
          setCho={setCho}
          Bokcount={Bokcount}
          setBok={setBok}
          lastScore={lastScore}
          setScore={setScore}
        />
      </div>
      <div className="right-panel"></div>
    </KioskLayout>
  );
};

export default Kiosk;
