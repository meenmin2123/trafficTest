import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import Login from './login/login';
import Main from './page/main';
import Register from './login/register';
import ScrollDown from './components/ScrollDown/ScrollDown';

function App() {
  return (
    <Router>
      <AppContent /> {/* Router 내부에서 AppContent 컴포넌트 호출 */}
    </Router>
  );
}

function AppContent() {
  const location = useLocation(); // Router 내부에서 호출

  return (
    <div>
      {/* '/' 경로에서만 ScrollDown 표시 */}
      {location.pathname === '/' && <ScrollDown />}

      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
      </Routes>
    </div>
  );
}

export default App;