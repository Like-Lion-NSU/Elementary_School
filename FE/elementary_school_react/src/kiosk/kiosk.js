import React from 'react';
import MegaHeader from '../kiosk/megaHeader';
import KioskLayout from './kioskLayout';
import MegaMenu from './megaMenu';
import Sidebar from "../sidebar/sidebar";


const Kiosk = () => {
    return (
        <KioskLayout>
          <Sidebar />
      <div className="left-panel"></div>
      <div className="center-panel">
        <MegaHeader />
        <MegaMenu />
      </div>
      <div className="right-panel"></div>
    </KioskLayout>
    );
  };
  
  export default Kiosk;