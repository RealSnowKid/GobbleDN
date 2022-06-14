import Nav from "./components/Nav";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomeView from './views/Home';
import Other from "./views/Other";

function App() {
  return (
    <BrowserRouter>
      <Nav />
      <div className="container mx-auto">
        <Routes>
          <Route path="/" element={<HomeView />} />
          <Route path="/explore" element={<Other />} />
          <Route path="/notifications" element={<Other />} />
          <Route path="/messages" element={<Other />} />
          <Route path="/profile" element={<Other />} />
          <Route path="/settings" element={<Other />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
