import React from "react";
import { Link } from "react-router-dom";
import MegaHeader from "../kiosk/megaHeader";
import KioskLayout from "./kioskLayout";
import Megabody from "./megabody";

const Kiosk = () => {
  return (
    <KioskLayout>
      <div className="left-panel"></div>
      <div className="center-panel">
        <MegaHeader />
        <Megabody />
      </div>
      <div className="right-panel"></div>
    </KioskLayout>
  );
};

export default Kiosk;
