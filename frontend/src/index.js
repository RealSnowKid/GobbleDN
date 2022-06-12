import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider } from "@auth0/auth0-react";
import { Flowbite } from 'flowbite-react';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Auth0Provider
      domain="gobbledn.eu.auth0.com"
      clientId="GzPWOBs3vL5zbhst4ja1IwipHi2AB3VZ"
      redirectUri={window.location.origin}
      audience="gobbledn-api.com"
    >
      <Flowbite>
        <App />
      </Flowbite>
    </Auth0Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
