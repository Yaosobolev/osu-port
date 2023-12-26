import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Header from "./layout/Header";
import Ship from "./pages/ship/Ship";
import Crane from "./pages/crane/Crane";
import AddBtn from "./pages/ship/AddBtn";
import EditBtn from "./pages/ship/EditBtn";
import Request from "./pages/request/Request";
import AddRequestForm from "./pages/request/AddRequestForm";
import Schedule from "./pages/schedule/Schedule";
import Port from "./pages/port/Port";
import Modeling from "./pages/modeling/Modeling";

// import AddBtn from "./pages/ship/AddShip";

const App: React.FC = () => {
  return (
    <Router>
      <div>
        <Header />
        <Routes>
          <Route path="/" element={<Ship />} />
          <Route path="/crane" element={<Crane />} />
          <Route path="/request" element={<Request />} />
          <Route path="/schedule" element={<Schedule />} />
          <Route path="/port" element={<Port />} />
          <Route path="/modeling" element={<Modeling />} />
          <Route path="/addRequest" element={<AddRequestForm />} />
          <Route path="/addShip" element={<AddBtn />} />
          <Route path="/editShip/:id" element={<EditBtn />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
