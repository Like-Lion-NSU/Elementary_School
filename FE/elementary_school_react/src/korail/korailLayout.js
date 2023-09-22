import React from "react";
import { Link } from "react-router-dom";
import "../css/korailLayout.css";

function KorailLayout({ children }) {
  return <div className='korail-layout'>{children}</div>;
}
export default KorailLayout;
