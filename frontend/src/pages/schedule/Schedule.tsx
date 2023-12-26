import React from "react";
import { useEffect, useState } from "react";
import axios from "axios";

("use client");

import {
  Table,
  TableBody,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import Items from "./Items";

const Schedule: React.FC = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    loadRequests();
  }, []);

  const loadRequests = async () => {
    const result = await axios.get("http://localhost:8080/schedules");
    console.log(result.data);
    setRequests(result.data);
  };

  const sortRequest = [...requests].sort((a, b) => a.crane.id - b.crane.id);

  const [active, setActive] = useState<boolean>(true);

  const handleActive = () => {
    setActive(!active); // Инвертируем значение состояния
  };

  const selectedSort = active ? requests : sortRequest;
  const selectedSortText = active ? "Сорт по кранам" : "Сорт по id";

  return (
    <div className="container">
      <form onSubmit={() => loadRequests()}>
        <h1 className="text-5xl font-bold text-center mb-12 mt-12">
          Расписание{" "}
        </h1>
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-2">
          Обновить
        </button>
        <a
          onClick={handleActive}
          className="bg-white hover:bg-gray-100 text-blue-500  font-bold py-2 px-4 rounded border-blue-500 border-2 cursor-pointer"
        >
          {selectedSortText}
        </a>
        <Table className="mt-6">
          <TableHeader>
            <TableRow>
              <TableHead className="w-[100px]">ID</TableHead>
              {/* <TableHead>Время прибытия</TableHead> */}
              <TableHead>Фактическое Время прибытия</TableHead>
              {/* <TableHead>Планируемое время стоянки</TableHead> */}
              <TableHead>Время отбывания</TableHead>
              <TableHead className="text-right">Название судна</TableHead>
              <TableHead className="text-right">Название крана</TableHead>
              <TableHead className="text-right">Тип груза</TableHead>
              <TableHead className="text-right">Название груза</TableHead>
              <TableHead className="text-right">Объем груза</TableHead>
              <TableHead className="text-right">Статус</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {selectedSort.map((request, index) => {
              return <Items request={request} key={index} />;
            })}
          </TableBody>
        </Table>
      </form>
    </div>
  );
};

export default Schedule;
