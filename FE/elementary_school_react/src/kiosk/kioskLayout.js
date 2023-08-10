import React from "react";
import { Link } from "react-router-dom";
import "../css/kioskLayout.css";

function KioskLayout({ children }) {
  return <div className="kiosk-layout">{children}</div>;
}
export default KioskLayout;
