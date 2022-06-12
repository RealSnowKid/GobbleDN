import Nav from "./components/Nav";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import HomeView from './views/Home';

function App() {
  const bhistory = createBrowserHistory();
  return (
    <BrowserRouter>
      <Nav />
      <div className="container mx-auto">
        <Routes>
          <Route path="/" element={<HomeView />} />
          <Route path="/explore" element={<HomeView />} />
          <Route path="/notifications" element={<HomeView />} />
          <Route path="/messages" element={<HomeView />} />
          <Route path="/profile" element={<HomeView />} />
          <Route path="/settings" element={<HomeView />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
