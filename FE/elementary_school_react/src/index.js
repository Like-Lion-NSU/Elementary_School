import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';

import Role from './role/role';

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Role />
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);