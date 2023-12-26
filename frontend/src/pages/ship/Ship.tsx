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
import IItems from "./IItems";

const Ship: React.FC = () => {
  const [ships, setShips] = useState([]);

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    const result = await axios.get("http://localhost:8080/ships");
    // console.log(result.data[0].id_product_types.id);
    console.log(result.data);
    setShips(result.data);
  };

  const deleteShip = async (id) => {
    await axios.delete(`http://localhost:8080/ship/${id}`);
    loadProducts();
  };
  return (
    <div className="container">
      {/* <div className="container mx-auto flex flex-col justify-center items-center text-center h-full"> */}
      <h1 className="text-5xl font-bold text-center mb-12 mt-12">Судно </h1>
      <Link
        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        to={"/addShip"}
      >
        Добавить судно
      </Link>
      <Table className="mt-6">
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Название</TableHead>
            <TableHead>Грузоподъемность</TableHead>
            <TableHead className="text-right">Тип судна</TableHead>
            <TableHead className="text-right">Груз</TableHead>
            <TableHead className="text-right">Тип груза</TableHead>
            <TableHead className="text-right">Объем груза</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {/* <TableRow> */}
          {ships.map((ship, index) => {
            return <IItems ship={ship} key={index} deleteShip={deleteShip} />;
          })}
        </TableBody>
      </Table>
      {/* </div> */}
    </div>
  );
};

export default Ship;
