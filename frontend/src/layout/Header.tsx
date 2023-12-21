import React from "react";
import { Link } from "react-router-dom";

const Header: React.FC = () => {
  return (
    <header className="bg-blue-500 p-4 text-white">
      <div className="container mx-auto flex justify-between items-center">
        <h1 className="text-2xl font-bold">
          Моделирование работы морского порта{" "}
        </h1>
        <div className="flex space-x-4">
          {/* Button 1 */}
          <Link
            className="bg-white text-blue-500 px-4 py-2 rounded duration-200 ease-in hover:-translate-y-1 hover:bg-slate-100"
            to={"/"}
          >
            Судно
          </Link>
          {/* Button 2 */}
          <Link
            className="bg-white text-blue-500 px-4 py-2 rounded duration-200 ease-in hover:-translate-y-1 hover:bg-slate-100"
            to={"/crane"}
          >
            Кран
          </Link>
          {/* Button 3 */}
          <Link
            className="bg-white text-blue-500 px-4 py-2 rounded duration-200 ease-in hover:-translate-y-1 hover:bg-slate-100"
            to={"/request"}
          >
            Заявка
          </Link>
          {/* Button 4 */}
          <Link
            className="bg-white text-blue-500 px-4 py-2 rounded duration-200 ease-in hover:-translate-y-1 hover:bg-slate-100"
            to={"/schedule"}
          >
            Расписание
          </Link>
          <Link
            className="bg-white text-blue-500 px-4 py-2 rounded duration-200 ease-in hover:-translate-y-1 hover:bg-slate-100"
            to={"/modeling"}
          >
            Моделирование
          </Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
