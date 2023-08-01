import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom';

import Role from './role/role';
import LoJLogin from './login/login';

ReactDOM.render(
  <React.StrictMode>
    <Router>
      <Role />
      <LoJLogin />
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

