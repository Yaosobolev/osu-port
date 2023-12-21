import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Header from "./layout/Header";
import Ship from "./pages/ship/Ship";
import Crane from "./pages/crane/Crane";
import AddBtn from "./pages/ship/AddBtn";
import EditBtn from "./pages/ship/EditBtn";

// import AddBtn from "./pages/ship/AddShip";

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<Ship />} />
          <Route path="/crane" element={<Crane />} />
          <Route path="/addShip" element={<AddBtn />} />
          <Route path="/editShip/:id" element={<EditBtn />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
