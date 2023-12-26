import React from "react";
import { Link } from "react-router-dom";
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

const Port: React.FC = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    loadRequests();
  }, []);

  const loadRequests = async () => {
    const result = await axios.get("http://localhost:8080/result");
    console.log(result.data);
    setRequests(result.data);
  };

  const workPort = async () => {
    try {
      await axios.post("http://localhost:8080/work");
      //   navigate("/request");
      // Handle success or additional logic here if needed
    } catch (error) {
      // Handle error
      console.error("Error adding product:", error);
    }
  };

  const sortRequest = [...requests].sort((a, b) => a.crane.id - b.crane.id);

  const [active, setActive] = useState<boolean>(true);

  const handleActive = () => {
    setActive(!active); // Инвертируем значение состояния
  };

  const selectedSort = active ? sortRequest : requests;
  const selectedSortText = active ? "Сорт по id" : "Сорт по кранам";

  //   const [counter, setCounter] = useState(0);
  //   const [isRefreshing, setIsRefreshing] = useState(false);

  //   const handleRefresh = () => {
  //     window.location.reload();
  //     console.log("hello");
  //   };

  //   useEffect(() => {
  //     const intervalId = setInterval(() => {
  //       setCounter((prevCounter) => prevCounter + 1);
  //     }, 1000);

  //     return () => {
  //       clearInterval(intervalId);
  //     };
  //   }, []);

  //   useEffect(() => {
  //     if (counter >= 2) {
  //       window.location.reload();
  //     }
  //   }, [counter]);

  const [counter, setCounter] = useState(0);
  const [isRefreshing, setIsRefreshing] = useState(true);

  const handleToggleRefresh = () => {
    setIsRefreshing((prevIsRefreshing) => !prevIsRefreshing);
  };

  useEffect(() => {
    let intervalId: NodeJS.Timeout;

    if (isRefreshing) {
      intervalId = setInterval(() => {
        setCounter((prevCounter) => prevCounter + 1);
      }, 1000);
    }

    return () => {
      clearInterval(intervalId);
    };
  }, [isRefreshing]);

  useEffect(() => {
    if (counter >= 2) {
      setIsRefreshing(false);
      window.location.reload();
    }
  }, [counter]);
  return (
    <div className="container">
      <button
        onClick={handleToggleRefresh}
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-5 px-7 rounded mx-2 cursor-pointer my-10"
      >
        {isRefreshing ? "Остановить" : "Анимировать"}
      </button>
      <form onSubmit={() => loadRequests()}>
        <h1 className="text-5xl font-bold text-center mb-12 mt-12">Порт </h1>
        <button
          onClick={() => workPort()}
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-2"
        >
          Начать
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
              <TableHead>Время прибытия</TableHead>
              <TableHead>Фактическое Время прибытия</TableHead>
              <TableHead>Времая ожидания на разгрузку</TableHead>
              <TableHead>Планируемое время стоянки</TableHead>
              <TableHead>Время отбывания</TableHead>
              <TableHead className="text-left">Название судна</TableHead>
              <TableHead className="text-right">Название крана</TableHead>
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
      {/* </div> */}
    </div>
  );
};

export default Port;
